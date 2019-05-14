package essential.part4.exercises

sealed trait Source
case class Well() extends Source
case class Spring() extends Source
case class Tap() extends Source

case class BottledWater(size:Int, source: Source, carbonated:Boolean)
