#include <iostream>
#include <unordered_map>
#include <bitset>
#include <sstream>
#include <vector>

using namespace std;

unordered_map<string, string> ipRoutingTable = {
    {"192.4.153.0/26", "R3"},
    {"128.96.39.0/25", "�ӿ�m0"},
    {"128.96.39.128/25", "�ӿ�m1"},
    {"128.96.40.0/25", "R2"},
};

bitset<32> convertToBinary(const string& ipAddress) {
	istringstream iss(ipAddress);
    vector<string> octets;
	
	// ��ip��ַ����"."���в�ֲ�ת��Ϊ32λ�������� 
    string octet;
    while (getline(iss, octet, '.')) {
        octets.push_back(octet);
    }

    // ���ڴ洢ƴ�Ӻ�Ķ�������
    bitset<32> combinedBinary;

    // ��ÿ������ת��Ϊ8λ����������ƴ��
    for (const auto& part : octets) {
        bitset<32> binaryPart(stoi(part));
        combinedBinary <<= 8;  // ����8λ
        combinedBinary |= binaryPart;
    }
    
    return combinedBinary;
}

string findNextHop(const string& destAddress) {
	// �Ƚ�������Ŀ�ĵ�ַת��Ϊ32λ�������� 
    bitset<32> destAddrBits = convertToBinary(destAddress);
	
	// ͨ������·�ɱ���������һ����·�ɱ��д�ŵ�����Ҫ�ȸ���"/"���л��֣���ǰ�벿��ת��Ϊ32λ������������벿����ת���������һ������
	// Ȼ���ж����������Ƿ���ȣ������ȡ��Ӧ����һ�� 
    for (const auto& entry : ipRoutingTable) {
		// ���ҵ�һ��"/"
	    size_t pos = entry.first.find('/');
	    string routingEntry = entry.first.substr(0, pos);
	    int mask = stoi(entry.first.substr(pos + 1));
	    	
      	bitset<32> routingEntryBits = convertToBinary(routingEntry);
      	
      	if (destAddrBits.to_string().substr(0, mask) == routingEntryBits.to_string().substr(0, mask)) {
      		return entry.second;		      		
		}	
    }
    
    // Ĭ���� 
	return "R4";

}

int main() {

	// ����Ŀ��IP��ַ
    string destAddress;
	cout << "������Ŀ�ĵ�ַ������\"quit\"ֹͣ����" << endl;

    while (true) {
        cout << "> ";
        getline(cin, destAddress);  

        if (destAddress == "quit") {
            cout << "���������" << endl;
            break;
        }

        string nextHop = findNextHop(destAddress);
    	cout << "��һ����" << nextHop << endl;
    }

    return 0;
}

//192.168.0.1
//128.96.39.127
//128.96.39.129
//128.96.40.1
//192.4.153.3


