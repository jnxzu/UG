import scala.annotation.tailrec

object ListMax extends App {
  def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    @tailrec
    def helper(
        a: Double,
        b: Double,
        restOfA: List[Double],
        restOfB: List[Double],
        result: List[Double]
    ): List[Double] = {
      if (restOfA.length == 0) {
        if (a >= b) (restOfB.reverse ::: a :: result).reverse
        else (restOfB.reverse ::: b :: result).reverse
      } else if (restOfB.length == 0) {
        if (a >= b) (restOfA.reverse ::: a :: result).reverse
        else (restOfA.reverse ::: b :: result).reverse
      } else {
        if (a >= b) helper(restOfA.head, restOfB.head, restOfA.tail, restOfB.tail, a :: result)
        else helper(restOfA.head, restOfB.head, restOfA.tail, restOfB.tail, b :: result)
      }
    }
    helper(l1.head, l2.head, l1.tail, l2.tail, List[Double]())
  }

  println(maksimum(List(2.0, -1.6, 3.2, 5.4, -8.4), List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)))
}
