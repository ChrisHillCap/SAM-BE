package controllers

import javax.inject.{Inject, Singleton}

import models.SensorEvent
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, JsValue}
import play.api.mvc._

import scala.concurrent.Future

@Singleton
class SaveEventController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def saveSingleEvent(id: String): Action[JsValue] = Action(parse.json).async {
    implicit request =>
      request.body.validate[SensorEvent] match {
        case JsSuccess(event, _) => {
          Logger.info(s"Received event: $event")
          Future.successful(Ok("Got it"))
        }
        case err @JsError(_) => {
          Logger.info(s"Error reading event: $err")
          Future.successful(BadRequest(s"Noop: $err"))
        }
      }
  }

}
