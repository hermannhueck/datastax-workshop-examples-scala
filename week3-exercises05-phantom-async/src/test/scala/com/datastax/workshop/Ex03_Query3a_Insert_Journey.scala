package com.datastax.workshop

import scala.concurrent.ExecutionContext.Implicits.global

class Ex03_Query3a_Insert_Journey extends ExerciseBase("Exercise3") {

  test("insert a journey") {

    val spaceCraft     = "Crew Dragon Endeavour, SpaceX"
    val journeySummary = "Bring Astronauts to ISS"

    for {
      // Inserting a new journey
      journeyId <- JourneyDB
                     .journeys
                     .create(spaceCraft, journeySummary)
                     .map { id =>
                       // Validate that journey has been created
                       LOGGER.info("Journey created : {}", id)
                       LOGGER.info("SUCCESS")
                       id
                     }

      // retrieve the journey just created
      journey <- JourneyDB
                   .journeys
                   .findById(journeyId, spaceCraft)
    } yield journey match {
      case None          =>
        LOGGER.info("Could not find Journey by space craft {} with id: {}", spaceCraft, journeyId);
      case Some(journey) =>
        LOGGER.info("Found Journey by space craft {} with id: {}", spaceCraft, journeyId);
        LOGGER.info("Journey: {}", journey);
    }
  }
}
