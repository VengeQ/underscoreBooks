package essential.part4.exercises

sealed trait Shape {
  val sides:Int
  def perimeter:Double
  def area:Double
}

sealed trait Rectangular extends Shape {
  val side1:Double
  val side2:Double
  override val sides: Int = 4
  override def perimeter: Double = 2*(side1+side2)
  override def area: Double = side1*side2
}

case class Circle(r:Double) extends Shape {
  override val sides: Int = 1
  override val perimeter: Double = 2 * Math.PI * r
  override val area: Double = Math.PI * r * r
}

final case class Rectangle(override val side1: Double, override val side2 :Double) extends Shape with Rectangular


final case class Square(override val side1: Double) extends Shape with Rectangular {
  override val side2: Double = side1
}

object Draw{
  def apply(shape: Shape) = shape match {
    case c:Circle => println(s"circle of radius ${c.r}cm")
    case r:Rectangle => println(s"A rectangle of width ${r.side1}cm and height ${r.side2}cm")
    case s:Square => println(s"A square of length ${s.side1}cm")
  }
}