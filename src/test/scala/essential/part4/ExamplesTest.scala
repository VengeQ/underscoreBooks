package essential.part4

import essential.part4.jsondata._
import essential.part4.calculator._
import essential.part4.calculator.Expression.implicits._
import org.scalatest.{FunSuite, Matchers}

class ExamplesTest  extends FunSuite with Matchers{
  test("CalculatorTest") {


    Addition(2,3).eval shouldBe Success(5)
    Division(2,0).eval shouldBe Failure("Division by zero")
    SquareRoot(-4).eval shouldBe  Failure("Square root of negative number")
  }

  test("JsonTest") {
    val a = JsNumber(23)
    val b = JsBoolean(true)
    val c =JsString("sd")


    val seq1 = SeqCell(a, SeqEnd)
    val seq2 = SeqCell(b,seq1)
    val seq3 = SeqCell(c,seq2)

    seq3.fromJson shouldBe """["sd", true, 23.0]"""

    val d1 =JsNumber(11)

    val k = ObjectCell("key",d1,ObjectEnd)
    val k2 = ObjectCell("digit", d1, k)
    val k3 =  ObjectCell("seq", seq3, k2)
    val k4 =ObjectCell("string",k3, k3)
    println(k4.fromJson)
    import JsonReaderInstances.personReader
    import JsonSyntax._


    val p =Person("Vasya", "Pupkin", 42)
    p.fromJson shouldBe Json.fromJson(p)

  }
}
