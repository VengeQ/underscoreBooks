package essential.part7.order

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

object ByUnitPriceOrdering {
  implicit val ordering = Ordering.fromLessThan[Order]( _.unitPrice < _.unitPrice)
}

object ByTotalUnitsOrdering {
  implicit val ordering = Ordering.fromLessThan[Order](_.units < _.units)
}

object ByTotalPriceOrdering {
  implicit val ordering = Ordering.fromLessThan[Order](_.totalPrice < _.totalPrice)
}