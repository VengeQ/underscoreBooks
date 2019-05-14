package withcats.part1.printable

import withcats.part1.models.Cat

object PrintableInstances {
  implicit val intPrintable:Printable[Int]= (value:Int) => s"${value.toString}"
  implicit val stringPrintable:Printable[String] = (value:String) => s"$value"
  implicit val catPrintable:Printable[Cat] = (cat: Cat) => {
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat"
  }
}
