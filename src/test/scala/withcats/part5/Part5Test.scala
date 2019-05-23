package withcats.part5

import cats.Monad
import cats.instances.option._
import cats.instances.try_._
import org.scalatest.{FunSuite, Matchers}
import cats.syntax.option._
import cats.syntax.try_._
import cats.syntax.applicative._

import scala.util.{Success, Try}

class Part5Test extends FunSuite with Matchers{
  test("Monads test") {
    val a = 12.pure[Try]
    val b =13.pure[Try]
    val c = Try(1/0)

    println(
      a.flatMap(x=> b.map(_+x)))
    println(
      a.flatMap(x=> c.map(_+x)))


  }
}