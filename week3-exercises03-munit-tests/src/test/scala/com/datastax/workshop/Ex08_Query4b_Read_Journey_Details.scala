package com.datastax.workshop

import java.util.UUID

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

class Ex08_Query4b_Read_Journey_Details extends munit.FunSuite {

  private val LOGGER                         = LoggerFactory.getLogger("Exercise4")
  private var cqlSession: CqlSession         = _
  private var journeyRepo: JourneyRepository = _

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  /*
   * select * from spacecraft_journey_catalog
   * WHERE journey_id=47b04070-c4fb-11ea-babd-17b91da87c10
   * AND spacecraft_name='DragonCrew,SpaceX';
   */
  test("read a journey") {

    journeyRepo.find(UUID.fromString(JOURNEY_ID), SPACECRAFT) match {
      case None          =>
        LOGGER.info("Journey {} not found, check class 'Ex04_ReadParsePage' or DB", JOURNEY_ID)
        LOGGER.info("FAILURE")
      case Some(journey) =>
        LOGGER.info("Journey has been found")
        LOGGER.info("- Uid:\t\t {}", journey.id)
        LOGGER.info("- Spacecraft:\t {}", journey.spaceCraft)
        LOGGER.info("- Summary:\t {}", journey.summary)
        LOGGER.info("- Takeoff:\t {}", journey.start)
        LOGGER.info("- Landing:\t {}", journey.end)
        LOGGER.info("SUCCESS")
    }
  }
}
