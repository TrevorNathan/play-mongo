package controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok

import scala.concurrent.{ExecutionContext, Future}
import javax.inject.Inject
import models.Post
import play.api.libs.json.Json
import play.api.mvc._
import play.mvc.Action
import reactivemongo.bson.BSONObjectID
import repositories.PostRepository

class PostController @Inject()(
                              executionContext: ExecutionContext,
                              components: ControllerComponents,
                              postsRepo: PostRepository
                              )
extends AbstractController(components) {

  //Action: 1
  def listPosts() = Action.async {
    postsRepo.list().map { posts =>
      Ok(Json.toJson(posts))

    }
  }


  //Action: 2
  def createPost = Action.async(parse.json){
    _.body.validate[Post].map { post =>
      postsRepo.create(post).map { _ => Created }
    }
      .getOrElse(Future.successful(BadRequest(
        "Invalid format")))
    }



  //Action: 3
  def readPost(id: BSONObjectID) = Action.async{
    postsRepo.read(id).map { maybePost =>
      maybePost.map { post =>
        Ok(Json.toJson(post))
      }
        .getOrElse(NotFound)
    }
  }



  //Action: 4
  def updatePost(id: BSONObjectID) = Action.async(parse.json){
    _.body.validate[Post].map { post =>
      postsRepo.update(id, post).map {
        case Some(post) => Ok(Json.toJson(post))
        case _          => NotFound
      }
    }
      .getOrElse(Future.successful(BadRequest(
        "Invalid format")))
  }



  //Action: 5
  def deletePost(id: BSONObjectID) = Action.async{
    postsRepo.destroy(id).map {
      case Some(post) => Ok(Json.toJson(post))
      case _          =>  NotFound
    }

  }



}
