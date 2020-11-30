import scala.io.Source

object CharDiversity extends App {
  val src: List[String] = Source.fromFile("osoby.txt").getLines().toList

  def two(source: List[String]): Any = {
    val mostDistinctLettersInFirstName =
      source
        .map(s => s.split(' ').head.toList.distinct.length)
        .max

    val onlyMostDistincFirstNames = source.filter(s =>
      s.split(' ').head.toList.distinct.length == mostDistinctLettersInFirstName
    )

    val maxLastNameLen =
      onlyMostDistincFirstNames
        .map(s => s.split(' ').tail.head.length())
        .max

    onlyMostDistincFirstNames.filter(s =>
      s.split(' ').tail.head.length() == maxLastNameLen
    )
  }

  println(two(src))
}
