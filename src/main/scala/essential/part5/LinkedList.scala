package essential.part5

import scala.annotation.tailrec


sealed trait LinkedList[+A]{
  def length:Int = {
    @tailrec def go(list:LinkedList[A] ,result:Int):Int = list match {
      case End => result
      case Pair(_, t) => go(t, result+1)
    }
    go (this,0)
  }

  @tailrec final def contains[B >: A](value:B):Boolean = this match {
    case End => false
    case Pair(h, t) => h == value || t.contains(value)
  }

  @tailrec final def fold[B](end:B)(f:(A,B)=>B):B = this match {
    case End => end
    case Pair(hd, tl) => tl.fold(f(hd, end))(f)
  }

  def foldl[B](end: B)(f: (A, B) => B): B =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd,tl.foldl(end)(f))
    }

  def apply[B>:A](i:Int):Result[B] = {
    @tailrec def go(list:LinkedList[A],current:Int):Result[B]= list match {
      case End => Failure(s"Index out of bounds")
      case Pair(h,_) if current == 0 => Success(h)
      case Pair(_,t) => go(t,current-1)
    }
    if (i<0) Failure("Negative index value") else go(this,i)
  }
}

case class Pair[A](head:A, tail:LinkedList[A]) extends LinkedList[A]
case object End extends LinkedList[Nothing]

object LinkedList{
  def apply[A](as:Seq[A]):LinkedList[A]= {
    def go (result:LinkedList[A], as:Seq[A]):LinkedList[A] = as.headOption match {
      case None => result
      case Some(a) => go(Pair(a,result), as.tail)
    }
    go(End, as)
  }


}