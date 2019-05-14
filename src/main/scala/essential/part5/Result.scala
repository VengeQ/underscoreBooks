package essential.part5

sealed trait Result[A]{
  def get = this match {
    case Success(x) => x
    case Failure(x) => throw new IllegalArgumentException("None.get")
  }
}
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]