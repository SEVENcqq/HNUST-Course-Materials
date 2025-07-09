/*
  1、若接收不到消息需关闭发送方和接收方防火墙
  2、注意发送方和接收方不能是同一台机器，因为同一端口号不能同时分配两个进程
  3、可以用 netsh interface ipv4 show joins 命令查看所用网卡有哪些多播地址，通信双方都要是同样的多播地址
 */
#include <winsock2.h>
#include <ws2tcpip.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#define MCASTADDR "224.0.0.1" //本例使用的多播组地址。
#define MCASTPORT 5150 //本地端口号。
#define BUFSIZE 1024 //发送数据缓冲大小。
using namespace std;

//【可选】 用于表明自己上线了
const TCHAR HELLO[BUFSIZE]="HELLO";
//【可选】 用于表明自己下线了
const TCHAR BYE[BUFSIZE]="BYE";

#pragma comment(lib,"ws2_32")
int main( int argc,char ** argv)
{
	WSADATA wsd;//存储被WSAStartup函数调用后返回的Windows Sockets数据
	struct sockaddr_in remote;//remote指明地址信息
	SOCKET sock,sockM;//sock是接收的套接字，sockM是发送的套接字
	TCHAR sendbuf[BUFSIZE];//双字节，为unsigned short类型
	int len = sizeof( struct sockaddr_in);
	//初始化 WinSock2.2
	//使用Socket的程序在使用Socket之前必须调用WSAStartup函数，以后应用程序就可以调用所请求的Socket库中的其他Socket函数了
	if( WSAStartup( MAKEWORD(2,2),&wsd) != 0 )
	{
		printf("WSAStartup() failed\n");
		return -1;
	}
	//WSASocket()创建一个与指定传送服务提供者捆绑的套接口，可选地创建和/或加入一个套接口组
	//SOCK_DGRAM (数据报套接字)
	//将参数设置为WSA_FLAG_MULTIPOINT_C_LEAF、WSA_FLAG_MULTIPOINT_D_LEAF和WSA_FLAG_ OVERLAPPED的位和，
	//指明IP多播通信在控制层面和数据层面都是“无根的”，只存在叶节点，它们可以任意加入一个多播组，而且从一个叶节点发送的数据会传送到每一个叶节点（包括它自己）
	//如果创建套接口发生错误
	if((sock = WSASocket(AF_INET,SOCK_DGRAM,0,NULL,0,WSA_FLAG_MULTIPOINT_C_LEAF|WSA_FLAG_MULTIPOINT_D_LEAF|WSA_FLAG_OVERLAPPED)) == INVALID_SOCKET)
	{
		printf("socket failed with:%d\n",WSAGetLastError());//WSAGetLastError()获取相应的错误代码
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
	if(( sockM = WSAJoinLeaf(sock,(SOCKADDR*)&remote,sizeof(remote),NULL,NULL,NULL,NULL,JL_BOTH)) == INVALID_SOCKET)
	{
		printf("WSAJoinLeaf() failed:%d\n",WSAGetLastError());
		closesocket(sock);//关闭套接字sock
		WSACleanup();//释放资源
		return -1;
	}
	
	bool begin=true;
	
	//发送多播数据，当用户在控制台输入"QUIT"时退出。
	while(1)
	{
		if(begin){
			//一开始通知接收方（主持人）自己上线了
			strcpy(sendbuf,HELLO);
		}else{
			printf("SEND : ");
			cin.getline(sendbuf, BUFSIZE); 
		}
		
		//sendto()将数据由指定的sockM发送给对方主机
		//remote为指向多播组套接口的地址
		//如果发送出现错误
		if( sendto(sockM,(char*)sendbuf,strlen(sendbuf),0,(struct sockaddr*)&remote,sizeof(remote))==SOCKET_ERROR)
		{
			printf("sendto failed with: %d\n",WSAGetLastError());
			closesocket(sockM);//关闭套接字sockM
			closesocket(sock);//关闭套接字sock
			WSACleanup();//释放资源
			return -1;
		}
		if(strcmp(sendbuf,"QUIT")==0){//如果发送QUIT，则发送方和接收方都退出
			printf("INFO: 您已将会议结束！\n");
			break;
		}else if(strcmp(sendbuf,BYE)==0){
			printf("INFO: 您已退出会议！\n");
			break;
		}
		begin=false;
		Sleep(500);//休眠500毫秒
	}
	closesocket(sockM);//关闭套接字sockM
	closesocket(sock);//关闭套接字sock
	WSACleanup();//释放资源
	return 0;
}


