package com.datastax.workshop

import java.util.UUID

class Ex06_Query3d_Landing extends ExerciseBase("Exercise3") {

  test("landing journey") {

    JourneyDB.journeys.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now landed", JOURNEY_ID)
    LOGGER.info("SUCCESS")

    JourneyDB.journeys.findById(UUID.fromString(JOURNEY_ID), SPACECRAFT) match {
      case None          =>
        LOGGER.info("\nJourney {} not found, check DB", JOURNEY_ID)
      case Some(journey) =>
        LOGGER.info("\n{}", journey)
    }
  }
}
