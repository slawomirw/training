package subarrays

object Solution extends App {
  // find the longest/shortest/all subarrays which adds to N

  def findSubarrWhichAddsTo(a: Array[Int], ss: Long, op: ((Int, Int), Option[(Int, Int)]) => Boolean): Option[(Int, Int)] = {
    var s = 0
    var e = 0
    var current: Option[(Int, Int)] = None

    def sum: Long = {
      a.to(LazyList).slice(s, s + e + 1 - s).sum
    }

    var ac = true
    while (ac) {
      val vsum = sum
      if (vsum == ss) {
        if (op((s, e), current)) {
          current = Some((s, e))
        }
      }
      if (vsum < ss && e < a.length - 1 || e < a.length - 1 && s == e) {
        e = e + 1
      } else if (s < e) {
        s = s + 1
      } else {
        ac = false
      }
    }
    current
  }

  def findSubarrWhichAddsToNumOfGivenSize(a: Array[Int], ss: Long, wsize: Int): Option[(Int, Int)] = {
    a.dropRight(wsize - 1)
      .zipWithIndex.map(ni => a.slice(ni._2, ni._2 + wsize))
      .zipWithIndex.filter(_._1.sum == ss)
      .map(t => (t._2, t._2 + wsize - 1))
      .headOption
  }

  def findSubarrWhichAddsToMax(a: Array[Int]): Option[(Int, Int)] = {
    var currenti = 0
    var maxSum = 0L
    var o: Option[(Int, Int)] = None

    for (i <- a.indices) {
      if (currenti <= i) {
        val css = a.slice(currenti, i + 1).map(_.toLong).sum
        if (css < 0L) {
          currenti = i + 1
        } else if (css >= maxSum) {
          maxSum = css
          o = Some((currenti, i))
        }
      }
    }
    o
  }

  // test longest

  val testsLargest = Seq(
    (9L, Array(1, 2, 3, 4, 5, 6, 7, 8, 9), Some((1, 3))),
    (5L, Array(2, 1, 1, 2, 5, 4, 2, 1, 2), Some((6, 8))),
    (4L, Array(10, 9, 6, 3, 1, 1, 1, 1, 1), Some((4, 7))),
    (4L, Array(1, 0, 0, 1, 0, 1, 10, 0, 0), None),
  )

  val testsShortest = Seq(
    (9L, Array(1, 2, 3, 4, 5, 6, 7, 8, 9), Some((8, 8))),
    (5L, Array(2, 1, 1, 2, 5, 4, 2, 1, 2), Some((4, 4))),
    (4L, Array(10, 9, 6, 3, 1, 1, 1, 1, 1), Some((3, 4))),
    (4L, Array(1, 0, 0, 1, 0, 1, 10, 0, 0), None),
  )

  val testsGivenSize = Seq(
    (3, 9L, Array(1, 2, 3, 4, 5, 6, 7, 8, 9), Some((1, 3))),
    (1, 5L, Array(2, 1, 1, 2, 5, 4, 2, 1, 2), Some((4, 4))),
    (4, 4L, Array(10, 9, 6, 3, 1, 1, 1, 1, 1), Some((4, 7))),
    (1, 4L, Array(1, 0, 0, 1, 0, 1, 10, 0, 0), None),
  )

  val testsMaxSumSubarr = Seq(
    (Array(1, 2, -4, 4, 5, 6, 7, -3, 9), Some((3, 8))),
    (Array(2, -1, 1, 2, -5, 4, 2, 1, 2), Some((5, 8))),
    (Array(10, -9, -6, -3, 1, 1, 1, 1, 1), Some((0, 0))),
    (Array(1, 0, 0, -1, 0, -1, -10, 0, 0), Some((0, 2))),
  )

  var longest = (c: (Int, Int), ref: Option[(Int, Int)]) => ref.map(t => c._2 - c._1 > t._2 - t._1).getOrElse(true)

  var shortest = (c: (Int, Int), ref: Option[(Int, Int)]) => ref.map(t => c._2 - c._1 < t._2 - t._1).getOrElse(true)

  def givenSize(size: Int) = (c: (Int, Int), ref: Option[(Int, Int)]) => {
    if (c._2 - c._1 != size - 1) {
      false
    } else {
      ref.map(t => c._2 - c._1 < t._2 - t._1).getOrElse(true)
    }
  }

  testsLargest.zipWithIndex.foreach { t =>
    val res = findSubarrWhichAddsTo(t._1._2, t._1._1, longest)
    println(s"longest test ${t._2} -> $res")
    assert(res == t._1._3)
  }
  testsShortest.zipWithIndex.foreach { t =>
    val res = findSubarrWhichAddsTo(t._1._2, t._1._1, shortest)
    println(s"shortest test ${t._2} -> $res")
    assert(res == t._1._3)
  }
  val t4_1 = testsGivenSize.zipWithIndex.foreach { t =>
    val res = findSubarrWhichAddsTo(t._1._3, t._1._2, givenSize(t._1._1))
    println(s"given size 1 test ${t._2} -> $res")
    assert(res == t._1._4)
  }
  val t4_2 = testsGivenSize.zipWithIndex.foreach { t =>
    val res = findSubarrWhichAddsToNumOfGivenSize(t._1._3, t._1._2, t._1._1)
    println(s"given size 2 test ${t._2} -> $res")
    assert(res == t._1._4)
  }
  val t5 = testsMaxSumSubarr.zipWithIndex.foreach { t =>
    val res = findSubarrWhichAddsToMax(t._1._1)
    println(s"max sum subarr test ${t._2} -> $res")
    assert(res == t._1._2)
  }

}
