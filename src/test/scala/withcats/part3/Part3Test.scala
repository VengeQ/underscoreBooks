package withcats.part3

import org.scalatest.{FunSuite, Matchers}

class Part3Test extends FunSuite with Matchers{
  import cats.Functor
  import cats.instances.list._
  import cats.instances.option._
  import cats.instances.function._ // for Functor
  import cats.syntax.functor._ // for map
  test("Functors test") {
    val list1 = List(1, 2, 3)
    val list2 =Functor[List].map(list1)(_ * 2) shouldBe List(2,4,6)
    val option1=Option(123)
    Functor[Option].map(option1)(_.toString).get shouldBe "123"
    val func =(x:Int) => x+1
    val liftedFunc=Functor[Option].lift(func)
    liftedFunc(Option(3)) shouldBe Option(4)

    val r =scala.util.Random.nextInt(10000)
    val func1 = (a: Int) => a + 1
    val func2 = (a: Int) => a * 2
    val func3 = (a: Int) => a + "!"
    val func4 = func1.map(func2).map(func3)
    func4(r) shouldBe func3(func2(func1(r)))

    val t =Branch(Branch(Leaf(1),Leaf(4)),Leaf(2))
    Functor[Tree].map(Tree.branch(Tree.leaf(10), Tree.leaf(20)))(_ * 2) shouldBe Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)

    import PrintableInstances._
    Printable.format("hello") shouldBe "\"hello\""
    Printable.format(true) shouldBe "yes"
    Printable.format(Box("23")) shouldBe "\"23\""

    import CodecInstances._
    Codec.encode(Box("23")) shouldBe "23"
    Codec.decode[Box[Int]]("23").value shouldBe 23
  }
}
