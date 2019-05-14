package withcats.part1

import models._

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    (value: String) => JsString(value)

  implicit val personWriter: JsonWriter[Person] =
    (value: Person) => JsObject(Map(
      "name" -> JsString(value.name),
      "email" -> JsString(value.email)
    ))

  implicit val productWriter: JsonWriter[models.Product] =
    (value: models.Product) => value match {
      case x: Disk => JsObject(Map(
        "title" -> JsString(x.title),
        "artist" -> JsString(x.artist)
      ))
      case x: Book => JsObject(Map(
        "title" -> JsString(x.title),
        "author" -> JsString(x.author)
      ))
    }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    (option: Option[A]) => option match {
      case Some(aValue) => writer.write(aValue)
      case None => JsNull
    }
}