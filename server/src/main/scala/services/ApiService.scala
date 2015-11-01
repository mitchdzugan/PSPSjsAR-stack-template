package services

import java.sql.DriverAction
import java.util.{UUID, Date}

import slick.driver
import shared.Api

import slick.driver.JdbcProfile
import play.api.db.slick.{HasDatabaseConfig, DatabaseConfigProvider}
import play.api.Play

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import scala.util.{Try, Success, Failure}

class ApiService extends Api with HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
}
