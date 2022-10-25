package amazon.sumlist

/**
 * In the list representing packages of data find max sum of the pair's sizes of packages processed together.
 * Packages are processed in pairs 0, N; 1, N-1; ...
 */

class Package (size: Int, var next: Package) {
  def getSize:Int = size
  def getNext:Option[Package] = Option(next)
  def setNext(nnext: Package):Unit = this.next = nnext
}

object Solution extends App {
  val examples = Seq(
    new Package(1, new Package(5, new Package(3, new Package(1, null)))),
    new Package(5, new Package(3, new Package(1, null))),
    new Package(1, new Package(3, new Package(1, null))),
    new Package(5, new Package(3, new Package(1, new Package(1, null)))),
    new Package(1, null)
  )

  def findMax(pack: Package): Int = {
    var p: Package = pack
    var last: Package = null
    var s = 0
    while (p.getNext.isDefined) {
      val tmp = p.next
      p.setNext(last)
      last = p
      p = tmp
      s = s + 1
    }
    if (s > 0) {
      var i = s - 1
      while(i > s / 2) {
        val tmp = last.next
        last.setNext(p)
        p = last
        last = tmp
        i = i - 1
      }
      var sum = 0
      if (s % 2 == 0) {
        sum = last.getSize
        last = last.next
      }
      while (last != null) {
        sum = Math.max(sum, last.getSize + p.getSize)
        last = last.next
        p = p.next
      }
      sum
    } else {
      pack.getSize
    }
  }

  examples.foreach(ep => {
    println(findMax(ep))
  })

}
