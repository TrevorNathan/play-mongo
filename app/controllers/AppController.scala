package controllers

import javax.inject._
import scala.concurrent.ExecutionContext
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import repositories.PostRepository

@Singleton
class AppController @Inject()(
                             implicit executionContext: ExecutionContext,
                             controllerComponents: ControllerComponents,
                             postsRepo: PostRepository
                             ) extends AbstractController(controllerComponents) {


  def index(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
    Ok("App works!")
  }


}
