package com.datastax.workshop

import java.util.UUID

class Ex04_Query3b_TakeOff extends ExerciseBase("Exercise3") {

  test("takeoff the spacecraft") {

    val journeyId =
      JourneyDB
        .journeys
        .create(SPACECRAFT, "Investigate Mars", UUID.fromString(JOURNEY_ID))
    LOGGER.info("Journey created : {}", journeyId)

    LOGGER.info("9..8..7..6..5..4..3..2..1 Ignition")
    JourneyDB.journeys.takeoff(journeyId, SPACECRAFT)
    LOGGER.info("Journey {} has now taken off", JOURNEY_ID)
    LOGGER.info("SUCCESS")

    JourneyDB.journeys.findById(UUID.fromString(JOURNEY_ID), SPACECRAFT) match {
      case None          =>
        LOGGER.info("\nJourney {} not found, check DB", JOURNEY_ID)
      case Some(journey) =>
        LOGGER.info("\n{}", journey)
    }
  }
}
