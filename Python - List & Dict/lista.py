import re
import datetime

class Osoba:
    def __init__(self, lista):
        if len(lista) != 5:
            raise ValueError
        self.imie = lista[0]
        self.nazwisko = lista[1]
        for i in lista[2:]:
            pesel = re.search("[0-9]{11}",str(i))
            numer = re.search("[0-9]{3}-[0-9]{3}-[0-9]{3}",str(i))
            if pesel:
                self.pesel = pesel.group(0)
            elif numer:
                self.tel = numer.group(0)
            else:
                self.wiek = i
        self.skorygujWiek()

    def __str__(self):
        return "\nImie: " + str(self.imie) + "\nNazwisko: " + str(self.nazwisko) + "\nWiek: " + str(self.wiek) + "\nPesel: " + str(self.pesel) + "\nTelefon: " + str(self.tel)

    def skorygujWiek(self):
        now = datetime.datetime.now()
        rok = now.year
        if str(self.pesel)[:1] == "0" or str(self.pesel)[:1] == "1":
            self.wiek = int(rok) - int("20"+str(self.pesel)[:2])
        else:
            self.wiek = int(rok) - int("19" + str(self.pesel)[:2])