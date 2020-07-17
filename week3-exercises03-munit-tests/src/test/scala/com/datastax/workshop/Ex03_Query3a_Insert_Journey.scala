package com.datastax.workshop

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

class Ex03_Query3a_Insert_Journey extends munit.FunSuite {

  private val LOGGER                         = LoggerFactory.getLogger("Exercise3")
  private var cqlSession: CqlSession         = _
  private var journeyRepo: JourneyRepository = _

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  test("insert a journey") {

    val spaceCraft     = "Crew Dragon Endeavour,SpaceX"
    val journeySummary = "Bring Astronauts to ISS"

    // Inserting a new journey
    val journeyId = journeyRepo.create(spaceCraft, journeySummary)

    // Validate that journey has been created
    LOGGER.info("Journey created : {}", journeyId)
    LOGGER.info("SUCCESS")

    // retrieve the journey
    journeyRepo.find(journeyId, spaceCraft) match {
      case None          =>
        LOGGER.info("Could not find Journey by space craft {} with id: {}", spaceCraft, journeyId);
      case Some(journey) =>
        LOGGER.info("Found Journey by space craft {} with id: {}", spaceCraft, journeyId);
        LOGGER.info("Journey: {}", journey);
    }
  }

  /*
   * select * from spacecraft_journey_catalog WHERE
   * journey_id=47b04070-c4fb-11ea-babd-17b91da87c10 AND
   * spacecraft_name='DragonCrew,SpaceX';
   */
}
