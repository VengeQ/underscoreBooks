package essential.part4.exercises

import scala.annotation.tailrec

sealed trait IntList{
  def length = {
    @tailrec  def go(list:IntList ,l:Int):Int = list match {
      case End => l
      case Pair(_, tail) => go(tail, l+1)
    }
    go(this,0)
  }

  def product ={
    @tailrec  def go(list:IntList, result:Int):Int = list match {
      case End => result
      case Pair(head, tail) => go(tail, head*result)
    }

    go(this,1)
  }

  def double = {
    @tailrec  def go(list:IntList, result: IntList):IntList = list match {
      case End => result.reverse
      case Pair(head, tail) => go(tail, Pair(head*2,result))
    }

    go(this, End)
  }

  def reverse ={
    @tailrec  def go(list: IntList, result: IntList):IntList = list match {
      case End => result
      case Pair(head, tail) => go(tail ,Pair(head,result))
    }

    go(this, End)
  }
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList