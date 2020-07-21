package com.datastax.workshop

import java.util.UUID

class Ex08_Query4b_Read_Journey_Details extends ExerciseBase("Exercise4") {

  test("read a journey") {

    JourneyDB.journeys.findById(UUID.fromString(JOURNEY_ID), SPACECRAFT) match {
      case None          =>
        LOGGER.info("Journey {} not found, check DB", JOURNEY_ID)
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
