#include <windows.h>
#include <iostream>

#define MAX_READER_NUM 512
#define READER_NUM 3
#define WRITER_NUM 2
#define MOD 100
using namespace std;


int readcount = 0;
HANDLE WriteSemaphore; //实现读写互斥
HANDLE XSemaphore; //对于人数修改的互斥量
DWORD WINAPI reader(LPVOID); //读者线程
DWORD WINAPI writer(LPVOID); //写者线程
bool p_ccontinue = true;    //控制程序结束

int test = 0;

int main()
{
    //初始化两个信号量
    /*结构体指针;信号量对象的初始计数;信号量对象的最大计数;信号量对象的名称*/
    WriteSemaphore = CreateSemaphore(NULL,1,MAX_READER_NUM,NULL);
    XSemaphore = CreateSemaphore(NULL,1,MAX_READER_NUM,NULL);

    //用WindowsAPI模拟课本上 parbegin(reader,writer);
    //总的线程数
    HANDLE hThreads[READER_NUM + WRITER_NUM]; //各线程的 handle
    DWORD readerID[READER_NUM]; //读者线程的标识符
    DWORD writerID[WRITER_NUM]; //写者线程的标识符
    //创建读者线程
    for (int i=0; i<READER_NUM; i++)
    {
        hThreads[i]=CreateThread(NULL,0,reader,NULL,0,&readerID[i]);
        if (hThreads[i]==NULL)
            return -1;
    }
    //创建写者线程
    for (int i=0; i<WRITER_NUM; i++)
    {
        hThreads[READER_NUM+i]=CreateThread(NULL,0,writer,NULL,0,&writerID[i]);
        if (hThreads[i]==NULL)
            return -1;
    }
    while(p_ccontinue)
    {
        if(getchar())  //按回车后终止程序运行
        {
            p_ccontinue = false;
        }
    }
    return 0;
}

//读者阅读
void READUNIT()
{
    cout<<"一个读者开始阅读:";
    cout<<test<<endl;
}

//写者写
void WRITEUNIT()
{
    cout<<"写者开始写:";
    test = (test+1) % MOD;
    cout<<test<<endl;
}
//读者
DWORD WINAPI reader(LPVOID lpPara)
{
    while(p_ccontinue)
    {
        WaitForSingleObject(XSemaphore,INFINITE); //semWait(x);
        readcount++;
        if(readcount == 1)  //第一个读者来了
            WaitForSingleObject(WriteSemaphore,INFINITE); //semWait(wsem);
        ReleaseSemaphore(XSemaphore,1,NULL); //semSignal(x);
        READUNIT();
        //阅读完毕
        WaitForSingleObject(XSemaphore,INFINITE); //semWait(x);
        readcount--;
        //无读者，释放资源
        if(readcount == 0)
            ReleaseSemaphore(WriteSemaphore,1,NULL); //semSignal(wsem);
        ReleaseSemaphore(XSemaphore,1,NULL); //semSignal(x);
        Sleep(3000);
    }
    return 0;
}
//写者
DWORD WINAPI writer(LPVOID lpPara)
{
    while(p_ccontinue)
    {
        WaitForSingleObject(WriteSemaphore,INFINITE); //semWait(wsem);
        WRITEUNIT();
        ReleaseSemaphore(WriteSemaphore,1,NULL); //semSignal(wsem);
        Sleep(2000);
    }
    return 0;
}

