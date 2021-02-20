import scala.annotation.tailrec;

object Prime extends App {
  def jestPierwsza(n: Int): Boolean = {
    @tailrec
    def primeHelper(t: Int): Boolean = {
      if (t == 1) true
      else if (n % t == 0) false
      else primeHelper(t - 1)
    }
    primeHelper(n / 2)
  }

  println(jestPierwsza(12))
}
