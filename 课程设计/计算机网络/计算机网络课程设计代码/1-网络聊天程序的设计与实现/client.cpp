// 聊天程序客户端
#include<iostream>
#include<Winsock2.h>//Windows Sockets 2 头文件，包含了 Socket 编程所需的函数和结构体
#include<cstring>
using namespace std;

//载入系统提供的socket动态连接库
#pragma comment(lib,"ws2_32.lib")   //socket库

const int BUFFER_SIZE = 1024;//缓冲区大小

DWORD WINAPI recvMsgThread(LPVOID IpParameter);

int main() {
	//1、初始化socket库
	
	//一个结构体，用于存储 Winsock 库的版本信息。
	WSADATA wsaData;
	//MAKEWORD(主版本号, 副版本号)；初始化 Winsock库，参数 MAKEWORD(2, 2) 表示使用 Winsock 2.2版本
	WSAStartup(MAKEWORD(2, 2), &wsaData);

	//2、创建socket
	
	//AF_INET表示使用 IPv4 协议；SOCK_STREAM表示使用面向连接的 TCP 协议；0表示根据协议自动选择 
	SOCKET cliSock = socket(AF_INET, SOCK_STREAM, 0);

	//3、打包地址

	//服务端
	SOCKADDR_IN servAddr = { 0 };//一个结构体，表示地址信息。
	servAddr.sin_family = AF_INET;//和服务器的socket一样，sin_family表示协议簇，一般用AF_INET表示TCP/IP协议。
	servAddr.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//服务端地址设置为本地回环地址
	servAddr.sin_port = htons(12345);//host to net short 端口号设置为12345

	if (connect(cliSock, (SOCKADDR*)&servAddr, sizeof(SOCKADDR)) == SOCKET_ERROR)
	{
		cout << "连接出现错误，错误代码" << WSAGetLastError() << endl;
	}

	//创建接受消息线程
	//recvMsgThread线程的入口点函数，用于接收服务器发送的消息；(LPVOID)&cliSock将客户端的Socket作为线程参数传递 
	CloseHandle(CreateThread(NULL, 0, recvMsgThread, (LPVOID)&cliSock, 0, 0));
	//主线程用于输入要发送的消息
	while (1)
	{
		char buf[BUFFER_SIZE] = { 0 };
		cin.getline(buf, sizeof(buf));
		if (strcmp(buf, "quit") == 0)//若输入“quit”，则退出聊天室
		{
			break;
		}
		send(cliSock, buf, sizeof(buf), 0);
	}
	closesocket(cliSock); //关闭一个打开的套接字，并释放相关的资源。
	WSACleanup(); //目的是在应用程序不再需要 Winsock 服务时，释放为 Winsock 分配的资源
	return 0;
}

DWORD WINAPI recvMsgThread(LPVOID IpParameter)//接收消息的线程
{
	SOCKET cliSock = *(SOCKET*)IpParameter;//获取客户端的SOCKET参数

	while (1)
	{
		char buffer[BUFFER_SIZE] = { 0 };//字符缓冲区，用于接收和发送消息
		int nrecv = recv(cliSock, buffer, sizeof(buffer), 0);//nrecv是接收到的字节数
		if (nrecv > 0)//如果接收到的字符数大于0
		{
			cout << buffer << endl;
		}
		else if (nrecv < 0)//如果接收到的字符数小于0就说明断开连接
		{
			cout << "与服务器断开连接" << endl;
			break;
		}
	}
	return 0;
}
