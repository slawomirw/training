package maxpeople

class Solution {
  def maxYeayPeopleNum(birth: List[Int], death: List[Int]):Int = {
    var c = 0
    var maxCount = Seq.empty[Int]

    val bs = birth.sorted
    var births = bs
    var deaths = death.sorted

    while (births.nonEmpty) {
      if (births.nonEmpty) {
        if (births.head < deaths.head) {
          births = births.tail
          c = c + 1
          maxCount = maxCount :+ c
        } else if (births.head == deaths.head) {
          births = births.tail
          deaths = deaths.tail
          maxCount = maxCount :+ c
        } else {
          deaths = deaths.tail
          c = c - 1
          maxCount = maxCount :+ c
        }
      }
    }
    bs(maxCount.indexOf(maxCount.max))
  }
}

object RunSolution {
  def main(args: Array[String]): Unit = {
    val sol = new Solution()
    println(sol.maxYeayPeopleNum(List(1, 2, 2, 5, 5, 5), List(5, 5, 8, 10, 10, 12)))
  }
}

