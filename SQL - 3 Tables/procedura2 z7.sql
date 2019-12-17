create procedure z7 @miasto varchar(50) as
select dwa.nazwisko, dwa.miasto, trzy.pensja from tabela2 as dwa left join tabela3 as trzy on dwa.nazwisko = trzy.nazwisko where miasto = @miasto;

exec z7 @miasto = 'Sopot';