// �������ͻ���
#include<iostream>
#include<Winsock2.h>//Windows Sockets 2 ͷ�ļ��������� Socket �������ĺ����ͽṹ��
#include<cstring>
using namespace std;

//����ϵͳ�ṩ��socket��̬���ӿ�
#pragma comment(lib,"ws2_32.lib")   //socket��

const int BUFFER_SIZE = 1024;//��������С

DWORD WINAPI recvMsgThread(LPVOID IpParameter);

int main() {
	//1����ʼ��socket��
	
	//һ���ṹ�壬���ڴ洢 Winsock ��İ汾��Ϣ��
	WSADATA wsaData;
	//MAKEWORD(���汾��, ���汾��)����ʼ�� Winsock�⣬���� MAKEWORD(2, 2) ��ʾʹ�� Winsock 2.2�汾
	WSAStartup(MAKEWORD(2, 2), &wsaData);

	//2������socket
	
	//AF_INET��ʾʹ�� IPv4 Э�飻SOCK_STREAM��ʾʹ���������ӵ� TCP Э�飻0��ʾ����Э���Զ�ѡ�� 
	SOCKET cliSock = socket(AF_INET, SOCK_STREAM, 0);

	//3�������ַ

	//�����
	SOCKADDR_IN servAddr = { 0 };//һ���ṹ�壬��ʾ��ַ��Ϣ��
	servAddr.sin_family = AF_INET;//�ͷ�������socketһ����sin_family��ʾЭ��أ�һ����AF_INET��ʾTCP/IPЭ�顣
	servAddr.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//����˵�ַ����Ϊ���ػػ���ַ
	servAddr.sin_port = htons(12345);//host to net short �˿ں�����Ϊ12345

	if (connect(cliSock, (SOCKADDR*)&servAddr, sizeof(SOCKADDR)) == SOCKET_ERROR)
	{
		cout << "���ӳ��ִ��󣬴������" << WSAGetLastError() << endl;
	}

	//����������Ϣ�߳�
	//recvMsgThread�̵߳���ڵ㺯�������ڽ��շ��������͵���Ϣ��(LPVOID)&cliSock���ͻ��˵�Socket��Ϊ�̲߳������� 
	CloseHandle(CreateThread(NULL, 0, recvMsgThread, (LPVOID)&cliSock, 0, 0));
	//���߳���������Ҫ���͵���Ϣ
	while (1)
	{
		char buf[BUFFER_SIZE] = { 0 };
		cin.getline(buf, sizeof(buf));
		if (strcmp(buf, "quit") == 0)//�����롰quit�������˳�������
		{
			break;
		}
		send(cliSock, buf, sizeof(buf), 0);
	}
	closesocket(cliSock); //�ر�һ���򿪵��׽��֣����ͷ���ص���Դ��
	WSACleanup(); //Ŀ������Ӧ�ó�������Ҫ Winsock ����ʱ���ͷ�Ϊ Winsock �������Դ
	return 0;
}

DWORD WINAPI recvMsgThread(LPVOID IpParameter)//������Ϣ���߳�
{
	SOCKET cliSock = *(SOCKET*)IpParameter;//��ȡ�ͻ��˵�SOCKET����

	while (1)
	{
		char buffer[BUFFER_SIZE] = { 0 };//�ַ������������ڽ��պͷ�����Ϣ
		int nrecv = recv(cliSock, buffer, sizeof(buffer), 0);//nrecv�ǽ��յ����ֽ���
		if (nrecv > 0)//������յ����ַ�������0
		{
			cout << buffer << endl;
		}
		else if (nrecv < 0)//������յ����ַ���С��0��˵���Ͽ�����
		{
			cout << "��������Ͽ�����" << endl;
			break;
		}
	}
	return 0;
}
