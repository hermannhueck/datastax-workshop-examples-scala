package com.datastax.workshop

class Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4") {

  test("list journeys") {

    JourneyDB.journeys.findAll(SPACECRAFT).foreach { j =>
      LOGGER.info("- Journey: {} Summary: {}", j.id, j.summary)
    }

    LOGGER.info("SUCCESS")
  }
}
