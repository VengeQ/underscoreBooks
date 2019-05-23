package object essential {


  def withTimer[T](f:  => T)(implicit count:Int = 20) ={
    for ( _<- 1 to 100) f
    val startTime = System.nanoTime()
    for ( _ <- 1 to count) f
    (System.nanoTime()-startTime)/count
  }



}
