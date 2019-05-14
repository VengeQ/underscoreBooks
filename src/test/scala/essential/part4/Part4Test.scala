package essential.part4

import essential.part4.exercises.{Circle, Draw, End, Finite, Infinite, Leaf, Node, Rectangle, Red, Square, Tree, Yellow, divide}
import org.scalatest.{FunSuite, Matchers}

class Part4Test extends FunSuite with Matchers{
  test("Exercise4.1 test"){
    val sq = Square(11)

    sq.sides shouldBe 4
    sq.area shouldBe 11 * 11
    sq.perimeter shouldBe 44
  }

  test("Exercise4.2 test"){
    val sq = Square(11)
    val rec = Rectangle(10,12)
    val cir = Circle(14)

    Draw(sq)
    Draw(rec)
    Draw(cir)

    divide(2,2) shouldBe Finite(1)
    divide(2,0) shouldBe Infinite
  }

  test("Exercise4.5 test"){
    Red().next.next shouldBe Yellow()
  }

  test("Exercise4.6 test"){
    val example = exercises.Pair(1, exercises.Pair(2, exercises.Pair(3, End)))
    example.length shouldBe 3
    example.tail.length shouldBe 2
    End.length shouldBe 0

    example.product shouldBe 6
    End.product shouldBe 1

    example.double shouldBe exercises.Pair(2, exercises.Pair(4, exercises.Pair(6, End)))


    val tree = Tree(Node(   Node(Node(Leaf(2), Leaf(7)), Leaf(11))     ,     Node(Node(Leaf(2), Leaf(4)), Leaf(23))    ))
    tree.sum shouldBe 2+7+11+2+4+23
    tree.product shouldBe 2*7*11*2*4*23

    //val calc =
  }
}
