package com.datastax.workshop

import java.util.UUID

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

class Ex06_Query3d_Landing extends munit.FunSuite {

  private val LOGGER                         = LoggerFactory.getLogger("Exercise3")
  private var cqlSession: CqlSession         = _
  private var journeyRepo: JourneyRepository = _

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  test("landing journey") {
    journeyRepo.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now landed", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }
}
