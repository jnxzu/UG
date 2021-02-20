object RecursivePrimeRepresentation extends App {
  // check if all even numbers to n can be made of two primes
  def rpr(n: Int): Boolean = {
    // check if number can be made of two primes
    def isSumOfPrimes(n: Int, i: Int): Boolean = {
      // check if prime
      def isPrime(a: Int): Boolean = {
        if (a <= 1) false
        else if (a == 2) true
        else !(2 to (a - 1)).exists(x => a % x == 0)
      }

      if (i < 2) false
      else if (isPrime(n - i) && isPrime(n - (n - i))) {
        println(s"$n: ${n - (n - i)} + ${n - i}")
        true
      } else isSumOfPrimes(n, i - 1)
    }

    if (n <= 2) true
    else if (n % 2 == 1) rpr(n - 1)
    else if (isSumOfPrimes(n, n - 2)) rpr(n - 2)
    else false
  }

  def runRpr(n: Int): Unit = {
    println(s"${rpr(n)} for even values (2,$n]")
  }

  runRpr(9)
}
