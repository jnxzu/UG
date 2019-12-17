from slownik import *

krotka = (
{'imie':'Anna', 'nazwisko':'ANNECKA', 'wiek': 18},
{'nazwisko':'beacka', 'dataUr': '1998/12/03'},
{'nazwisko':'Cezarski', 'imie':'CeZary'},
{'nazwisko': 'Darecki', 'imie': 'dariusz', 'dataUr': '08-03-2001' },
{'nazwisko': 'Francki', 'dataUr': '1/2/1999', 'imie': 'franciszek' },
{'imie': 'Henryk', 'dataUr': '1997' }
)

try:
    for slownik in krotka:
        x = Osoba(slownik)
        x.liczWIek()
        print(x)

except AttributeError:
    print("\nBrak nazwiska\n")
