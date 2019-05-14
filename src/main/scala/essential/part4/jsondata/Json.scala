package essential.part4.jsondata

import scala.annotation.tailrec

sealed trait Json {
  def fromJson: String = this match {
    case JsNull => ""
    case JsNumber(n) => n toString
    case JsString(str) => s""""$str""""
    case JsBoolean(b) => b toString
    case ObjectEnd => ObjectEnd.fromJson
    case SeqEnd => SeqEnd.fromJson
    case o:ObjectCell =>o.fromJson
    case s:SeqCell => s.fromJson

  }
}

final case class JsNumber(get:Double) extends Json
final case class JsString(get:String) extends Json
final case class JsBoolean(get:Boolean) extends Json
final case object JsNull extends Json


sealed trait JsSequence extends Json{
  override def fromJson: String = this match {
    case SeqEnd => ""
    case SeqCell(h, t) => {
      @tailrec def go(h: Json, t: JsSequence, result:String):String = t match {
        case SeqEnd => s"$result, ${h.fromJson}"
        case SeqCell(h2, t2) => go(h2,t2, if (result isEmpty) h.fromJson else s"$result, ${h.fromJson}")
      }
      s"[${go(h, t, "" )}]"
    }
  }
}
final case class SeqCell(head:Json, tail:JsSequence) extends JsSequence
object JsSequence{
  //def apply(head: Json, tail: JsSequence): JsSequence = new SeqCell(head, tail)
  def apply(js:List[Json]):JsSequence = js.length match {
    case 0 => SeqEnd
    case 1 => SeqCell(js.head,SeqEnd)
    case _ => SeqCell(js.head, JsSequence.apply(js.tail))
  }
}
final case object SeqEnd extends JsSequence


sealed trait JsObject extends Json{
  override def fromJson: String = this match {
    case ObjectEnd => ""
    case ObjectCell(k, v, t) => {
      @tailrec def go(k:String, v:Json, t:JsObject, result:String):String = t match {
        case ObjectEnd => result +s""", "$k": ${v.fromJson}"""
        case ObjectCell(k2,v2,t2) => go(k2,v2,t2, if (result.isEmpty) s""""$k": ${v.fromJson}""" else result + s""", "$k": ${v.fromJson}""")
      }

      s"{${ go(k, v, t, "")}}"
    }
  }
}
final case class ObjectCell(key:String, value:Json, tail:JsObject) extends JsObject
final case object ObjectEnd extends JsObject



trait JsonReader[A] {
  def read(value: A): String
}


object JsonSyntax{
  implicit class JsonReaderOps[A](value:A){
    def fromJson(implicit reader:JsonReader[A]):String = reader.read(value)
  }
}

object Json {
  def fromJson[A](value: A)(implicit w: JsonReader[A]): String = w.read(value)
}