/*
  Zadanie 2.

  Lista wyników składa się z wyników idywidualnych zawodników
  postaci:

  (miejsce, zawodnik, średni wynik)

  Wynik końcowy powinien mieć postać posortowanej listy postaci:

  List(
    (1, "Miles Hughes", 19.75),
    (2, "Rose Pink", 19.0),
    (2, "Tony Miles", 19.0),
    (4, "George Reed", 18.25),
    (4, "Gerry Cummings", 18.25)
    (6, "Teddy O'Connor", 16.25),
    (7, "Bill Gatsby", 16.0)
  )

  typu: List[(Int, String, Double)]

  Ostatnia składowa „trójki” to średnia arytmetyczna wyników danego
  zawodnika, znajdujących się w pliku z danymi.

  Istotne elementy rozwiązania:

   1. Zawodnicy o identycznych ocenach powinni otrzymać miejsca ex aequo.
   2. Kolejność prezentacji miejsc ex aequo powinna być alfabetyczna.
   3. Z uwagi na miejsca ex aequo kolejne miejsca mają zmienioną numerację.

  UWAGA!!! W swoim rozwiązaniu nie używaj zmiennych, kolekcji mutowalnych, ani pętli
  (konstrukcji takich, jak „while” czy „foreach”).

  Wartość zadania: 3 pkt.

 */
import scala.io.Source

object Zad2 {
  def main(args: Array[String]): Unit = {
    val lines = Source
      .fromFile("./data/zawodnicy-i-wyniki.txt")
      .getLines()

    val parsedList = lines
      .map { case s"$name,$score" =>
        (name, score.toDouble)
      }
      .toList
      .groupBy(_._1)

    val avarageScores = parsedList.map { case (k, v) =>
      (k, v.map(_._2).sum / v.length)
    }

    val sortedScores =
      avarageScores.toList.sortBy(record => (-record._2, record._1))

    val sortedWithIndex = sortedScores.zipWithIndex.map {
      case ((name, score), pos) => (pos + 1, name, score)
    }

    val slidingVals = sortedWithIndex
      .sliding(2)
      .map { case List(left, right) =>
        if (left._3 == right._3) List(left, (left._1, right._2, right._3))
        else List(left, right)
      }
      .toList

    //   println(slidingVals) <--------

    // jestem chyba na dobrej drodze ale nie wiem jak ładnie wydobyć właściwe osoby
    // warning: UGLY
    val first = slidingVals.head.head
    val second = slidingVals.head.last
    val rest = slidingVals.tail.map { case List(left, right) => right }
    // warning: UGLY

    println(first :: second :: rest)
  }
}
