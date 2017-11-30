package controllers

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}
import javax.inject.{Inject, Singleton}

import models.{SensorEvent, SensorEventMongoReadsWrites}
import play.api.mvc._
import repository.EventRespoitory
import play.api.libs.json.Json
import reactivemongo.play.json._

import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class ReadEventsController @Inject()(cc: ControllerComponents, mongo: EventRespoitory) extends AbstractController(cc) {

  private val testModels = Seq[SensorEvent](
    SensorEvent(
      id = "space-1",
      in = true,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,0,3), ZoneId.of("UTC")),
      reg = "MD34KJA"
    ),
    SensorEvent(
      id = "space-2",
      in = false,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,20,13), ZoneId.of("UTC")),
      reg = "MD34KJB"
    ),
    SensorEvent(
      id = "space-3",
      in = true,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,18,19), ZoneId.of("UTC")),
      reg = "MD23OXB"
    ),
    SensorEvent(
      id = "space-4",
      in = true,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,31,10), ZoneId.of("UTC")),
      reg = "KL19GB2"
    ),
    SensorEvent(
      id = "space-5",
      in = false,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,2,1), ZoneId.of("UTC")),
      reg = "TS20FA1"
    ),
    SensorEvent(
      id = "space-6",
      in = false,
      time = ZonedDateTime.of(LocalDateTime.of(2017,11,30,12,2,48), ZoneId.of("UTC")),
      reg = "JS37AWY"
    )
  )

  implicit val reads = SensorEventMongoReadsWrites.mongoReads
  implicit val writes = SensorEventMongoReadsWrites.mongoWrites

  val readLatestEvents: Action[AnyContent] = Action.async {
    implicit request =>
      mongo.collection.flatMap(_.find(Json.obj()).cursor[SensorEvent]().collect[Seq]()).map {
        allTheEvents => Ok(Json.toJson(allTheEvents))
      }
  }

}
