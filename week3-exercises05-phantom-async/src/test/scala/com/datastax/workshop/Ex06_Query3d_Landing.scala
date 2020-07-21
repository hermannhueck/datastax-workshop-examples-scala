package com.datastax.workshop

import java.util.UUID

import scala.concurrent._

class Ex06_Query3d_Landing extends ExerciseBase("Exercise3") {

  implicit val ec: ExecutionContext = ExecutionContext.global

  test("landing journey") {

    for {
      _ <- JourneyDB.journeys.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT)
      _ <- LOGGER.info("Journey {} has now landed", JOURNEY_ID).futureSuccess
      _ <- LOGGER.info("SUCCESS").futureSuccess

      journey <- JourneyDB.journeys.findById(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    } yield journey match {
      case None    =>
        LOGGER.info("\nJourney {} not found, check DB", JOURNEY_ID)
      case Some(j) =>
        LOGGER.info("\n{}", j)
    }
  }
}
