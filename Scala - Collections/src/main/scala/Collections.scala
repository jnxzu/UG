object Collections extends App {
  def subSeq[A](seq: Seq[A], begIdx: Int, endIdx: Int): Seq[A] =
    seq
      .drop(begIdx)
      .take(endIdx)

//   println(subSeq(Seq('A', 'B', 'C', 'D'), 1, 2).mkString)

  def remElems[A](seq: Seq[A], k: Int): Seq[A] =
    seq.zipWithIndex
      .filter(_._2 != k)
      .map(_._1)

//   println(remElems(Seq('A', 'B', 'C', 'D'), 2).mkString)

  def isOrdered[A](seq: Seq[A])(leq: (A, A) => Boolean): Boolean = {
    seq
      .sliding(2)
      .toSeq
      .map { case Seq(a, b) => leq(a, b) }
      .foldLeft(true)((a, b) => a && b)
  }

  // println(isOrdered(Seq(1, 2, 2, 4))(_ <= _))

  def freq[A](seq: Seq[A]): Seq[(A, Int)] = seq.groupBy(x => x).mapValues(_.size).toSeq

  // println(freq(Seq('a', 'b', 'a', 'c', 'c', 'a')))

  def deStutter[A](seq: Seq[A]): Seq[A] =
    seq
      .foldLeft[Seq[A]](Seq()) {
        case (Seq(), el)               => Seq(el)
        case (s, el) if (s.last == el) => s
        case (s, el)                   => s :+ el
      }

  // println(deStutter(Seq(1, 1, 2, 4, 4, 4, 1, 3)))

  def diff[A](seq1: Seq[A], seq2: Seq[A]): Seq[A] =
    seq1
      .zip(seq2)
      .filter(tup => tup._1 != tup._2)
      .map(_._1)

  // println(diff(Seq(1, 2, 3), Seq(2, 2, 1, 3)))

  def indices[A](seq: Seq[A], el: A): Set[Int] = {
    seq.zipWithIndex.filter(_._1 == el).map(_._2).toSet
  }

  // println(indices(Seq(1, 2, 1, 1, 5), 1))
  // println(indices(Seq(1, 2, 1, 1, 5), 7))

  def minNotContained(set: Set[Int]): Int = {
    (0 to set.max).toSet.diff(set).min
  }

  // println(minNotContained(Set(-3, 0, 1, 2, 5, 6)))

  def swap[A](seq: Seq[A]): Seq[A] =
    seq
      .sliding(2, 2)
      .toSeq
      .map {
        case Seq(a, b) => Seq(b, a)
        case Seq(a)    => Seq(a)
      }
      .toList
      .flatten

  // println(swap(Seq(1, 2, 3, 4, 5)))

  val strefy = java.util.TimeZone.getAvailableIDs.toSeq
    .filter(zone => zone.startsWith("Europe/"))
    .map(zone => zone.stripPrefix("Europe/"))
    .sortBy(s => (s.length, s))

  // println(strefy)

  def score(code: Seq[Int])(move: Seq[Int]): (Int, Int) = {
    val black = code.zip(move).filter(pair => pair._1 == pair._2).length
    val white = code.intersect(move).length - black
    (black, white)
  }

  // println(score(Seq(1, 3, 2, 2, 4, 5))(Seq(2, 1, 2, 4, 7, 2)))
}
