package withcats

package object part4 {
  def parseInt(str: String): Option[Int] = scala.util.Try(str.toInt).toOption
  def divide(a: Int, b: Int): Option[Int] = if(b == 0) None else Some(a / b)

  def stringDivideBy(aStr: String, bStr: String): Option[Int] =
    parseInt(aStr).flatMap { aNum =>
      parseInt(bStr).flatMap { bNum =>
        divide(aNum, bNum)
      }
    }


  import scala.language.higherKinds
  import cats.Monad
  import cats.syntax.functor._ // for map
  import cats.syntax.flatMap._ // for flatMap
  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x*x + y*y))


  import cats.Id
  def pure[A](value: A): Id[A] = value

  def map[A, B](init:Id[A])(fn: A => B) :Id[B] = fn(init)
}
