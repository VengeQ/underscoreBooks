package essential

package object ext {
  def zeroOrOne(value:Int) = value match {
    case 0 | 1 => true
    case _ => false
  }



  def bindPattern(a:Int, b:Int) = calcWithCounter.calc(a+b) match {
    case t @ _ => println(t)
      calcWithCounter.counter
  }


  def bindPattern2(a:Int, b:Int) = calcWithCounter.calc(a+b) match {
    case _ =>
      println(calcWithCounter.calc(a+b))
      calcWithCounter.counter
  }




  object calcWithCounter{
    private[ext] var counter: Int = 0
    def calc[A](f: => A) ={
      counter +=1
      f
    }
  }
}
