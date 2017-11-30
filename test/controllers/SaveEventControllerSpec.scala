package controllers

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class SaveEventControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val mat: Materializer = ActorMaterializer()

  "SaveSingleEvent" should {

//    "return Ok from a valid request" in {
//      val controller = new SaveEventController(stubControllerComponents())
//      val saveResult = route(app, FakeRequest(POST, "sam-be/events/save/space-1").withJsonBody(
//        Json.parse(
//          """
//            |{
//            |  "id": "space-1",
//            |  "in": true,
//            |  "time": 1511701272000,
//            |  "reg": "MJ87GS2"
//            |}
//          """.stripMargin)
//      ).withHeaders("ContentType" -> "application/json")).get
//
//      status(saveResult) mustBe OK
//    }
//
//    "return Bad Request from an invalid request" in {
//      val controller = new SaveEventController(stubControllerComponents())
//      val saveResult = controller.saveSingleEvent("space-1").apply(FakeRequest(POST, "/space-1").withJsonBody(
//        Json.parse(
//          """
//            |{
//            |  "id": "space-1",
//            |  "time": 1511701272000,
//            |  "reg": "MJ87GS2"
//            |}
//          """.stripMargin)
//      ).withHeaders("ContentType" -> "application/json"))
//
//      status(saveResult) mustBe BAD_REQUEST
//    }
  }
}
