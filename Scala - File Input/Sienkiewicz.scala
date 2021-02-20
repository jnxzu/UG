import scala.io.Source

object Sienkiewicz extends App {
  def histogram(max: Int): Unit = {
    val src: List[String] =
      Source.fromFile("ogniem_i_mieczem.txt").getLines().toList

    val chars =
      src.flatMap(line => line.split(' ')).flatMap(word => word.toCharArray)

    val occurenceCounts =
      chars
        .filter(c => c.isLetter)
        .map(c => c.toLower)
        .groupBy(c => c)
        .map { case (char, list) =>
          if (list.length > max) (char, max) else (char, list.length)
        }

    occurenceCounts.foreach { case (char, count) =>
      println(f"${char}: ${"*" * count}")
    }
  }

  histogram(50)
}
