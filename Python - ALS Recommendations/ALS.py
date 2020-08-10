import random
import math as m
import Parser as pr
from Matrix import RecMatrix
import copy


def full(R, lambda_v, features, iterations=30):
    U, P = generate_U_P(features, R.cols, R.rows)
    conv = []
    for iteration in range(iterations):
        U, P = als(R, U, P, lambda_v, features)
        result = functionCalculate(R, U, P, lambda_v)
        conv.append(result)
    return result, conv


def als(R, U, P, lambda_v, features):
    for u in range(len(U[0])):
        Xu = getXu(R, P, u, features, lambda_v)
        for i in range(len(U)):
            U[i][u] = Xu[i]
    for p in range(len(P[0])):
        Xp = getXp(R, U, p, features, lambda_v)
        for i in range(len(P)):
            P[i][p] = Xp[i]
    return U, P


def functionCalculate(R, U, P, lambda_v):
    a = b = c = 0
    for u in range(len(U[0])):
        for p in range(len(P[0])):
            if(R.ratingsmatrix[u][p] != 0):
                a += (R.ratingsmatrix[u][p] -
                      multiplyVectors(getColumn(U, u), getColumn(P, p)))**2
    for u in range(len(U[0])):
        b += vectorNorm(getColumn(U, u))**2
    for p in range(len(P[0])):
        c += vectorNorm(getColumn(P, p))**2
    result = a+lambda_v*(b+c)
    return result


def getXu(R, P, index, features, lambda_v):
    products_rated_by_user = R.productsRatedByCustomer(index)
    return partialPivotGauss(getAu(R, P, index, products_rated_by_user, lambda_v), getVu(R, P, index, products_rated_by_user, features))


def getXp(R, U, index, features, lambda_v):
    customers_who_rated_product = R.customersWhoRatedProduct(index)
    return partialPivotGauss(getAp(R, U, index, customers_who_rated_product, lambda_v), getVp(R, U, index, customers_who_rated_product, features))


def getAu(R, P, index, prbu, lambda_v):
    Piu = getPiu(R, P, index, prbu)
    return addLambda(multiplyMatrices(Piu, transpose(Piu)), lambda_v)


def getAp(R, U, index, uwrp, lambda_v):
    Uip = getUip(R, U, index, uwrp)
    return addLambda(multiplyMatrices(Uip, transpose(Uip)), lambda_v)


def getVu(R, P, index, prbu, f):
    Vu = [0 for _ in range(f)]
    for prodId in prbu:
        rating = R.ratingsmatrix[index][prodId]
        for i in range(f):
            Vu[i] += P[i][prodId] * rating
    return Vu


def getVp(R, U, index, uwrp, f):
    Vp = [0 for _ in range(f)]
    for userId in uwrp:
        rating = R.ratingsmatrix[userId][index]
        for i in range(f):
            Vp[i] += U[i][userId] * rating
    return Vp


def getPiu(R, P, index, prbu):
    Piu = [[0]*len(prbu) for _ in range(len(P))]
    for i in range(len(P)):
        Piu[i] = [P[i][prodId] for prodId in prbu]
    return Piu


def getUip(R, U, index, uwrp):
    Uip = [[0]*len(uwrp) for _ in range(len(U))]
    for i in range(len(U)):
        Uip[i] = [U[i][userId] for userId in uwrp]
    return Uip


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


def resultGauss(matrix):
    return [matrix[i][-1] for i in range(len(matrix))]


def generate_U_P(features, prods, customers):
    P = [[random.random() for i in range(prods)] for j in range(features)]
    U = [[random.random() for i in range(customers)]
         for j in range(features)]
    return U, P


def multiplyMatrices(a, b):
    zip_b = zip(*b)
    zip_b = list(zip_b)
    return [[sum(ele_a*ele_b for ele_a, ele_b in zip(row_a, col_b))
             for col_b in zip_b] for row_a in a]


def multiplyVectors(a, b):
    return sum(x*y for x, y in zip(a, b))


def getColumn(matrix, idx):
    return [matrix[x][idx] for x in range(len(matrix))]


def vectorNorm(vector):
    return m.sqrt(sum(i**2 for i in vector))


def transpose(matrix):
    return [[matrix[j][i] for j in range(len(matrix))] for i in range(len(matrix[0]))]


def addLambda(matrix, lambda_v):
    for i in range(len(matrix)):
        matrix[i][i] += lambda_v
    return matrix


def combineMatrixVector(matrix, vector):
    for i in range(len(matrix)):
        matrix[i] = matrix[i] + [vector[i]]


def printMatrix(matrix):
    for row in matrix:
        print(row)


def recommendationTest(R):
    features = 3
    lambda_value = 0.1
    U, P = generate_U_P(3, R.cols, R.rows)
    U, P = als(R, U, P, lambda_value, features)
    return [[round(multiplyVectors(getColumn(U, u), getColumn(P, p)), 2) for p in range(
        len(P[0]))] for u in range(len(U[0]))]


# GAUSS TEST
# A = [[1, 2, -3, -1],
#      [0, -3, 2, 6],
#      [-3, -1, 3, 1],
#      [2, 3, 2, -1]]
# b = [0, -8, 0, -8]
# print(partialPivotGauss(A, b))


# TRANSPOSE TEST
# A = [[1, 2, -3, -1],
#      [0, -3, 2, 6],
#      [-3, -1, 3, 1],
#      [2, 3, 2, -1]]
# printMatrix(transpose(A))


# LAMBDA TEST
# A = [[1, 2, -3, -1],
#      [0, -3, 2, 6],
#      [-3, -1, 3, 1],
#      [2, 3, 2, -1]]
# printMatrix(addLambda(A, 1))


# VECTOR NORM TEST
# v = [1, 3, 5, 7]
# print(vectorNorm(v))


# MULTIPLY MATRICES TEST
# A = [[3, 9, 2, 0],
#      [1, 3, 2, 1],
#      [2, 3, 5, 6]]
# B = [[1, 7, 8],
#      [9, 8, 9],
#      [5, 4, 6],
#      [2, 3, 1]]
# printMatrix(multiplyMatrices(A, B))


# ALS TEST
# R = pr._parse_file(5)
# print("ORIGINAL")
# printMatrix(R.ratingsmatrix)
# print("\nGENERATED")
# printMatrix(recommendationTest(R))
