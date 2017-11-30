package repository

import java.time.{Instant, ZoneId, ZonedDateTime}

import models.{SensorEvent, SensorEventMongoReadsWrites}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json._

import scala.concurrent.ExecutionContext.Implicits.global

class EventRepositorySpec extends PlaySpec
  with GuiceOneAppPerSuite
  with ScalaFutures
  with IntegrationPatience {


  override lazy val app: Application = new GuiceApplicationBuilder()
    .configure(Map("mongodb.uri" -> "mongodb://localhost:27017/sam-be"))
    .build()

  implicit lazy val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]

  implicit val reads = SensorEventMongoReadsWrites.mongoReads
  implicit val writes = SensorEventMongoReadsWrites.mongoWrites

  "EventRepository" should {
    //TODO replace the test below with more meaningful read examples
    "be able to read from the events collection" in {

      val repository = new EventRespoitory()
      val sensorEvent = SensorEvent("1",in = true,ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("UTC")),"12345")

      repository.collection.map(_.drop(failIfNotFound = false))
      repository.collection.flatMap(_.insert(sensorEvent))

      val readFuture = repository.collection.flatMap(_.find(Json.obj("id" -> "1")).one[SensorEvent])

      whenReady(readFuture) {
        readSensorEvent => readSensorEvent mustBe Some(sensorEvent)
      }

    }
  }
}
