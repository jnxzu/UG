object RecursiveLength extends App {
  def len(word: String): Int = {
    if (word == "") 0 else 1 + len(word.tail)
  }

  println(len("hello world"))
}
