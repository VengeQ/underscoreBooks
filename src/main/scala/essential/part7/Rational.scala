package essential.part7

import scala.util.{Failure, Success, Try}

case class Rational(numerator:Int, denominator:Int)

object Rational{
  def apply(numerator: Int, denominator: Int): Try[Rational] = denominator match {
    case 0 => Failure(new ArithmeticException)
    case _ => Success(new Rational(numerator, denominator))
  }

}
