import numpy as np
import pandas as pd

# 读取文件
def creatDataSet():
    df = pd.read_csv("data/data_PageRank.csv", encoding='utf-8')
    dataSet = df.values
    return dataSet


def pageRank(A, d):  # d为点击概率
    # shape[0]是行数，shape[1]是列数
    R0 = np.ones(A.shape[1])  # R0为初始等级值向量
    Q = np.ones((A.shape[0], A.shape[1])) / A.shape[0]  # Q为常量矩阵
    M = np.dot((1 - d), Q) + np.dot(d, A)  # M为转移概率矩阵

    iter_term_condition = 0.1  # 迭代终止条件
    iter_condition = float('inf')  # 两向量逐分量和，初始化为无穷大
    R2 = R0
    # 直到值小于等于迭代终止条件时结束迭代
    while (iter_condition > iter_term_condition):
        R1 = R2
        R2 = np.dot(M, R1)
        iter_condition = np.sum(np.fabs(R2 - R1))
    # 这里因为课本上是一开始就进行了保留两位小数的操作，所以精确度不高，结果有差别，该方法的处理是最后进行两位小数的保留操作
    R2 = np.round(R2, decimals=2)  # 保存两位小数

    return R2


if __name__ == "__main__":

    # 获取数据集，此时为有向图的表示，即数据集中存放的是有向图的表示，只不过由原先的正向链转为了反向链
    dataSet = creatDataSet()
    column_ones_count = np.sum(dataSet, axis=0)  # 计算每列1的个数，方便计算邻接矩阵
    A = np.array(dataSet/column_ones_count)  # A为邻接矩阵

    result = pageRank(A, 0.85)
    print('-------------------结果-------------------')
    for i in range(len(result)):
        print(F"{chr(65+i)}的等级值:{result[i]}")
