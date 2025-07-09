//receiver.cpp
#include <winsock2.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>
#define MCASTADDR "224.0.0.1" //本例使用的多播组地址。
#define MCASTPORT 5150 //绑定的本地端口号。
#define BUFSIZE 1024 //接收数据缓冲大小。

//【可选】 用于表明发送方上线了
const TCHAR HELLO[BUFSIZE]="HELLO";
//【可选】 用于表明发送方下线了
const TCHAR BYE[BUFSIZE]="BYE";

#pragma comment(lib,"ws2_32")
int main( int argc, char ** argv) {
	WSADATA wsd;//存储被WSAStartup函数调用后返回的Windows Sockets数据
	struct sockaddr_in local, remote, from; //local指明地址信息，remote指要加入的多播组，from装有指向源地址的缓冲区
	SOCKET sock, sockM; //sock是接收的套接字，sockM是发送的套接字
	TCHAR recvbuf[BUFSIZE];//双字节，为unsigned short类型
	/*struct ip_mreq mcast; // Winsock1.0 */

	int len = sizeof( struct sockaddr_in);
	int ret;
	//初始化 WinSock2.2
	//使用Socket的程序在使用Socket之前必须调用WSAStartup函数，以后应用程序就可以调用所请求的Socket库中的其他Socket函数了
	if ( WSAStartup( MAKEWORD(2, 2), &wsd) != 0 ) {
		printf("WSAStartup() failed\n");
		return -1;
	}
	//WSASocket()创建一个与指定传送服务提供者捆绑的套接口，可选地创建和/或加入一个套接口组
	//SOCK_DGRAM (数据报套接字)
	//将参数设置为WSA_FLAG_MULTIPOINT_C_LEAF、WSA_FLAG_MULTIPOINT_D_LEAF和WSA_FLAG_ OVERLAPPED的位和，
	//指明IP多播通信在控制层面和数据层面都是“无根的”，只存在叶节点，它们可以任意加入一个多播组，而且从一个叶节点发送的数据会传送到每一个叶节点（包括它自己）
	//如果创建套接口发生错误
	if ((sock = WSASocket(AF_INET, SOCK_DGRAM, 0, NULL, 0, WSA_FLAG_MULTIPOINT_C_LEAF | WSA_FLAG_MULTIPOINT_D_LEAF | WSA_FLAG_OVERLAPPED)) == INVALID_SOCKET) {
		//WSAGetLastError()获取相应的错误代码
		printf("socket failed with:%d\n", WSAGetLastError());
		WSACleanup();
		return -1;
	}
	//将 sock 绑定到本机某端口上。
	local.sin_family = AF_INET;
	local.sin_port = htons(MCASTPORT);//绑定的主机端口号5150
	local.sin_addr.s_addr = INADDR_ANY;//表示主机的所有IP
	//bind()绑定套接字sock到一个IP地址和一个端口上,local是一个指向sockaddr结构体类型的指针，赋予套接字地址
	//如果绑定发生错误
	if ( bind(sock, (struct sockaddr*)&local, sizeof(local)) == SOCKET_ERROR ) {
		//WSAGetLastError()获取相应的错误代码
		printf( "bind failed with:%d \n", WSAGetLastError());
		closesocket(sock);//关闭套接字
		WSACleanup();//释放资源
		return -1;
	}
	//加入多播组，对多播组的设置
	remote.sin_family = AF_INET;
	remote.sin_port = htons(MCASTPORT);//绑定的主机端口号5150
	remote.sin_addr.s_addr = inet_addr( MCASTADDR );//将端口号转化为二进制的数
	//WSAJoinLeaf()仅仅将sock用于加入组播组，一个组是用组播地址确定的，remote是将与sock连接的远端名字
	//JL_BOTH用于指定sock既为发送者，又为接收者
	//返回新创建的多点套接口的描述字
	if (( sockM = WSAJoinLeaf(sock, (SOCKADDR*)&remote, sizeof(remote), NULL, NULL, NULL, NULL, JL_BOTH)) == INVALID_SOCKET) {
		printf("WSAJoinLeaf() failed:%d\n", WSAGetLastError()); //WSAGetLastError()获取相应的错误代码
		closesocket(sock);//关闭套接字
		WSACleanup();//释放资源
		return -1;
	}
	//接收多播数据，当接收到的数据为"QUIT"时退出。
	while (1) {
		//recvfrom()用于从已连接的套接口上接收数据，并捕获数据发送源的地址
		//recvbuf表示接收数据缓冲区
		//from指向装有源地址的缓冲区，len指向from缓冲区长度值
		//如果接收发生错误
		if (( ret = recvfrom(sock, recvbuf, BUFSIZE, 0, (struct sockaddr*)&from, &len)) == SOCKET_ERROR) {
			printf("recvfrom failed with:%d\n", WSAGetLastError()); //WSAGetLastError()获取相应的错误代码
			closesocket(sockM);//关闭套接字sockM
			closesocket(sock);//关闭套接字sock
			WSACleanup();//释放资源
			return -1;
		}
		if ( strcmp(recvbuf, "QUIT") == 0 ) { //如果接收到QUIT，则退出
			printf("INFO: 会议被 <%s> 结束！\n", inet_ntoa(from.sin_addr));
			break;
		}
		else if ( strcmp(recvbuf, HELLO) == 0 ) { //如果接收到HELLO，则
			printf("INFO: <%s> 加入会议。\n", inet_ntoa(from.sin_addr));
		}
		else if ( strcmp(recvbuf, BYE) == 0 ) { //
			printf("INFO: <%s> 退出会议。\n", inet_ntoa(from.sin_addr));
		}
		else {
			//输出收到的消息及发送方的IP地址
			recvbuf[ret] = '\0';
			printf("RECV:' %s ' FROM <%s> \n", recvbuf, inet_ntoa(from.sin_addr));
		}
		memset(recvbuf,0,sizeof(recvbuf));
	}
	closesocket(sockM);//关闭sockM
	closesocket(sock);//关闭sock
	WSACleanup();//释放资源
	return 0;
}


