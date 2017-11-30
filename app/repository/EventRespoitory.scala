package repository

import javax.inject.Singleton

import com.google.inject.Inject
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import reactivemongo.play.json._

@Singleton
class EventRespoitory @Inject()(implicit val reactiveMongoApi: ReactiveMongoApi) extends ReactiveMongoComponents {

  def collection: Future[JSONCollection] =
    reactiveMongoApi.database.map(_.collection[JSONCollection]("event"))

//  collection.flatMap(_.insert[Sensor])

}


