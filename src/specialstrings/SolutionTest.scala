package specialstrings

object SolutionTest extends App {
  val solution = new Solution()

  Seq(
    ("asasd", 7), ("abcbaba", 10), ("aaaa", 10), ("aaaai", 11), ("iaaaa", 11), ("abcdef", 6),
    ("abcicdef", 9), ("abcicidef", 11), ("aabcicideff", 15),
    ("", 0), ("a", 1), ("ioioioioio", 18), ("ooooooioo", 26), ("ooioooooo", 26)).foreach { example =>
    print(s"Example '${example._1}' ... ")
    val value = solution.substrCount(example._1.length, example._1)
    assert(value == example._2, s"expected: ${example._2}, actual=$value")
    println("ok")
  }
}
