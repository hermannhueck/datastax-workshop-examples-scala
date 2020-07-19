package com.datastax.workshop

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(classOf[JUnitPlatform])
object Ex03_Query3a_Insert_Journey extends ExerciseBase("Exercise3")

@RunWith(classOf[JUnitPlatform])
class Ex03_Query3a_Insert_Journey {

  import Ex03_Query3a_Insert_Journey._

  @Test
  def insert_a_journey(): Unit = {

    val spaceCraft     = "Crew Dragon Endeavour,SpaceX"
    val journeySummary = "Bring Astronauts to ISS"

    // When inserting a new
    val journeyId = journeyRepo.create(spaceCraft, journeySummary)

    // Validate that journey has been created
    LOGGER.info("Journey created : {}", journeyId)
    LOGGER.info("SUCCESS")

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
