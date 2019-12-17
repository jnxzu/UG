#!/bin/bash
clear
echo -n "Podaj liczbe naturalna: "
read x
./2.sh $x &
wait $!
syn_wynik=$?
echo "Wynik obliczenia: $((syn_wynik))"
exit 0
