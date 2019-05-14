package essential.part4.exercises

sealed trait TrafficLight{
  def next:TrafficLight = this match {
    case Red() => Green()
    case Green() => Yellow()
    case Yellow() => Red()
  }

}

final case class Red() extends TrafficLight

final case class Green() extends TrafficLight

final case class Yellow() extends TrafficLight