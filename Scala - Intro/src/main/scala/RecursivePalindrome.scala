object RecursivePalindrome extends App {
  def palindrome(tab: Array[Int]): Boolean = {
    if (tab.length < 2) true
    else if (tab(0) == tab(tab.length - 1)) palindrome(tab.slice(1, tab.length - 1))
    else false
  }

  println(palindrome(Array(1, 2, 3, 4, 5, 4, 3, 2, 1)))
  println(palindrome(Array(1, 2, 3, 5, 5, 4, 3, 2, 9)))
}
