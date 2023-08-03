package dynamic.candies

object Result {

  /*
    Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
    All the children sit in a line and each of them has a rating score according to his or her performance in the class.
    Alice wants to give at least 1 candy to each child.
    If two children sit next to each other, then the one with the higher rating must get more candies.
    Alice wants to minimize the total number of candies she must buy.
  */

  def candies(n: Int, arr: Array[Int]): Long = {
      val candies = Array.ofDim[Int](n)

      candies(0) = 1;

      for (i <- 1 to n - 1) {
        if (arr(i) > arr(i - 1)) candies(i) = candies(i - 1) + 1 else candies(i) = 1;
      }
      for (i <- (n - 2) to 0 by -1) {
        if (arr(i) > arr(i + 1) && candies(i) <= candies(i + 1)) candies(i) = candies(i + 1) + 1;
      }

      println(candies.toList)

      return candies.foldLeft(0L)((sum, v) => sum + v)
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    println(Result.candies(3, Array(1, 2, 2)));
    println(Result.candies(10, Array(2, 4, 2, 6, 1, 7, 8, 9, 2, 1)))
  }
}

//    2 , 4 , 2 , 6 , 1 , 7 , 8 , 9 , 2 , 1

//    1   2   1   2   1   2   3   4   2   1


//   2 , 4 , 2 , 6 , 1 , 7 , 5 , 3 , 2 , 1
//           1       u  max  t   x  min  y
//                      u+1         x-1
//                      t+1         y+1

// y = if > [n-1] +1 else 1

//    2 , 4 , 2 , 6 , 1 , 7 , 8 , 9 , 2 , 1
/*
                                  *
                              *   *
                          *   *   *
                  *       *   *   *
                  *       *   *   *
          *       *       *   *   *
          *       *       *   *   *
      *   *   *   *       *   *   *   *
      *   *   *   *   *   *   *   *   *   *
*/
//    1   2   1   2   1   2   3   4   1   1
//                        2   3   4  +1   1

//    1   2   1   2   1   2   3   4   2   1
