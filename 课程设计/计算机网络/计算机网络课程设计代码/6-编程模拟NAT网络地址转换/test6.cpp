#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

// 定义地址映射表的条目结构
struct MappingEntry {
    string oldIpPort;  // 原始地址和端口
    string newIpPort;  // 转换后的地址和端口
};

// NAT 路由器类
class NatRouter {
public:
    // 处理从内网到外网的报文，进行地址转换
    void translateOutgoing(string& sourceIpPort) {
        // 如果映射表中没有该条目，创建新的映射并输出转换信息
        if (outgoingMappings.find(sourceIpPort) == outgoingMappings.end()) {
        	
        	// 在内网到外网的映射表中存储相应的数据 
            string newIpPort = assignNewIpPort();
            MappingEntry entry_in = {sourceIpPort, newIpPort};
            outgoingMappings[sourceIpPort] = entry_in;
            
            // 在外网到内网的映射表中存储相应的数据 
            MappingEntry entry_out = {newIpPort, sourceIpPort};
            incomingMappings[newIpPort] = entry_out;

            cout << "出向映射: " << sourceIpPort << " -> " << newIpPort << endl;
        }

        // 执行地址转换
        sourceIpPort = outgoingMappings[sourceIpPort].newIpPort;
    }

    // 处理从外网到内网的报文，进行地址转换还原
    void translateIncoming(string& destinationIpPort) {
        // 如果映射表中没有该条目，输出错误信息
        if (incomingMappings.find(destinationIpPort) == incomingMappings.end()) {
            cerr << "错误: 未找到入向映射表中的条目。" << endl;
            return;
        }
		
		cout << "入向映射: " << destinationIpPort << " -> " << incomingMappings[destinationIpPort].newIpPort << endl;
        // 执行地址还原
        destinationIpPort = incomingMappings[destinationIpPort].newIpPort;

        // 可选：从映射表中删除该条目（NAT 具有状态的行为）
        incomingMappings.erase(destinationIpPort);
    }

private:
    unordered_map<string, MappingEntry> outgoingMappings;  // 内网到外网的映射表
    unordered_map<string, MappingEntry> incomingMappings;  // 外网到内网的映射表

    // 分配新的内网地址和端口
    string assignNewIpPort() {
        // 这是一个简化的例子；在实际场景中需要更加谨慎地管理 IP 和端口的分配
        static int portCounter = 40000;
        return "172.38.1.5:" + to_string(++portCounter);
    }
};

int main() {
    // 创建 NAT 路由器实例
    NatRouter natRouter;

	//	sourceIpPort1 = "192.168.0.3:30000"
	//	sourceIpPort2 = "192.168.0.4:30000"
	//	destinationIpPort1 = "172.38.1.5:40001"
	//	destinationIpPort2 = "172.38.1.5:40002"

	// 输入出向IP地址 
	string sourceIpPort;
	cout << "请输入出向IP地址（输入\"quit\"停止）：" << endl;

    while (true) {
        cout << "> ";
        getline(cin, sourceIpPort);  

        if (sourceIpPort == "quit") {
            cout << "输入结束。" << endl;
            break;
        }

		// 模拟内网到外网的报文转发
        natRouter.translateOutgoing(sourceIpPort);
    	cout << "转换后的出向报文: " << sourceIpPort << endl;
    }
    
    cout << endl;
    // 输入入向IP地址
    string destinationIpPort;
	cout << "请输入入向IP地址（输入\"quit\"停止）：" << endl;

    while (true) {
        cout << "> ";
        getline(cin, destinationIpPort);  

        if (destinationIpPort == "quit") {
            cout << "输入结束。" << endl;
            break;
        }

		// 模拟外网到内网的报文转发
        natRouter.translateIncoming(destinationIpPort);
    	cout << "转换后的入向报文: " << destinationIpPort << endl;
    }

    return 0;
}

