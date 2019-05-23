package essential.part5

sealed trait Maybe[+A]{
  def fold[B](result:A =>B)(empty:B) = this match {
    case Full(x) => result(x)
    case Empty => empty
  }

  def map[B](fn:A=>B) = this match {
    case Full(x) => Full(fn(x))
    case Empty => Empty
  }

  def mapInTermsFlatMap[B](fn:A=>B) = flatMap(a => Full(fn(a)))



  def flatMap[B](fn: A => Maybe[B]): Maybe[B] = this match {
    case Full(x) => fn(x)
    case Empty => Empty
  }
}

final case class Full[A](get:A) extends Maybe[A]
final case object Empty extends Maybe[Nothing]