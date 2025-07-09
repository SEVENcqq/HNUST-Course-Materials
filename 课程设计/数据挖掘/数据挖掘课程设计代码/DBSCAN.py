import matplotlib.pyplot as plt
import random
import numpy as np
import math
import pandas as pd
from sklearn import datasets

# 读取文件
def creatDataSet():
    df = pd.read_csv("data/data_DBSCAN.csv", encoding='utf-8')
    dataSet = df.values.tolist()
    return dataSet

# 计算两个点之间的欧式距离，参数为两个元组
def dist(t1, t2):
    # dis = math.sqrt((np.power((t1[0] - t2[0]), 2) + np.power((t1[1] - t2[1]), 2)))  # 欧氏距离
    dis = math.fabs(t1[0] - t2[0]) + math.fabs(t1[1] - t2[1])  # 绝对值距离
    return dis


# DBSCAN算法，参数为数据集，Eps为指定半径参数，MinPts为指定邻域密度阈值
def dbscan(Data, Eps, MinPts):
    num = len(Data)  # 点的个数
    unvisited = [i for i in range(num)]  # 没有访问到的点的列表
    visited = []  # 已经访问的点的列表
    C = [-1 for i in range(num)]
    # C为输出结果，默认是一个长度为num的值全为-1的列表
    # 用k来标记不同的簇，k = -1表示噪声点
    k = -1
    # 如果还有没访问的点
    while len(unvisited) > 0:
        # 随机选择一个unvisited对象
        p = random.choice(unvisited)
        unvisited.remove(p)
        visited.append(p)
        # N为p的epsilon邻域中的对象的集合
        N = []
        for i in range(num):
            if (dist(Data[i], Data[p]) <= Eps):  # and (i!=p):
                N.append(i)
        # 如果p的epsilon邻域中的对象数大于指定阈值，说明p是一个核心对象
        if len(N) >= MinPts:
            k = k + 1
            C[p] = k
            # 对于p的epsilon邻域中的每个对象pi
            for pi in N:
                if pi in unvisited:
                    unvisited.remove(pi)
                    visited.append(pi)
                    # 找到pi的邻域中的核心对象，将这些对象放入N中
                    # M是位于pi的邻域中的点的列表
                    M = []
                    for j in range(num):
                        if (dist(Data[j], Data[pi]) <= Eps):  # and (j!=pi):
                            M.append(j)
                    if len(M) >= MinPts:
                        for t in M:
                            if t not in N:
                                N.append(t)
                # 若pi不属于任何簇，C[pi] == -1说明C中第pi个值没有改动
                if C[pi] == -1:
                    C[pi] = k
        # 如果p的epsilon邻域中的对象数小于指定阈值，说明p是一个噪声点
        else:
            C[p] = -1

    return C


if __name__ == "__main__":
    # 获取数据集
    dataSet = creatDataSet()
    C = dbscan(dataSet, 2, 3)  # 类相同的值一样
    # print(C)  # 打印分类结果
    # 分簇情况
    groups = set()
    for item in C:
        groups.add(item)
    groups = list(groups)
    clusters = [[] for i in range(len(groups))]
    for i in range(len(groups)):
        for j in range(len(C)):
            if groups[i] == C[j]:
                clusters[i].append(j+1)
    print('-------------------结果-------------------')

    color = ['red', 'dodgerblue', 'lime', 'blueviolet', 'fuchsia', 'orange', 'aqua', 'sienna', 'yellow', 'gray']
    for i in range(len(clusters)-1):
        list_scatter = list(clusters[i])
        print(f'Cluster【{i + 1}】 = {clusters[i]}')
        for scatter in list_scatter:
            plt.scatter(dataSet[scatter-1][0], dataSet[scatter-1][1], c=color[i])
    list_noise = list(clusters[-1])
    print(f'Noise = {list_noise}')
    for scatter in list_noise:
        plt.scatter(dataSet[scatter - 1][0], dataSet[scatter - 1][1], c=color[-1], marker='x')

    plt.xlabel('x-coordinate')
    plt.ylabel('y-coordinate')
    plt.title('DBSCAN')
    plt.show()


