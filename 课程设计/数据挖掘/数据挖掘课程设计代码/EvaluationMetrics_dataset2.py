import numpy as np
from sklearn.metrics import confusion_matrix, accuracy_score, precision_score, recall_score, roc_curve, auc, f1_score
import matplotlib.pyplot as plt

# 实际类别标签
actual_labels = [0] * 36 + [1] * 40 + [2] * 24

# 预测类别标签
predicted_labels = [0] * 30 + [1] * 4 + [2] * 2 + [0] * 6 + [1] * 34 + [2] * 24

# precision, recall, f1-score中属性average取值说明：
# micro：将所有类别的 True Positive、False Positive 和 False Negative 汇总，然后计算精确率、召回率和 F1-Score。适用于类别不平衡的情况。
# macro：对每个类别分别计算精确率、召回率和 F1-Score，然后取平均值。适用于每个类别的权重相同的情况。
# weighted：对每个类别分别计算精确率、召回率和 F1-Score，然后按照每个类别的样本数进行加权平均。适用于类别不平衡的情况，且更关注样本数多的类别。
# None：返回每个类别的精确率、召回率和 F1-Score，不进行平均。

# 1. 混淆矩阵
conf_matrix = confusion_matrix(actual_labels, predicted_labels)
print("Confusion Matrix:")
print(conf_matrix)
print('\n')

# 2. 精确率
precision = precision_score(actual_labels, predicted_labels, average='weighted')
print("precision:", round(precision, 3))

# 3. 准确率
accuracy = accuracy_score(actual_labels, predicted_labels)
print("accuracy:", round(accuracy, 3))

# 4. 召回率
recall = recall_score(actual_labels, predicted_labels, average='weighted')
print("recall:", round(recall, 3))

# 5. F1-Score
f1 = f1_score(actual_labels, predicted_labels, average='weighted')
print("f1-Score:", round(f1, 3))

# 6. ROC曲线和AUC面积
fpr = dict()
tpr = dict()
roc_auc = dict()
for i in range(3):
    class_i_actual = [1 if x == i else 0 for x in actual_labels]
    class_i_predicted = [1 if x == i else 0 for x in predicted_labels]
    fpr[i], tpr[i], _ = roc_curve(class_i_actual, class_i_predicted)
    roc_auc[i] = auc(fpr[i], tpr[i])

plt.figure()
colors = ['red', 'dodgerblue', 'blueviolet']
for i in range(3):
    if i in roc_auc:
        plt.plot(fpr[i], tpr[i], color=colors[i], lw=2, label=f'roc_curve(auc={roc_auc[i]:.3f}) for class {i}')
        print(f'AUC Class {i}: {roc_auc[i]:.3f}')

plt.plot([0, 1], [0, 1], color='gray', lw=2, linestyle='--')
plt.xlabel('FPR')
plt.ylabel('TPR')
plt.title('ROC for multi-class')
plt.legend(loc="lower right")
plt.show()


