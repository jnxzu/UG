class Point(val x: Double, val y: Double) {
  def ==(that: Point): Boolean = this.x == that.x && this.y == that.y

  def !=(that: Point): Boolean = !(this == that)

  def dist(that: Point): Double =
    Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2))

  def <(that: Point): Boolean = {
    this.dist(Point(0, 0)) < that.dist(Point(0, 0))
  }

  def >(that: Point): Boolean = {
    this.dist(Point(0, 0)) > that.dist(Point(0, 0))
  }

  def <=(that: Point): Boolean = {
    this.dist(Point(0, 0)) <= that.dist(Point(0, 0))
  }

  def >=(that: Point): Boolean = {
    this.dist(Point(0, 0)) >= that.dist(Point(0, 0))
  }
}

object Point {
  def apply(x: Double, y: Double): Point = new Point(x, y)
}

object PointTesting extends App {
  println(Point(1, 1) == Point(1, 1))
  println(Point(2, 2) != Point(3, 3))
  println(Point(4, 4) < Point(5, 5))
  println(Point(6, 6) > Point(0, 0))
  println(Point(2, 3) <= Point(3, 2))
  println(Point(1, 0) >= Point(0, 1))
}
