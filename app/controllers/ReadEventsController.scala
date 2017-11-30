package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc._

import scala.concurrent.Future

@Singleton
class ReadEventsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val readLatestEvents: Action[AnyContent] = Action.async {
    implicit request =>
      Future.successful(Ok)
  }

}
