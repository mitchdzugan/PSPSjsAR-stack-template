package client.services

import java.nio.ByteBuffer

import upickle.default
import org.scalajs.dom

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object AjaxClient extends autowire.Client[String, default.Reader, default.Writer] {
  override def doCall(req: Request): Future[String] = {
    dom.ext.Ajax.post(
      url = "/api/" + req.path.mkString("/"),
      data = default.write(req.args),
      headers = Map("Content-Type" -> "application/json;charset=UTF-8")
    ).map(_.responseText)
  }

  override def read[Result: default.Reader](p: String) = default.read[Result](p)
  override def write[Result: default.Writer](r: Result) = default.write(r)
}
