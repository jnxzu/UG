# https://towardsdatascience.com/pca-using-python-scikit-learn-e653f8989e60
# pretty much copy paste

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA

# load
df = pd.read_csv('iris.csv')

# split
features = ['sepal.length', 'sepal.width', 'petal.length', 'petal.width']
x = df.loc[:, features].values
y = df.loc[:, ['variety']].values

# standardize
x = StandardScaler().fit_transform(x)

# pca
pca = PCA(n_components=2)
principalComponents = pca.fit_transform(x)
principalDf = pd.DataFrame(data=principalComponents, columns=[
                           'pc1', 'pc2'])
finalDf = pd.concat([principalDf, df['variety']], axis=1)

# data loss
top = np.var(principalComponents, axis=0)
top_sum = np.sum(top)
bottom = np.var(x, axis=0)
bottom_sum = np.sum(bottom)
data_loss = 1 - (top_sum / bottom_sum)
data_loss_percentage = int(round(data_loss*100, 0))
print(f'Data loss: {data_loss_percentage}%')            # 4%

# plot
fig = plt.figure(figsize=(8, 8))
ax = fig.add_subplot(1, 1, 1)
ax.set_xlabel('Principal Component 1', fontsize=15)
ax.set_ylabel('Principal Component 2', fontsize=15)
ax.set_title('2 component PCA', fontsize=20)
targets = ['Setosa', 'Versicolor', 'Virginica']
colors = ['c', 'r', 'y']
for target, color in zip(targets, colors):
    indicesToKeep = finalDf['variety'] == target
    ax.scatter(finalDf.loc[indicesToKeep, 'pc1'],
               finalDf.loc[indicesToKeep, 'pc2'], c=color, s=50)
ax.legend(targets)
ax.grid()
plt.show()
