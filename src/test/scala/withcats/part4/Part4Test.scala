package withcats.part4

import cats.Monad
import cats.data.Reader
import org.scalatest.{FunSuite, Matchers}
import withcats.part4.Factorial.factorial
import withcats.part4.ReaderExample.Db
import cats.FlatMap

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._

class Part4Test extends FunSuite with Matchers{
  test("flatMap test"){
    stringDivideBy("23","11").get shouldBe 2
    stringDivideBy("23","a11") shouldBe None

     (1 to 3).toList.flatMap( x => (4 to 5).toList.map(y=>(x,y))) shouldBe (for {
      x <- (1 to 3).toList
      y <- (4 to 5).toList
    } yield (x, y))

  }

  test("Monads example test") {
    import cats.Monad
    import cats.instances.option._ // for Monad
    import cats.instances.list._ // for Monad

     Monad[Option].point(23) shouldBe Option(23)
     Monad[List].flatMap(List(1,2,3))(v => List(v,v*2)) shouldBe List(1,2,2,4,3,6)

     val doubleToString = Monad[List].lift((x:Double) => x.toInt.toString)
     doubleToString(List(1,2,3)).foldRight("")(_+_) shouldBe "123"
     Monad[List].fproduct(List(1,2,3))(_ * 2) shouldBe List((1,2),(2,4),(3,6))

    sumSquare(Option(3),Option(5)) shouldBe Option(3*3 + 5*5)
    import cats.Id
    sumSquare(2:Id[Int],3:Id[Int]) shouldBe ((2*2 + 3*3):Id[Int])

    import cats.syntax.either._
    val y = 3.asRight[String]
    val x =4.asRight[String]
    val z ="wtf".asLeft[Int]

   val p = y.flatMap((value:Int) => z.map((value2:Int) => value2+value))
    p shouldBe Left("wtf")


  }

  test("Writer test") {
    //val (log, res) = Factorial.factorial(5).run
    //println(log)
    //println(res)

    val (log, res) = Factorial.factorial(5).run


    val Vector((logA, ansA), (logB, ansB)) =
      Await.result(Future.sequence(Vector(
        Future(factorial(3).run),
        Future(factorial(5).run)
      )), 10.seconds)

    println(logA)
    println(ansA)
    println(logB)
    println(ansB)
  }

  test("Reader test") {
    val r = ReaderExample.Db(Map(1 ->"first",2 -> "second"), Map("first" -> "fp", "second" -> "sp"))
    Db.checkPassword("second", "sp").run(r) shouldBe true
    Db.checkPassword("first", "asda").run(r) shouldBe false
    Db.checkPassword("thrid","pass").run(r) shouldBe false
  }

  test("State test"){
    import cats.data.State
    val a = State[Int, String] { state =>
      (state, s"The state is $state")
    }

    println(a.run(10).value)
    println(a.runS(19).value)
    println(a.runA(9).value)

  }

  test("tree test") {
    import withcats.part4.Tree.treeMonad._
    import withcats.part4.Tree._
    import cats.syntax.functor._ // for map
    import cats.syntax.flatMap._ // for flatMap
    val a= branch(leaf(100), leaf(200)).
      flatMap(x => branch(leaf(x - 1), leaf(x + 1)))
    println(a)

    val a2 =for {
      a <- branch(leaf(100), leaf(200))
      b <- branch(leaf(a - 10), leaf(a + 10))
      c <- branch(leaf(b - 1), leaf(b + 1))
    } yield c
    println(a2)
  }
}
