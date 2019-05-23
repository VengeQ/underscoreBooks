package essential.part7

import essential.part7.tc.{Eq, Person, PersonEqualByNameAndEmail}
import org.scalatest.{FunSuite, Matchers}

import scala.util.Failure

class Part7Test extends FunSuite with Matchers {
  test("Ordering Example") {
    Examples.maxOfList(List(1, 2, 3, 4, 6, 5)) shouldBe 6
    Examples.maxOfList(List('a', 'f', 'g', 'm', 'k')) shouldBe 'm'

    val absOrdering = Ordering.fromLessThan[Int]((x, y) => scala.math.abs(x) < scala.math.abs(y))
    List(-4, -1, 0, 2, 3).sorted(absOrdering) shouldBe List(0, -1, 2, 3, -4)
    implicit val implicitAbsOrdering = Ordering.fromLessThan[Int]((x, y) => scala.math.abs(x) < scala.math.abs(y))
    List(-4, -1, 0, 2, 3).sorted shouldBe List(0, -1, 2, 3, -4)

    val a = (Rational(2, 0))
    a.getClass shouldBe Failure(new ArithmeticException).getClass

    implicit val ordering = Ordering.fromLessThan[Rational]((x, y) => x.numerator * y.denominator < y.numerator * x.denominator)
    (List(Rational(1, 2).get, Rational(3, 4).get, Rational(1, 3).get).sorted shouldBe
      List(Rational(1, 3).get, Rational(1, 2).get, Rational(3, 4).get))


    val p1 = Person("vasya", "vasya@mail.ru")
    val p2 = Person("john", "john@mail.ru")
    val p3 = Person("john", "any@mail.com")


    PersonEqualByNameAndEmail.emailAndNameEqual.equal(p1, p2) shouldBe false
    PersonEqualByNameAndEmail.emailAndNameEqual.equal(p1, p1) shouldBe true
    PersonEqualByNameAndEmail.emailAndNameEqual.equal(p1, p3) shouldBe false

    import PersonEqualByNameAndEmail.emailAndNameEqual

    Eq(p1,p3) shouldBe false

  }
}
