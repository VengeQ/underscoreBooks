package withcats.monad



import org.scalatest.{FunSuite, Matchers}


class WriterTest extends FunSuite with Matchers{

  import cats.data.Writer
  import cats.instances.vector._
  import cats.syntax.applicative._
  import cats.syntax.writer._
  import scala.concurrent.{Future, Promise}
  import scala.concurrent.ExecutionContext.Implicits.global

  test("Writer Test") {
    type Logged[A] = Writer[Vector[String], A]

    def calculateWithLog[A](fn: => Logged[A]): Logged[A] =fn

    def fibo(n: Double):Logged[Double] = {
      def go(f: Double, s: Double, acc: Double, count: Double): Double = count match {
        case 0 => acc
        case _ => go(s, f + s, acc + s, count - 1)
      }

      def forLog(finish:Double, acc:Double, current:Double, prev:Double) :Logged[Double]=
        for {
          ans <- if (finish ==0) {
            acc.pure[Logged]
          }else {
            forLog(finish - 1, acc+current, acc, current)
          }
          _ <- Vector(s"fibo for ${n-finish+1 toInt} is $acc. ${finish toInt} step${if (finish>1) "s" else ""} last. Execute in ${Thread.currentThread.getName}").tell
        } yield ans
      forLog(n,0,1,1)
    }

    def factorial(n: Int): Logged[Int] =
      for {
        ans <- if (n == 0) {
          1.pure[Logged]
        } else {
          (factorial(n - 1).map(_ * n))
        }
        _ <- Vector(s"fact $n $ans").tell
      } yield ans


    def fut[A](b: => A):Future[A] = {
      val p = Promise[A]
      global.execute(() => try{p.success(b)} catch {case e:Throwable => p.failure(e)})
      p.future
    }


    //for (_ <- 1 to 5) fut(fibo(scala.util.Random.nextInt(10)))

    val res = (Future.sequence(Vector(fut(fibo(10).run), fut(fibo(10).run), fut(fibo(10).run), fut(fibo(10).run))))



    Thread.sleep(4000)

    res.foreach(x=>x.foreach(y=>y._1.foreach(println)))
  }

}

class StateTest extends FunSuite with Matchers {

  import cats.data.State

  test("State Test") {
    final case class Rg(init:Int) {
      def nextState = {
        val (state, result) = State[Int, Int]{ s =>
          def nextValue(value:Int, init:Int) = (value+init << 9)+3 *value
          (init, nextValue(s,init))
        }.run(init).value
        Rg(result)
      }
      def printState = println(s"current state is $init")
    }


    Rg(12).printState
    Rg(12).nextState.printState

    Rg(12).nextState.nextState.printState

    Rg(12).nextState.nextState.nextState.printState
    Rg(12).nextState.nextState.nextState.nextState.nextState.printState
  }
}