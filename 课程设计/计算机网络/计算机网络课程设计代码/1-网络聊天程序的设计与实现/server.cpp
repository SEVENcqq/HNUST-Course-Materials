// �����������
#include<iostream>
#include<Winsock2.h>//socketͷ�ļ�
#include<cstring>
using namespace std;

//����ϵͳ�ṩ��socket��̬���ӿ�
#pragma comment(lib,"ws2_32.lib")   //socket��

//==============================ȫ�ֱ�����===================================
const int BUFFER_SIZE = 1024;//��������С
int RECV_TIMEOUT = 100;//������Ϣ��ʱ����λ����
int SEND_TIMEOUT = 100;//������Ϣ��ʱ����λ����
const int WAIT_TIME = 10;//ÿ���ͻ��˵ȴ��¼���ʱ�䣬��λ����
const int MAX_LINK_NUM = 3;//��������ͬʱ�������������˷�������
SOCKET cliSock[MAX_LINK_NUM];//�ͻ����׽��� 0��Ϊ�����
SOCKADDR_IN cliAddr[MAX_LINK_NUM];//�ͻ��˵�ַ
WSAEVENT cliEvent[MAX_LINK_NUM];//�ͻ����¼� 0��Ϊ�����,�������ó����һ���ֵȴ�������һ���ֵ��źš����磬�����ݴ��׽��ֱ�Ϊ����ʱ��winsock ��Ὣ�¼�����Ϊ�ź�״̬
int total = 0;//��ǰ�Ѿ����ӵĿͷ��˷�����


//==============================��������===================================
DWORD WINAPI servEventThread(LPVOID IpParameter);//�������˴����߳�


int main() {
	//1����ʼ��socket��
	WSADATA wsaData;//��ȡ�汾��Ϣ��˵��Ҫʹ�õİ汾
	WSAStartup(MAKEWORD(2, 2), &wsaData);//MAKEWORD(���汾��, ���汾��)

	//2������socket
	SOCKET servSock = socket(AF_INET, SOCK_STREAM, 0);//��ʽ�׽��֣�����TCPЭ�飩

	//3������������ַ�����һ���ṹ������
	SOCKADDR_IN servAddr; //sockaddr_in ��internet�������׽��ֵĵ�ַ��ʽ
	servAddr.sin_family = AF_INET;//�ͷ�������socketһ����sin_family��ʾЭ��أ�һ����AF_INET��ʾTCP/IPЭ�顣
	servAddr.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");//����˵�ַ����Ϊ���ػػ���ַ
	servAddr.sin_port = htons(12345);//host to net short �˿ں�����Ϊ12345

	//4���󶨷���˵�socket�ʹ���õĵ�ַ
	bind(servSock, (SOCKADDR*)&servAddr, sizeof(servAddr)); //���Լ����õ�ַ�Ͷ˿ڣ����ȴ��ͻ������ӡ�

	//4.5�������sokect��һ���¼������������տͻ������ӵ��¼�
	WSAEVENT servEvent = WSACreateEvent();//����һ���˹�����Ϊ���ŵ��¼�����
	WSAEventSelect(servSock, servEvent, FD_ALL_EVENTS);//���¼����󣬲��Ҽ��������¼�

	cliSock[0] = servSock;
	cliEvent[0] = servEvent;

	//5���������������׽���ת�ɼ���ģʽ��
	listen(servSock, 10);//�������г���Ϊ10

	//6���������ܿͻ������ӣ����տͻ�����Ϣ���߳�
	//����Ҫ�������ֱ�ӹر�
	CloseHandle(CreateThread(NULL, 0, 
		servEventThread, (LPVOID)&servSock, //�̺߳�����������
		0, 0));

	cout << "�����ҷ������ѿ�����˵�������ͬʱ������Ϊ " << MAX_LINK_NUM << "��" << endl;

	bool run=true;
	//��Ҫ�����߳�һֱ������ȥ
	//������Ϣ��ȫ���ͻ���
	while (run) {

		char contentBuf[BUFFER_SIZE] = { 0 };
		char sendBuf[BUFFER_SIZE] = { 0 };
		cin.getline(contentBuf, sizeof(contentBuf));
		if (strcmp(contentBuf, "quit") == 0)//�����롰quit������ر������ң��������˳�
		{
			run=false;
			strcpy(contentBuf,"�������Ѿ��رգ�");
		}
		sprintf(sendBuf, "[ϵͳ��Ϣ]%s", contentBuf);
		//��ÿ���ͻ��˷��ͣ���1��ʼ���������������ͣ�
		for (int j = 1; j <= total; j++) {
			send(cliSock[j], sendBuf, sizeof(sendBuf), 0);
		}

	}
	//1-�ر�socket�����β����
	for(int i=0;i<total;i++){
		closesocket(cliSock[i]);
		WSACloseEvent(cliEvent[i]);
	}
	WSACleanup();
	return 0;
}

DWORD WINAPI servEventThread(LPVOID IpParameter) { //���������߳�
	//���̸߳��������˺͸����ͻ��˷������¼�
	//������Ĳ�����ʼ��
	SOCKET servSock = *(SOCKET*)IpParameter;//LPVOIDΪ��ָ�����ͣ���Ҫ��ת��SOCKET���������ã�����ʹ�ô����SOCKET
	while (1) { //��ִͣ��
		for (int i = 0; i < total + 1; i++) { //i�����������ڼ����¼����ն�
			//����һ���ͻ������ӣ���total==1��ѭ�����Σ������ͻ��˺ͷ����
			//��ÿһ���նˣ��ͻ��˺ͷ���ˣ����鿴�Ƿ����¼����ȴ�WAIT_TIME����
			int index = WSAWaitForMultipleEvents(1, //�ȴ����¼�����
				&cliEvent[i], //�¼�����
				false, //�Ƿ�ȴ������¼�����
				WAIT_TIME,  //��ʱʱ�䣨���룩
				0); //ָ���߳��Ƿ�Ϊalertable�ȴ�״̬��һ������ΪFALSE

			index -= WSA_WAIT_EVENT_0;//��ʱindexΪ�����¼����ն��±�

			if (index == WSA_WAIT_TIMEOUT || index == WSA_WAIT_FAILED) {
				continue;//���������߳�ʱ�����������ն�
			}

			else if (index == 0) {
				WSANETWORKEVENTS networkEvents;
				WSAEnumNetworkEvents(cliSock[i], cliEvent[i], &networkEvents);//�鿴��ʲô�¼�

				//�¼�ѡ��
				if (networkEvents.lNetworkEvents & FD_ACCEPT) { //������accept�¼����˴���λ�������룩
					if (networkEvents.iErrorCode[FD_ACCEPT_BIT] != 0) {
						cout << "����ʱ�������󣬴������" << networkEvents.iErrorCode[FD_ACCEPT_BIT] << endl;
						continue;
					}
					//�����¿ͻ�������
					if (total + 1 < MAX_LINK_NUM) { //������һ���ͻ�����ȻС�����������������ܸ�����
						//totalΪ�����ӿͻ�������
						int nextIndex = total + 1;//������¿ͻ��˵��±�
						int addrLen = sizeof(SOCKADDR);
						//���ܿͻ��˵���������
						SOCKET newSock = accept(servSock, (SOCKADDR*)&cliAddr[nextIndex], &addrLen);
						if (newSock != INVALID_SOCKET) {
							//���÷��ͺͽ���ʱ��
							//��TCP�����У�recv�Ⱥ���Ĭ��Ϊ����ģʽ(block)����ֱ�������ݵ���֮ǰ�������᷵�أ���������ʱ����Ҫһ�ֳ�ʱ����ʹ����һ��ʱ��󷵻ض������Ƿ������ݵ������������Ǿͻ��õ�setsockopt()����
							setsockopt(newSock, SOL_SOCKET, SO_SNDTIMEO, (const char*) &SEND_TIMEOUT, sizeof(SEND_TIMEOUT));
							setsockopt(newSock, SOL_SOCKET, SO_RCVTIMEO, (const char*) &RECV_TIMEOUT, sizeof(RECV_TIMEOUT));
							
							//���¿ͻ��˷���socket
							cliSock[nextIndex] = newSock;
							//�¿ͻ��˵ĵ�ַ�Ѿ�����cliAddr[nextIndex]����
							//Ϊ�¿ͻ��˰��¼�����,ͬʱ���ü�����close��read��write �¼���WSAWaitForMultipleEventsʱ�õ������¼�
							WSAEVENT newEvent = WSACreateEvent();
							WSAEventSelect(cliSock[nextIndex], // �󶨵��¼������ϵ��׽���
								newEvent, // �����¼�����
								FD_CLOSE | FD_READ | FD_WRITE); // �������¼���������Ϣ����FD_XXX
							cliEvent[nextIndex] = newEvent;
							total++;//�ͻ�������������
							cout << "�û�[#" << nextIndex << "] ��IP��" << inet_ntoa(cliAddr[nextIndex].sin_addr) << ")�����������ң���ǰ��������" << total << endl;

							//�����пͻ��˷��ͻ�ӭ��Ϣ
							char buf[BUFFER_SIZE] = "[ϵͳ��Ϣ]��ӭ�û���IP��";
							strcat(buf, inet_ntoa(cliAddr[nextIndex].sin_addr));
							strcat(buf, ") [#");
							strcat(buf, to_string(nextIndex).c_str());
							strcat(buf, "] ���빫��������");
							for (int j = i; j <= total; j++) {

								send(cliSock[j], buf, sizeof(buf), 0);

							}
						}
					} else {
						//���Ӵﵽ���ޣ�����ʱ���ͻ��˷���һ�����ӣ�������ʾ��Ϣ���ٶϿ�
						SOCKET tempSock[1];//�ͻ����׽��� 0��Ϊ�����
						SOCKADDR_IN tempAddr[1];//�ͻ��˵�ַ
						int nextIndex=0;
						int addrLen = sizeof(SOCKADDR);
						//���ܿͻ��˵���������
						SOCKET newSock = accept(servSock, (SOCKADDR*)&tempAddr[nextIndex], &addrLen);
						if (newSock != INVALID_SOCKET) {
							//���÷��ͺͽ���ʱ��
							//��TCP�����У�recv�Ⱥ���Ĭ��Ϊ����ģʽ(block)����ֱ�������ݵ���֮ǰ�������᷵�أ���������ʱ����Ҫһ�ֳ�ʱ����ʹ����һ��ʱ��󷵻ض������Ƿ������ݵ������������Ǿͻ��õ�setsockopt()����
							setsockopt(newSock, SOL_SOCKET, SO_SNDTIMEO, (const char*) &SEND_TIMEOUT, sizeof(SEND_TIMEOUT));
							setsockopt(newSock, SOL_SOCKET, SO_RCVTIMEO, (const char*) &RECV_TIMEOUT, sizeof(RECV_TIMEOUT));
							
							//���¿ͻ��˷���socket
							tempSock[nextIndex] = newSock;
							
							//���¿ͻ��˷�����ʾ��Ϣ
							char buf[BUFFER_SIZE] = "[ϵͳ��Ϣ]�û���IP��";
							strcat(buf, inet_ntoa(tempAddr[nextIndex].sin_addr));
							strcat(buf, "����ã���������æ�����Ժ����ԣ�");
							send(tempSock[nextIndex], buf, sizeof(buf), 0);
						}
						//�ͷ�����ͻ��˵���Դ���ر�����
						closesocket(tempSock[nextIndex]);
						
						cout << "���Ӵﵽ���ޣ���ǰ��������"<< total <<" ��" << endl;
					}
				} else if (networkEvents.lNetworkEvents & FD_CLOSE) { //�ͻ��˱��رգ����Ͽ�����

					//i��ʾ�ѹرյĿͻ����±�
					total--;
					cout << "�û�[#" << i << "] ��IP��" << inet_ntoa(cliAddr[i].sin_addr) << ")�˳��������ң���ǰ��������" << total << endl;
					//�ͷ�����ͻ��˵���Դ
					closesocket(cliSock[i]);
					WSACloseEvent(cliEvent[i]);

					//�������,��˳���ɾ��Ԫ�أ�������ǰ��һ��
					for (int j = i; j < total; j++) {
						cliSock[j] = cliSock[j + 1];
						cliEvent[j] = cliEvent[j + 1];
						cliAddr[j] = cliAddr[j + 1];
					}
					//�����пͻ��˷����˳������ҵ���Ϣ
					char buf[BUFFER_SIZE] = "[ϵͳ��Ϣ]��IP��";
					strcat(buf, inet_ntoa(cliAddr[i].sin_addr));
					strcat(buf, ") [#");
					strcat(buf, to_string(i).c_str());
					strcat(buf, "] �˳��˹���������");
					for (int j = 1; j <= total; j++) {
						send(cliSock[j], buf, sizeof(buf), 0);
					}
				} else if (networkEvents.lNetworkEvents & FD_READ) { //���յ���Ϣ

					char buffer[BUFFER_SIZE] = { 0 };//�ַ������������ڽ��պͷ�����Ϣ
					char buffer2[BUFFER_SIZE] = { 0 };

					for (int j = 1; j <= total; j++) {
						int nrecv = recv(cliSock[j], buffer, sizeof(buffer), 0);//nrecv�ǽ��յ����ֽ���
						if (nrecv > 0) { //������յ����ַ�������0
							sprintf(buffer2, "[�û�#%d]%s", j, buffer);
							//�ڷ������ʾ
							cout << buffer2 << endl;
							//�������ͻ�����ʾ���㲥�������ͻ��ˣ�
							for (int k = 1; k <= total; k++) {
								send(cliSock[k], buffer2, sizeof(buffer), 0);
							}
						}
					}
				}
			}
		}
	}
	return 0;
}
