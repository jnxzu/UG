import pandas as pd


def myPredictRow(sl, sw, pl, pw):
    if pl < 2:
        return 'Setosa'
    elif pl < 4.8:
        return 'Versicolor'
    else:
        return 'Virginica'


def main():
    df = pd.read_csv('iris.csv')
    total = 0
    correct = 0
    for row in df.values:
        total += 1
        prediction = myPredictRow(*row[:4])
        actual = row[-1]
        if prediction == actual:
            correct += 1
        else:
            print(f'MISTAKE: predicted {prediction} actual {actual}')
    print(f'\naccuracy: {correct/total * 100:0.4f}%')


if __name__ == '__main__':
    main()
