package withcats

import java.util.Date

import cats.Show

package object part1 {
  implicit val dateShow: Show[Date] = (date:Date) =>  s"${date.getTime} ms since the epoch."


  import cats.Eq
  import cats.instances.int._
  import cats.syntax.eq._
  import cats.instances.option._
  val eqOpt = Eq[Option[Int]]
  //eqOpt.eqv(Some(1),None)

  (Some(1):Option[Int]) === (None:Option[Int])
}
