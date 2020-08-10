import math


class Approximator:
    def __init__(self, x, n):
        self.x = x
        self.n = n
        self.wyrazy = []
        self.wyniki_fwd = []
        self.wyniki_back = []
        self.wyniki_fwd_rec = []
        self.wyniki_back_rec = []

    def tester(self):
        print(self.taylor_fwd())
        print(self.taylor_back())
        self.wyrazy_rec()
        print(self.fwd_rec())
        print(self.back_rec())

    def taylor_fwd(self):
        self.wyniki_fwd.clear()
        result = 0
        for i in range(self.n):
            sign = power(-1, i)
            result = result + (power(self.x, 2 * i + 1) / factorial(2 * i + 1)) * sign
            self.wyniki_fwd.append(abs(result - math.sin(self.x)))
        return result

    def taylor_back(self):
        self.wyniki_back.clear()
        result = 0
        for i in reversed(range(self.n)):
            sign = power(-1, i)
            result = result + (power(self.x, 2 * i + 1) / factorial(2 * i + 1)) * sign
            self.wyniki_back.append(abs(result - math.sin(self.x)))
        return result

    def wyrazy_rec(self):
        self.wyrazy.clear()
        s = self.x
        self.wyrazy.append(s)
        for i in range(self.n):
            diff = (-1 * self.x * self.x / ((2 * i + 2) * (2 * i + 3)))
            s = s * diff
            self.wyrazy.append(s)

    def fwd_rec(self):
        self.wyniki_fwd_rec.clear()
        result = 0
        for i in range(self.n):
            result += self.wyrazy[i]
            self.wyniki_fwd_rec.append(abs(result - math.sin(self.x)))
        return result

    def back_rec(self):
        self.wyniki_back_rec.clear()
        result = 0
        for i in reversed(range(self.n)):
            result += self.wyrazy[i]
            self.wyniki_back_rec.append(abs(result - math.sin(self.x)))
        return result

    def taylor_fwd_limit(self):
        self.wyniki_fwd.clear()
        result = 0
        i = 0
        while abs(result - math.sin(self.x)) > 0.000001:
            sign = power(-1, i)
            result = result + (power(self.x, 2 * i + 1) / factorial(2 * i + 1)) * sign
            i += 1
        return i


def factorial(n):
    if n > 1:
        return n * factorial(n - 1)
    return 1


def power(a, b):
    n = 1
    for i in range(b):
        n = n * a
    return n

test = Approximator(0.2,20)
test.tester()