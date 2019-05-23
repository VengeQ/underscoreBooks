package withcats

import cats.Functor

package object part3 {


  implicit def treeFunctor:Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Leaf(a) => Leaf(f(a))
      case Branch(l,r) => Branch(map(l)(f),map(r)(f))
    }
  }


  final case class Box[A](value: A)
}
