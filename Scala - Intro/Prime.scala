object Prime extends App {
  def prime(a: Int): Boolean = {
    if (a <= 1) false
    else if (a == 2) true
    else !(2 to (a - 1)).exists(x => a % x == 0)
  }

  print("Input your number: ")
  val a = io.StdIn.readInt()
  val result = prime(a)
  println()
  println(s"It is $result that your number $a is prime.")
}
