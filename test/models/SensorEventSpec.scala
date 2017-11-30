package models

import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsSuccess, Json}

class SensorEventSpec extends PlaySpec {

  "Sensor event" should {
    "read from Json" in {
      val json = Json.parse(
        """
          |{
          |  "id": "space-1",
          |  "in": true,
          |  "time": 1511701272000,
          |  "reg": "MJ05KGZ"
          |}
        """.stripMargin)
      val model = SensorEvent(
        id = "space-1",
        in = true,
        time = ZonedDateTime.of(LocalDateTime.of(2017,11,26,13,1,12), ZoneId.of("UTC")),
        reg = "MJ05KGZ"
      )
      Json.fromJson[SensorEvent](json) mustBe JsSuccess(model)
    }

    "write to Json" in {
      val json = Json.parse(
        """
          |{
          |  "id": "space-12",
          |  "in": false,
          |  "time": 1511686872000,
          |  "reg": "MJ05KGZ"
          |}
        """.stripMargin)
      val model = SensorEvent(
        id = "space-12",
        in = false,
        time = ZonedDateTime.of(LocalDateTime.of(2017,11,26,9,1,12), ZoneId.of("UTC")),
        reg = "MJ05KGZ"
      )
      Json.toJson[SensorEvent](model) mustBe json
    }


    "read from Mongo Json" in {
      val json = Json.parse(
        """
          |{
          |  "id": "space-1",
          |  "in": true,
          |  "time": {
          |    "$date": 1511701272000
          |  },
          |  "reg": "MJ05KGZ"
          |}
        """.stripMargin)
      val model = SensorEvent(
        id = "space-1",
        in = true,
        time = ZonedDateTime.of(LocalDateTime.of(2017,11,26,13,1,12), ZoneId.of("UTC")),
        reg = "MJ05KGZ"
      )
      Json.fromJson[SensorEvent](json)(SensorEvent.mongoReads) mustBe JsSuccess(model)
    }

    "write to mongo Json" in {
      val json = Json.parse(
        """
          |{
          |  "id": "space-12",
          |  "in": false,
          |  "time": {
          |    "$date": 1511686872000
          |  },
          |  "reg": "MJ05KGZ"
          |}
        """.stripMargin)
      val model = SensorEvent(
        id = "space-12",
        in = false,
        time = ZonedDateTime.of(LocalDateTime.of(2017,11,26,9,1,12), ZoneId.of("UTC")),
        reg = "MJ05KGZ"
      )
      Json.toJson[SensorEvent](model)(SensorEvent.mongoWrites) mustBe json
    }
  }

}
