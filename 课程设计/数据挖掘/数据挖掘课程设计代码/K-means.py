import random
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# 获取数据集
def creatDataSet():
    df = pd.read_csv("data/data_KMeans.csv", encoding='utf-8')
    dataSet = df.values.tolist()
    return dataSet

def kmeans(dataSet, k, centroids):
    groups = [[] for _ in range(k)]   # 创建存放k组数据的二维列表
    print(centroids)  # 打印每次迭代的质心列表
    # 将各数据进行分组
    for data in dataSet:
        diff = np.tile(data, (k, 1)) - centroids   # 计算各点与参考点的距离
        absDist = np.sum(np.abs(diff), axis=1)  # 计算与各质点绝对值之和(axis=1表示行)的距离
        group_index = list(absDist).index(np.min(absDist))  # 所属于哪组的索引
        groups[group_index].append(data)  # 将该点添加到该组中去

    newCentroids = []   # 初始化新质心列表
    # 计算各组质心
    for group in groups:
        # 计算分组后的新的质心
        newCentroids.append(list(np.mean(group, axis=0)))  # axis=0表示列

    flag = (newCentroids == centroids)  # 判断新旧质心组是否改变

    if flag == False:  # 如果改变了递归kmeans函数，否则就返回该质心和分组
        return kmeans(dataSet, k, newCentroids)
    return newCentroids, groups

if __name__ == '__main__':
    dataSet = creatDataSet()  # 获取数据集

    k = 3  # 分组数量
    # 随机版本
    centroids = random.sample(dataSet, k)  # 随机取k个点作为初始点

    # 指定版本
    # centroids = [[1, 2], [2, 1], [2, 4]]

    finalCentroids, groups = kmeans(dataSet, k, centroids)
    print('----------------结果----------------')

    color = ['red', 'dodgerblue', 'blueviolet', 'lime', 'fuchsia', 'orange', 'aqua', 'sienna', 'yellow', 'gray']
    for i in range(len(groups)):
        list_scatter = list(groups[i])
        centroid = list(map(lambda x: round(x, 3), finalCentroids[i]))
        print(f'Cluster【{i + 1}】={groups[i]}')
        print(f'Centroid【{i + 1}】={centroid}')
        print('-----------------------------------')
        for scatter in list_scatter:
            plt.scatter(scatter[0], scatter[1], c=color[i])
        plt.scatter(centroid[0], centroid[1], c=color[i], marker='D')

    plt.xlabel('attribute-1')
    plt.ylabel('attribute-2')
    plt.title('K-means')
    plt.show()
