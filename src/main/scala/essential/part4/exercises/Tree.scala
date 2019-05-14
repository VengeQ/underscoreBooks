package essential.part4.exercises

sealed trait Tree {
  def sum:Int = this match {
    case Leaf(e) => e
    case Node(l,r) => l.sum + r.sum
  }

  def product:Int = this match {
    case Leaf(e) => e
    case Node(l,r) => l.product * r.product
  }
}

final case class Node(left:Tree, right:Tree) extends Tree
final case class Leaf(element: Int) extends Tree

object Tree{
  def apply(t:Tree): Tree = t match{
    case Node(l,r) => Node(l,r)
    case Leaf(e) => Leaf(e)
  }
}