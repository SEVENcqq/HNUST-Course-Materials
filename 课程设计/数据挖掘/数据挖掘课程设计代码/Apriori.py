import pandas as pd

def createDataSet():
    df = pd.read_csv("data/data_Apriori.csv", encoding='utf-8')
    # df = pd.read_csv("data/test2.csv", encoding='utf-8')
    dataSet = df['dataset'].values.tolist()

    for i in range(len(dataSet)):
        dataSet[i] = dataSet[i].split('###')

    return dataSet

def is_apriori(Ck_item, Lk_copy):

    for item in Ck_item:
        sub_Ck = Ck_item - frozenset([item])
        if sub_Ck not in Lk_copy:
            return False
    return True

# 生成1-候选项目集C1
def create_C1(dataSet):

    C1 = set()
    for row in dataSet:
        for item in row:
            # frozenset是冻结的集合，它是无序不可变的，没有add()，remove()方法。
            item_set = frozenset([item])
            C1.add(item_set)
    return C1

# 生成k-候选项目集Ck
def create_Ck(Lk_copy, k):

    Ck = set()
    len_Lk_copy = len(Lk_copy)
    list_Lk_copy = list(Lk_copy)
    for i in range(len_Lk_copy):
        # 优化
        for j in range(i + 1, len_Lk_copy):
            l1 = list(list_Lk_copy[i])
            l2 = list(list_Lk_copy[j])
            l1.sort()
            l2.sort()
            # 优化，例如：[1,5,6] [1,6,7] 必定有[1,5,7], 如果没有, [1,5,6,7] 不能存在
            if l1[:k - 2] == l2[:k - 2]:
                # if l1[:-2] == l2[:-2]:  # 等同于  l1[:k - 2] == l2[:k - 2]
                # if len(set(l1 + l2)) == len(l1) +1:  # 优化前，需要去重
                # |:并集, &：交集
                Ck_item = list_Lk_copy[i] | list_Lk_copy[j]
                # 判断Ck_item中的每个元素是否在Lk_copy数据集中，即生成的k-候选项目集Ck中的元素是否是频繁项目集
                # 判断依据：①频繁项集的非空子集一定是频繁项集；②非频繁项集的超集一定是非频繁项目集
                if is_apriori(Ck_item, Lk_copy):
                    Ck.add(Ck_item)
    return Ck

def generate_Lk_by_Ck(dataSet, Ck, min_support, support_data):

    Lk = set()
    item_count = dict()
    for t in dataSet:
        for item in Ck:
            # 判断集合item的所有元素是否都包含在集合t中，即item是否是t的子集，即该候选项目集是否是遍历的数据的子集
            if item.issubset(t):
                # 对选取的候选项的个数进行统计
                # 对于dict中的get函数，如果能取到数据则返回字典中存储的value，否则返回一个默认值，这里则是因为之前未记录所以返回0次
                item_count[item] = item_count.get(item, 0) + 1

    t_num = float(len(dataSet))
    for item in item_count:
        # 计算支持度
        support = item_count[item] / t_num
        # 如果该支持度大于等于给定的最小支持度，则将其存放到k-频繁项目集中去，并且保存到支持度数据字典中
        if support >= min_support:
            Lk.add(item)
            support_data[item] = support
    return Lk

# 生成所有的频繁项目集
def generate_L(dataSet, k, min_support):

    support_data = {}
    # 获取1-候选项目集C1
    C1 = create_C1(dataSet)
    # 获取1-频繁项目集L1
    L1 = generate_Lk_by_Ck(dataSet, C1, min_support, support_data)

    # 定义L列表用于存放所有频繁项目集
    L = []
    # 定义Lk副本数据集，用于生成下一候选项目集以及用于添加到L列表中
    Lk_copy = L1.copy()
    L.append(Lk_copy)
    # 获取k-候选项目集Ck和k-频繁项目集Lk
    for i in range(2, k + 1):
        Ci = create_Ck(Lk_copy, i)
        Li = generate_Lk_by_Ck(dataSet, Ci, min_support, support_data)
        # 把生成的k-频繁项目集添加到L列表中；注意：不能直接把Li添加到L中
        Lk_copy = Li.copy()
        L.append(Lk_copy)
    return L, support_data

# 生成最大强关联规则
def generate_big_rules(L, support_data, min_conf):

    big_rule_list = []
    sub_set_list = []
    # 循环频繁项数据集 集合列表
    for i in range(0, len(L)):
        for freq_set in L[i]:
            for sub_set in sub_set_list:
                if sub_set.issubset(freq_set):
                    # 置信度计算公式
                    conf = support_data[freq_set] / support_data[freq_set - sub_set]
                    big_rule = (freq_set - sub_set, sub_set, conf)
                    if conf >= min_conf and big_rule not in big_rule_list:
                        big_rule_list.append(big_rule)
            sub_set_list.append(freq_set)
    return big_rule_list


if __name__ == "__main__":

    # 获得数据集
    dataSet = createDataSet()

    # 获得最大项的长度
    max_len = 0
    for i in range(len(dataSet)):
        data_len = len(dataSet[i])
        if data_len > max_len:
            max_len = data_len

    # L中存放着所有的频繁项目集，support_data中存放所有频繁项目集及其支持度
    # 获取最大项为max_len，最小支持度为0.5的频繁项集
    L, support_data = generate_L(dataSet, k=max_len, min_support=0.5)
    # 获取最小置信度为0.5的数据
    big_rules_list = generate_big_rules(L, support_data, min_conf=0.5)

    count = 1
    print("-------------------频繁项目集-------------------")
    for Lk in L:
        if not Lk:
            break
        print(str(count) + "-频繁项集" + "\t\t支持度")
        for freq_set in Lk:
            print(str(sorted(list(freq_set))), '\t\t', round(support_data[freq_set], 3))
        count += 1
        print("----------------------------------------------")
    print("\n\n")
    print("-------------------强关联规则-------------------")
    print("关联规则\t\t\t\t置信度")
    for item in big_rules_list:
        s = "{}=>{}\t\t{:.3f}".format(str(sorted(list(item[0]))), str(sorted(list(item[1]))), item[2])
        print(s)