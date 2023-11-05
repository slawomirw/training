package dynamic.sherlockandcost

/**
 * You will be given an array B and must determine an array A.
 * There is a special rule: For all i, A[i] <= B[i] . That is, A[i] can be any number you choose such that 1 <= A[i] <= B[i].
 * Your task is to select a series of A[i] given B[i] such that the sum of the absolute difference of consecutive pairs of A is maximized.
 * This will be the array's cost, and will be represented by the variable S = sum( | A[i] - A[i-1] | ).
 */

object Result {

  def cost(B: Array[Int]): Int = {
    (1 to B.length - 1).foldLeft(Set((1,0), (B(0), 0)))(
      (A, i) => {
        A.flatMap(choice => {
          val op = (Math.abs(B(i) - choice._1), choice._1 - 1)
          Set((B(i), choice._2 + op._1), (1, choice._2 + op._2))
        }).foldLeft(Map[Int, Int]())((m, t) => {
          if (!m.contains(t._1) || m(t._1) < t._2) m + (t._1 -> t._2) else m
        }).toSet
      }
    ).map(_._2).max
  }

}

object Solution {
  def main(args: Array[String]): Unit = {

    println("1501: " + Result.cost(Array(52,60,50,90,84,35,56,64,52,20,43,19,12,73,48,93,43,78,22,53,60,100,26,54,84)))
    println("5001: " + Result.cost(Array(69,19,15,81,93,100,35,18,81,16,65,20,4,45,81,83,90,14,82,85,43,7,64,76,33,47,95,12,78,93,14,22,97,36,65,66,36,26,59,81,81,82,44,79,89,94,32,94,22,33,19,46,46,62,19,47,70,91,97,62,17,43,11,25,74,73,64,98,73,7,40,8,2,96,89,81,80,17,88,13,31,44,64)))
    println("1011: " + Result.cost(Array(14,30,82,49,47,96,34,66,15,11,43,45,56,77,53,13,43,92,67,37)))
    println("4342: " + Result.cost(Array(64,62,62,18,74,68,93,57,7,45,34,73,75,10,22,23,35,37,62,30,88,23,79,74,21,87,80,73,56,20,27,69,5,86,72,79,79,49,20,41,70,100,99,57,39,51,23,33,8,38,39,65,31,85,24,60,16,94,15,72,40,45,57,21,19,79,54,77,18,99,63,35,17,48)))
    println("6186: " + Result.cost(Array(73,14,93,13,61,94,12,71,72,82,89,63,31,12,55,64,91,97,96,82,13,100,84,84,39,57,56,95,50,23,5,32,90,51,26,50,76,74,85,46,57,75,23,93,96,83,99,38,68,78,23,41,19,49,51,61,100,78,52,31,30,94,99,17,51,56,91,36,94,64,58,78,33,77,96,60,64,58,23,93,80,89,17,54,56,23,77,44,80,73,7,97,97,31,48,23,23)))
    /*
    println(Result.cost(Array(10, 1, 10, 1, 10)))
    println(Result.cost(Array(3, 15, 4, 12, 10)))
    println(Result.cost(Array(4, 7, 9)))
    */
    println(Result.cost(Array(1, 2, 3)))
  }
}

/**
 * (50)
 * 3, 15, 4, 12, 10 -> 15 1 12 1
 *
 *
 *
 */