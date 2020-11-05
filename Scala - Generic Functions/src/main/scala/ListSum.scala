import scala.annotation.tailrec

object ListSum extends App {
  def sum(l: List[Option[Int]]): Option[Int] = {
    @tailrec
    def helper(list: List[Option[Int]], sum: Option[Int] = None): Option[Int] = {
      if (list.isEmpty) sum
      else
        (list.head, sum) match {
          case (None, None)       => helper(list.tail, None)
          case (None, Some(y))    => helper(list.tail, Some(y))
          case (Some(x), None)    => helper(list.tail, Some(x))
          case (Some(x), Some(y)) => helper(list.tail, Some(x + y))
        }
    }
    helper(l)
  }

  val result = sum(List(Some(1), Some(5), None))
  println(result)
}
