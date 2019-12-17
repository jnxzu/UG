#bin/vasg/!
trap jeden 10
trap dwa 12

global=1

while true; do
sleep 10
done

jeden()
{
	$text = "user_" + $i + ".txt"
	touch $text
	$global = $((global+1))
}

dwa()
{
	$text = "user_" + $i + ".txt"
	rm $text
	$global = $((global-1))
}
