package essential.part5

import org.scalatest.{FunSuite, Matchers}

class Part5Test extends FunSuite with Matchers {
  test("List test") {
    val example = Pair(1, Pair(2, Pair(3, End)))
    example.length shouldBe 3
    End.length shouldBe 0
    example.tail.length shouldBe 2

    val a1:Animal = Animal("animal1")
    val a2:Animal = Animal("animal2")
    val c1:Cat = new Cat("cat1")
    val c2:Cat = new Cat("cat2")
    val animals =Pair(a1, Pair(a2, End))
    val cats = Pair(c1, Pair(c2, End))
    val animalWithCats=Pair(a1,Pair(c1,End))

    animalWithCats.contains(new Cat("vasya")) shouldBe false
    animalWithCats.contains(c1) shouldBe true
    cats.contains(a1) shouldBe false
    animals.contains(c1) shouldBe false

    val animalsInv:InvariantList[Animal] = InvariantPair(a1, InvariantPair(a2, InvariantEnd()))
    val catsInv:InvariantList[Cat] = InvariantPair(c1, InvariantPair(c2, InvariantEnd()))
    animalsInv.contains(c1) shouldBe false
    //catsInv.contains(a1) shouldBe false
    example(0).get shouldBe 1
    example(1).get shouldBe 2
    example(2).get shouldBe 3
    example(3) shouldBe Failure("Index out of bounds")
    example(-2) shouldBe Failure("Negative index value")
  }

  test("fold test"){
    val example = LinkedList(Seq(1,2,3))
    example.fold(3)(_ + _) shouldBe 9
    example.fold(0)((_, y) => y + 1) shouldBe 3
    example.fold(1)(_ * _) shouldBe 6
    example.foldl(End:LinkedList[Int])((value, list)=> Pair(value*2,list)) shouldBe LinkedList(Seq(2, 4, 6))
  }
}
