package hackerland

import scala.collection.mutable.ArrayBuffer
import scala.collection.{immutable, mutable}

object Result {

  /*
   * Complete the 'roadsInHackerland' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. 2D_INTEGER_ARRAY roads
   */

  def roadsInHackerland(n: Int, roads: Array[Array[Int]]): String = {
    val parent = mutable.Map.empty[Int, Int]
    val graph = mutable.Map[Int, immutable.Vector[Int]]()

    def getParent(node: Int): Int = if (parent.getOrElse(node, -1) == (-1)) { node } else { getParent(parent(node)) }

    roads.sortWith((a1, a2) => a1(2) < a2(2)).foreach { r =>
      var n1 = -1
      var n2 = -1
      if (r(0) < r(1)) { n1 = r(0); n2 = r(1) } else { n2 = r(0); n1 = r(1) }
      val p1 = getParent(n1)
      val p2 = getParent(n2)
      if (p1 != p2) {
        parent.put(p2, p1)
        graph.put(n1, graph.getOrElse(n1, immutable.Vector.empty[Int]) :+ r(2))
        graph.put(n2, graph.getOrElse(n2, immutable.Vector.empty[Int]) :+ r(2))
      }
    }

    val counts = Array.ofDim[Int](roads.length * 2)
    val binNums = mutable.ArrayBuffer.empty[Int]
    def dfs(node: Int, enteringEdge: Int = -1): Int = {
      val nodeEdges = graph(node).filterNot(_ == enteringEdge)
      nodeEdges.foldLeft(0)((prevBelowsCount, edge) => {
        val childOfCurrEdge = graph.find(n => n._1 != node && n._2.contains(edge))
        val countBelow = childOfCurrEdge.map(child => 1 + dfs(child._1, edge)).getOrElse(0)
        counts(edge) = countBelow * (n - countBelow)
        countBelow + prevBelowsCount
      })
    }
    dfs(getParent(parent.keySet.min))
    for (i <- counts.indices) {
      if (counts(i) > 0) {
        var c = counts(i)
        var expValue = 0
        while (c > 0) {
          if (c % 2 == 1) {
            if (expValue == 0) {
              binNums.addOne(i)
            } else {
              counts(expValue + i) = counts(expValue + i) + 1
            }
          }
          c = c >>> 1
          expValue = expValue + 1
        }
      }
    }
    var prev = binNums.max
    val out = ArrayBuffer.empty[String]
    for (i <- binNums.view.reverse) {
      if (prev - i > 1) {
        out.addOne("0" * (prev - i - 1))
      }
      out.addOne("1")
      prev = i
    }
    out.addOne("0" * prev)
    out.mkString
  }

}

object Solution {
  def hackerLand(args: Array[String]): String = {
    val lines = args.iterator
    val firstMultipleInput = lines.next().replaceAll("\\s+$", "").split(" ")

    val n = firstMultipleInput(0).toInt

    val m = firstMultipleInput(1).toInt

    val roads = Array.ofDim[Int](m, 3)

    for (i <- 0 until m) {
      roads(i) = lines.next().replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }

    Result.roadsInHackerland(n, roads)
  }
}

object RunSolution {
  def main(args: Array[String]): Unit = {
    println(Solution.hackerLand(Array(
      "2 1",
      "1 2 0")).equals("1"))
    println(Solution.hackerLand(Array(
      "4 2",
      "1 2 0",
      "3 4 1"
    )).equals("11"))
    println(Solution.hackerLand(Array(
      "5 4",
      "1 2 3",
      "1 3 2",
      "1 4 1",
      "1 5 0"
    )).equals("111100"))
    println(Solution.hackerLand(Array(
      "5 6",
      "1 2 3",
      "4 5 4",
      "1 3 2",
      "1 4 1",
      "3 2 5",
      "1 5 0"
    )).equals("111100"))
    println(Solution.hackerLand(Array(
      "5 6",
      "1 3 5",
      "4 5 0",
      "2 1 3",
      "3 2 1",
      "4 3 4",
      "4 2 2")).equals("1000100"))
    println(Solution.hackerLand(Array(
      "20 19",
      "1 2 10",
      "1 3 9",
      "1 4 8",
      "1 5 7",
      "6 1 6",
      "1 7 5",
      "1 8 4",
      "9 1 3",
      "1 10 2",
      "1 11 1",
      "12 1 0",
      "1 13 11",
      "14 1 12",
      "1 15 13",
      "16 1 14",
      "1 17 15",
      "18 1 16",
      "1 19 17",
      "20 1 18",
    )).equals("100101111111111111101101"))
    println(Solution.hackerLand(Array(
      "21 19",
      "1 2 10",
      "1 3 9",
      "2 3 20",
      "1 4 8",
      "1 5 7",
      "6 1 6",
      "1 7 5",
      "1 8 4",
      "9 1 3",
      "1 10 2",
      "1 11 1",
      "12 1 0",
      "1 13 11",
      "14 1 12",
      "1 15 13",
      "16 1 14",
      "1 17 15",
      "18 1 16",
      "1 19 17",
      "20 1 18",
    )).equals("10011111111111111101100"))
  }
}

