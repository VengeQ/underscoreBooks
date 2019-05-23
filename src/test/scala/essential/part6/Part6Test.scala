package essential.part6

import essential.part6.calculator._
import org.scalatest.{FunSuite, Matchers}

import scala.util.Try

class Part6Test extends FunSuite with Matchers {

  test("exer6.1 Test") {
    val a = Seq(1, 2, 3, 4)
    a.size shouldBe 4
    a.head shouldBe 1
    a.headOption shouldBe Some(1)
    a.mkString("") shouldBe "1234"
    Option(1).isDefined shouldBe true

    import Films._

    def f1(numberOfFilms: Int) = directors.filter(_.films.size > numberOfFilms)

    def f2(year: Int) = directors.filter(_.yearOfBirth < year)

    def f3(numberOfFilms: Int, year: Int) = directors.filter(dr => dr.yearOfBirth < year && dr.films.size > numberOfFilms)

    def f4(acsending: Boolean) = directors.sortWith((d1, d2) => acsending match {
      case true => d1.yearOfBirth > d2.yearOfBirth
      case false => d2.yearOfBirth > d1.yearOfBirth
    })


    //List(1,2,3,4).permutations.toList.foreach(println)
  }

  test("Test part6.2") {
    val list = List(4, 5, 6, 1, 2, 3, 8, 7, 1, 2, 3, 4, 4, 5, 6, 1, 8)

    def smallest[T: Ordering](l: List[T]): T = l.foldLeft(l.head)((a, b) => (implicitly[Ordering[T]].min(a, b)))

    smallest(list) shouldBe 1

    def unique[T: Ordering](l: Seq[T]) =
      l.sortWith((f, s) => implicitly[Ordering[T]].gt(f, s)).
        foldLeft(Seq.empty[T])((seq, elem) =>
          if (seq.isEmpty) seq.:+(elem)
          else if (implicitly[Equiv[T]].equiv(seq.last, elem)) seq else seq.:+(elem))

    def unique2[T: Equiv](s: Seq[T]): Seq[T] = s.foldRight(Seq.empty[T])((elem, result) =>
      if (result.contains(elem)) result else result :+ elem)

    unique(list).sortWith(_ > _) shouldBe unique2(list).sortWith(_ > _)

    def newLongList() = Iterator.continually(scala.util.Random.nextInt(1000)).take(100000).toList

    def reverse[T](seq: Seq[T]) = seq.foldRight(Seq.empty[T])((elem, result) => result :+ elem)

    reverse(List(1, 2, 3)) shouldBe (List(3, 2, 1))

    def map[T, R](seq: Seq[T])(fn: T => R) = seq.foldLeft(Seq.empty[R])((result, current) => result :+ fn(current))

    def foldLeftM[T, R](seq: Seq[T], init: R)(fn: (R, T) => R) = {
      var value = init
      seq.foreach { x =>
        value = fn(value, x)
      }
      value
    }

    map(List(1, 2, 3))(_ * 2 toString) shouldBe List("2", "4", "6")
    foldLeftM(List(1, 2, 3, 4), 0)(_ + _) shouldBe 10




    /*
      val withSortUnique = essential.withTimer(unique(newLongList()))
      println(s"With sorting: $withSortUnique ns")
      val withoutSortUnique = essential.withTimer(unique2(newLongList()))
      println(s"Without sorting: $withoutSortUnique ns")
  */
  }

  test("Test part6.5") {

    def addOptions(a: Option[Int], b: Option[Int]): Option[Int] =
      for {
        value1 <- a
        value2 <- b
      } yield value1 + value2

    def addOptions2(a: Option[Int], b: Option[Int]): Option[Int] = a.flatMap(aValue => b.map(bValue => bValue + aValue))

    addOptions(Option(3), Option(4)) shouldBe Option(7)
    addOptions(Option(3), None) shouldBe None

    addOptions2(Option(3), Option(4)) shouldBe Option(7)
    addOptions2(Option(3), None) shouldBe None


    def divide(n: Int, d: Int): Option[Int] = d match {
      case 0 => None
      case _ => Some(n / d)
    }

    def divideOption(numerator: Option[Int], denominator: Option[Int]) = for {
      d <- denominator
      n <- numerator
      c <- divide(n, d)
    } yield c

    divide(4, 2) shouldBe Some(2)
    divideOption(Option(12), Option(3)) shouldBe Some(4)
    divide(2, 0) shouldBe None
    divideOption(Option(3), None) shouldBe None
  }

  test("6.5 calculator test") {
    val calc = Calculator()

    calc.calculator("2", "+", " 4") shouldBe Succ(6d)
    calc.calculator("2f", "+", " 4") shouldBe IncorrectFirstValue
    calc.calculator("4", "-", "11") shouldBe Succ(-7d)
    calc.calculator("12", "/", "0") shouldBe IncorrectZeroDenominator
    calc.calculator("11", "+", "1s") shouldBe IncorrectSecondValue
    calc.calculator("13", "+sdafa", "14") shouldBe IncorrectOperator("+sdafa")
  }


  test("Test part6.8") {
    def favoriteColor(name: String) = favoriteColors.get(name)

    favoriteColor("Derek") shouldBe Some("magenta")
    favoriteColor("Me") shouldBe None

    def printColors() = favoriteColors.foreach((pc) => println(s"Favourite color of ${pc._1} is ${pc._2}"))

    printColors()

    def lookup[A](name: String, table: Map[String, A]) = table.get(name)

    lookup("Alice", ages) shouldBe Option(20)
    lookup("Charlie", favoriteLolcats) shouldBe Option("Ceiling Cat")
    lookup("Vasya", ages) shouldBe None

    favoriteColors.get(ages./:(ages.head)((p1, p2) => if (p1._2 > p2._2) p1 else p2)._1) shouldBe Some("yellow")

    val m1 = Map("a" -> 1, "b" -> 2, "c" -> 3, "e" -> 5)
    val m2 = Map("a" -> 2, "c" -> 4, "d" -> 12)

    def unionMaps[A, B: Numeric](x: Map[A, B], y: Map[A, B]) =
      x.foldLeft(y) { (result, current) =>
        val (k, v1) = current
        val newValue = result.get(k).map(v2 => implicitly[Numeric[B]].plus(v1, v2)).getOrElse(v1)
        result + (k -> newValue)
      }

    unionMaps(m1, m2).foreach(println)
  }

  test("Test part6.10") {



  }


}

