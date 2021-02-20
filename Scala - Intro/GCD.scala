object GCD extends App {
  def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  print("Input first number: ")
  val a = io.StdIn.readInt()
  println()
  print("Input second number: ")
  val b = io.StdIn.readInt()
  val result = gcd(a, b)
  println(s"$a's and $b's greatest common divisor is: $result")
}
