import pandas as pd

from keras.models import Sequential
from keras.layers import Dense
from keras.utils import to_categorical

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import scale
from sklearn.metrics import confusion_matrix


def main():
    df = pd.read_csv('iris.csv')

    inputs = scale(df.iloc[:, :-1].values)
    classes = to_categorical(df['variety'].map(
        {'Setosa': 0, 'Versicolor': 1, 'Virginica': 2}))

    train_inputs, test_inputs, train_classes, test_classes = train_test_split(
        inputs, classes, test_size=0.3)

    model = Sequential()
    model.add(Dense(4, input_dim=4, activation='selu'))
    model.add(Dense(3, activation='softmax'))
    model.compile('adam', 'categorical_crossentropy', ['accuracy'])

    model.fit(train_inputs, train_classes, validation_data=(
        test_inputs, test_classes), epochs=250, batch_size=10, verbose=0)

    predicted_classes = model.predict(test_inputs)
    nn_score = model.evaluate(test_inputs, test_classes, verbose=0)

    predicted_classes = [[round(x) for x in values]
                         for values in predicted_classes]

    def convert_back(triple):
        most_expected = max(triple)
        if(most_expected == triple[0]):
            return 'Setosa'
        elif(most_expected == triple[1]):
            return 'Versicolor'
        else:
            return 'Virginica'

    predicted_classes = list(map(convert_back, predicted_classes))
    test_classes = list(map(convert_back, test_classes))

    nn_confusion = confusion_matrix(test_classes, predicted_classes, labels=[
                                    'Setosa', 'Versicolor', 'Virginica'])

    print(nn_confusion)
    print(f'Accuracy: {round(nn_score[1]*100)}%\n')


if __name__ == "__main__":
    main()
