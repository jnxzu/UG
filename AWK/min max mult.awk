{min=$1; max=$1; il = $1}
{for(i=2;i<=NF;i++){
if($i<min) {min=$i}
if($i>max) {max=$i}
il *= $i
}
print "Wiersz " NR ": min == " min ", max == " max ", iloczyn == " il
}
