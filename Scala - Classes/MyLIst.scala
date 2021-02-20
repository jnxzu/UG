abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](a: B): MyList[B]
  override def toString(): String = {
    def helper(
        list: MyList[A] = this.tail,
        str: String = this.head.toString()
    ): String = {
      if (!list.isEmpty) helper(list.tail, str + ", " + list.head.toString())
      else str
    }

    if (this.isEmpty) "[]"
    else
      "[" + helper() + "]"
  }
}

object MyList {
  def apply(args: Int*): MyList[Int] = {
    if (args.length > 0) {
      def helper(elements: Seq[Int], l: MyList[Int]): MyList[Int] = {
        if (elements.isEmpty) l
        else helper(elements.tail, l.add(elements.head))
      }
      helper(
        args.reverse.drop(1),
        new MyNonEmptyList[Int](args.reverse.head, MyEmptyList)
      )
    } else {
      MyEmptyList
    }
  }
}

protected object MyEmptyList extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail = throw new NoSuchElementException
  def isEmpty = true
  def add[A >: Nothing](a: A): MyList[A] = new MyNonEmptyList[A](a, this)
}

protected class MyNonEmptyList[A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](a: B): MyList[B] = new MyNonEmptyList[B](a, this)
}

object ListTesting extends App {
  println(MyList())
  println(MyList(1, 2, 3))
}
