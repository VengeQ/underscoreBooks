package essential.part3

import essential.part3.models.{Adder, Cat, Counter, Director, Film}
import org.scalatest.{FunSuite, Matchers}

class Part3Test extends FunSuite with Matchers {
  test("Exercises Part3.1") {
    val c1 = Cat("Oswald", "Black", "Milk")
    val c2 =Cat("Henderson", "Ginger", "Chips")
    c1.getClass shouldBe c2.getClass
    c1 == c2 shouldBe false

    ChipShop.willServe(c1) shouldBe false
    ChipShop.willServe(c2) shouldBe true


    val eastwood = new Director("Clint", "Eastwood", 1930)
    val mcTiernan = new Director("John", "McTiernan", 1951)
    val nolan = new Director("Christopher", "Nolan", 1970)
    val someBody = new Director("Just", "Some Body", 1990)
    val memento = new Film("Memento", 2000, 8.5, nolan)
    val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception = new Film("Inception", 2010, 8.8, nolan)
    val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7,
      eastwood)
    val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9,
      eastwood)
    val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
    val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
    val invictus = new Film("Invictus", 2009, 7.4, eastwood)
    val predator = new Film("Predator", 1987, 7.9, mcTiernan)
    val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
    val huntForRedOctober = new Film("The Hunt for Red October", 1990,
      7.6, mcTiernan)
    val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8,
      mcTiernan)

    eastwood.yearOfBirth shouldBe 1930
    memento isDirectedBy nolan shouldBe true
    unforgiven isDirectedBy someBody shouldBe false
    thomasCrownAffair.imdbRating shouldBe 6.8
    huntForRedOctober.director shouldBe predator.director
    darkKnight.directorAge shouldBe darkKnight.yearOfRelease - darkKnight.director.yearOfBirth

    val c =Counter()
    c.inc.dec.inc.inc shouldBe Counter().inc.inc

    (Counter(12) adjust Adder(4)) shouldBe (Counter(4) adjust Adder(12))

    Director("Vasya Pupkin", 1991).firstName shouldBe "Vasya"

    Director.older(nolan, eastwood) shouldBe eastwood
    Director.older(nolan, someBody) shouldBe nolan

  }
}
