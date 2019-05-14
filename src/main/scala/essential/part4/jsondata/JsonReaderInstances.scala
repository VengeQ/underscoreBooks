package essential.part4.jsondata

object JsonReaderInstances {
  implicit val personReader:JsonReader[Person] = (p:Person) => JsSequence(List(JsString(p.firstName),JsString(p.lastName), JsNumber(p.age))).fromJson
}
