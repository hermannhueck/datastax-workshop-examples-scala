package com.datastax

import java.nio.file.Paths

import org.slf4j.Logger

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.CqlSessionBuilder

import hutil.stringformat._

package object workshop {

  // ===> WE WILL USE THIS VALUES EVERYWHERE
  var SPACECRAFT = "Crew Dragon Endeavour,SpaceX"
  var JOURNEY_ID = "90163870-c5d6-11ea-b11f-c30e2b038000"

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
}
