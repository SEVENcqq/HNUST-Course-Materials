#include <iostream>
#include <unordered_map>
#include <bitset>
#include <sstream>
#include <vector>

using namespace std;

unordered_map<string, string> ipRoutingTable = {
    {"192.4.153.0/26", "R3"},
    {"128.96.39.0/25", "接口m0"},
    {"128.96.39.128/25", "接口m1"},
    {"128.96.40.0/25", "R2"},
};

bitset<32> convertToBinary(const string& ipAddress) {
	istringstream iss(ipAddress);
    vector<string> octets;
	
	// 将ip地址根据"."进行拆分并转换为32位二进制数 
    string octet;
    while (getline(iss, octet, '.')) {
        octets.push_back(octet);
    }

    // 用于存储拼接后的二进制数
    bitset<32> combinedBinary;

    // 将每个部分转换为8位二进制数并拼接
    for (const auto& part : octets) {
        bitset<32> binaryPart(stoi(part));
        combinedBinary <<= 8;  // 左移8位
        combinedBinary |= binaryPart;
    }
    
    return combinedBinary;
}

string findNextHop(const string& destAddress) {
	// 先将给出的目的地址转换为32位二进制数 
    bitset<32> destAddrBits = convertToBinary(destAddress);
	
	// 通过遍历路由表，查找其下一跳；路由表中存放的数据要先根据"/"进行划分，将前半部分转换为32位二进制数，后半部分则将转换后的数再一步划分
	// 然后判断这两个数是否相等，相等则取对应的下一跳 
    for (const auto& entry : ipRoutingTable) {
		// 查找第一个"/"
	    size_t pos = entry.first.find('/');
	    string routingEntry = entry.first.substr(0, pos);
	    int mask = stoi(entry.first.substr(pos + 1));
	    	
      	bitset<32> routingEntryBits = convertToBinary(routingEntry);
      	
      	if (destAddrBits.to_string().substr(0, mask) == routingEntryBits.to_string().substr(0, mask)) {
      		return entry.second;		      		
		}	
    }
    
    // 默认项 
	return "R4";

}

int main() {

	// 输入目的IP地址
    string destAddress;
	cout << "请输入目的地址（输入\"quit\"停止）：" << endl;

    while (true) {
        cout << "> ";
        getline(cin, destAddress);  

        if (destAddress == "quit") {
            cout << "输入结束。" << endl;
            break;
        }

        string nextHop = findNextHop(destAddress);
    	cout << "下一跳：" << nextHop << endl;
    }

    return 0;
}

//192.168.0.1
//128.96.39.127
//128.96.39.129
//128.96.40.1
//192.4.153.3


