import scala.io.Source
import scala.annotation.tailrec

object Podciag extends App {
  val src: List[String] =
    Source.fromFile("liczby2.txt").getLines().toList

  val srcNumbers = src.map(s => s.toInt)

  def maxSubSeqs(seq: Seq[Int]): Set[Seq[Int]] = {
    val increasingSubseqs = seq.foldLeft(Set[Seq[Int]]())((all, x) => {
      if (all.isEmpty || x <= all.head.last) Set(Seq(x)) ++ all
      else Set(all.head :+ x) ++ all.tail
    })

    val maxSubseqsSize = increasingSubseqs.map(ss => ss.size).max

    increasingSubseqs.filter(ss => ss.size == maxSubseqsSize)
  }

  println(maxSubSeqs(srcNumbers))
}
