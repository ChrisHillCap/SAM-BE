package controllers

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}
import javax.inject.{Inject, Singleton}

import models.SensorEvent
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class ReadEventsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

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

  val readLatestEvents: Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(Ok(Json.toJson(testModels)))
  }

}
