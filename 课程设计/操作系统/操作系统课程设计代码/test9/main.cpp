#include <windows.h>
#include <iostream>

#define MAX_READER_NUM 512
#define READER_NUM 3
#define WRITER_NUM 2
#define MOD 100
using namespace std;


int readcount = 0;
HANDLE WriteSemaphore; //ʵ�ֶ�д����
HANDLE XSemaphore; //���������޸ĵĻ�����
DWORD WINAPI reader(LPVOID); //�����߳�
DWORD WINAPI writer(LPVOID); //д���߳�
bool p_ccontinue = true;    //���Ƴ������

int test = 0;

int main()
{
    //��ʼ�������ź���
    /*�ṹ��ָ��;�ź�������ĳ�ʼ����;�ź��������������;�ź������������*/
    WriteSemaphore = CreateSemaphore(NULL,1,MAX_READER_NUM,NULL);
    XSemaphore = CreateSemaphore(NULL,1,MAX_READER_NUM,NULL);

    //��WindowsAPIģ��α��� parbegin(reader,writer);
    //�ܵ��߳���
    HANDLE hThreads[READER_NUM + WRITER_NUM]; //���̵߳� handle
    DWORD readerID[READER_NUM]; //�����̵߳ı�ʶ��
    DWORD writerID[WRITER_NUM]; //д���̵߳ı�ʶ��
    //���������߳�
    for (int i=0; i<READER_NUM; i++)
    {
        hThreads[i]=CreateThread(NULL,0,reader,NULL,0,&readerID[i]);
        if (hThreads[i]==NULL)
            return -1;
    }
    //����д���߳�
    for (int i=0; i<WRITER_NUM; i++)
    {
        hThreads[READER_NUM+i]=CreateThread(NULL,0,writer,NULL,0,&writerID[i]);
        if (hThreads[i]==NULL)
            return -1;
    }
    while(p_ccontinue)
    {
        if(getchar())  //���س�����ֹ��������
        {
            p_ccontinue = false;
        }
    }
    return 0;
}

//�����Ķ�
void READUNIT()
{
    cout<<"һ�����߿�ʼ�Ķ�:";
    cout<<test<<endl;
}

//д��д
void WRITEUNIT()
{
    cout<<"д�߿�ʼд:";
    test = (test+1) % MOD;
    cout<<test<<endl;
}
//����
DWORD WINAPI reader(LPVOID lpPara)
{
    while(p_ccontinue)
    {
        WaitForSingleObject(XSemaphore,INFINITE); //semWait(x);
        readcount++;
        if(readcount == 1)  //��һ����������
            WaitForSingleObject(WriteSemaphore,INFINITE); //semWait(wsem);
        ReleaseSemaphore(XSemaphore,1,NULL); //semSignal(x);
        READUNIT();
        //�Ķ����
        WaitForSingleObject(XSemaphore,INFINITE); //semWait(x);
        readcount--;
        //�޶��ߣ��ͷ���Դ
        if(readcount == 0)
            ReleaseSemaphore(WriteSemaphore,1,NULL); //semSignal(wsem);
        ReleaseSemaphore(XSemaphore,1,NULL); //semSignal(x);
        Sleep(3000);
    }
    return 0;
}
//д��
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

