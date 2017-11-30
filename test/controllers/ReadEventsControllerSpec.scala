package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Injecting}

class ReadEventsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "ReadLatestEvents" should {

    "return Ok from a new instance of controller" in {
      val controller = new ReadEventsController(stubControllerComponents())
      val readResult = controller.readLatestEvents().apply(FakeRequest(GET, "/"))

      status(readResult) mustBe OK
      contentType(readResult) mustBe Some("application/json")
    }

    "return Ok from the application" in {
      val controller = inject[ReadEventsController]
      val readResult = controller.readLatestEvents().apply(FakeRequest(GET, "/"))

      status(readResult) mustBe OK
      contentType(readResult) mustBe Some("application/json")
    }

    "return Ok from the router" in {
      val request = FakeRequest(GET, "/sam-be/events/read/latest")
      val readResult = route(app, request).get

      status(readResult) mustBe OK
      contentType(readResult) mustBe Some("application/json")
    }
  }
}
