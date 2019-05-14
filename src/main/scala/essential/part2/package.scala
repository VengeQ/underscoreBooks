

package object part2 {

  object Cat1 {
    val name = "Oswald"
    val colour = "Black"
    val food = "Milk"
  }

  object calc {
    def square(v: Double) = v * v

    def cube(v: Double) = v * v * v
  }


  object argh {
    def a = {
      println("a")
      1
    }

    val b = {
      println("b")
      a + 2
    }

    def c = {
      println("c")
      a
      b + "c"
    }
  }

  object Person{
    val firstName = "Vasya"
    val lastName = "Pupkin"
  }

  object alien{
    def greet(p:Person.type) = s"greet ${p.firstName}"
  }

}
