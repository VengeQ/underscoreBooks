package essential.part6.GRD

final case class Distribution[A](events: List[(A, Double)]){

  def flatMap[B](f: A => Distribution[B]): Distribution[B] = {
    ???
  }
  def map[B](f: A => B): Distribution[B] = Distribution(events.map(a => f(a._1) -> a._2))
}


object Distribution{
  def uniform[A](atoms: List[A]): Distribution[A] = {
    val p = 1.0 / atoms.length
    Distribution(atoms.map(a => a -> p))
  }
  // uniform: [A](atoms: List[A])Distribution[A]
}
