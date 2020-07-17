package com.datastax

import java.nio.file.Paths

import org.slf4j.Logger

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.CqlSessionBuilder

import hutil.stringformat._

package object workshop {

  def sessionBuilder: CqlSessionBuilder =
    CqlSession
      .builder
      .withCloudSecureConnectBundle(Paths.get(DBConnection.SECURE_CONNECT_BUNDLE))
      .withAuthCredentials(DBConnection.USERNAME, DBConnection.PASSWORD)
      .withKeyspace(DBConnection.KEYSPACE)

  def createCqlSession(logger: Logger): CqlSession = {
    logger.info("===== Start exercise ========================".yellow)
    sessionBuilder.build
  }

  def closeCqlSession(cqlSession: CqlSession, logger: Logger): Unit = {
    if (null != cqlSession)
      cqlSession.close
    logger.info(s"===== Exercise terminated ===================".yellow)
  }
}
