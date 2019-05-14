package withcats.part1.catstc

import cats.Eq
import withcats.part1.models.Cat
import cats.instances.string._
import cats.instances.int._
import cats.syntax.eq._

object CatsEq {
  implicit val catEqual: Eq[Cat] = {
    Eq.instance[Cat]{
      (cat1, cat2) => cat1.name === cat2.name && cat1.age === cat2.age && cat1.color == cat1.color
    }
  }
}
