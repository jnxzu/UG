import pandas as pd
import matplotlib.pyplot as plt

from sklearn.cluster import KMeans


def main():
    df = pd.read_csv('iris2D.csv')
    vals = df.iloc[:, [1, 2]].values

    km = KMeans(init='random', n_clusters=3)
    res = km.fit_predict(vals)

    plt.scatter(vals[res == 0, 0], vals[res == 0, 1],
                s=100, c='red', label='1')
    plt.scatter(vals[res == 1, 0], vals[res == 1, 1],
                s=100, c='blue', label='2')
    plt.scatter(vals[res == 2, 0], vals[res == 2, 1],
                s=100, c='green', label='3')
    plt.legend()
    plt.show()


if __name__ == "__main__":
    main()
