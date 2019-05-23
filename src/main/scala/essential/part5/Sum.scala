package essential.part5

sealed trait Sum[+A,+B]{
  val value:Any
  def fold[C](a: A=>C, b: B=>C) = this match {
    case Left(v) => a(v)
    case Right(v) => b(v)
  }

  def map[C](fna: A=>C, fnb: B=>C): C = ???

  def flatMap[C, AA >: A](f: B => Sum[AA, C]): Sum[AA, C] = this match {
      case Left(v) => Left(v)
      case Right(v) => f(v)
    }
}

case class Left[A](override val value:A) extends Sum[A,Nothing]
case class Right[B](override val value:B) extends Sum[Nothing,B]
