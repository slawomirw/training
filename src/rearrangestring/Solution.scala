package rearrangestring

object Solution extends App {
  /*
   * Complete the 'getLargestString' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. INTEGER k
   */
  def getLargestString(s: String, k: Int): String = {
    val sortedString = s.toCharArray.sortWith((c1, c2) => c2 < c1)

    var i = 0
    var j = i
    var count = k
    val sb = new StringBuffer()
    var lastchi = -1

    println(s"sortedString = '${sortedString.toSeq}'")

    while (i < sortedString.length || j < i) {
      if (j < i && count > 0) {
        if (sortedString(j) == sortedString(lastchi)) {
          count = count - 1
        }
        if (count > 0) {
          println(s"app j=$j, count = $count, '${sortedString(j)}'")
          sb.append(sortedString(j))
          lastchi = j
          j = j + 1
        }
        if (j < i && sortedString(j) != sortedString(lastchi)) {
          j = i
        }
      } else if (j < i && count == 0 && i >= sortedString.length) {
        j = i
      } else if (i == 0 || sortedString(i) != sortedString(lastchi) || count > 0) {
        if (lastchi > -1 && sortedString(i) == sortedString(lastchi)) {
          count = count - 1
        } else {
          count = k
        }
        if (count > 0) {
          println(s"app i=$i, count = $count, '${sortedString(i)}'")
          sb.append(sortedString(i))
          lastchi = i
          if (i == j) {
            i = i + 1
            j = i
          } else {
            i = i + 1
          }
        }
      } else {
        println(s"app i=$i, j=$j, count = $count")
        i = i + 1
      }
    }

    sb.toString
  }

  val tests = Seq(
    ("axxzzxb", 2, "zzxxbxa"),
    ("zzzazzz", 2, "zzazz"),
    ("axxzzx", 2, "zzxxax"),
    ("axxzzx", 1, "zxzxax"),
    ("axxzzxaxxzzxaxxzzxaxxzzxaxxzzxaxxzzzxaxxzzxaxxzzxaxxzzxaxxzwezxaxxzzzxaxxzzxaxxzzxaxxzzxaxdxzzxaxxzzzxaxxzzgxaxxzzxaxxzzxaxxzzxaxxzzxxzzxaxxzzxaxxzzxaxxzzxaxxzzzxaxxzzxaxxzzxaxxzzxaxxzwezxaxxzzzxaxxzzxaxxzzxaxxzzxaxdxzzxaxxzzzxaxxzzgxaxxzzxaxxzzxaxxzzxaxxzzxxzzxaxxzzxaxxzzxaxxzzxaxxzzzxaxxzzxaxxzzxaxxzzxaxxzwezxaxxzzzxaxxzzxaxxzzxaxxzzxaxdxzzxaxxzzzxaxxzzgxaxxzzxaxxzzxaxxzzxaxxzzxxzzxaxxzzxaxxzzxaxxzzxaxxzzzxaxxzzxaxxzzxaxxzzxaxxzwezxaxxzzzxaxxzzxaxxzzxaxxzzxaxdxzzxaxxzzzxaxxzzgxaxxzzxaxxzzxaxxzzxaxxzzx", 100, "zxzxax"),
  )

  tests.foreach { t =>

    val out = getLargestString(t._1, t._2)
    println(s"in: '${t._1}', out: '$out'")
    assert(out == t._3)

  }
}
