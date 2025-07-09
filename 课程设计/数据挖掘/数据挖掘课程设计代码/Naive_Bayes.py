import pandas as pd
import numpy as np

# 通过csv文件读取数据集
def creatDataSet():
    df = pd.read_csv("data/data_NaiveBayes.csv", encoding='utf-8')
    dataSet = df.values.tolist()  # 数据集
    return dataSet

# 无条件下计算结果标签的数量
def calcLabelCount(dataSet):
    label_count = {}  # 保存每个标签（label）出现次数的字典
    # 统计各标签出现的次数
    for i in dataSet:
        label = i[-1]  # 提取标签信息
        if label not in label_count.keys():  # 如果标签没有放入统计次数的字典，添加进去
            label_count[label] = 0
        label_count[label] += 1  # 否则的话相应的label进行计数，即+1
    return label_count

# 无条件下计算结果标签的概率
def calcLabelProb(label_count):
    sum = np.array(list(label_count.values())).sum()  # 统计结果标签的总数
    label_prob = {}  # 保存每个标签对应的概率
    for key in label_count.keys():
        prob = float(label_count[key]) / sum  # 计算结果标签的概率，出现次数/标签总数
        label_prob[key] = prob
    return label_prob

# 根据结果标签值进行数据集划分
def splitDataSet(dataSet, value):
    # 创建返回的数据集列表
    split_dataSet = []
    # 遍历数据集，选取符合结果的数据
    for data in dataSet:
        if data[-1] == value:
            split_dataSet.append(data)
    return split_dataSet

# 计算条件概率，在筛选后的子集中选择符合给定条件列表数据，进行概率计算
def calcCondProb(dataSet, list_condition, label_count):  # list_condition是测试数据的条件列表，label_count是标签数量
    print('--------------条件概率数据-----------------')
    dict_condProb = {}  # 用于存放条件概率的字典
    # 通过遍历标签次数字典，获取对应条件的概率
    for label, count in label_count.items():
        subDataSet = splitDataSet(dataSet, label)  # 获得分割后的数据集子集，例如，label为yes，则splitDataSet里面就是标签为yes的数据
        # 遍历给定的条件列表，得出条件概率
        for i in range(len(list_condition)):
            # 通过numpy将subDataSet转为数组，获取第i列，即给定条件对应的列，然后获取该条件的数据数量
            conditionCount = list(np.array(subDataSet)[:, i]).count(list_condition[i])
            condProb = conditionCount/count  # 计算在标签结果下的该条件的条件概率
            string_condition = list_condition[i] + "|" + label  # 用于辨识条件概率的键值，格式为“xxx|xxx”
            print("P(%s)=%.2f" % (string_condition, condProb))
            dict_condProb[string_condition] = condProb
    return dict_condProb

# 对测试数据进行分类以及预测
def classifyAndPredict(list_condition):
    dataSet = creatDataSet()  # 创建数据集
    label_count = calcLabelCount(dataSet)   # 计算结果标签数量
    print(f'各标签结果对应数量={label_count}')
    dict_condProb = calcCondProb(dataSet, list_condition, label_count)  # 计算条件概率
    dict_labelProb = calcLabelProb(label_count)  # 计算标签概率
    predictLabel = "无"
    maxProb = 0.0
    # 通过遍历累乘得出最佳分类标签以及其对应的概率。
    for label, labelProb in dict_labelProb.items():
        resultProb = labelProb
        for condition, condProb in dict_condProb.items():
            if label in condition[-3:]:
                resultProb *= condProb
        if resultProb > maxProb:
            maxProb = resultProb
            predictLabel = label
    return maxProb, predictLabel

if __name__ == '__main__':
    # 测试数据
    list_condition = ['young', 'medium', 'yes', 'fair']
    # 进行分类以及预测
    maxProb, predictLabel = classifyAndPredict(list_condition)
    print('-----------------预测结果------------------')
    print(f'age,income,student,credit_rating={list_condition} ===>>> 分类预测:{predictLabel}', '+ 预测概率:%.3f' % (maxProb))

