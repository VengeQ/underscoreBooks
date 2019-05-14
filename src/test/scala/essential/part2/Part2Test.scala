package essential.part2

import org.scalatest.{FlatSpec, FunSuite, Matchers}
import part2._

class Part2Test extends FunSuite with Matchers{

  test("Exercises Part2.1"){
    (1 + 2) shouldBe 3
    (1 + 2).getClass.toString.toLowerCase shouldBe "int"
    "3".toInt shouldBe 3
    intercept[java.lang.NumberFormatException]{
      "Foo".toInt
    }
  }

  test("Exercises Part2.2"){
    "foo" take (1) shouldBe "f"
    (1).+(2).+(3) shouldBe 6
  }

  test("Exercises Part2.3"){
    24.getClass shouldBe classOf[Int]
    120l.getClass shouldBe classOf[Long]
    true.getClass shouldBe classOf[Boolean]
    42.0.getClass shouldBe classOf[Double]
    'a'.getClass shouldBe classOf[Char]
    "a".getClass shouldBe classOf[String]

    "Hello".getClass shouldBe classOf[String]
    println("hello").getClass shouldBe classOf[Unit]

  }

  test("Exercises Part2.4"){
    s"${Cat1.name} ${Cat1.colour} ${Cat1.food}" shouldBe "Oswald Black Milk"
    calc.square(2) shouldBe 4
    calc.cube(3) shouldBe 27
    argh.c + argh.b + argh.a shouldBe "3c31"
    alien.greet(Person) shouldBe "greet Vasya"
  }

  test("Exercises Part2.6"){
    def alienOrPredator(i:Int) = if (1<2) "Alien" else "Predator"
    for (i <- 1 to 100) alienOrPredator(i).getClass shouldBe classOf[String]
  }


}
