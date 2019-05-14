package essential.part3.models

case class Adder(amount: Int) {
  def add(in: Int) = in + amount
}