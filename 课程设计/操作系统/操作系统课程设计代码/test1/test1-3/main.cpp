# include <windows.h>
# include <iostream>
# include <cstdio>
static LPCTSTR g_szMutexName = "w2kdg.ProcTerm.mutex.Suicide" ;
// ������ǰ���̵Ŀ�¡���̵ļ򵥷���
void StartClone()
{
// ��ȡ��ǰ��ִ���ļ����ļ���
    TCHAR szFilename[MAX_PATH] ;
    GetModuleFileName(NULL, szFilename, MAX_PATH) ;
// ��ʽ�������ӽ��̵������У��ַ�����child������Ϊ�βδ��ݸ��ӽ��̵� main ����
    TCHAR szCmdLine[MAX_PATH] ;
//ʵ�� 1-3 ���� 3�����¾��е��ַ��� child ��Ϊ����ַ��������±���ִ�У�ִ��ǰ���ȱ����Ѿ�
    //��ɵĹ���
    sprintf(szCmdLine, "\"%s\" child", szFilename) ;
// �ӽ��̵�������Ϣ�ṹ
    STARTUPINFO si;
    ZeroMemory(&si,sizeof(si)) ;
    si.cb = sizeof(si) ; // Ӧ���Ǵ˽ṹ�Ĵ�С
// ���ص������ӽ��̵Ľ�����Ϣ
    PROCESS_INFORMATION pi;
// ��ͬ���Ŀ�ִ���ļ����������д������̣���ָ������һ���ӽ���
    BOOL bCreateOK=CreateProcess(
                       szFilename, // ������Ӧ�ó�������� (�� EXE �ļ�)
                       szCmdLine, // ������������һ���ӽ��̵ı�־
                       NULL, // ���ڽ��̵�ȱʡ�İ�ȫ��
                       NULL, // �����̵߳�ȱʡ��ȫ��
                       FALSE, // ���̳о��
                       CREATE_NEW_CONSOLE, //�����´���
                       NULL, // �»���
                       NULL, // ��ǰĿ¼
                       &si, // ������Ϣ�ṹ
                       &pi ) ; // ���صĽ�����Ϣ
// �ͷ�ָ���ӽ��̵�����
    if (bCreateOK)
    {
        CloseHandle(pi.hProcess) ;
        CloseHandle(pi.hThread) ;
    }
}
void Parent()
{
// ��������ɱ�����������
    HANDLE hMutexSuicide=CreateMutex(
                             NULL, // ȱʡ�İ�ȫ��
                             TRUE, // ���ӵ�е�7
                             g_szMutexName) ; // ����������
    if (hMutexSuicide != NULL)
    {
// �����ӽ���
        std :: cout << "Creating the child process." << std :: endl;
        StartClone() ;
// ָ���ӽ��̡�ɱ��������
        std :: cout << "Telling the child process to quit. "<< std :: endl;
//�ȴ������̵ļ�����Ӧ
        getchar() ;
//�ͷŻ����������Ȩ������źŻᷢ�͸��ӽ��̵� WaitForSingleObject ����
        ReleaseMutex(hMutexSuicide) ;
// �������
        CloseHandle(hMutexSuicide) ;
    }
}
void Child()
{
// �򿪡���ɱ��������
    HANDLE hMutexSuicide = OpenMutex(
                               SYNCHRONIZE, // ������ͬ��
                               FALSE, // ����Ҫ���´���
                               g_szMutexName) ; // ����
    if (hMutexSuicide != NULL)
    {
// �����������ڵȴ�ָ��
        std :: cout <<"Child waiting for suicide instructions. " << std :: endl;

//�ӽ��̽�������״̬���ȴ�������ͨ�������巢�����ź�
        /*INFINITE��ʾ���,������Ϊ�������ͺ�����*/
        //WaitForSingleObject(hMutexSuicide,INFINITE) ;
        WaitForSingleObject(hMutexSuicide,0);
        //WaitForSingleObject(hMutexSuicide,5000);
//ʵ�� 1-3 ���� 4�����Ͼ��Ϊ WaitForSingleObject(hMutexSuicide, 0) �����±���ִ��
// ׼������ֹ��������
        std :: cout << "Child quiting." << std :: endl;
        CloseHandle(hMutexSuicide) ;
    }
}
int main(int argc, char* argv[] )
{
// ��������Ϊ�Ǹ����̻����ӽ���
    if (argc>1 && :: strcmp(argv[1], "child" ) == 0)
    {
        Child() ;
    }
    else
    {
        Parent() ;
    }
    return 0;
}

