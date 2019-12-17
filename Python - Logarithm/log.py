from math import *
from listy import *

class ZlaPodstawa(Exception):
    def __str__(self):
        return "Nieprawidlowa podstawa."

class ZlyArgument(Exception):
    def __str__(self):
        return "Nieprawidlowy argument."

class RoznePodstawy(Exception):
    def __str__(self):
        return "Rozne podstawy."

class Log:
    def __init__(self, podst, arg):
        if podst==1 or podst<=0:
            raise ZlaPodstawa
        else:
            self.podst = podst
        if arg<=0:
            raise ZlyArgument
        else:
            self.arg = arg

    def __str__(self):
        return "log" + str(self.podst) + "(" + str(self.arg) + ")"

    def __add__(self, other):
        if self.podst==other.podst:
            return Log(self.podst, self.arg*other.arg)
        else:
            raise RoznePodstawy

    def oblicz(self):
        return log(self.arg, self.podst)

    def redukuj(self):
        if type(self.podst)==int:
            x = self
            a = newLista(prime_factors(self.podst))
            b = newLista(prime_factors(self.arg))
            if a[0]==b[0]:
                x.podst=a[0]
                x.arg=int(pow(a[0],b[1]/a[1]))
            return x
        else:
            raise TypeError("Zly typ")

try:
    #log1 = Log(0,2)
    #log2 = Log(2,0)
    #print(log1)
    #print(log2)
    #log3 = Log(2,8)
    #log4 = Log(3,4)
    #log5 = log3+log4
    #print(log5)
    #print(log5.oblicz())
    log6 = Log(9,5)
    print(log6.oblicz())
    print(log6)
    print(log6.redukuj())

except ZlyArgument as za:
    print(za)
except ZlaPodstawa as zp:
    print(zp)
except RoznePodstawy as rp:
    print(rp)
except TypeError as te:
    print(te)
