package dynamic.sweets

import scala.collection.immutable.TreeMap

object Result {

  /**
   * For each operation, she can give 1, 2 or 5 pieces to all but one colleague.
   * Everyone who gets a piece in a round receives the same number of pieces.
   * Given a starting distribution, calculate the minimum number of operations needed
   * so that every colleague has the same number of pieces.
   */

  def equal(arr: Array[Int]): Int = {
    val globalMin = arr.min
    val minArr = Array(globalMin, globalMin - 1, globalMin - 2, globalMin - 3)

    val newTab = (0 to arr.length).map(_ => Array(0, 0, 0, 0)).toArray

    val five = 5
    val two = 2

    newTab.zipWithIndex.filter(elementIndex => elementIndex._2 > 0).foreach(e => {
      val elementIndex = e._2
      val prevElementResultVector = newTab(elementIndex - 1)
      val elementResultVector = e._1
      val element = arr(elementIndex - 1)
      minArr.zipWithIndex.foreach( vec => {
        val (vecMinLevel, vecIndex) = (vec._1, vec._2)
        // what is minimal step amount to reach vecMinLevel from startValue
        val startValue = element - vecMinLevel
        elementResultVector(vecIndex) = prevElementResultVector(vecIndex) +
          (startValue / five) +
          ((startValue - (startValue / five) * five) / two) +
          (startValue - (startValue / five) * five - ((startValue - (startValue / five) * five) / two) * two)
      })
    })

    newTab.last.min
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    println(s"1, 2, 3, 4, 5, 6, 7, 8 => ${Result.equal(Array(1, 2, 3, 4, 5, 6, 7, 8))}")
    println(s"1, 2, 3 => ${Result.equal(Array(1, 2, 3))}")
    println(s"1 => ${Result.equal(Array(1))}")
    println(s" => ${Result.equal(Array(53,361,188,665,786,898,447,562,272,123,229,629,670,848,994,54,822,46,208,17,449,302,466,832,931,778,156,39,31,777,749,436,138,289,453,276,539,901,839,811,24,420,440,46,269,786,101,443,832,661,460,281,964,278,465,247,408,622,638,440,751,739,876,889,380,330,517,919,583,356,83,959,129,875,5,750,662,106,193,494,120,653,128,84,283,593,683,44,567,321,484,318,412,712,559,792,394,77,711,977,785,146,936,914,22,942,664,36,400,857))}")
  }
}
