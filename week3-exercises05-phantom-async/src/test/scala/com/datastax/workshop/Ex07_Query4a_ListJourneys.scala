package com.datastax.workshop

import scala.concurrent._

class Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4") {

  test("list journeys") {

    implicit val ec: ExecutionContext = ExecutionContext.global

    JourneyDB
      .journeys
      .findAll(SPACECRAFT)
      .map { js =>
        js.foreach { j =>
          LOGGER.info("- Journey: {} Summary: {}", j.id, j.summary)
        }
      }
      .map { _ =>
        LOGGER.info("SUCCESS")
      }
  }
}
