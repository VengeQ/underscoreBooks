package withcats.part1.catstc

import cats.Show
import withcats.part1.models.Cat
import cats.syntax.show._
import cats.instances.string._
import cats.instances.int._

object CatShow {
  implicit val catShow:Show[Cat] = (cat:Cat) => {
    val name =cat.name.show
    val age = cat.age.show
    val color =Show[String].show((cat.color))
    s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }

}

