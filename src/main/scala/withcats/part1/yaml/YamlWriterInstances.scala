package withcats.part1.yaml

import withcats.part1.models._

object YamlWriterInstances {
  implicit val stringWriter:YamlWriter[String] = (value: String) => YaString(s"- $value")
  //implicit val doubleWriter:YamlWriter[String] = (value:Double) => YaNumber(s"- $value\n")
  implicit def listWriter[T]:YamlWriter[List[T]] = (list:List[T])=>{
    YaList(list.map((value:T)=>s"- ${value.toString}"))
  }

  implicit val productWriter:YamlWriter[Product] = (product:Product) => product match {
    case disk:Disk => YaObject(Map(
      "title:\n"->YaString(s"- ${disk.title}"),
      "\nartist:\n"->YaString(s"- ${disk.artist}"),
    ))
    case book:Book => YaObject(Map(
      "title:\n"->YaString(s"- ${book.title}"),
      "\nauthor:\n"->YaString(s"- ${book.author}"),
    ))
  }
}
