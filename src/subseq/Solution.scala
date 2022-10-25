package subseq

/**
 * def findLIS(s: Array[Int]): Int = {
 * println(s.toSeq)
 * if(s.isEmpty) {
 * return 0
 * }
 * val ls = Array.ofDim[Int](s.length)
 * ls(ls.length - 1) = 1
 * for(i <- s.indices.reverse.drop(1)) {
 * ls(i) = if (s(i) < s(i+1)) ls(i + 1) + 1 else ls(i+1)
 * }
 * ls.max
 * }
 */

object Solution extends App {

  def findLIS(s: Array[Int]): Int = {

    def findLISIn(first: Seq[Int], sls: Array[Int]): Int = {
//      println(s"${first.toSeq}")
      if (sls.isEmpty) {
        first.size
      } else if (sls.length == 1) {
        if (first.nonEmpty && first.last < sls.head) {
          first.size + 1
        } else {
          first.size
        }
      } else {
        if (first.isEmpty || first.last < sls.head) {
          Math.max(
            findLISIn(first :+ sls.head, sls.tail),
            findLISIn(first, sls.tail)
          )
        } else {
          findLISIn(first, sls.tail)
        }
      }
    }

    findLISIn(Seq.empty[Int], s)
  }

  val test = Seq(
    (Array(1, 4, 3), 2),
    (Array(11, 2, 40, 38, 3, 4, 50, 58, 5, 6), 5),
    (Array(10, 70, 50, 30, 10, 4, 40, 60, 1, 6, 2), 4),
    (Array(1, 2, 2, 3, 1, 6), 4),
    (Array(6, 5, 1, 1, 2, 3, 1), 3),
    (Array(808, 7905, 9625, 7874, 8131, 8756, 12843, 11408, 9260, 15106, 11092, 16533, 16862, 17319, 19108, 22263, 22614, 15376, 22711, 25360), 14)
  )

  test.zipWithIndex.foreach { t =>
    val out = findLIS(t._1._1)
    println(s"test ${t._2}, \t\tin: ${t._1._1.toSeq}, \n\t\tout: $out")
    assert(out == t._1._2)
  }

}
