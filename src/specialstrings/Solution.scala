package specialstrings

class Solution {
  /*
   * A string is said to be a special string if either of two conditions is met:
   * All of the characters are the same, e.g. aaa.
   * All characters except the middle one are the same, e.g. aadaa.
   *
   * add number of chars to the result
   * if next char is different or first
   *    if already started xxx..y..xxx
   *      case 1: xxx..y..x
   *      case 2: xxx..y..z
   *      case 3: xxx..y..xxxy
   *    else
   *      if first char - nothing else start building xxx..y..xxx by remembering number of previous characters
   *    reset same char count
   * if next char is the same than the previous
   *    if already started xxx..y..xxx
   *      check current xxx..y..xxx and update/reset accordingly
   *    else
   *       nothing
   *    add one to the result + remember char and the count
   *
   * Tuple(previous_count, block_count, word_count)
   */

  def substrCount(n: Int, s: String): Long = {
    (0 until s.length).foldLeft((Option[Tuple2[Char, Int]](null), Option[Tuple2[Char, Int]](null), n))((data, i) => {
      println(s"($i)\t$data")
      data._1 match {
        case None => data._2 match {
          case Some(_) => throw new IllegalArgumentException("")
          case None => (Some((s(i), 1)), None, data._3)
        }
        case Some((letter, len)) if letter != s(i) => data._2 match {
          case None => (Some((s(i), 1)), Some((letter, len)), data._3 + ((1 + len) * len / 2.0 - len).asInstanceOf[Int])
          case Some((blockLetter, _)) if blockLetter != s(i) => (Some((s(i), 1)), Some((letter, len)), data._3)
          case Some(block) => (Some((s(i), 1)), Some((letter, len)), data._3 + Math.min(block._2, len))
        }
        case Some((letter, len)) => data._2 match {
          case None => (Some((letter, len + 1)), None, data._3)
          case Some(block) => (Some((letter, len + 1)), None, data._3)
        }
      }
    }) match {
      case (None, None, c) => c
      case (Some((_, len)), None, c) => c + ((1 + len) * len / 2.0 - len).asInstanceOf[Int]
      case (Some(a), Some(b), c) if a._1 == b._1 => c + Math.min(a._2, b._2)
      case (Some((_, len)), Some(_), c) => c + len -1
    }
  }
}