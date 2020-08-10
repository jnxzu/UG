from numpy import array, diag, diagflat, dot


def jacobi(a, b, n=25):
    a = array(a)
    b = array(b)
    x = [0 for i in range(len(a))]
    d = diag(a)
    r = a - diagflat(d)
    for i in range(n):
        x = (b - dot(r, x)) / d
    return x
