package dynamic.coinchange

import scala.collection.immutable.TreeMap

/**
 * Find how many ways change can be done for given coins and amount.
 */
object Result {
  def getWays(n: Int, c: Array[Long]): Long = {

    val tmp = c.foldLeft(TreeMap[Long, Array[Long]]())((a, coin) => {
      val tmp = Array.ofDim[Long](n + 1)
      tmp(0) = 1
      a.updated(coin, tmp)
    })

    tmp.keySet.foldRight(tmp)((coin, _) => {
      (1 to n).foldLeft(tmp)((array, amount) => {
        val greaterCoins = tmp.keySet.filter(_ >= coin)
        array(coin)(amount) = greaterCoins.foldLeft(0L)((sum, curCoin) => {
          if (amount - curCoin >= 0) {
            sum + array(curCoin)((amount - curCoin).asInstanceOf[Int])
          } else {
            sum
          }
        })
        array
      })
    }).head._2(n)
  }
}

object Solution {
  def main(args: Array[String]):Unit = {
    println(Result.getWays(7, Array(1, 2, 5)))
    println(Result.getWays(4, Array(1, 2, 3)))
    println(Result.getWays(10, Array(2, 5, 3, 6)))
    println(Result.getWays(3, Array(8, 3, 2, 1)))
  }
}

