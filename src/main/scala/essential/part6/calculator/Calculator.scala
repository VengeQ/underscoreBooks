package essential.part6.calculator

case class Calculator() {


  def calculator(operand1: String, operator: String, operand2: String): Result[Double] = {
    def operandToDouble(value: String) = if (value matches """^\s*-?\s*\d+(\.\d+)*\s*$""") Some(value.toDouble) else None
    def calculate(operand1: Option[Double], operator: String, operand2: Option[Double]): Result[Double] = (operand1, operand2, operator.trim) match {
      case (None, Some(_), _) => IncorrectFirstValue
      case (Some(_), None, _) => IncorrectSecondValue
      case (Some(a), Some(b), "+") => Succ(a + b)
      case (Some(a), Some(b), "-") => Succ(a - b)
      case (Some(a), Some(b), "*") => Succ(a * b)
      case (Some(a), Some(b), "/") => divide(a, b)
      case t => IncorrectOperator(operator)
    }
    def divide(n: Double, d: Double): Result[Double] = d match {
      case 0 => IncorrectZeroDenominator
      case _ => Succ(n / d)
    }

    calculate(operandToDouble(operand1), operator, operandToDouble(operand2))
  }
}
