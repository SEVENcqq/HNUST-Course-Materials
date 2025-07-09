#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;

struct edge {
	int start;//���
	int end;//�յ�
	int distance;//���� (��Ȩ)
	edge(int s, int e, int d) :start(s), end(e), distance(d) {}
};

void init(vector<edge>& edges, vector<vector<int>>& inf);
void Dijkstra(vector<vector<int>>& inf, int fir, int& n);
void findPath(vector<pair<int, int>> path, vector<vector<int>>& inf, int fir, int& n);
//��ʼ���ڽӾ���
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


void Dijkstra(vector<vector<int>>& inf, int fir, int& n) {//��� fir �㵽������������·��
	unordered_set<int> result;//���뵽������ĵ�
	vector<pair<int, int>> path(n, make_pair(fir, INT_MAX / 2));//·������
	int cur = fir;//��ǰ��Ѱ�����·���ĵ� fir - > cur 
	path[cur] = make_pair(fir, 0);//���Լ��ľ���Ϊ0
	result.insert(cur);
	
	for (int i = 1; i < n; i++) {//ʣ�µ�n - 1���㶼��Ҫ�ó����·��(��һ���㲻��ҪѰ��)
		//ѡ��ǰ״̬�����·���ĵ���Ϊ��չ�ڵ�
		for (int j = 0; j < n; j++) {
			if (inf[cur][j] != 0 && result.find(j) == result.end()) {
				//cur �� j ����·�� �� �õ㻹δ�ҵ�����·�� : ���ڽ������ ��ʱ���������j��·������
				int tmp = inf[cur][j] + path[cur].second;
				if (tmp < path[j].second) {
					path[j].second = tmp;
					path[j].first = cur;
				}
			}
		}

		int minimum = INT_MAX;
		int next = -1;//����Ѱ����һ�ε���չ�ڵ�
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
			curPath.push_back(cur);//��֮ǰ�ĵ���뵽·��������
			if (cur == fir)
				break;
			cur = path[cur].first;//������ǰѰ��
		}
		cout << "\n��" << char(65 + fir) << "��" << char(65 + i) << "�����·��Ϊ��";
		for (int j = curPath.size() - 1; j >= 1; j--) {
			cout << char(curPath[j] + 65) << " -> ";
		}
        cout << char(curPath[0] + 65);
		cout << endl << "����Ϊ" << path[i].second << endl;
	}
}

int main() {
	int n = 6;
	vector<vector<int>> inf(n, vector<int>(n, 0));//�ٽ����
	vector<edge> edges;//�洢ͼ�и��ߵ���Ϣ
	init(edges, inf);
    for (int i = 0; i < n; i++) {
        Dijkstra(inf, i, n);
        cout << "----------------------------------------";
    }
	
	return 0;
}

