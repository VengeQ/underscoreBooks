package essential.part3.models

case class Director(firstName:String, lastName:String, yearOfBirth:Int) {
  val name = s"$firstName $lastName"
}

object Director{
  def apply(name:String, yearOfBirth: Int): Director = {
    val parts = name.split(" ")
    new Director(parts.head, parts.tail.head, yearOfBirth)
  }

  def older(first:Director, second:Director) = first.yearOfBirth - second.yearOfBirth match {
    case x if x > 0 => second
    case _ => first
  }
}