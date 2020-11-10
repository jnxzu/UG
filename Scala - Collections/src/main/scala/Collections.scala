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
    seq.foldLeft[Seq[A]](Seq()) {
      case (Seq(), el)               => Seq(el)
      case (s, el) if (s.last == el) => s
      case (s, el)                   => s :+ el
    }

  // println(deStutter(Seq(1, 1, 2, 4, 4, 4, 1, 3)))

  def diff[A](seq1: Seq[A], seq2: Seq[A]): Seq[A] =
    seq1.zip(seq2).filter(tup => tup._1 != tup._2).map(_._1)

  // println(diff(Seq(1, 2, 3), Seq(2, 2, 1, 3)))
}
