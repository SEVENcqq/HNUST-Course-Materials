#include <windows.h>
#include <iostream>
#include <cstdio>
// �������ݹ����Ľ��̵Ŀ�¡���̲������� ID ֵ
void StartClone(int nCloneID)
{
// ��ȡ���ڵ�ǰ��ִ���ļ����ļ���
    TCHAR szFilename[MAX_PATH] ; /*�ַ�������*/

//��һ������Ϊ�����NULL��ָ��ǰ���򡣵ڶ����������ڴ�ŵ�ַ��ָ�룬������������ϵͳ�Դ��ĺ궨�塣���ù�.
    GetModuleFileName(NULL, szFilename, MAX_PATH);
// ��ʽ�������ӽ��̵������в�֪ͨ�� EXE �ļ����Ϳ�¡ ID
    TCHAR szCmdLine[MAX_PATH];
    sprintf(szCmdLine,"\"%s\" %d",szFilename,nCloneID);
// �����ӽ��̵� STARTUPINFO �ṹ
    STARTUPINFO si;
    ZeroMemory(&si, sizeof(si) ) ; /*��0���*/
    /*����STARTUPINFO�ṹ�е��ֽ���.���Microsoft������չ�ýṹ,���������汾�����ֶΣ�Ӧ�ó�����뽫cb��ʼ��Ϊsizeof(STARTUPINFO)*/
    si.cb = sizeof(si) ; // �����Ǳ��ṹ�Ĵ�С5
// ���ص������ӽ��̵Ľ�����Ϣ
    PROCESS_INFORMATION pi;
// ����ͬ���Ŀ�ִ���ļ��������д������̣����������ӽ��̵�����
    BOOL bCreateOK=::CreateProcess(
                       szFilename, // ������� EXE ��Ӧ�ó��������
                       szCmdLine, // ��������Ϊ��һ���ӽ��̵ı�־
                       NULL, // ȱʡ�Ľ��̰�ȫ��
                       NULL, // ȱʡ���̰߳�ȫ��
                       FALSE, // ���̳о��
                       CREATE_NEW_CONSOLE, // ʹ���µĿ���̨
                       NULL, // �µĻ���
                       NULL, // ��ǰĿ¼
                       &si, // ������Ϣ
                       &pi) ; // ���صĽ�����Ϣ
// ���ӽ����ͷ�����
    if (bCreateOK)
    {
        /*�ر�һ���ں˶���*/
        CloseHandle(pi.hProcess) ;
        CloseHandle(pi.hThread) ;
    }
}
int main(int argc, char* argv[] )
{
// ȷ���������������̣������������ڽ����б��е�λ��
    int nClone=0;
//�޸���䣺int nClone;
//��һ���޸ģ�nClone=0;
    //nClone=0;
    if (argc > 1)
    {
// �ӵڶ�����������ȡ��¡ ID
        :: sscanf(argv[1], "%d", &nClone) ;
    }
//�ڶ����޸ģ�nClone=0;
/*�˴��޸����������ӽ���*/
    nClone = 0;
// ��ʾ����λ��
    std :: cout << "Process ID:" << :: GetCurrentProcessId()
                << ", Clone ID:" << nClone
                << std :: endl;
// ����Ƿ��д����ӽ��̵���Ҫ
    const int c_nCloneMax=5;
    if (nClone < c_nCloneMax)
    {
// �����½��̵������кͿ�¡��
        StartClone(++nClone) ;
    }
// �ȴ���Ӧ���������������
    getchar();

    return 0;
}

