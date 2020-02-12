package models

import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._
import play.api.libs.json._

case class Post(
                  _id: Option[BSONObjectID],
                   title: String,
                   description: String
               )

object Post {

  import play.api.libs.json.Json
  //implicit val format: OFormat[Post] = json.Json.format[Post]
  implicit val format: OFormat[Post] = Json.format[Post]


}
