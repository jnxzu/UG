package genericops

object GenericOps extends App {
  def compose[A, B, C](f: A => B)(g: B => C): A => C = { A => g(f(A)) }

  def prod[A, B, C, D](f: A => C, g: B => D): (A, B) => (C, D) = { (A, B) => (f(A), g(B)) }

  def lift[A, T](op: (T, T) => T)(f: A => T, g: A => T): A => T = { A => op(f(A), g(A)) }
}
