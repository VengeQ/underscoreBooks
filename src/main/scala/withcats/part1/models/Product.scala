package withcats.part1.models

sealed trait Product

final case class Disk(title:String, artist:String) extends Product
final case class Book(title:String, author:String) extends Product
