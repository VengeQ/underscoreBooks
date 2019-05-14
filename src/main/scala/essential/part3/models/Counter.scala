package essential.part3.models

case class Counter(val count:Int = 1 ) {
  def inc =Counter(count+1)
  def dec =Counter(count-1)
  def adjust(adder: Adder) = Counter(adder.add(count))
}

