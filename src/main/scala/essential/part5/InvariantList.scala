package essential.part5

import scala.annotation.tailrec


sealed trait InvariantList[A]{
  def length:Int = {
    @tailrec def go(list:InvariantList[A] ,result:Int):Int = list match {
      case InvariantEnd() => result
      case InvariantPair(_, t) => go(t, result+1)
    }
    go (this,0)
  }

  @tailrec final def contains(value:A):Boolean = this match {
    case InvariantEnd() => false
    case InvariantPair(h, t) => h == value || t.contains(value)
  }
}

case class InvariantPair[A](head:A, tail:InvariantList[A]) extends InvariantList[A]
case class InvariantEnd[A]() extends InvariantList[A]
