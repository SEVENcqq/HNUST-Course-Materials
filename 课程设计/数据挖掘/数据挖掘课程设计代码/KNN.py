import numpy as np
import pandas as pd

# 给出训练数据以及对应的类别
def createDataSet():
    df = pd.read_csv("data/data_KNN.csv", encoding='utf-8')
    # 对列表进行切片，每行的除最后一列，均是用于计算的数据
    dataSet = df.values[:, :-1]
    # 最后一列为每条数据所属的类别
    labels = df.values[:, -1]
    return dataSet, labels

# 通过KNN进行分类
def KNN(input_data, dataSet, labels, k):

    # 计算身高差值，原理：通过调用函数让身高列表依次与给定身高进行相减，所得结果转为绝对值并保留两位小数
    list_height = np.round(list(map(abs, map(lambda x: x-input_data, dataSet[:, 1]))), 2)
    # np.argsort()根据元素的值进行降序排序并返回下标值
    sorted_index = np.argsort(list_height)

    # 定义一个字典用于存放各类型数量
    label_count = dict()
    # 只对前k个数据的类别数量进行统计
    for i in range(k):
        # 通过得出排序后的下标去映射其相应的类别
        level = labels[sorted_index[i]]
        # 对选取的K个数据所属的类别个数进行统计
        # 对于dict中的get函数，如果能取到数据则返回字典中存储的value，否则返回一个默认值，这里则是因为之前未记录所以返回0次
        label_count[level] = label_count.get(level, 0) + 1

    # 选取出现次数最多的类别
    max_count = 0
    result_level = ''
    for key, value in label_count.items():
        if value > max_count:
            max_count = value
            result_level = key

    return result_level

if __name__ == '__main__':

    # 获取数据集
    dataSet, labels = createDataSet()
    # 输入测试数据
    input_data = np.array(['易昌', 1.74])
    # 定义k值
    k = 5
    # 实现KNN算法
    output_data = KNN(float(input_data[1]), dataSet, labels, k)
    # 输出结果
    print(f"新同学【{input_data[0]}】的身高等级为【{output_data}】")
