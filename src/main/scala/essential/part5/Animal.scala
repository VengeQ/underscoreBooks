package essential.part5

case class Animal(name:String)
class Cat(override val name:String) extends Animal(name:String)
