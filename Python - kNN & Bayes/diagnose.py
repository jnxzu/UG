import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix


def main():
    df = pd.read_csv('diabetes.csv')

    numeric_inputs = df.iloc[:, :-1].values
    classes = df['class'].values

    (train_inputs, test_inputs, train_classes, test_classes) = train_test_split(
        numeric_inputs, classes, train_size=0.67)

    print('KNN3:')
    knn3 = KNeighborsClassifier(n_neighbors=3)
    knn3.fit(train_inputs, train_classes)
    knn3_predicted = knn3.predict(test_inputs)
    knn3_score = knn3.score(test_inputs, test_classes)
    knn3_confusion = confusion_matrix(test_classes, knn3_predicted,
                                      labels=['tested_positive', 'tested_negative'])
    print(knn3_confusion)
    print(f'Accuracy: {round(knn3_score*100)}%\n')

    print('-------------------------\n')

    print('KNN5:')
    knn5 = KNeighborsClassifier(n_neighbors=5)
    knn5.fit(train_inputs, train_classes)
    knn5_predicted = knn5.predict(test_inputs)
    knn5_score = knn5.score(test_inputs, test_classes)
    knn5_confusion = confusion_matrix(test_classes, knn5_predicted,
                                      labels=['tested_positive', 'tested_negative'])
    print(knn5_confusion)
    print(f'Accuracy: {round(knn5_score*100)}%\n')

    print('-------------------------\n')

    print('KNN11:')
    knn11 = KNeighborsClassifier(n_neighbors=11)
    knn11.fit(train_inputs, train_classes)
    knn11_predicted = knn11.predict(test_inputs)
    knn11_score = knn11.score(test_inputs, test_classes)
    knn11_confusion = confusion_matrix(test_classes, knn11_predicted,
                                       labels=['tested_positive', 'tested_negative'])
    print(knn11_confusion)
    print(f'Accuracy: {round(knn11_score*100)}%\n')

    print('-------------------------\n')

    print('Naive-Bayes:')
    bay = GaussianNB()
    bay.fit(train_inputs, train_classes)
    bay_predicted = bay.predict(test_inputs)
    bay_score = bay.score(test_inputs, test_classes)
    bay_confusion = confusion_matrix(test_classes, bay_predicted,
                                     labels=['tested_positive', 'tested_negative'])
    print(bay_confusion)
    print(f'Accuracy: {round(bay_score*100)}%\n')

    print('-------------------------\n')

    print('DTC:')
    dtc = DecisionTreeClassifier()
    dtc.fit(train_inputs, train_classes)
    dtc_predicted = dtc.predict(test_inputs)
    dtc_score = dtc.score(test_inputs, test_classes)
    dtc_confusion = confusion_matrix(test_classes, dtc_predicted,
                                     labels=['tested_positive', 'tested_negative'])
    print(dtc_confusion)
    print(f'Accuracy: {round(dtc_score*100)}%\n')

    plot_labels = ['KNN3', 'KNN5', 'KNN11', 'Naive-Bayes', 'DTC']
    plot_values = [knn3_score, knn5_score, knn11_score, bay_score, dtc_score]
    plt.bar(plot_labels, plot_values)
    plot_space = max(plot_values) - min(plot_values)
    plt.ylim([min(plot_values) - plot_space, max(plot_values) + plot_space])
    plt.xlabel('Classifier')
    plt.ylabel('% Accuracy')
    plt.title('Classifier Accuracy - Diabetes Dataset')
    plt.savefig('classifier_accuracy.png')

    plt.close()

    false_negative_values = [x[0][1]*100/np.sum(x) for x in
                             [knn3_confusion, knn5_confusion, knn11_confusion, bay_confusion, dtc_confusion]]
    plt.bar(plot_labels, false_negative_values)
    plot_space = max(false_negative_values) - min(false_negative_values)
    plt.ylim([min(false_negative_values) - plot_space,
              max(false_negative_values) + plot_space])
    plt.xlabel('Classifier')
    plt.ylabel('% False Negatives')
    plt.title('False Negatives Percentage - Diabetes Dataset')
    plt.savefig('false_negatives.png')


if __name__ == "__main__":
    main()
