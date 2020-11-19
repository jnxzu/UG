import pandas as pd
import graphviz

from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier, export_text, export_graphviz


def main():
    df = pd.read_csv('iris.csv')

    numeric_inputs = df[['sepal.length', 'sepal.width',
                         'petal.length', 'petal.width']].values
    variety_classes = df['variety'].values

    (train_inputs, test_inputs, train_classes, test_classes) = train_test_split(
        numeric_inputs, variety_classes, train_size=0.7)

    dtc = DecisionTreeClassifier()
    dtc.fit(train_inputs, train_classes)

    score = dtc.score(test_inputs, test_classes)

    feature_names = [name for name in df.columns[:4]]
    text_tree = export_text(dtc, feature_names=feature_names)
    print(text_tree)

    graph_tree = export_graphviz(dtc, out_file=None, filled=True, )
    graph = graphviz.Source(graph_tree)
    graph.render(format='png', cleanup=True)


if __name__ == "__main__":
    main()
