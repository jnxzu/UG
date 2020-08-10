def partialPivotGauss(matrix, vector):
    combineMatrixVector(matrix, vector)
    for i in range(len(matrix)-1):
        matrix = partialPivot(matrix, i)
        for j in range(i+1, len(matrix)):
            mnoznik = matrix[j][i]/matrix[i][i]*-1
            matrix[j] = [x*mnoznik+y for x, y in zip(matrix[i], matrix[j])]
    return calculateGauss(matrix)


def partialPivot(matrix, idx):
    maxval = abs(matrix[idx][idx])
    maxidx = idx
    for i in range(idx, len(matrix)):
        if abs(matrix[i][idx]) > maxval:
            maxval = abs(matrix[i][idx])
            matrix[i], matrix[maxidx] = matrix[maxidx], matrix[i]
            maxidx = i
    return matrix


def calculateGauss(matrix):
    for i in reversed(range(len(matrix))):
        matrix[i] = [matrix[i][j]/matrix[i][i] for j in range(len(matrix[i]))]
        for j in reversed(range(i)):
            mnoznik = matrix[j][i]*-1
            matrix[j] = [x*mnoznik+y for x, y in zip(matrix[i], matrix[j])]
    return resultGauss(matrix)


def combineMatrixVector(matrix, vector):
    for i in range(len(matrix)):
        matrix[i] = matrix[i] + [vector[i]]


def resultGauss(matrix):
    return [matrix[i][-1] for i in range(len(matrix))]
