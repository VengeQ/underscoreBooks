package essential.ext

import org.scalatest.{FunSuite, Matchers}

class Ext extends FunSuite with Matchers {

  test("pattern matching test") {
    zeroOrOne(1) shouldBe true
    zeroOrOne(0) shouldBe true

    bindPattern(1, 1) shouldBe 1
    bindPattern2(1, 1) shouldBe 3
  }

  test("redux test") {
    def streamOfNumber = Stream.iterate(1)(_ + 1)

    val numbers = List("one", "two", "three")

    streamOfNumber.zip(numbers).toList shouldBe List((1, "one"), (2, "two"), (3, "three"))
  }

}
