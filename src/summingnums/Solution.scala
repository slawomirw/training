package summingnums

class Solution {
  def findComponents(numbers: List[Int], target: Int):Seq[List[Int]] = {
    def findComponents(numbers: List[Int], target: Int, sums: List[Int]):Seq[List[Int]] = {
      val csum = sums.sum
      var iv = 0;
      numbers.foldLeft(Seq.empty[List[Int]])((l, i) => {
        iv = iv + 1
        if (csum + i == target) {
          l :+ (sums :+ i)
        } else if (csum + i < target) {
          l ++ findComponents(numbers.drop(iv), target, sums :+ i)
        } else {
          l
        }
      })
    }
    findComponents(numbers, target, List.empty[Int])
  }
}

object RunSolution {
  def main(args: Array[String]): Unit = {
    val sol = new Solution()
    println(sol.findComponents(List(1, 2, 3, 5, 7), 7))
  }
}

