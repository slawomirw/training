package stairs

object Solution extends App {

  def countWays(height: Int, steps: Array[Int]): Int = {
    val buf = Array.ofDim[Int](height)

    def countWays(stepId: Int, steps: Array[Int], b: Array[Int]): Int = {
      stepId match {
        case 0 => 1
        case n =>
          val cs = steps.map { s =>
            if (s <= n) {
              b(stepId - s)
            } else {
              0
            }
          }.sum

          println(s"for step $stepId: $cs")

          cs
      }
    }

    for(i <- 0 until height + 1) {
      buf(i) = countWays(i, steps, buf)
    }

    buf.last
  }

  val tests = Seq((4, Array(1, 2, 3), 9))

  tests.zipWithIndex.foreach{ ti =>
    val t = ti._1
    val testNo = ti._2 + 1

    print(s"test no $testNo, ")

    val result = countWays(t._1, t._2)
    println(s"result = $result")

    assert(result == t._3)
  }

}
