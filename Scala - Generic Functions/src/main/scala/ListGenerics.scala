import scala.annotation.tailrec
object ListGenerics extends App {
  def insertInto[A](a: A, list: List[A])(leq: (A, A) => Boolean): List[A] = {
    @tailrec
    def helper(
        before: List[A],
        insert: A,
        next: List[A],
        compare: (A, A) => Boolean
    ): List[A] = {
      next match {
        case Nil => (a :: list.reverse).reverse
        case right :: rest =>
          if (leq(insert, right)) before ::: (insert :: next)
          else helper((right :: before.reverse).reverse, insert, rest, compare)
      }
    }

    if (list.isEmpty) List(a)
    else if (list.length < 2) {
      if (leq(list.head, a)) list :+ a else a :: list
    } else {
      helper(List(), a, list, leq)
    }
  }

//   val newList = insertInto(5, List(1, 2, 4, 6, 7, 8))((x, y) => x <= y)
//   println(newList.mkString)

  def divide[A](list: List[A]): (List[A], List[A]) = {
    @tailrec
    def helper(
        l: List[A],
        odd: List[A] = List(),
        even: List[A] = List(),
        isOdd: Boolean = true
    ): (List[A], List[A]) = {
      l match {
        case Nil => (odd.reverse, even.reverse)
        case head :: tl =>
          if (isOdd) helper(tl, head :: odd, even, !isOdd)
          else helper(tl, odd, head :: even, !isOdd)
      }
    }

    helper(list)
  }

//   val (odds, evens) = divide(List(1, 3, 5, 6, 7))
//   println(odds.mkString)
//   println(evens.mkString)

  def compress[A](list: List[A]): List[(A, Int)] = {
    @tailrec
    def helper(
        l: List[A],
        current: A,
        count: Int = 1,
        result: List[(A, Int)] = List()
    ): List[(A, Int)] = {
      l match {
        case Nil => result :+ (current, count)
        case head :: tl =>
          if (head == current) helper(tl, current, count + 1, result)
          else helper(tl, head, 1, result :+ (current, count))
      }
    }

    if (list.length < 2) List((list.head, 1)) else helper(list.tail, list.head)
  }

//   val compressed = compress(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'd', 'c'))
//   println(compressed.mkString)
}
