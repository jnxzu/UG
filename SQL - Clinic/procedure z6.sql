create procedure show_visits as
select Wizyta.id, Pacjent.nazwisko, Wizyta.data, Lekarz.nazwisko, Specjalizacja.nazwa from Wizyta
left join Pacjent on Pacjent.id = Wizyta.pacjent
left join Lekarz on Lekarz.id = Wizyta.lekarz
left join Specjalizacja on Specjalizacja.id = Lekarz.specjalizacja;

exec show_visits;