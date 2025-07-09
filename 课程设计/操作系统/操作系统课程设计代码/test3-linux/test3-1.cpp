#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/msg.h>
#include <sys/ipc.h>
#include <unistd.h>
#include<sys/wait.h>
#define MSGKEY 75
struct msgform
{
    long mtype;
    char mtext[1030];
} msg;
int msgqid,i;
void CLIENT()
{
    int i;
    //flag 本身由操作允许权和控制命令值相“或”得到。
    msgqid=msgget(MSGKEY,0777);
    for (i=10; i>=1; i--)
    {
        msg.mtype=i;
        printf("(client) sent \n");
        //flag 规定当核心用尽内部缓冲空间时应执行的动作
        msgsnd(msgqid,&msg,1024,0);
    }
    exit(0);
}
void SERVER()
{
    //IPC_CREAT | 0400 是否该队列应被创建；
    msgqid=msgget(MSGKEY,0777|IPC_CREAT);
    do
    {
        //type 是用户要读的消息类型：
        msgrcv(msgqid,&msg,1030,0,0);
        printf("(Server) recieved\n");
    }
    while(msg.mtype!=1);
    msgctl(msgqid,IPC_RMID,0);
    exit(0);
}
int main()
{
    while((i=fork())==-1);
    if(!i)
        SERVER();
    while((i=fork())==-1);
    if(!i)
        CLIENT();
    wait(0);
    wait(0);
    return 0;
}
