package com.datastax.workshop

import java.nio.file.Paths

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.CqlSessionBuilder

import hutil.stringformat._

abstract class ExerciseBase(exerciseName: String) extends munit.FunSuite {

  // ===> WE WILL USE THIS VALUES EVERYWHERE
  protected var SPACECRAFT = "Crew Dragon Endeavour,SpaceX"
  protected var JOURNEY_ID = "90163870-c5d6-11ea-b11f-c30e2b038000"

  protected val LOGGER                         = LoggerFactory.getLogger(exerciseName)
  protected var cqlSession: CqlSession         = _
  protected var journeyRepo: JourneyRepository = _

  def sessionBuilder: CqlSessionBuilder =
    CqlSession
      .builder
      .withCloudSecureConnectBundle(Paths.get(DBConnection.SECURE_CONNECT_BUNDLE))
      .withAuthCredentials(DBConnection.USERNAME, DBConnection.PASSWORD)
      .withKeyspace(DBConnection.KEYSPACE)

  val startMarker = "===== Start exercise ========================".yellow
  val stopMarker  = "===== Exercise terminated ===================".yellow

  def createCqlSession(logger: Logger): CqlSession = {
    logger.info(startMarker)
    sessionBuilder.build
  }

  def closeCqlSession(cqlSession: CqlSession, logger: Logger): Unit = {
    if (null != cqlSession)
      cqlSession.close
    logger.info(stopMarker)
  }

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)
}
