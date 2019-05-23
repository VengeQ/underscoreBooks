package essential.part7

object Examples {

  def maxOfList[A:Ordering](list: List[A]) = list.reduce(implicitly[Ordering[A]].max(_,_))

}
