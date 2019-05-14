package withcats.part1

import java.util.Date

import org.scalatest.{FunSuite, Matchers}
import withcats.part1.models._
import withcats.part1.printable.Printable
import withcats.part1.yaml.Yaml


class Part1Test extends FunSuite with Matchers{

  test("Easy jsonParser test"){
    import JsonWriterInstances.productWriter
    import JsonWriterInstances.optionWriter
    import JsonSyntax._
    val someBook:Option[Product] = Option(Book("The Best", "Author"))
    Json.toJson(someBook)
    println(someBook.toJson)
  }

  test("Printable") {
    import withcats.part1.printable.PrintableInstances._
    import withcats.part1.printable.PrintableSyntax._
    val cat =Cat("Ryzhik",3,"black")
    Printable.format(cat) shouldBe cat.format

    Printable.print(cat)

    cat.print

  }

  test("Meet cats test"){
    import cats.instances.int._ // for Show
    import cats.syntax.show._
    val showInt=123.show
    val showDate = (new Date).show

    println(showInt)
    println(showDate)


    val cat =Cat("Ryzhik",3,"black")
    import withcats.part1.catstc.CatShow._
    println(cat.show)

  }

  test("Eq test"){
    import cats.Eq
    import cats.instances.int._
    import cats.syntax.eq._
    import cats.instances.option._
    import withcats.part1.catstc.CatsEq._
    val eqOpt = Eq[Cat]

    val cat1 =Cat("Ryzhik", 4, "black")
    val cat2 = Cat("Vasya",5, "white")
    val cat3 = cat1.copy()


    eqOpt.eqv(cat1, cat2) shouldBe false
    eqOpt.eqv(cat1, cat3) shouldBe true


  }

  test("Yamltest"){
    val a:Product = Disk("Madonna","The Best")

    import withcats.part1.yaml.YamlWriterInstances._
    println(Yaml.toYaml(a))

    val list = List("first", "second", "third")
    println(Yaml.toYaml(list))
  }



}
