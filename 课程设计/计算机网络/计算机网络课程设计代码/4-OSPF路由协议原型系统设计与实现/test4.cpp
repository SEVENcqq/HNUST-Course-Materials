#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;

struct edge {
	int start;//起点
	int end;//终点
	int distance;//距离 (边权)
	edge(int s, int e, int d) :start(s), end(e), distance(d) {}
};

void init(vector<edge>& edges, vector<vector<int>>& inf);
void Dijkstra(vector<vector<int>>& inf, int fir, int& n);
void findPath(vector<pair<int, int>> path, vector<vector<int>>& inf, int fir, int& n);
//初始化邻接矩阵
void init(vector<edge>& edges, vector<vector<int>>& inf) {
	edges.push_back(edge(0, 1, 4));
	edges.push_back(edge(0, 4, 5));
	edges.push_back(edge(1, 0, 4));
	edges.push_back(edge(1, 2, 2));
	edges.push_back(edge(1, 5, 6));
	edges.push_back(edge(2, 1, 2));
	edges.push_back(edge(2, 3, 3));
	edges.push_back(edge(2, 4, 1));
	edges.push_back(edge(3, 2, 3));
	edges.push_back(edge(3, 5, 7));
	edges.push_back(edge(4, 0, 5));
	edges.push_back(edge(4, 2, 1));
    edges.push_back(edge(4, 5, 8));
	edges.push_back(edge(5, 1, 6));
	edges.push_back(edge(5, 3, 7));
	edges.push_back(edge(5, 4, 8));
	for (int i = 0; i < edges.size(); i++) {
		inf[edges[i].start][edges[i].end] = edges[i].distance;
		inf[edges[i].end][edges[i].start] = edges[i].distance;
	}
}


void Dijkstra(vector<vector<int>>& inf, int fir, int& n) {//求解 fir 点到其他各点的最短路径
	unordered_set<int> result;//加入到结果集的点
	vector<pair<int, int>> path(n, make_pair(fir, INT_MAX / 2));//路径数组
	int cur = fir;//当前正寻找最短路径的点 fir - > cur 
	path[cur] = make_pair(fir, 0);//到自己的距离为0
	result.insert(cur);
	
	for (int i = 1; i < n; i++) {//剩下的n - 1个点都需要得出最短路径(第一个点不需要寻找)
		//选择当前状态下最短路径的点作为拓展节点
		for (int j = 0; j < n; j++) {
			if (inf[cur][j] != 0 && result.find(j) == result.end()) {
				//cur 与 j 存在路径 且 该点还未找到最优路径 : 不在结果集中 此时视情况更新j的路径距离
				int tmp = inf[cur][j] + path[cur].second;
				if (tmp < path[j].second) {
					path[j].second = tmp;
					path[j].first = cur;
				}
			}
		}

		int minimum = INT_MAX;
		int next = -1;//用于寻找下一次的拓展节点
		for (int k = 0; k < n; k++) {
			if (result.find(k) == result.end()) {
				if (path[k].second < minimum) {
					minimum = path[k].second;
					next = k;
				}
			}
		}
		cur = next;
		result.insert(cur);
	}
	findPath(path, inf, fir, n);
}

void findPath(vector<pair<int, int>> path,vector<vector<int>>& inf, int fir, int& n) {
	for (int i = 0; i < n; i++) {
		if (i == fir) continue;
		vector<int> curPath;
		int cur = i;
		while (1) {
			curPath.push_back(cur);//将之前的点加入到路径数组中
			if (cur == fir)
				break;
			cur = path[cur].first;//继续向前寻找
		}
		cout << "\n从" << char(65 + fir) << "到" << char(65 + i) << "的最短路径为：";
		for (int j = curPath.size() - 1; j >= 1; j--) {
			cout << char(curPath[j] + 65) << " -> ";
		}
        cout << char(curPath[0] + 65);
		cout << endl << "距离为" << path[i].second << endl;
	}
}

int main() {
	int n = 6;
	vector<vector<int>> inf(n, vector<int>(n, 0));//临界矩阵
	vector<edge> edges;//存储图中各边的信息
	init(edges, inf);
    for (int i = 0; i < n; i++) {
        Dijkstra(inf, i, n);
        cout << "----------------------------------------";
    }
	
	return 0;
}

