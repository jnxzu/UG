/*
  Zadanie 1.

  Wykorzystując dane zawarte w pliku „dane-bankowe.txt” rozwiąż poniższe zadanie.
  Każda z linii w tym pliku zawiera informację na temat pojedynczej „operacji bankowej”.
  Jej elementami są data operacji oraz jej kwota. Kwota ujemna oznacza obciążenie
  rachunku (wydatek).

  Wykorzystując operacje na kolekcjach znajdź miesiąc(e), w którym(ch) balans wpływów
  i obciążeń rachunku był najkorzystniejszy tzn. suma wpływów pomniejszona o sumę obciążeń
  była maksymalna. Pamiętaj, że miesięcy o identycznym balansie może być kilka!

  UWAGA!!! W swoim rozwiązaniu nie używaj zmiennych, kolekcji mutowalnych, ani pętli
  (konstrukcji takich, jak „while” czy „foreach”).

  Wartość zadania: 2 pkt.

 */
import scala.io.Source

object Zad1 {
  def main(args: Array[String]): Unit = {
    val lines = Source
      .fromFile("./data/dane-bankowe.txt")
      .getLines()

    val monthAndTransactions = lines
      .map { case s"2020-$m-$d,$tran" =>
        (m, tran.toDouble)
      }
      .toList
      .groupBy(_._1)
      .map { case (k, v) => (k, v.map(_._2).sum) }

    val maxTransactionSum = monthAndTransactions.maxBy(_._2)._2

    val monthsWithMaxTransactionSums =
      monthAndTransactions.filter(_._2 == maxTransactionSum).map(_._1)

    println(monthsWithMaxTransactionSums) // 03
  }
}
