--5
create table tabela1(lp int identity primary key, nazwisko varchar(50) not null, dataurodzenia date);
create table tabela2(lp int identity primary key, nazwisko varchar(50) not null, miasto varchar(50));
create table tabela3(lp int identity primary key, nazwisko varchar(50) not null, pensja int);
insert into tabela1 (nazwisko, dataurodzenia) values ('Kowalski', '1980.01.01'), ('Nowak', '1990.01.01'), ('Malinowska', '2000.01.01'), ('Kowalska', '1970.01.01');
insert into tabela2 (nazwisko, miasto) values ('Malinowska', 'Gdynia'), ('Kowalska', 'Gdansk'), ('Kowalski', 'Gdansk'), ('Nowak', 'Sopot');
insert into tabela3 (nazwisko, pensja) values ('Kowalski', 5000), ('Nowak', 6000), ('Kowalska', 4500), ('Malinowska', 5500);

--8
select miasto, count(nazwisko) as 'ilosc_mieszkancow' from tabela2 group by miasto;

--9
select jeden.nazwisko, year(jeden.dataurodzenia) as 'rok_urodzenia', trzy.pensja 
from tabela1 as jeden left join tabela3 as trzy on jeden.nazwisko = trzy.nazwisko
where year(jeden.dataurodzenia) > 1985 and trzy.pensja > 5700;

--10
select jeden.nazwisko, year(jeden.dataurodzenia) as 'rok_urodzenia', 2019-year(jeden.dataurodzenia) as 'wiek', trzy.pensja, dwa.miasto
from tabela1 as jeden left join tabela3 as trzy on jeden.nazwisko = trzy.nazwisko left join tabela2 as dwa on dwa.nazwisko = jeden.nazwisko
where year(jeden.dataurodzenia) > 1985 and trzy.pensja > 5700;

