package essential.part6.calculator

sealed trait Result[+T]

final case class Succ[T](get:Double) extends Result[T]
final case class Fail[T](get:String) extends Result[T]
final case object IncorrectFirstValue extends Result[Nothing]
final case object IncorrectSecondValue extends Result[Nothing]
final case object IncorrectZeroDenominator extends Result[Nothing]
final case class IncorrectOperator[T](get:String) extends Result[T]