package com.datastax.workshop

import java.util.UUID

import scala.concurrent._

class Ex04_Query3b_TakeOff extends ExerciseBase("Exercise3") {

  test("takeoff the spacecraft") {

    implicit val ec: ExecutionContext = ExecutionContext.global

    for {
      journeyId <- JourneyDB
                     .journeys
                     .create(SPACECRAFT, "Investigate Mars", UUID.fromString(JOURNEY_ID))
      _         <- Future(LOGGER.info("Journey created : {}", journeyId))

      _ <- Future(LOGGER.info("9..8..7..6..5..4..3..2..1 Ignition"))
      _ <- JourneyDB.journeys.takeoff(journeyId, SPACECRAFT)
      _ <- Future(LOGGER.info("Journey {} has now taken off", JOURNEY_ID))
      _ <- Future(LOGGER.info("SUCCESS"))

      journey <- JourneyDB.journeys.findById(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    } yield journey match {
      case None          =>
        LOGGER.info("\nJourney {} not found, check DB", JOURNEY_ID)
      case Some(journey) =>
        LOGGER.info("\n{}", journey)
    }
  }
}
