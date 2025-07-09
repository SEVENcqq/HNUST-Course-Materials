import numpy as np
import math
import pandas as pd

# 读取文件
def creatDataSet():
    df = pd.read_csv("data/data_DIANA.csv", encoding='utf-8')
    dataSet = df.values
    return dataSet


# 寻找直径最大的簇
def diameterMax(cls):
    # 计算簇的直径
    def diameter(each):
        max_d = 0
        for x in each:
            for y in each:
                if (x == y).all(): continue
                d = math.sqrt((x - y) @ (x - y).T)
                if d > max_d:
                    max_d = d
        return max_d

    index = -1
    maxs = 0
    for i in range(0, len(cls)):
        dia = diameter(cls[i])
        if dia > maxs:
            maxs = dia
            index = i

    return [cls[index], index]


# 计算某个样本与集合的平均相异度
def distinct(x, C):
    totals = 0.0
    for c in C:
        totals += math.sqrt((x - c) @ (x - c).T)
    return totals / C.shape[0]


def diana(data, k):
    m, n = data.shape
    # 初始化所有样本为一个簇
    cls = [data]
    # 记录当前簇的个数
    q = 1
    # 分裂到指定簇数结束
    while q < k:
        # 找到直径最大的簇及其位置，从最大直径的簇进行分簇
        C, index = diameterMax(cls)
        # 找到平均相异度最大的点作为新的簇一个样本
        max_val = 0
        j = -1
        for i in range(0, C.shape[0]):
            diff = distinct(C[i], np.delete(C, i, axis=0))
            if max_val < diff:
                j = i
                max_val = diff
        # 初始化分裂后的两个集合
        cls_new = C[j].reshape((1, n))
        cls_old = np.delete(C, j, axis=0)
        # new和old集合不再变动结束
        while True:
            count = 0  # 标记集合是否更新过
            for i in range(0, cls_old.shape[0]):
                l = distinct(cls_old[i], cls_new)
                r = distinct(cls_old[i], np.delete(cls_old, i, axis=0))
                # 若old集合的样本到new的最短距离比到自身的最短距离还要小
                if l < r:
                    count += 1
                    # 更新old和new
                    cls_new = np.concatenate((cls_new, [cls_old[i]]), axis=0)
                    cls_old = np.delete(cls_old, i, axis=0)
                    break

            if count == 0: break
        # 一分为二，弹出最大直径的簇，添加划分后的簇
        cls.pop(index)
        cls.append(cls_new)
        cls.append(cls_old)
        q += 1
    return cls

if __name__ == "__main__":
    # 获取数据集
    dataSet = creatDataSet()
    # k=2
    clusters = diana(dataSet, 2)
    print('-------------------结果-------------------')
    for i in range(len(clusters)):
        print(f'分组{i + 1}={clusters[i].tolist()}')

