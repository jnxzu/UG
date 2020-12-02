class C(val real: Double, val imaginary: Double) {
  override def toString(): String =
    if (imaginary < 0) (real.toString + "-" + (-imaginary).toString + "i")
    else if (imaginary > 0) (real.toString + "+" + imaginary.toString + "i")
    else real.toString()

  def this(real: Double) = { this(real, 0) }

  def +(that: C): C =
    new C(this.real + that.real, this.imaginary + that.imaginary)

  def +(that: Double): C = this + C(that)

  def -(that: C): C =
    new C(this.real - that.real, this.imaginary - that.imaginary)

  def -(that: Double): C = this - C(that)

  def *(that: C): C = C(
    this.real * that.real - this.imaginary * that.imaginary,
    this.real * that.imaginary + this.imaginary * that.real
  )

  def *(that: Double): C = this * C(that)

  def /(that: C): C = {
    try {
      val bot = that.real * that.real + that.imaginary * that.imaginary
      if (bot == 0) throw new IllegalArgumentException()
      C(
        (this.real * that.real + this.imaginary * that.imaginary) / bot,
        (this.imaginary * that.real - this.real * that.imaginary) / bot
      )
    } catch {
      case ex: IllegalArgumentException =>
        println("zero division")
        C(Double.NaN)
    }
  }

  def /(that: Double): C = this / C(that)
}

object C {
  def apply(real: Double, imaginary: Double): C = new C(real, imaginary)
  def apply(real: Double): C = new C(real)
}

object Testing extends App {
  println(C(2, 1) + C(3, 3))
  println(C(4, 0) - C(1, 1))
  println(C(5, 3) * C(2, 2))
  println(C(10, 1) / C(2, 5))

  println(C(2, 1) + 2)
  println(C(2, 1) - 1)
  println(C(2, 1) * 3)
  println(C(2, 1) / 4)
}
