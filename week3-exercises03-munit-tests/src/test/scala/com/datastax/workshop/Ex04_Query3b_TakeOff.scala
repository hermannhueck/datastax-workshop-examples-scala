package com.datastax.workshop

import java.util.UUID

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

class Ex04_Query3b_TakeOff extends munit.FunSuite {

  private val LOGGER                         = LoggerFactory.getLogger("Exercise3")
  private var cqlSession: CqlSession         = _
  private var journeyRepo: JourneyRepository = _

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  test("takeoff the spacecraft") {
    LOGGER.info("9..8..7..6..5..4..3..2..1 Ignition")
    journeyRepo
      .takeoff(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now taken off", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }
}
