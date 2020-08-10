def seidel(a, b, n=25):
    x = [0 for i in range(len(a))]
    for i in range(n):
        n = len(a)
        for j in range(0, n):
            d = b[j]
            for i in range(0, n):
                if(j != i):
                    d -= a[j][i] * x[i]
            x[j] = d / a[j][j]
    return x
