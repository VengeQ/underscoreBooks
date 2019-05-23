package essential.part7.tc

trait Equal[A] {
  def equal(f:A, s:A):Boolean
}

object Eq{
  def apply[A](f:A, s:A)(implicit equal: Equal[A]) =equal.equal(f, s)
}