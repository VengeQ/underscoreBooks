package essential.part5

sealed trait Tree[+A]{

  def fold[B](leaf: A => B)(f: (B, B) => B): B = this match {
    case Leaf(elem) => leaf(elem)
    case Node(l,r) => f(l.fold(leaf)(f),r.fold(leaf)(f))
  }

}
final case class Node[A](left:Tree[A], right:Tree[A]) extends Tree[A]
final case class Leaf[A](element:A) extends Tree[A]
