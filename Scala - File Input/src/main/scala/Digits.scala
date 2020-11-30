import scala.io.Source

object Digits extends App {
  val src: List[String] = Source.fromFile("liczby.txt").getLines().toList

  def one(source: List[String]): Int =
    source
      .map(s => s.toList.map(c => c.asDigit))
      .filter(s => s.sum % 2 == 1)
      .filter(s => s.zip(s.tail).forall { case (a, b) => a < b })
      .length

  println(one(src))
}
