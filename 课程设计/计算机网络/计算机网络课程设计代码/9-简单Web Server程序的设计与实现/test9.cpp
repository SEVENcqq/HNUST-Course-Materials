#include <cstdio>
#include <string>
#include <fstream>
#include <WinSock2.h>
#include <iostream>
using namespace std;
#define BUFFER_SIZE 1024
//#define HOST "192.168.43.253"
//端口默认为8000
int PORT=8000;
//响应头
#define HEADER "\
HTTP/1.1 200 OK\r\n\
Content-Type: text/html; charset=UTF-8\r\n\
Server: http_v1.0.1\r\n\
Content-Length: %ld\r\n\r\n\
"
const string index = "login.html";
const string error = "error.html";
#pragma comment(lib, "WS2_32")
// 获取文件的大小
long GetFileLength(string strPath);

//这个其实方法可以不用加
string getRequestUrl(char *buffer) {
	string temp = buffer;
	int index = temp.find("\r\n");
	temp = temp.substr(0, index);
	index = temp.find(" ");
	temp = temp.substr(index, temp.size());
	index = temp.find(" ", 1);
	temp = temp.substr(1, index);
	index = temp.find(" ");
	temp = temp.substr(0, index);
	cout << "请求的相对路径为：" << temp <<endl;
	
//	 强制更改路径部分 
//	size_t questionMarkPos = temp.find('?');
//	if (questionMarkPos != string::npos) {
//         截取问号之前的部分
//        temp = temp.substr(0, questionMarkPos);
//        size_t loginPos = temp.find("login");
//        if (loginPos != string::npos) {
//        	temp.replace(loginPos, 5, "index");
//		}
//    }
//     强制更改路径部分 
    
	//相对路径转为绝对路径，其实这个应该是用请求头中Host字段拼接上
	if(temp[0]=='/'){
		temp="http://127.0.0.1:"+to_string(PORT)+temp;
	}
	cout << "当前请求的绝对路径为：" << temp << "。" << endl;
	return temp;
}

void getRelativePath(string &temp) {
	//避免不规范的请求导致找不到相对资源路径，如：GET http://127.0.0.1/index.html HTTP/1.1\r\n ...
	//一般应该是：GET /index.html HTTP/1.1\r\nHost: 127.0.0.1\r\n ...
	int index=temp.find("//");
	index=temp.find(":",index);
	index=temp.find("/",index);
	temp=temp.substr(index+1,temp.size());
}

// 单线程函数
int main(int argc, char **argv) {
	// 定义并且初始化一个服务器套接字
	sockaddr_in addrServer;
	addrServer.sin_family = AF_INET;
	addrServer.sin_addr.S_un.S_addr = INADDR_ANY;
	
	printf("请输入服务器端口：");
	scanf("%d",&PORT);
	
	addrServer.sin_port = htons(PORT);
	// 初始化
	WSADATA wsaData;
	WORD socketVersion = MAKEWORD(2, 2);
	if (WSAStartup(socketVersion, &wsaData) != 0) {
		printf("初始化失败!");
		exit(1);
	}
	// 创建套接字
	SOCKET socketServer = socket(AF_INET, SOCK_STREAM, 0);
	if (socketServer == SOCKET_ERROR) {
		printf("创建失败!");
		exit(1);
	}
	// 绑定服务器套接字
	if (bind(socketServer, (LPSOCKADDR)&addrServer, sizeof(addrServer)) == SOCKET_ERROR) {
		printf("绑定失败!");
		exit(1);
	}
	// 监听
	if (listen(socketServer, 10) ==  SOCKET_ERROR) {
		printf("监听失败!");
		exit(1);
	}
	while (true) {
		printf("\nListening ... \n");
		sockaddr_in addrClient;
		int nClientAddrLen = sizeof(addrClient);
		//服务器端建立连接
		SOCKET socketClient = accept(socketServer, (sockaddr*)&addrClient, &nClientAddrLen);
		if (SOCKET_ERROR == socketClient) {
			printf("接收失败!");
			break;
		}
		char buffer[BUFFER_SIZE];
		memset(buffer, 0, BUFFER_SIZE);
		//接收数据
		if (recv(socketClient, buffer, BUFFER_SIZE, 0) < 0) {
			printf("接收数据失败!");
			break;
		}
		printf("接收到的请求数据 : \n%s", buffer);
		
		//获取请求的路径
		string path = getRequestUrl(buffer);
		
		getRelativePath(path);
		
		//首页
		if (path.compare("") == 0) {
			path = index;
		}
		cout << "定位到要找的文件相对路径为：" << path;
		
		if (FILE *file = fopen(path.c_str(), "r")) {
		//该文件存在
			fclose(file);
			cout << " 该文件存在" << endl;
		} else {
        //文件不存在
			path = error;
			cout << " 该文件不存在，返回错误页面" << endl;
		}

		// response
		// send header
		memset(buffer, 0, BUFFER_SIZE);

		//读取文本
		ifstream fin(path.c_str(), ios::in | ios::binary);
		fin.seekg(0, ios_base::end);//将读取位置设置为文件末尾
		streampos pos = fin.tellg();//返回当前文件位置
		long lSize = static_cast<long>(pos);//获取文件长度
		fin.seekg(0, ios_base::beg);//将读取位置设置为文件开头
		sprintf(buffer, HEADER, lSize);//把文件和头文件合并然后发送数据

		if (fin.is_open()) {
			int result=-1;
			do{
				//第一次是发送响应头，之后发送响应体
				if ((result=send(socketClient, buffer, strlen(buffer), 0)) < 0) {
					break;
				}
				memset(buffer, 0, BUFFER_SIZE);
				fin.read(buffer, sizeof(buffer));
			}while(fin.gcount());//获取上一次read读取了多少字节
			
			if (result < 0) {
				printf("发送数据失败!");
				fin.close();
				closesocket(socketClient);
				break;
			}
		}
		//printf("发送!");
		//关于网络连接与数据传递
		fin.close();
		closesocket(socketClient);
	}
	closesocket(socketServer);
	WSACleanup();
	return 0;
}

