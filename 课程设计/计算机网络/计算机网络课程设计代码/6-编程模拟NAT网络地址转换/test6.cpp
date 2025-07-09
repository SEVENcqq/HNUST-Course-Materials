#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

// �����ַӳ������Ŀ�ṹ
struct MappingEntry {
    string oldIpPort;  // ԭʼ��ַ�Ͷ˿�
    string newIpPort;  // ת����ĵ�ַ�Ͷ˿�
};

// NAT ·������
class NatRouter {
public:
    // ����������������ı��ģ����е�ַת��
    void translateOutgoing(string& sourceIpPort) {
        // ���ӳ�����û�и���Ŀ�������µ�ӳ�䲢���ת����Ϣ
        if (outgoingMappings.find(sourceIpPort) == outgoingMappings.end()) {
        	
        	// ��������������ӳ����д洢��Ӧ������ 
            string newIpPort = assignNewIpPort();
            MappingEntry entry_in = {sourceIpPort, newIpPort};
            outgoingMappings[sourceIpPort] = entry_in;
            
            // ��������������ӳ����д洢��Ӧ������ 
            MappingEntry entry_out = {newIpPort, sourceIpPort};
            incomingMappings[newIpPort] = entry_out;

            cout << "����ӳ��: " << sourceIpPort << " -> " << newIpPort << endl;
        }

        // ִ�е�ַת��
        sourceIpPort = outgoingMappings[sourceIpPort].newIpPort;
    }

    // ����������������ı��ģ����е�ַת����ԭ
    void translateIncoming(string& destinationIpPort) {
        // ���ӳ�����û�и���Ŀ�����������Ϣ
        if (incomingMappings.find(destinationIpPort) == incomingMappings.end()) {
            cerr << "����: δ�ҵ�����ӳ����е���Ŀ��" << endl;
            return;
        }
		
		cout << "����ӳ��: " << destinationIpPort << " -> " << incomingMappings[destinationIpPort].newIpPort << endl;
        // ִ�е�ַ��ԭ
        destinationIpPort = incomingMappings[destinationIpPort].newIpPort;

        // ��ѡ����ӳ�����ɾ������Ŀ��NAT ����״̬����Ϊ��
        incomingMappings.erase(destinationIpPort);
    }

private:
    unordered_map<string, MappingEntry> outgoingMappings;  // ������������ӳ���
    unordered_map<string, MappingEntry> incomingMappings;  // ������������ӳ���

    // �����µ�������ַ�Ͷ˿�
    string assignNewIpPort() {
        // ����һ���򻯵����ӣ���ʵ�ʳ�������Ҫ���ӽ����ع��� IP �Ͷ˿ڵķ���
        static int portCounter = 40000;
        return "172.38.1.5:" + to_string(++portCounter);
    }
};

int main() {
    // ���� NAT ·����ʵ��
    NatRouter natRouter;

	//	sourceIpPort1 = "192.168.0.3:30000"
	//	sourceIpPort2 = "192.168.0.4:30000"
	//	destinationIpPort1 = "172.38.1.5:40001"
	//	destinationIpPort2 = "172.38.1.5:40002"

	// �������IP��ַ 
	string sourceIpPort;
	cout << "���������IP��ַ������\"quit\"ֹͣ����" << endl;

    while (true) {
        cout << "> ";
        getline(cin, sourceIpPort);  

        if (sourceIpPort == "quit") {
            cout << "���������" << endl;
            break;
        }

		// ģ�������������ı���ת��
        natRouter.translateOutgoing(sourceIpPort);
    	cout << "ת����ĳ�����: " << sourceIpPort << endl;
    }
    
    cout << endl;
    // ��������IP��ַ
    string destinationIpPort;
	cout << "����������IP��ַ������\"quit\"ֹͣ����" << endl;

    while (true) {
        cout << "> ";
        getline(cin, destinationIpPort);  

        if (destinationIpPort == "quit") {
            cout << "���������" << endl;
            break;
        }

		// ģ�������������ı���ת��
        natRouter.translateIncoming(destinationIpPort);
    	cout << "ת�����������: " << destinationIpPort << endl;
    }

    return 0;
}

