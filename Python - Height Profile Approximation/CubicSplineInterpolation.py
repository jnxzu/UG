import methods.GaussSeidelMethod as gsm
import methods.JacobiMethod as jm
import methods.PartialPivotGaussMethod as ppgm
import numpy as np
import time


def csi(x, y, func="ppgm", jump_count=200, algo_precision=25):
    if(func != "gsm" and func != "jm" and func != "ppgm"):
        print("incorrect equation solving method")
        return 0
    # PREP
    n = len(x)
    h_table = getH(x)
    mu_table = getMu(x, h_table)
    la_table = getLa(x, h_table)
    de_table = getDe(x, y, h_table)
    matrix = [[2.0 if i == j else 0.0 for i in range(n)]for j in range(n)]
    vector = [0.0 for i in range(n)]
    # FILL MATRIX/VECTOR
    for i in range(n):
        vector[i] = de_table[i]
        if i != 0:
            matrix[i][i-1] = mu_table[i]
        if i != n-1:
            matrix[i][i+1] = la_table[i]
    # CALCULATE
    gsm_start = time.perf_counter()
    M_values_gsm = gsm.seidel(matrix, vector, algo_precision)
    gsm_end = time.perf_counter()
    jm_start = time.perf_counter()
    M_values_jm = jm.jacobi(matrix, vector, algo_precision)
    jm_end = time.perf_counter()
    ppgm_start = time.perf_counter()
    M_values_ppgm = ppgm.partialPivotGauss(matrix, vector)
    ppgm_end = time.perf_counter()
    # GET RESULTS
    start_point = x[0]
    end_point = x[-1]
    return [getS(i, x, y, h_table, M_values_gsm) for i in np.linspace(start_point, end_point, jump_count)], gsm_end-gsm_start, [getS(i, x, y, h_table, M_values_jm) for i in np.linspace(start_point, end_point, jump_count)], jm_end-jm_start, [getS(i, x, y, h_table, M_values_ppgm) for i in np.linspace(start_point, end_point, jump_count)], ppgm_end-ppgm_start


def getH(x):
    result = [0.0 for i in range(len(x))]
    for i in range(len(x)-1):
        result[i+1] = x[i+1]-x[i]
    return result


def getMu(x, h):
    result = [0.0 for i in range(len(x))]
    for i in range(1, len(x)-1):
        result[i] = h[i]/(h[i]+h[i+1])
    return result


def getLa(x, h):
    result = [0.0 for i in range(len(x))]
    for i in range(1, len(x)-1):
        result[i] = h[i+1]/(h[i]+h[i+1])
    return result


def getDe(x, y, h):
    result = [0.0 for i in range(len(x))]
    for i in range(1, len(x)-1):
        result[i] = (6.0/(h[i]+h[i+1])) * \
            (((y[i+1]-y[i])/h[i+1])-((y[i]-y[i-1])/h[i]))
    return result


def getS(x, x_table, y, h, m):
    i = getIndex(x, x_table)
    diff = x - x_table[i]
    return getA(i, y)+getB(i, h, y, m)*diff+getC(i, m)*(diff**2)+getD(i, m, h)*(diff**3)


def getIndex(x, x_table):
    for i in range(1, len(x_table)):
        if x <= x_table[i]:
            return i-1


def getA(i, y):
    return y[i]


def getB(i, h, y, m):
    return ((y[i+1]-y[i])/h[i+1])-((2*m[i]+m[i+1])/6)*h[i+1]


def getC(i, m):
    return m[i]/2


def getD(i, m, h):
    return (m[i+1]-m[i])/(6*h[i+1])
