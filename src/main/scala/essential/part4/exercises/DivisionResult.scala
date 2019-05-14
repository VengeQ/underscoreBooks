package essential.part4.exercises

sealed trait DivisionResult[A]{
}

case class Finite[A](val result:A) extends DivisionResult[A]

case object Infinite extends DivisionResult[Nothing]
