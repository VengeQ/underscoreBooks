package essential.part5

import org.scalatest.{FunSuite, Matchers}

class Part5Test extends FunSuite with Matchers {
  test("List test") {
    val example = Pair(1, Pair(2, Pair(3, End)))
    example.length shouldBe 3
    End.length shouldBe 0
    example.tail.length shouldBe 2
    example.map(_ + 2).fold(0)(_ + _) shouldBe 1+2+2+2+3+2
    example.map(_ * 2) shouldBe Pair(2, Pair(4, Pair(6, End)))

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

  test("Tree test") {
    val tree =Node(Node(Leaf(1),Node(Leaf(2),Leaf(5))), Leaf(3))
    val tree2: Tree[String] =
      Node(Node(Leaf("To"), Leaf("iterate")),
        Node(Node(Leaf("is"), Leaf("human,")),
          Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

    tree.fold((r:Int) => r:Int)((l,r)=>l+r) shouldBe 1+2+5+3
    val ft = tree2.fold[String](identity(_))(_ + " "+ _)
    ft shouldBe "To iterate is human, to recurse divine"
    println(ft)
  }

  test("Sum Type Test") {
    val a:Sum[Int,String] =Left[Int](1)
    val b =Right[String]("foo")
    a.value shouldBe 1
    b.value shouldBe "foo"

    val e1:Maybe[Int] =Empty
    val e2 =Full(1)
    e2 shouldBe Full(1)
    e1 shouldBe Empty
    e2.map(_ + 4) shouldBe Full(5)
    e2.mapInTermsFlatMap(_ + 4) shouldBe Full(5)

    e1.fold(_+3)(2) shouldBe 2
    e2.fold(_+3)(2) shouldBe 4

    a.fold(_+3, (x:String) =>x+"foo") shouldBe a.value.asInstanceOf[Int]+3 //оч плохо
    b.fold((x:Int)=>x+3, _+"foo") shouldBe b.value+"foo"

    val s:Sum[Double, String] = Right("string")

    s.flatMap(str => Right(str+str)).value shouldBe "stringstring"

  }

  test("Scala list ex") {
    val list =List(1,2,3)
    list.map(x=>(x,-x))
    list.flatMap(x=>List(x,-x))
    val list2: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
    val afterMap = list2.map((maybe: Maybe[Int]) => maybe.flatMap(value => if (value%2==0) Full(value) else Empty))
    afterMap shouldBe List(Empty, Full(2), Empty)

  }
}
