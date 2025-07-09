import math
import pandas as pd
import numpy as np

# 获取数据集
def creatDataSet():
    df = pd.read_csv("data/data_AGNES.csv", encoding='utf-8')
    dataSet = df.values.tolist()
    return dataSet

# 计算簇之间的欧几里得距离
def dist(x, y):
	return math.sqrt(math.pow(x[0] - y[0], 2) + math.pow(x[1] - y[1], 2))

def dist_min(x, y):
	return min(dist(i, j) for i in x for j in y)

def dist_max(x, y):
	return max(dist(i, j) for i in x for j in y)

def dist_avg(x, y):
	return sum(dist(i, j) for i in x for j in y) / (len(x) * len(y))

# 找到距离最小的索引，即最小距离对应的簇的索引
def findMin(distance_clusters):
	# 最初的最小距离
	minDistance = math.inf
	# 初始化距离最小位置的索引，cluster_i_index为簇i的索引，cluster_j_index为簇j的索引
	cluster_i_index = 0
	cluster_j_index = 0
	for i in range(len(distance_clusters)):
		for j in range(len(distance_clusters[i])):
			if i != j and distance_clusters[i][j] < minDistance:
				minDistance = distance_clusters[i][j]
				cluster_i_index = i
				cluster_j_index = j
	return cluster_i_index, cluster_j_index, minDistance

def agnes(dataSet, dist, k):
	clusters = []  # 初始簇
	distance_clusters = []
	# 第一遍遍历，将所有数据单独自成一个簇
	for data in dataSet:
		clusters.append([data])

	# 获取各簇之间的距离矩阵
	for cluster_i in clusters:
		# 初始化单个簇的距离列表
		distance_cluster = []
		# 计算单个簇与其他簇的距离，并存储起来
		for cluster_j in clusters:
			distance_cluster.append(dist(cluster_i, cluster_j))
			distance_cluster.append(np.linalg.norm(np.array(cluster_i) - np.array(cluster_j)))
		# 将计算后的结果保存在所有簇距离列表中
		distance_clusters.append(distance_cluster)

	count = len(dataSet)
	# 合并簇，如果大于k说明分组数还没达到要求，直至目标分组数再结束分组
	while count > k:
		# 找出距离最近的两个簇
		cluster_i_index, cluster_j_index, minDistance = findMin(distance_clusters)
		# 将簇j添加到簇i的列表中，并删除簇j的记录，相当于簇的数量-1
		clusters[cluster_i_index].extend(clusters[cluster_j_index])
		clusters.remove(clusters[cluster_j_index])

		# 获取新簇组之间的距离矩阵
		distance_clusters = []
		for cluster_i in clusters:
			distance_cluster = []
			for cluster_j in clusters:
				distance_cluster.append(dist(cluster_i, cluster_j))
			distance_clusters.append(distance_cluster)
		count -= 1
	return clusters

if __name__ == "__main__":
	# 分类数
	k = 2
	# 获取数据集
	dataSet = creatDataSet()
	# 进行AGNES算法，获取簇
	clusters = agnes(dataSet, dist_max, k)
	print('-------------------结果-------------------')
	for i in range(len(clusters)):
		print(f'分组{i + 1}={clusters[i]}')
