package withcats.part3

trait Printable[A] {
  self =>
  def format(value: A): String
  def contramap[B](func: B => A): Printable[B] =
    (value: B) => self.format(func(value))
}

object Printable{
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)
}

object PrintableInstances{
  implicit val stringPrintable: Printable[String] = (value: String) => "\"" + value + "\""
  implicit val intPrintable: Printable[Int] = (value: Int) => "\"" + value.toString + "\""

  implicit val booleanPrintable: Printable[Boolean] = (value: Boolean) => if (value) "yes" else "no"

  implicit def boxPrintable[A](implicit p: Printable[A]) = p.contramap[Box[A]](b => b.value)
}