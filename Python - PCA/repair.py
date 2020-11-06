import pandas as pd

df = pd.read_csv("iris_with_errors.csv")

print(df.isnull().sum())
# sepal.length    1
# sepal.width     0
# petal.length    0
# petal.width     1
# variety         0

for column in df:
    if column != 'variety':
        df[column] = pd.to_numeric(df[column], errors='coerce')
        median = df[column].median()
        df[column].fillna(median, inplace=True)
        df[column].mask((df[column] >= 15) | (
            df[column] <= 0), median, inplace=True)

print(df['variety'].unique())
# ['Setosa' 'setosa' 'Versicolor' 'Versicolour' 'Virginica' 'virginica']

for i, val in enumerate(df['variety']):
    fixed_value = val.capitalize().replace('colour', 'color')
    df.loc[i, 'variety'] = fixed_value

print(df['variety'].unique())
# ['Setosa' 'Versicolor' 'Virginica']
