//receiver.cpp
#include <winsock2.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>
#define MCASTADDR "224.0.0.1" //����ʹ�õĶಥ���ַ��
#define MCASTPORT 5150 //�󶨵ı��ض˿ںš�
#define BUFSIZE 1024 //�������ݻ����С��

//����ѡ�� ���ڱ������ͷ�������
const TCHAR HELLO[BUFSIZE]="HELLO";
//����ѡ�� ���ڱ������ͷ�������
const TCHAR BYE[BUFSIZE]="BYE";

#pragma comment(lib,"ws2_32")
int main( int argc, char ** argv) {
	WSADATA wsd;//�洢��WSAStartup�������ú󷵻ص�Windows Sockets����
	struct sockaddr_in local, remote, from; //localָ����ַ��Ϣ��remoteָҪ����Ķಥ�飬fromװ��ָ��Դ��ַ�Ļ�����
	SOCKET sock, sockM; //sock�ǽ��յ��׽��֣�sockM�Ƿ��͵��׽���
	TCHAR recvbuf[BUFSIZE];//˫�ֽڣ�Ϊunsigned short����
	/*struct ip_mreq mcast; // Winsock1.0 */

	int len = sizeof( struct sockaddr_in);
	int ret;
	//��ʼ�� WinSock2.2
	//ʹ��Socket�ĳ�����ʹ��Socket֮ǰ�������WSAStartup�������Ժ�Ӧ�ó���Ϳ��Ե����������Socket���е�����Socket������
	if ( WSAStartup( MAKEWORD(2, 2), &wsd) != 0 ) {
		printf("WSAStartup() failed\n");
		return -1;
	}
	//WSASocket()����һ����ָ�����ͷ����ṩ��������׽ӿڣ���ѡ�ش�����/�����һ���׽ӿ���
	//SOCK_DGRAM (���ݱ��׽���)
	//����������ΪWSA_FLAG_MULTIPOINT_C_LEAF��WSA_FLAG_MULTIPOINT_D_LEAF��WSA_FLAG_ OVERLAPPED��λ�ͣ�
	//ָ��IP�ಥͨ���ڿ��Ʋ�������ݲ��涼�ǡ��޸��ġ���ֻ����Ҷ�ڵ㣬���ǿ����������һ���ಥ�飬���Ҵ�һ��Ҷ�ڵ㷢�͵����ݻᴫ�͵�ÿһ��Ҷ�ڵ㣨�������Լ���
	//��������׽ӿڷ�������
	if ((sock = WSASocket(AF_INET, SOCK_DGRAM, 0, NULL, 0, WSA_FLAG_MULTIPOINT_C_LEAF | WSA_FLAG_MULTIPOINT_D_LEAF | WSA_FLAG_OVERLAPPED)) == INVALID_SOCKET) {
		//WSAGetLastError()��ȡ��Ӧ�Ĵ������
		printf("socket failed with:%d\n", WSAGetLastError());
		WSACleanup();
		return -1;
	}
	//�� sock �󶨵�����ĳ�˿��ϡ�
	local.sin_family = AF_INET;
	local.sin_port = htons(MCASTPORT);//�󶨵������˿ں�5150
	local.sin_addr.s_addr = INADDR_ANY;//��ʾ����������IP
	//bind()���׽���sock��һ��IP��ַ��һ���˿���,local��һ��ָ��sockaddr�ṹ�����͵�ָ�룬�����׽��ֵ�ַ
	//����󶨷�������
	if ( bind(sock, (struct sockaddr*)&local, sizeof(local)) == SOCKET_ERROR ) {
		//WSAGetLastError()��ȡ��Ӧ�Ĵ������
		printf( "bind failed with:%d \n", WSAGetLastError());
		closesocket(sock);//�ر��׽���
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
	if (( sockM = WSAJoinLeaf(sock, (SOCKADDR*)&remote, sizeof(remote), NULL, NULL, NULL, NULL, JL_BOTH)) == INVALID_SOCKET) {
		printf("WSAJoinLeaf() failed:%d\n", WSAGetLastError()); //WSAGetLastError()��ȡ��Ӧ�Ĵ������
		closesocket(sock);//�ر��׽���
		WSACleanup();//�ͷ���Դ
		return -1;
	}
	//���նಥ���ݣ������յ�������Ϊ"QUIT"ʱ�˳���
	while (1) {
		//recvfrom()���ڴ������ӵ��׽ӿ��Ͻ������ݣ����������ݷ���Դ�ĵ�ַ
		//recvbuf��ʾ�������ݻ�����
		//fromָ��װ��Դ��ַ�Ļ�������lenָ��from����������ֵ
		//������շ�������
		if (( ret = recvfrom(sock, recvbuf, BUFSIZE, 0, (struct sockaddr*)&from, &len)) == SOCKET_ERROR) {
			printf("recvfrom failed with:%d\n", WSAGetLastError()); //WSAGetLastError()��ȡ��Ӧ�Ĵ������
			closesocket(sockM);//�ر��׽���sockM
			closesocket(sock);//�ر��׽���sock
			WSACleanup();//�ͷ���Դ
			return -1;
		}
		if ( strcmp(recvbuf, "QUIT") == 0 ) { //������յ�QUIT�����˳�
			printf("INFO: ���鱻 <%s> ������\n", inet_ntoa(from.sin_addr));
			break;
		}
		else if ( strcmp(recvbuf, HELLO) == 0 ) { //������յ�HELLO����
			printf("INFO: <%s> ������顣\n", inet_ntoa(from.sin_addr));
		}
		else if ( strcmp(recvbuf, BYE) == 0 ) { //
			printf("INFO: <%s> �˳����顣\n", inet_ntoa(from.sin_addr));
		}
		else {
			//����յ�����Ϣ�����ͷ���IP��ַ
			recvbuf[ret] = '\0';
			printf("RECV:' %s ' FROM <%s> \n", recvbuf, inet_ntoa(from.sin_addr));
		}
		memset(recvbuf,0,sizeof(recvbuf));
	}
	closesocket(sockM);//�ر�sockM
	closesocket(sock);//�ر�sock
	WSACleanup();//�ͷ���Դ
	return 0;
}


