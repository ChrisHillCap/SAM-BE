package models

import java.time.{Instant, ZoneId, ZonedDateTime}

import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.util.{Failure, Success, Try}

case class SensorEvent (
                       id: String,
                       in: Boolean,
                       time: ZonedDateTime,
                       reg: String
                       )

object SensorEvent {

  val dateTimeFromMillis: Reads[ZonedDateTime] = new Reads[ZonedDateTime] {
    override def reads(json: JsValue): JsResult[ZonedDateTime] = json.validate[Long] match {
      case JsSuccess(long, path) =>
        Try{
          ZonedDateTime.ofInstant(Instant.ofEpochMilli(long), ZoneId.of("UTC"))
        } match {
          case Success(dt) => JsSuccess(dt)
          case Failure(err) => JsError(s"Couldn't make dateTime: ${err.getMessage}")
        }
      case err @JsError(_) => err
    }
  }

  val dateTimeToMillis: Writes[ZonedDateTime] = new Writes[ZonedDateTime] {
    override def writes(o: ZonedDateTime): JsValue = {
      JsNumber(o.toInstant.toEpochMilli)
    }
  }

  implicit val reads: Reads[SensorEvent] = (
    (__ \ "sensor" \"id").read[String] and
    (__ \ "sensor" \"in").read[Boolean] and
    (__ \ "sensor" \"time").read[ZonedDateTime](dateTimeFromMillis) and
    (__ \ "sensor" \"reg").read[String]
  )(SensorEvent.apply _)

  implicit val writes: Writes[SensorEvent] = (
    (__ \ "id").write[String] and
    (__ \ "in").write[Boolean] and
    (__ \ "time").write[ZonedDateTime](dateTimeToMillis) and
    (__ \ "reg").write[String]
  )(unlift(SensorEvent.unapply))


}

object SensorEventMongoReadsWrites {
  import SensorEvent.dateTimeFromMillis

  private val dateTimeMongoWrites = new Writes[ZonedDateTime] {
    override def writes(o: ZonedDateTime): JsValue = {
      Json.obj("$date" -> o.toInstant.toEpochMilli)
    }
  }

  val mongoWrites: OWrites[SensorEvent] = (
    (__ \ "id").write[String] and
      (__ \ "in").write[Boolean] and
      (__ \ "time").write[ZonedDateTime](dateTimeMongoWrites) and
      (__ \ "reg").write[String]
    )(unlift(SensorEvent.unapply))

  val mongoReads: Reads[SensorEvent] = (
    (__ \ "id").read[String] and
      (__ \ "in").read[Boolean] and
      (__ \ "time" \ "$date").read[ZonedDateTime](dateTimeFromMillis) and
      (__ \ "reg").read[String]
    )(SensorEvent.apply _)
}
