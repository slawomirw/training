package printtree

/*
 Write a function to print the given tree in pre-order (starting from the left of each node)
*/

object Solution extends App {
  // 0 1 2 3 4 5 6    7 8 9    10   11   12  13 14   15   16   17   18
  writePre(Array[String]("5","4","2","6","1","7","null","3","8","null","null","null","null","0","null","null","null","null","null"))

  def writePre(tree: Array[String], i: Int = 0): Unit = {
    // 0->1,2
    // 1->3,4
    // 2->5,6
    // 3->7,8
    // 4->9,10
    //
    // 0,1,3,7

    def right(i: Int) = 2 * i + 2

    def left(i: Int) = 2 * i + 1

    if(i < tree.length && tree(i) != "null" && left(i) < tree.length) {
      if(tree(left(i)) == "null") {
        print(tree(i)); print(",")
      } else {
        writePre(tree,left(i));
        print(tree(i)); print(",")
      }
      writePre(tree,right(i))
    }
  }
}
