import genericops.GenericOps

object MultiSet extends App {
  type MSet[A] = A => Int

  val setOne: MSet[Char] = _ match {
    case 'A' => 3
    case 'B' => 2
    case 'C' => 1
    case _   => 0
  }

  val setTwo: MSet[Char] = _ match {
    case 'C' => 2
    case 'D' => 2
    case 'E' => 3
    case _   => 0
  }

  val plus = GenericOps.lift[Char, Int]((x, y) => x + y)(setOne, setTwo)

  println(plus('A'))
  println(plus('C'))
  println(plus('F'))

  val minus = GenericOps.lift[Char, Int]((x, y) => if (x - y >= 0) x - y else 0)(setOne, setTwo)

  println(minus('A'))
  println(minus('C'))
  println(minus('F'))

  val czescWspolna = GenericOps.lift[Char, Int]((x, y) => if (x >= y) y else x)(setOne, setTwo)

  println(czescWspolna('A'))
  println(czescWspolna('C'))
  println(czescWspolna('F'))
}
