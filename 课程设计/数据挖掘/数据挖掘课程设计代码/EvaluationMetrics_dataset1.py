import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import roc_curve, auc

def creatDataSet():
    # 定义数据集，其中1表示是猫，0表示不是猫
    data_true = np.array([1] * 13 + [0] * 53)
    data_predict = np.array([1] * 10 + [0] * 3 + [1] * 8 + [0] * 45)

    return data_true, data_predict

# 返回混淆矩阵的tp, fp, tn, fn
def confusion_matrix(data_true, data_predict):

    # 初始化定义tp, fp, tn, fn
    tp = 0
    fp = 0
    fn = 0
    tn = 0
    for i in range(len(data_true)):
        if data_true[i] == 1 and data_predict[i] == 1:
            tp += 1
        elif data_true[i] == 1 and data_predict[i] == 0:
            fp += 1
        elif data_true[i] == 0 and data_predict[i] == 1:
            fn += 1
        else:
            tn += 1

    return tp, fp, fn, tn

# 定义精确率
def precision(tp, fp, fn, tn):
    return round(float(tp) / (tp + fp), 3)

# 定义召回率
def recall(tp, fp, fn, tn):
    return round(float(tp) / (tp + fn), 3)

# 定义准确率
def accuracy(tp, fp, fn, tn):
    return round(float(tp + tn) / (tp + fp + fn + tn), 3)

# 定义f1_score
def f1_score(tp, fp, fn, tn):
    return round(float(2 * precision(tp, fp, fn, tn) * recall(tp, fp, fn, tn)) / (precision(tp, fp, fn, tn) + recall(tp, fp, fn, tn)), 3)

# 计算roc和auc
def calculate_roc_auc(data_true, data_predict):
    fpr, tpr, threshold = roc_curve(data_true, data_predict)
    result_auc = round(auc(fpr, tpr), 3)
    print(f'auc: {result_auc}')

    lw = 2
    plt.subplot(1, 1, 1)
    # 假正率为横坐标，真正率为纵坐标做曲线
    plt.plot(fpr, tpr, color='blueviolet', lw=lw, label='roc_curve(auc=%0.3f)' % result_auc)
    plt.plot([0, 1], [0, 1], color='gray', lw=lw, linestyle='--')
    # 限制x，y轴的范围为[0,1]
    plt.xlim([0.0, 1.0])
    plt.ylim([0.0, 1.0])
    # 将x轴命名为fpr，y轴命名为tpr
    plt.xlabel('fpr')
    plt.ylabel('tpr')
    plt.title('ROC', y=0.5)
    plt.legend(loc="lower right")
    plt.show()

if __name__ == '__main__':
    data_true, data_predict = creatDataSet()
    tp, fp, fn, tn = confusion_matrix(data_true, data_predict)
    print(f'tp: {tp}\tfp: {fp}\tfn: {fn}\ttn: {tn}')
    print(f'precision: {precision(tp, fp, fn, tn)}')
    print(f'accuracy: {accuracy(tp, fp, fn, tn)}')
    print(f'recall: {recall(tp, fp, fn, tn)}')
    print(f'f1_score: {f1_score(tp, fp, fn, tn)}')
    calculate_roc_auc(data_true, data_predict)