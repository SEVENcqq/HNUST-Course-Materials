#include <windows.h>
#include <iostream>
#include <cstdio>
// 创建传递过来的进程的克隆过程并赋于其 ID 值
void StartClone(int nCloneID)
{
// 提取用于当前可执行文件的文件名
    TCHAR szFilename[MAX_PATH] ; /*字符串类型*/

//第一个参数为句柄，NULL则指向当前程序。第二个参数用于存放地址的指针，第三个参数，系统自带的宏定义。不用管.
    GetModuleFileName(NULL, szFilename, MAX_PATH);
// 格式化用于子进程的命令行并通知其 EXE 文件名和克隆 ID
    TCHAR szCmdLine[MAX_PATH];
    sprintf(szCmdLine,"\"%s\" %d",szFilename,nCloneID);
// 用于子进程的 STARTUPINFO 结构
    STARTUPINFO si;
    ZeroMemory(&si, sizeof(si) ) ; /*用0填充*/
    /*包含STARTUPINFO结构中的字节数.如果Microsoft将来扩展该结构,它可用作版本控制手段，应用程序必须将cb初始化为sizeof(STARTUPINFO)*/
    si.cb = sizeof(si) ; // 必须是本结构的大小5
// 返回的用于子进程的进程信息
    PROCESS_INFORMATION pi;
// 利用同样的可执行文件和命令行创建进程，并赋于其子进程的性质
    BOOL bCreateOK=::CreateProcess(
                       szFilename, // 产生这个 EXE 的应用程序的名称
                       szCmdLine, // 告诉其行为像一个子进程的标志
                       NULL, // 缺省的进程安全性
                       NULL, // 缺省的线程安全性
                       FALSE, // 不继承句柄
                       CREATE_NEW_CONSOLE, // 使用新的控制台
                       NULL, // 新的环境
                       NULL, // 当前目录
                       &si, // 启动信息
                       &pi) ; // 返回的进程信息
// 对子进程释放引用
    if (bCreateOK)
    {
        /*关闭一个内核对象*/
        CloseHandle(pi.hProcess) ;
        CloseHandle(pi.hThread) ;
    }
}
int main(int argc, char* argv[] )
{
// 确定派生出几个进程，及派生进程在进程列表中的位置
    int nClone=0;
//修改语句：int nClone;
//第一次修改：nClone=0;
    //nClone=0;
    if (argc > 1)
    {
// 从第二个参数中提取克隆 ID
        :: sscanf(argv[1], "%d", &nClone) ;
    }
//第二次修改：nClone=0;
/*此处修改无限生成子进程*/
    nClone = 0;
// 显示进程位置
    std :: cout << "Process ID:" << :: GetCurrentProcessId()
                << ", Clone ID:" << nClone
                << std :: endl;
// 检查是否有创建子进程的需要
    const int c_nCloneMax=5;
    if (nClone < c_nCloneMax)
    {
// 发送新进程的命令行和克隆号
        StartClone(++nClone) ;
    }
// 等待响应键盘输入结束进程
    getchar();

    return 0;
}

