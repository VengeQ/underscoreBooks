package essential.part4.calculator

sealed trait Expression {
  def eval:Calculation = this match {
    case Number(value) => Success(value)
    case Addition(l, r) =>
      l.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r1) =>
          r.eval match {
            case Failure(reason) => Failure(reason)
            case Success(r2) => Success(r1 + r2)
          }
      }
    case Subtraction(l, r) =>
      l.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r1) =>
          r.eval match {
            case Failure(reason) => Failure(reason)
            case Success(r2) => Success(r1 - r2)
          }
      }
    case Division(l, r) =>
      l.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r1) =>
          r.eval match {
            case Failure(reason) => Failure(reason)
            case Success(r2) =>
              if(r2 == 0)
                Failure("Division by zero")
              else
                Success(r1 / r2)
          }
      }
    case SquareRoot(v) =>
      v.eval match {
        case Success(r) =>
          if(r < 0)
            Failure("Square root of negative number")
          else
            Success(Math.sqrt(r))
        case Failure(reason) => Failure(reason)
      }
  }

}

final case class Addition(left:Expression, right: Expression) extends Expression
final case class Subtraction(left:Expression, right: Expression) extends Expression
final case class Number(value:Double) extends Expression
final case class Division(left: Expression, right:Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
object Expression{
  object implicits {
    implicit def NumericToNumber[A: Numeric](value: A) = Number(implicitly[Numeric[A]].toDouble(value))
    implicit def numberToDouble(value:Number) =value.value
  }
}