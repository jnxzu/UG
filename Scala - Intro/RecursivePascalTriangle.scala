object RecursivePascalTriangle extends App {
  def rpt(n: Int): Unit = {
    def pascal(row: Int, col: Int): Int = {
      if (row == 0) 1
      else if (col == 0 || col == row) 1
      else pascal(row - 1, col - 1) + pascal(row - 1, col)
    }
    for (row <- 0 to n - 1) {
      for (col <- 0 to row) {
        print(s"${pascal(row, col)} ")
      }
      println()
    }
  }

  rpt(5)
}
