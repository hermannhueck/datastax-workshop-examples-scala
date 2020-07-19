package com.datastax.workshop

import java.nio.file.Paths

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.AfterAll

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.CqlSessionBuilder

import hutil.stringformat._

abstract class ExerciseBase(exerciseName: String) {

  // ===> WE WILL USE THIS VALUES EVERYWHERE
  var SPACECRAFT = "Crew Dragon Endeavour,SpaceX"
  var JOURNEY_ID = "90163870-c5d6-11ea-b11f-c30e2b038000"

  val LOGGER                         = LoggerFactory.getLogger(exerciseName)
  var cqlSession: CqlSession         = _
  var journeyRepo: JourneyRepository = _

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

  @BeforeAll def initConnection(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  @AfterAll def closeConnectionToCassandra(): Unit =
    closeCqlSession(cqlSession, LOGGER)
}
