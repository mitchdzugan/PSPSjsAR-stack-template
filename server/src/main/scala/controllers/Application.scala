package controllers

import java.nio.ByteBuffer

import upickle.default
import play.api.mvc._
import services.ApiService
import shared.Api

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

import slick.driver._

object Router extends autowire.Server[String, default.Reader, default.Writer] {
  def read[Res: default.Reader](p: String) = default.read[Res](p)
  def write[Res: default.Writer](r: Res) = default.write(r)
}

class Application @Inject() (dbConfigProvider: play.api.db.slick.DatabaseConfigProvider) extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  val apiService = new ApiService()

  def index = Action {
    Ok(views.html.index("UNTITLED APP"))
  }

  def autowireApi(path: String) = Action.async(parse.json.map(_.toString())) {
    implicit request =>
      println(s"Request path: $path")
      println(s"Request body: ${request.body}")

      // call Autowire route
      Router.route[Api](apiService)(
        autowire.Core.Request(path.split("/"), default.read[Map[String, String]](request.body))
      ).map(Ok(_))
  }

  def logging = Action(parse.anyContent) {
    implicit request =>
      request.body.asJson.foreach { msg =>
        println(s"CLIENT - $msg")
      }
      Ok("")
  }
}
