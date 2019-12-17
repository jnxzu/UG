create table Pacjent(id int primary key identity, imie varchar(50), nazwisko varchar(50) not null, miasto varchar(50), adres varchar(50), telefon int, pesel varchar(50) not null unique);
create table Specjalizacja(id int primary key identity, nazwa varchar(50) not null);
create table Lekarz(id int primary key identity, imie varchar(50), nazwisko varchar(50) not null, telefon int, specjalizacja int foreign key references Specjalizacja(id));
create table Wizyta(id int primary key identity, lekarz int foreign key references Lekarz(id), pacjent int foreign key references Pacjent(id), data date, godzina time, notatki varchar(50));
create table Recepta(id int primary key identity, wizyta int foreign key references Wizyta(id), lek varchar(50), cena float, dawkowanie varchar(50));
create table Zwolnienie(id int primary key identity, wizyta int foreign key references Wizyta(id), od date, do date, powod varchar(50));
create table Skierowanie(id int primary key identity, wizyta int foreign key references Wizyta(id), powod varchar(50), lekarz int foreign key references Lekarz(id));

insert into Specjalizacja(nazwa) values ('dermatolog'), ('endokrynolog'), ('okulista'), ('pediatra'), ('lekarz rodzinny');
insert into Lekarz(imie, nazwisko, telefon, specjalizacja) values ('typ','typowski',1,1),('ziom','ziomalski',1,1),('facet','facetowski',1,2),('dariusz','dariuszowy',1,2),('roman','makkeve',1,3),('pawel','pawlowski',1,4),('bot,','botowski',1,5),('pani','paniowa',1,5);
insert into Pacjent(imie, nazwisko, miasto, adres, telefon, pesel) values
('a','a','a','a',1,'a1'),
('b','b','b','b',2,'b2'),
('c','c','c','c',3,'c3'),
('d','d','d','d',4,'d4'),
('e','e','e','e',5,'e5'),
('f','f','f','f',6,'f6'),
('g','g','g','g',7,'g7'),
('h','h','h','h',8,'h8'),
('i','i','i','i',9,'i9'),
('j','j','j','j',10,'j10'),
('k','k','k','k',11,'k11'),
('l','l','l','l',12,'l12');
insert into Wizyta(lekarz, pacjent, data, godzina, notatki) values
(1, 1, null, null, null),
(2, 1, null, null, null),
(3, 2, null, null, null),
(4, 3, null, null, null),
(5, 4, null, null, null),
(6, 5, null, null, null),
(7, 6, null, null, null),
(8, 7, null, null, null),
(1, 8, null, null, null),
(2, 9, null, null, null),
(3, 10, null, null, null),
(4, 11, null, null, null),
(5, 12, null, null, null);


-- NAME REDACTED
backup database #####
to disk ='C:\Users\xsero\Desktop\Projekty\db przychodnia';

select * from Lekarz;
select * from Pacjent;
select * from Wizyta;