package withcats.part7

import cats.Monoid
import cats.kernel.Semigroup

object WithFold {
  def flatMap[A, B](list: List[A])(fn: A => List[B]) =
    list.foldRight(List.empty[B])((cur, acc) =>  fn(cur) ::: acc )

  def map[A, B](list: List[A])(fn: A => B): List[B] =
   list.foldRight(List.empty[B])((cur, acc) => fn(cur) :: acc)


  def filter[A](list: List[A])(fn: A => Boolean) =
    list.foldRight(List.empty[A])((cur, acc) => if (fn(cur)) cur :: acc else acc)

  def sum[A: Numeric](list: List[A]) =
    list.foldRight(implicitly[Numeric[A]].zero)(implicitly[Numeric[A]].plus(_, _))

  def sumWithMonoid[A: Monoid](list:List[A]) =
    list.foldRight(implicitly[Monoid[A]].empty)(implicitly[Monoid[A]].combine(_, _))

}
