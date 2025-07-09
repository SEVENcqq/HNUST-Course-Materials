from tqdm import tqdm
import pandas as pd


def load_data():  # 根据路径加载数据集
    result = []  # 将数据保存到该数组

    # dataset = pd.read_csv("data/data_fpgrowth.csv", encoding='utf-8')
    # data = dataset['data'].values.tolist()

    dataset = pd.read_csv("data/data_Apriori.csv", encoding='utf-8')
    data = dataset['dataset'].values.tolist()

    for i in range(len(data)):
        data[i] = data[i].split('###')

    for row in data:
        row = list(set(row))  # 去重，排序
        row.sort()
        result.append(row)  # 将添加好的数据添加到数组
    return result  # 返回处理好的数据集，为二维数组


# item[0]存放的是输入物品，item[1]存放的是输出物品，item[2]存放的是两者的置信度
def show_confidence(rule):
    index = 1
    # file.write("order,confidence,lift,rules_in,rules_out\n")
    for item in rule:
        # 只有一个输出的判断
        # if (str(list(item[1])).find(',') < 0):
            s = " {:<4d}  {:.3f}      {:.3f}        {}=>{}".format(index, item[2], item[3], str(list(item[0])), str(list(item[1])))
            # 写入到csv文件中要修改格式
            # s = "{},{:.3f},{:.3f},{},{}".format(index, item[2], item[3],str(list(item[0])).strip('[]').replace("'", "").replace(", ", "###"),str(list(item[1])).strip('[]').replace("'", ""))
            index += 1
            # 向csv文件中写入数据
            # file.write(s + '\n')
            print(s)
    # file.close()


class Node:
    def __init__(self, node_name, count, parentNode):
        self.name = node_name
        self.count = count
        self.nodeLink = None  # 根据nideLink可以找到整棵树中所有nodename一样的节点
        self.parent = parentNode  # 父亲节点
        self.children = {}  # 子节点{节点名字:节点地址}


class Fp_growth_plus():

    def data_compress(self, data_set):
        data_dic = {}
        for i in data_set:
            if frozenset(i) not in data_dic:
                data_dic[frozenset(i)] = 1
            else:
                data_dic[frozenset(i)] += 1
        return data_dic

    def update_header(self, node, targetNode):  # 更新headertable中的node节点形成的链表
        while node.nodeLink != None:
            node = node.nodeLink
        node.nodeLink = targetNode

    def update_fptree(self, items, count, node, headerTable):  # 用于更新fptree
        if items[0] in node.children:
            # 判断items的第一个结点是否已作为子结点
            node.children[items[0]].count += count
        else:
            # 创建新的分支
            node.children[items[0]] = Node(items[0], count, node)
            # 更新相应频繁项集的链表，往后添加
            if headerTable[items[0]][1] == None:
                headerTable[items[0]][1] = node.children[items[0]]
            else:
                self.update_header(headerTable[items[0]][1], node.children[items[0]])
        # 递归
        if len(items) > 1:
            self.update_fptree(items[1:], count, node.children[items[0]], headerTable)

    def create_fptree(self, data_dic, min_support, flag=False):  # 建树主函数
        item_count = {}  # 统计各项出现次数
        for t in data_dic:  # 第一次遍历，得到频繁一项集
            for item in t:
                if item not in item_count:
                    item_count[item] = data_dic[t]
                else:
                    item_count[item] += data_dic[t]
        headerTable = {}
        for k in item_count:  # 剔除不满足最小支持度的项
            if item_count[k] >= min_support:
                headerTable[k] = item_count[k]

        freqItemSet = set(headerTable.keys())  # 满足最小支持度的频繁项集
        if len(freqItemSet) == 0:
            return None, None
        for k in headerTable:
            headerTable[k] = [headerTable[k], None]  # element: [count, node]
        tree_header = Node('head node', 1, None)
        if flag:
            ite = tqdm(data_dic)
        else:
            ite = data_dic
        for t in ite:  # 第二次遍历，建树
            localD = {}
            for item in t:
                if item in freqItemSet:  # 过滤，只取该样本中满足最小支持度的频繁项
                    localD[item] = headerTable[item][0]  # element : count
            if len(localD) > 0:
                # 根据全局频数从大到小对单样本排序
                order_item = [v[0] for v in sorted(localD.items(), key=lambda x: x[1], reverse=True)]
                # 用过滤且排序后的样本更新树
                self.update_fptree(order_item, data_dic[t], tree_header, headerTable)
        return tree_header, headerTable

    def find_path(self, node, nodepath):
        '''
        递归将node的父节点添加到路径
        '''
        if node.parent != None:
            nodepath.append(node.parent.name)
            self.find_path(node.parent, nodepath)

    def find_cond_pattern_base(self, node_name, headerTable):
        '''
        根据节点名字，找出所有条件模式基
        '''
        treeNode = headerTable[node_name][1]
        cond_pat_base = {}  # 保存所有条件模式基
        while treeNode != None:
            nodepath = []
            self.find_path(treeNode, nodepath)
            if len(nodepath) > 1:
                cond_pat_base[frozenset(nodepath[:-1])] = treeNode.count
            treeNode = treeNode.nodeLink
        return cond_pat_base

    def create_cond_fptree(self, headerTable, min_support, temp, freq_items, support_data):
        # 最开始的频繁项集是headerTable中的各元素
        freqs = [v[0] for v in sorted(headerTable.items(), key=lambda p: p[1][0])]  # 根据频繁项的总频次排序
        for freq in freqs:  # 对每个频繁项
            freq_set = temp.copy()
            freq_set.add(freq)
            freq_items.add(frozenset(freq_set))
            if frozenset(freq_set) not in support_data:  # 检查该频繁项是否在support_data中
                support_data[frozenset(freq_set)] = headerTable[freq][0]
            else:
                support_data[frozenset(freq_set)] += headerTable[freq][0]

            cond_pat_base = self.find_cond_pattern_base(freq, headerTable)  # 寻找到所有条件模式基
            # 创建条件模式树
            cond_tree, cur_headtable = self.create_fptree(cond_pat_base, min_support)
            if cur_headtable != None:
                self.create_cond_fptree(cur_headtable, min_support, freq_set, freq_items, support_data)  # 递归挖掘条件FP树

    def generate_L(self, data_set, min_support):
        # 计算支持度
        data_dic = self.data_compress(data_set)
        freqItemSet = set()
        support_data = {}
        tree_header, headerTable = self.create_fptree(data_dic, min_support, flag=True)  # 创建数据集的fptree
        # 创建各频繁一项的fptree，并挖掘频繁项并保存支持度计数
        self.create_cond_fptree(headerTable, min_support, set(), freqItemSet, support_data)

        max_l = 0
        for i in freqItemSet:  # 将频繁项根据大小保存到指定的容器L中
            if len(i) > max_l: max_l = len(i)
        L = [set() for _ in range(max_l)]
        for i in freqItemSet:
            L[len(i) - 1].add(i)
        for i in range(len(L)):
            print("frequent item {}:{}".format(i + 1, L[i]))
        return L, support_data

    def generate_R(self, data_set, min_support, min_conf):
        L, support_data = self.generate_L(data_set, min_support)
        rule_list = []
        sub_set_list = []
        for i in range(0, len(L)):
            for freq_set in L[i]:
                for sub_set in sub_set_list:
                    if sub_set.issubset(freq_set) and freq_set - sub_set in support_data:  # and freq_set-sub_set in support_data
                        # 计算置信度和提升度
                        conf = support_data[freq_set] / support_data[freq_set - sub_set]
                        lift = conf * len(data_set) / support_data[sub_set]
                        big_rule = (freq_set - sub_set, sub_set, conf, lift)
                        if conf >= min_conf and conf <= 1 and big_rule not in rule_list:
                            rule_list.append(big_rule)
                sub_set_list.append(freq_set)
        rule_list = sorted(rule_list, key=lambda x: (x[2], x[3], x[0], x[1]), reverse=True)  # 根据置信度、提升度、输入、输出进行排序
        return rule_list


if __name__ == "__main__":

    # file = open('association_rules.csv', 'w')
    min_support = 7  # 最小支持数，不是最小支持度
    min_conf = 0.5  # 最小置信度
    data_set = load_data()
    fp = Fp_growth_plus()
    rule_list = fp.generate_R(data_set, min_support, min_conf)
    print("order confidence   lift           rules")
    show_confidence(rule_list)

