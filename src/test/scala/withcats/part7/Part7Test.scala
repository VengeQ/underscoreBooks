package withcats.part7

import cats.Monoid
import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.{ExecutionContext, Promise, forkjoin}

class Part7Test extends FunSuite with Matchers {
  test("Fold  test") {
    List(1, 2, 3).foldLeft(List(0))((accum, current) => current :: accum) shouldBe List(3, 2, 1, 0)
    List(1, 2, 3).foldRight(List(0))((cur, acc) => cur :: acc) shouldBe List(1, 2, 3, 0)

    WithFold.filter(List(1, 2, 3, 4, 5))(_ % 2 == 0) shouldBe List(2, 4)
    WithFold.sum(List(1, 3, 6)) shouldBe 10
    WithFold.map(List(1, 2, 3))(_ * 2) shouldBe List(2, 4, 6)
    WithFold.flatMap(List(1, 2, 3))(x => List(x, x + 2)) shouldBe List(1, 3, 2, 4, 3, 5)

    import cats.instances.int._


    def stringMonoid = new Monoid[String] {
      override def empty: String = ""

      override def combine(x: String, y: String): String = x + y
    }

    WithFold.sumWithMonoid(List("a", "b", "c"))(stringMonoid) shouldBe "abc"

  }

  test("Traverse future test") {
    //import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.{Await, Future}
    import scala.concurrent.duration._

    val pool = new forkjoin.ForkJoinPool(16)
   implicit val ectx = ExecutionContext.fromExecutorService(pool)



    def easyLookup(value: Int): Future[String] = {
      val p = Promise[String]
      ectx.execute(() => {
        Thread.sleep(50)
        p.success(s"${Thread.currentThread().getName}: $value became ${value * value}")
      })
      p.future
    }

    val values = for (x <- 1 to 10) yield scala.util.Random.nextInt(100)

    val allCalcs = Future.traverse(values)(easyLookup)
    val result = Await.result(allCalcs, 2 seconds)

    result.foreach(println)
  }
}
