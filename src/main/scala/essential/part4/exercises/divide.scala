package essential.part4.exercises

object divide {
  def apply[A](x:A, y:A)(implicit num: Numeric[A]) = y match {

    case v if v==num.zero => Infinite
    case _ => Finite(num.fromInt(num.toInt(x)/num.toInt(y)))
  }
}
