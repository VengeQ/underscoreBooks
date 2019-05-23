package creative.part2

import org.scalatest.{FlatSpec, FunSuite, Matchers}

class Part2Test extends FunSuite with Matchers{
  test("Method style should be equal Operator style"){
    val a= "asdads"
    val b ="fsbsw"
    a ++ b shouldBe a.++(b)
  }

  test("Type and values test") {
    1 + 2 shouldBe 3
    "3".toInt shouldBe 3

    intercept[IllegalArgumentException]{
      "Electric blue".toInt
    }
    "Electric blue".take(1) shouldBe "E"

    assertDoesNotCompile(""""Electric blue".take("blue")"""")
    1 + ("Moonage daydream".indexOf("N")) shouldBe 0

    intercept[ArithmeticException]{
      1 / (1 + ("Moonage daydream".indexOf("N")))shouldBe Double.PositiveInfinity
    }

      1 / 1 + ("Moonage daydream".indexOf("N")) shouldBe 0

  }
}
