import scala.annotation.tailrec

object Sort extends App {
  def isSorted(tab: Array[Int], mlr: (Int, Int) => Boolean): Boolean = {
    @tailrec
    def helper(a: Int, b: Array[Int], f: (Int, Int) => Boolean): Boolean = {
      if (f(a, b.head)) {
        if (b.length > 1) helper(b.head, b.tail, f)
        else true
      } else false
    }
    helper(tab.head, tab.tail, mlr)
  }

  def comp(x: Int, y: Int): Boolean = {
    x < y
  }

  println(isSorted(Array(1, 2, 3, 4, 5), comp))
}
