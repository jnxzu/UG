import scala.annotation.tailrec

object Sequence extends App {
  def seq(n: Int): Int = {
    @tailrec
    def helper(i: Int, last: Int, now: Int): Int = {
      if (i <= 0) now
      else helper(i - 1, last + now, last)
    }
    helper(n, 1, 1);
  }

  println(seq(10))
}
