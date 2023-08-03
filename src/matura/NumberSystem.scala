package matura

def convert(number: Long, base: Int): String =
  def div(number: Long): Long = number / base
  def lastDigit(number: Long): Int = (number % base).asInstanceOf[Int]
  def loop(curNum: Long) : Seq[Int] =
    if (curNum > 0)
       loop(div(curNum)) :+ lastDigit(curNum)
    else
      Nil

  loop(number).map(v => v match {
    case digit if digit < 10 => digit.toString
    case letter => ('A' - 10 + letter).asInstanceOf[Char]
  }).mkString

@main def check(): Unit =
  println("10b2:\t" + convert(10, 2))
  println("10b3:\t" + convert(10, 3))
  println("10b4:\t" + convert(10, 4))
  println("10b10:\t" + convert(10, 10))
  println("10b16:\t" + convert(10, 16))
  println("253b2:\t" + convert(253, 2))
  println("16375444b16:\t" + convert(16375444, 16))
