package withcats.part1.yaml

sealed trait Yaml

final case class YaString(get:String) extends Yaml
//final case class YaNumber(get:Double) extends Yaml
final case class YaList[T](get:List[T]) extends Yaml
final case class YaObject(get:Map[String, Yaml]) extends Yaml{
  override def toString: String = get.map((k)=>s"${k._1}\t${k._2}").mkString("")
}
final case object YaNothing extends Yaml

trait YamlWriter[A]{
  def write(value:A):Yaml
}

object Yaml{
  def toYaml[A](value:A)(implicit ya: YamlWriter[A]) = ya.write(value)
}