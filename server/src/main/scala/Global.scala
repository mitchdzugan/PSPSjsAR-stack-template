import play.api._
import play.api.Play.current
import play.api.libs.concurrent._
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.duration._
import scala.language.postfixOps

object Global extends GlobalSettings {
  override def onStart(app: Application): Unit = {
    Logger.info("Application has started")
    Akka.system.scheduler.schedule(0 seconds, 5 seconds) {
      ()
    }
  }

  override def onStop(app: Application) : Unit = {
    Logger.info("Application has ended")
  }
}
