package withcats.part2

import org.scalatest.{FunSuite, Matchers}

class Part2Test extends FunSuite with Matchers{
  test("Monoid base test"){
    import cats.Monoid
    import cats.instances.int._ // for Monoid
    import cats.instances.option._

    val list =List(Some(23), Some(14), None, Some(36))
    val o1 =Order(100,10)
    val o2 = Order(200,3)

    add(list).get shouldBe 23+14+36

  }
}
