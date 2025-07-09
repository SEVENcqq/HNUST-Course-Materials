from math import log
import pandas as pd
import numpy as np

# 获取数据集
def creatDataSet():
    df = pd.read_csv("data/data_ID3.csv", encoding='utf-8')
    temp = df.values.tolist()  # 数据集
    temp_copy = temp.copy()

    # 先将数据中的噪声数据进行剔除，即给定的属性值相同但是结果不同且它们数量相差大的数据集删除，这里的比值设置为0.1
    # 用copy后的数据进行判断，在原数据中进行剔除
    for i in range(len(temp_copy)):
        for j in range(i+1, len(temp_copy)):
            if (np.array(temp_copy)[i, 1:-1] == np.array(temp_copy)[j, 1:-1]).all() and (float(np.array(temp_copy)[j, 0]) / float(np.array(temp_copy)[i, 0])) <= 0.1:
                del(temp[j])

    dataSet = []
    for row in temp:
        iter = row[0]
        for j in range(iter):
            dataSet.append(row[1:])

    labels = df.columns.tolist()[1: -1]  # 分类属性

    # 返回数据集和分类属性
    return dataSet, labels

# 计算标准熵
def calcShannonEnt(dataSet):
    # 返回数据集行数
    num_data = len(dataSet)
    shannonEnt = 0.0  # 经验熵
    label_count = {}  # 保存每个标签（label）出现次数的字典

    # 统计各标签出现的次数
    for i in dataSet:
        # 当使用负数索引时，python将从右开始往左数，因此-1是最后一个元素的位置
        label = i[-1]   # 提取标签信息
        if label not in label_count.keys():   # 如果标签没有放入统计次数的字典，添加进去
            label_count[label] = 0
        label_count[label] += 1  # 否则的话相应的label进行计数，即+1

    # 计算经验熵
    for key in label_count.keys():
        prob = float(label_count[key])/num_data  # 选择该标签的概率
        shannonEnt += -(prob*log(prob, 2))  # 利用公式计算出相应的交叉熵值，每次遍历得出的交叉熵进行累加

    return shannonEnt

# 根据给出的分类标签进行子集划分，用于求解条件熵
def splitDataSet(dataSet, axis, value):
    # 创建返回的数据集列表
    retDataSet = []

    # 遍历数据集
    for featVec in dataSet:
        if featVec[axis] == value:
            # 去掉axis特征，将该分类标签前面以及后面的元素值添加，唯独不对该分类标签值进行添加
            reducedFeatVec = featVec[:axis]
            reducedFeatVec.extend(featVec[axis + 1:])
            # 将符合条件的添加到返回的数据集
            retDataSet.append(reducedFeatVec)

    # 返回划分后的数据集
    return retDataSet

# 得出信息增益最大特征的索引值，以及计算经验熵
def chooseBestFeatureToSplit(dataSet, labels):
    num_data = float(len(dataSet))  # 数据集行数
    num_label = len(dataSet[0]) - 1  # 特征数量
    # 计数数据集的香农熵，即什么都没有做时根据已知数据集计算出来的熵
    shannonEnt = calcShannonEnt(dataSet)
    best_gainRatio_value = 0.0  # 将最佳信息增益比例初始化为0
    best_label_axis = -1  # 最优特征的索引值

    # 遍历所有特征，即遍历所有的列
    for i in range(num_label):
        # 获取dataSet的第i个所有特征，即dataSet的第i个属性的所有数据
        label_list = [example[i] for example in dataSet]
        # 创建set集合{}，元素不可重复，即将此作为分类标签
        label_set = set(label_list)
        condition_Ent = 0.0  # 经验条件熵，初始化条件熵为0
        splitInfo = 0.0  # 属性的splitI值

        # 计算信息增益比例
        for label in label_set:
            # 通过set_after_split获取划分后的子集，即获取从属于该分类标签的数据集
            set_after_split = splitDataSet(dataSet, i, label)
            # 计算子集的概率
            prob = len(set_after_split)/num_data
            # 根据公式计算经验条件熵
            condition_Ent += prob*calcShannonEnt((set_after_split))
            splitInfo += -(prob * log(prob, 2))

        if splitInfo == 0:
            print("分类属性%s的splitI值为0，从而该属性无参考价值需排除" % (labels[i]))
            continue
        # 计算第i列属性的信息熵益比例
        gainRatio = (shannonEnt-condition_Ent)/splitInfo
        # 打印出每个特征的信息增益
        print("分类属性%s的信息增益比例为%.3f" % (labels[i], gainRatio))
        if gainRatio > best_gainRatio_value:  # 比较出最佳信息增益比例
            # 更新信息增益比例，找到最大的信息增益
            best_gainRatio_value = gainRatio
            # 记录信息增益比例最大的特征的索引值
            best_label_axis = i

    # 返回信息增益比例最大特征的索引值
    return best_label_axis  # 返回已使用的分类属性

# 构建决策树，需要递归
def createTree(dataSet, labels, k):
    # 取分类标签（是否买电脑：买 或 不买）
    class_list = [example[-1] for example in dataSet]
    # 如果类别完全相同，则停止继续划分
    if class_list.count(class_list[0]) == len(class_list):  # 如果类别完全相同则停止继续划分
        return class_list[0]
    print("第", k, "次划分:")
    bestFeat = chooseBestFeatureToSplit(dataSet, labels)  # 选择最优分类属性，此时返回的还是索引值
    bestFeatLabel = labels[bestFeat]    # 最优特征的标签（属性）
    print("--------------------------------------")
    k += 1
    # 根据最优特征的标签生成树
    mytree = {bestFeatLabel: {}}
    # 删除已经使用的特征标签
    del(labels[bestFeat])
    # 得到训练集中所有最优特征的属性值
    clasify_label_value = [example[bestFeat] for example in dataSet]
    # 利用set去掉重复的属性值，获取最优特征的所有属性值
    set_clasify_label_value = set(clasify_label_value)
    # 遍历特征，创建决策树
    for value in set_clasify_label_value:
        new_label = labels[:]  # 去除已使用的分类标签，利用剩余的标签进行再分类
        # 构建数据的子集合，通过递归创建决策子树。其中dataSet为原本所有的数据集，bestFeat为需要去除的列，value为对应该列中的某一属性值
        mytree[bestFeatLabel][value] = createTree(splitDataSet(dataSet, bestFeat, value), new_label, k)
    return mytree


if __name__ == '__main__':
    dataSet, labels = creatDataSet()
    mytree = createTree(dataSet, labels, 1)
    print(f'决策树：{mytree}')  # 训练好的决策树


