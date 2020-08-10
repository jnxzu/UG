from random import randrange

import math
import matplotlib.pyplot as plt
import numpy as np

import Approximator as Ap


# hipotezy 1-3 z ciagu taylora
def h123_a(n):
    fwd = []
    back = []
    args = ["0-24", "25-49", "50-74", "75-99", "100-124", "125-149", "150-174", "175-199", "200-224", "225-249",
            "250-274", "275-299", "300-324", "325-349", "350-374", "375-399", "400-424", "425-449", "450-474",
            "475-499", "500-524", "525-549", "550-574", "575-599", "600-624", "625-649", "650-674", "675-699",
            "700-724", "725-749", "750-774", "775-799", "800-824", "825-849", "850-874", "875-899", "900-924",
            "925-949", "950-974", "975-1000"]
    print("\nTask A:")
    for i in range(0, 1000000):
        x = i * 6.28319 / 1000000
        if i % 10000 == 0:
            print("Progress: {}%".format(i / 10000))
        tester = Ap.Approximator(x, n)
        fwd.append(abs(tester.taylor_fwd() - math.sin(x)))
        back.append(abs(tester.taylor_back() - math.sin(x)))
    fwd_avgs = [np.mean(a) for a in np.array_split(fwd, 40)]
    back_avgs = [np.mean(a) for a in np.array_split(back, 40)]
    plt.ylabel('Error')
    plt.xlabel('x * (6.28319/1000000)')
    plt.xticks(range(40), args, rotation='vertical')
    plt.ylim(0, max(max(fwd_avgs), max(back_avgs)))
    plt.title('Series Length: {} // Taylor Chain'.format(n))
    plt.plot(args, fwd_avgs, label='Forward')
    plt.plot(args, back_avgs, 'r--', label='Back')
    plt.legend()
    plt.show()
    plt.close()


# hipotezy 1-3 z poprzedniego wyrazu
def h123_b(n):
    fwd_r = []
    back_r = []
    args = ["0-24", "25-49", "50-74", "75-99", "100-124", "125-149", "150-174", "175-199", "200-224", "225-249",
            "250-274", "275-299", "300-324", "325-349", "350-374", "375-399", "400-424", "425-449", "450-474",
            "475-499", "500-524", "525-549", "550-574", "575-599", "600-624", "625-649", "650-674", "675-699",
            "700-724", "725-749", "750-774", "775-799", "800-824", "825-849", "850-874", "875-899", "900-924",
            "925-949", "950-974", "975-1000"]
    print("\nTask B:")
    for i in range(0, 1000000):
        x = i * 6.28319 / 1000000
        if i % 10000 == 0:
            print("Progress: {}%".format(i / 10000))
        tester = Ap.Approximator(x, n)
        tester.wyrazy_rec()
        fwd_r.append(abs(tester.fwd_rec() - math.sin(x)))
        back_r.append(abs(tester.back_rec() - math.sin(x)))
    fwd_avgs = [np.mean(a) for a in np.array_split(fwd_r, 40)]
    back_avgs = [np.mean(a) for a in np.array_split(back_r, 40)]
    plt.ylabel('Error')
    plt.xlabel('x * (6.28319/1000000)')
    plt.xticks(range(40), args, rotation='vertical')
    plt.ylim(0, max(max(fwd_avgs), max(back_avgs)))
    plt.title('Series Length: {} // Recursive'.format(n))
    plt.plot(args, fwd_avgs, label='Forward Recursive')
    plt.plot(args, back_avgs, 'r--', label='Back Recursive')
    plt.legend()
    plt.show()
    plt.close()


# jak zalezy dokladnosc obliczen (blad) od liczby sumowanych skladnikow?
def q1_a():
    n = 20
    barsize = 0.15
    x = randrange(62000) / 10000
    tester = Ap.Approximator(x, n)
    tester.tester()
    fwd = tester.wyniki_fwd
    back = tester.wyniki_back
    plt.title('sin({})'.format(x))
    plt.ylabel('Error')
    plt.xlabel('n')
    plt.ylim(0, max(max(fwd), max(back)))
    plt.bar(np.arange(n), fwd, barsize, label='Forward')
    plt.bar(np.arange(n) + barsize, back, barsize, label='Backward')
    plt.xticks(np.arange(n) + barsize, range(n))
    plt.legend()
    plt.show()
    plt.close()


def q1_b():
    n = 20
    barsize = 0.15
    x = randrange(62000) / 10000
    tester = Ap.Approximator(x, n)
    tester.tester()
    fwd = tester.wyniki_fwd_rec
    back = tester.wyniki_back_rec
    plt.title('sin({})'.format(x))
    plt.ylabel('Error')
    plt.xlabel('n')
    plt.ylim(0, max(max(fwd), max(back)))
    plt.bar(np.arange(n), fwd, barsize, label='Forward Recursive')
    plt.bar(np.arange(n) + barsize, back, barsize, label='Backward Recursive')
    plt.xticks(np.arange(n) + barsize, range(n))
    plt.legend()
    plt.show()
    plt.close()


# ile skladnikow w zaleznosci od argumentu nalezy sumowac aby otrzymac dokladnosc 10^-6
def q2():
    fwd = []
    args = []
    for i in range(62000):
        x = i / 10000
        args.append(x)
        tester = Ap.Approximator(x, 0)
        fwd.append(tester.taylor_fwd_limit())
    plt.ylabel('Series Length')
    plt.xlabel('x')
    plt.ylim(0, max(fwd))
    plt.title('Series Needed For 10^-6 Accuracy')
    plt.plot(args, fwd)
    plt.show()
    plt.close()


h123_a(25)
h123_b(25)
# q1_a()
# q1_a()
# q1_a()
# q1_a()
# q1_b()
# q1_b()
# q1_b()
# q1_b()
# q2()
