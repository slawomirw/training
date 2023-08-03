package dynamic.abbreviation

object Result {

  /*
You can perform the following operations on the string, :

Capitalize zero or more of a's lowercase letters.
Delete all of the remaining lowercase letters in a.
Given two strings, a and b, determine if it's possible to make a equal to b as described. If so, print YES on a new line. Otherwise, print NO.
  */

  def abbreviation(a: String, b: String): String = {

    val out = b.foldLeft(Set(a) +: Seq[Set[String]]())((b, c) => {
      var step = Set[String]()
      b.last.foreach(lastStep => {
        val firstUpper = lastStep.zipWithIndex.foldLeft(lastStep.length)((i, cni) => if (i < lastStep.length) i else if (cni._1.isUpper) cni._2 else i)
        if (c.isUpper) {
          for (i <- 0 to firstUpper - 1) {
            if (lastStep(i).toUpper == c) {
              step += lastStep.substring(i + 1)
            }
          }
          if (firstUpper < lastStep.length && c == lastStep(firstUpper)) {
            val str = lastStep.substring(firstUpper + 1)
            if (str.isEmpty) step += "<EMPTY>" else step += str
          }
        } else {
          lastStep.substring(0, firstUpper).zipWithIndex.filter(sc => sc._1 == c).foreach(sc => step += lastStep.substring(sc._2 + 1))
        }
      })
      //println(c + ":" + step)
      b :+ step
    })

    return if (out.last.contains("<EMPTY>") || out.last.filter(s => s.find(_.isUpper).isEmpty).nonEmpty) "YES" else "NO"
  }
}


object Solution {
  def main(args: Array[String]): Unit = {
    println(Result.abbreviation("daBcd", "ABC"))
    println(Result.abbreviation("MbkaBCkE", "ABCE"))
    println(Result.abbreviation("abkaBCKE", "ABCE"))
    println(Result.abbreviation("abkcUCkE", "ABCE"))
    println(Result.abbreviation("abkaBCkE", "AbaE"))
    println(Result.abbreviation("abttkaddBcgggkssEwee", "AbaBE"))
  }
}
/*

abkaBCkE -> {AbkaBCkE, BCE} -> {ABkaBCkE, ABCE}


*/