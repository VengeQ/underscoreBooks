package withcats

import cats.Monoid
import cats.instances.int._ // for Monoid
import cats.instances.option._

package object part2 {
  def add(list:List[Option[Int]]):Option[Int] = list.foldLeft(Monoid[Option[Int]].empty)(Monoid[Option[Int]].combine(_,_))
  def add(o1:Order,o2:Order):Double = ???
  case class Order(totalCost: Double, quantity: Double)

  implicit def orderMonoid:Monoid[Order] = new Monoid[Order]{
    override def empty: Order = Order(0,0)

    override def combine(x: Order, y: Order): Order = Order( x.totalCost+y.totalCost, x.quantity+y.quantity)
  }



}
