import pandas as pd
import matplotlib.pyplot as plt

from pandas.core.frame import DataFrame
from sklearn.preprocessing import OneHotEncoder
from mlxtend.frequent_patterns import apriori, association_rules


def main():
    df = pd.read_csv('titanic.csv')
    df.drop(df.columns[0], axis=1, inplace=True)

    ohe = OneHotEncoder(sparse=False)
    ohe.fit(df)
    oh_encoded = DataFrame(ohe.transform(df))
    oh_encoded.columns = ['Class_1st', 'Class_2nd', 'Class_3rd', 'Class_Crew', 'Sex_Female', 'Sex_Male',
                          'Age_Adult', 'Age_Child', 'Survived_No', 'Survived_Yes']

    freq = apriori(oh_encoded, min_support=0.005, use_colnames=True)
    rules = association_rules(freq)
    interesting_rules = rules[rules['consequents'].apply(
        lambda x: 'Survived' in str(x))].sort_values('confidence', ascending=False)
    print(interesting_rules.head(15).to_string())

    plt.scatter(interesting_rules['support'], interesting_rules['confidence'])
    plt.ylabel('confidence')
    plt.xlabel('support')
    plt.title('Support vs Confidence')
    plt.legend()
    plt.show()


if __name__ == "__main__":
    main()
