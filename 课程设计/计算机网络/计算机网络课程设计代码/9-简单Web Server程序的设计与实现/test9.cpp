#include <cstdio>
#include <string>
#include <fstream>
#include <WinSock2.h>
#include <iostream>
using namespace std;
#define BUFFER_SIZE 1024
//#define HOST "192.168.43.253"
//�˿�Ĭ��Ϊ8000
int PORT=8000;
//��Ӧͷ
#define HEADER "\
HTTP/1.1 200 OK\r\n\
Content-Type: text/html; charset=UTF-8\r\n\
Server: http_v1.0.1\r\n\
Content-Length: %ld\r\n\r\n\
"
const string index = "login.html";
const string error = "error.html";
#pragma comment(lib, "WS2_32")
// ��ȡ�ļ��Ĵ�С
long GetFileLength(string strPath);

//�����ʵ�������Բ��ü�
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
	cout << "��������·��Ϊ��" << temp <<endl;
	
//	 ǿ�Ƹ���·������ 
//	size_t questionMarkPos = temp.find('?');
//	if (questionMarkPos != string::npos) {
//         ��ȡ�ʺ�֮ǰ�Ĳ���
//        temp = temp.substr(0, questionMarkPos);
//        size_t loginPos = temp.find("login");
//        if (loginPos != string::npos) {
//        	temp.replace(loginPos, 5, "index");
//		}
//    }
//     ǿ�Ƹ���·������ 
    
	//���·��תΪ����·������ʵ���Ӧ����������ͷ��Host�ֶ�ƴ����
	if(temp[0]=='/'){
		temp="http://127.0.0.1:"+to_string(PORT)+temp;
	}
	cout << "��ǰ����ľ���·��Ϊ��" << temp << "��" << endl;
	return temp;
}

void getRelativePath(string &temp) {
	//���ⲻ�淶���������Ҳ��������Դ·�����磺GET http://127.0.0.1/index.html HTTP/1.1\r\n ...
	//һ��Ӧ���ǣ�GET /index.html HTTP/1.1\r\nHost: 127.0.0.1\r\n ...
	int index=temp.find("//");
	index=temp.find(":",index);
	index=temp.find("/",index);
	temp=temp.substr(index+1,temp.size());
}

// ���̺߳���
int main(int argc, char **argv) {
	// ���岢�ҳ�ʼ��һ���������׽���
	sockaddr_in addrServer;
	addrServer.sin_family = AF_INET;
	addrServer.sin_addr.S_un.S_addr = INADDR_ANY;
	
	printf("������������˿ڣ�");
	scanf("%d",&PORT);
	
	addrServer.sin_port = htons(PORT);
	// ��ʼ��
	WSADATA wsaData;
	WORD socketVersion = MAKEWORD(2, 2);
	if (WSAStartup(socketVersion, &wsaData) != 0) {
		printf("��ʼ��ʧ��!");
		exit(1);
	}
	// �����׽���
	SOCKET socketServer = socket(AF_INET, SOCK_STREAM, 0);
	if (socketServer == SOCKET_ERROR) {
		printf("����ʧ��!");
		exit(1);
	}
	// �󶨷������׽���
	if (bind(socketServer, (LPSOCKADDR)&addrServer, sizeof(addrServer)) == SOCKET_ERROR) {
		printf("��ʧ��!");
		exit(1);
	}
	// ����
	if (listen(socketServer, 10) ==  SOCKET_ERROR) {
		printf("����ʧ��!");
		exit(1);
	}
	while (true) {
		printf("\nListening ... \n");
		sockaddr_in addrClient;
		int nClientAddrLen = sizeof(addrClient);
		//�������˽�������
		SOCKET socketClient = accept(socketServer, (sockaddr*)&addrClient, &nClientAddrLen);
		if (SOCKET_ERROR == socketClient) {
			printf("����ʧ��!");
			break;
		}
		char buffer[BUFFER_SIZE];
		memset(buffer, 0, BUFFER_SIZE);
		//��������
		if (recv(socketClient, buffer, BUFFER_SIZE, 0) < 0) {
			printf("��������ʧ��!");
			break;
		}
		printf("���յ����������� : \n%s", buffer);
		
		//��ȡ�����·��
		string path = getRequestUrl(buffer);
		
		getRelativePath(path);
		
		//��ҳ
		if (path.compare("") == 0) {
			path = index;
		}
		cout << "��λ��Ҫ�ҵ��ļ����·��Ϊ��" << path;
		
		if (FILE *file = fopen(path.c_str(), "r")) {
		//���ļ�����
			fclose(file);
			cout << " ���ļ�����" << endl;
		} else {
        //�ļ�������
			path = error;
			cout << " ���ļ������ڣ����ش���ҳ��" << endl;
		}

		// response
		// send header
		memset(buffer, 0, BUFFER_SIZE);

		//��ȡ�ı�
		ifstream fin(path.c_str(), ios::in | ios::binary);
		fin.seekg(0, ios_base::end);//����ȡλ������Ϊ�ļ�ĩβ
		streampos pos = fin.tellg();//���ص�ǰ�ļ�λ��
		long lSize = static_cast<long>(pos);//��ȡ�ļ�����
		fin.seekg(0, ios_base::beg);//����ȡλ������Ϊ�ļ���ͷ
		sprintf(buffer, HEADER, lSize);//���ļ���ͷ�ļ��ϲ�Ȼ��������

		if (fin.is_open()) {
			int result=-1;
			do{
				//��һ���Ƿ�����Ӧͷ��֮������Ӧ��
				if ((result=send(socketClient, buffer, strlen(buffer), 0)) < 0) {
					break;
				}
				memset(buffer, 0, BUFFER_SIZE);
				fin.read(buffer, sizeof(buffer));
			}while(fin.gcount());//��ȡ��һ��read��ȡ�˶����ֽ�
			
			if (result < 0) {
				printf("��������ʧ��!");
				fin.close();
				closesocket(socketClient);
				break;
			}
		}
		//printf("����!");
		//�����������������ݴ���
		fin.close();
		closesocket(socketClient);
	}
	closesocket(socketServer);
	WSACleanup();
	return 0;
}

