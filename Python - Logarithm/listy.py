def prime_factors(n):
    i = 2
    factors = []
    while i * i <= n:
        if n % i:
            i += 1
        else:
            n //= i
            factors.append(i)
    if n > 1:
        factors.append(n)
    return factors

def checkEqual(iterator):
   return len(set(iterator)) <= 1

def newLista(lista):
    nulista = []
    if checkEqual(lista):
        nulista.append(lista[0])
        nulista.append(len(lista))
    else:
        print("Nieprawidlowa lista")
    return nulista
