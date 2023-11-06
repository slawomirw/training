package dynamic.fibmodifier

/**
 * Given three integers, t1, t2, and n, compute and print the n-th term of a modified Fibonacci sequence: t[n] = t[n-2] + t[n-1]^2
 */
object Result {
  def fibonacciModified(t1: Int, t2: Int, n: Int): BigInt = {
    val buf = Array.ofDim[BigInt](3)
    buf(0) = BigInt(t1)
    buf(1) = BigInt(t2)
    var j = 2
    for (i <- 2 to n + 1) {
      val j2 = ((j - 2) + buf.length) % buf.length
      val j1 = ((j - 1) + buf.length) % buf.length
      buf(j) = buf(j2) + buf(j1) * buf(j1)
      j = (j + 1) % buf.length
    }
    buf(j)
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    println(Result.fibonacciModified(0, 1, 5))
  }
}
