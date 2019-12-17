import re
import datetime

class Osoba:
    def __init__(self, slownik):
        self.imie=None
        self.nazwisko=None
        self.wiek=None
        self.dataUr=None
        for key in slownik:
            setattr(self, key, slownik[key])
        if not self.nazwisko:
            raise AttributeError

    def __str__(self):
        if not self.imie:
            return "\nNazwisko: " + str(self.nazwisko).title() + "\nWiek: " + str(self.wiek)
        elif not self.wiek:
            return "\nImie: " + str(self.imie).title() + "\nNazwisko: " + str(self.nazwisko).title()
        elif not self.wiek and not self.imie:
            "\nNazwisko: " + str(self.nazwisko).title()
        else:
            return "\nImie: " + str(self.imie).title() + "\nNazwisko: " + str(self.nazwisko).title() + "\nWiek: " + str(self.wiek)

    def liczWIek(self):
        if self.dataUr:
            m = re.search('[0-9]{4}',str(self.dataUr))
            found = m.group(0)
            now = datetime.datetime.now()
            self.wiek=int(now.year)-int(found)