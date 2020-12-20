import numpy as np


def factorial(x):
    return 1.0 / (1.0 + np.math.exp(-x))


def fwdPass(wiek, waga, wzrost):
    d1w1 = (wiek * -0.46122) + (waga * 0.97314) + \
        (wzrost * -0.39203) + (0.80109)

    d1w2 = (wiek * 0.78548) + (waga * 2.10584) + \
        (wzrost * -0.57847) + (0.43529)

    h3 = (factorial(d1w1) * -0.81546) + (factorial(d1w2) * 1.03775) + (-0.2368)

    return h3


def main():
    print(fwdPass(23.0,     75.0,   176.0))
    print(fwdPass(25.0,     67.0,   180.0))
    print(fwdPass(28.0,     120.0,  175.0))
    print(fwdPass(22.0,     65.0,   165.0))
    print(fwdPass(46.0,     70.0,   187.0))
    print(fwdPass(50.0,     68.0,   180.0))
    print(fwdPass(48.0,     97.0,   178.0))


if __name__ == "__main__":
    main()
