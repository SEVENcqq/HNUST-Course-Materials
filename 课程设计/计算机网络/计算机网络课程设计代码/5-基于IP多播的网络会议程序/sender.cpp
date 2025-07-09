/*
  1�������ղ�����Ϣ��رշ��ͷ��ͽ��շ�����ǽ
  2��ע�ⷢ�ͷ��ͽ��շ�������ͬһ̨��������Ϊͬһ�˿ںŲ���ͬʱ������������
  3�������� netsh interface ipv4 show joins ����鿴������������Щ�ಥ��ַ��ͨ��˫����Ҫ��ͬ���Ķಥ��ַ
 */
#include <winsock2.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#define MCASTADDR "224.0.0.1" //����ʹ�õĶಥ���ַ��
#define MCASTPORT 5150 //���ض˿ںš�
#define BUFSIZE 1024 //�������ݻ����С��
using namespace std;

//����ѡ�� ���ڱ����Լ�������
const TCHAR HELLO[BUFSIZE]="HELLO";
//����ѡ�� ���ڱ����Լ�������
const TCHAR BYE[BUFSIZE]="BYE";

#pragma comment(lib,"ws2_32")
int main( int argc,char ** argv)
{
	WSADATA wsd;//�洢��WSAStartup�������ú󷵻ص�Windows Sockets����
	struct sockaddr_in remote;//remoteָ����ַ��Ϣ
	SOCKET sock,sockM;//sock�ǽ��յ��׽��֣�sockM�Ƿ��͵��׽���
	TCHAR sendbuf[BUFSIZE];//˫�ֽڣ�Ϊunsigned short����
	int len = sizeof( struct sockaddr_in);
	//��ʼ�� WinSock2.2
	//ʹ��Socket�ĳ�����ʹ��Socket֮ǰ�������WSAStartup�������Ժ�Ӧ�ó���Ϳ��Ե����������Socket���е�����Socket������
	if( WSAStartup( MAKEWORD(2,2),&wsd) != 0 )
	{
		printf("WSAStartup() failed\n");
		return -1;
	}
	//WSASocket()����һ����ָ�����ͷ����ṩ��������׽ӿڣ���ѡ�ش�����/�����һ���׽ӿ���
	//SOCK_DGRAM (���ݱ��׽���)
	//����������ΪWSA_FLAG_MULTIPOINT_C_LEAF��WSA_FLAG_MULTIPOINT_D_LEAF��WSA_FLAG_ OVERLAPPED��λ�ͣ�
	//ָ��IP�ಥͨ���ڿ��Ʋ�������ݲ��涼�ǡ��޸��ġ���ֻ����Ҷ�ڵ㣬���ǿ����������һ���ಥ�飬���Ҵ�һ��Ҷ�ڵ㷢�͵����ݻᴫ�͵�ÿһ��Ҷ�ڵ㣨�������Լ���
	//��������׽ӿڷ�������
	if((sock = WSASocket(AF_INET,SOCK_DGRAM,0,NULL,0,WSA_FLAG_MULTIPOINT_C_LEAF|WSA_FLAG_MULTIPOINT_D_LEAF|WSA_FLAG_OVERLAPPED)) == INVALID_SOCKET)
	{
		printf("socket failed with:%d\n",WSAGetLastError());//WSAGetLastError()��ȡ��Ӧ�Ĵ������
		WSACleanup();//�ͷ���Դ
		return -1;
	}
	//����ಥ�飬�Զಥ�������
	remote.sin_family = AF_INET;
	remote.sin_port = htons(MCASTPORT);//�󶨵������˿ں�5150
	remote.sin_addr.s_addr = inet_addr( MCASTADDR );//���˿ں�ת��Ϊ�����Ƶ���
	//WSAJoinLeaf()������sock���ڼ����鲥�飬һ���������鲥��ַȷ���ģ�remote�ǽ���sock���ӵ�Զ������
	//JL_BOTH����ָ��sock��Ϊ�����ߣ���Ϊ������
	//�����´����Ķ���׽ӿڵ�������
	if(( sockM = WSAJoinLeaf(sock,(SOCKADDR*)&remote,sizeof(remote),NULL,NULL,NULL,NULL,JL_BOTH)) == INVALID_SOCKET)
	{
		printf("WSAJoinLeaf() failed:%d\n",WSAGetLastError());
		closesocket(sock);//�ر��׽���sock
		WSACleanup();//�ͷ���Դ
		return -1;
	}
	
	bool begin=true;
	
	//���Ͷಥ���ݣ����û��ڿ���̨����"QUIT"ʱ�˳���
	while(1)
	{
		if(begin){
			//һ��ʼ֪ͨ���շ��������ˣ��Լ�������
			strcpy(sendbuf,HELLO);
		}else{
			printf("SEND : ");
			cin.getline(sendbuf, BUFSIZE); 
		}
		
		//sendto()��������ָ����sockM���͸��Է�����
		//remoteΪָ��ಥ���׽ӿڵĵ�ַ
		//������ͳ��ִ���
		if( sendto(sockM,(char*)sendbuf,strlen(sendbuf),0,(struct sockaddr*)&remote,sizeof(remote))==SOCKET_ERROR)
		{
			printf("sendto failed with: %d\n",WSAGetLastError());
			closesocket(sockM);//�ر��׽���sockM
			closesocket(sock);//�ر��׽���sock
			WSACleanup();//�ͷ���Դ
			return -1;
		}
		if(strcmp(sendbuf,"QUIT")==0){//�������QUIT�����ͷ��ͽ��շ����˳�
			printf("INFO: ���ѽ����������\n");
			break;
		}else if(strcmp(sendbuf,BYE)==0){
			printf("INFO: �����˳����飡\n");
			break;
		}
		begin=false;
		Sleep(500);//����500����
	}
	closesocket(sockM);//�ر��׽���sockM
	closesocket(sock);//�ر��׽���sock
	WSACleanup();//�ͷ���Դ
	return 0;
}


