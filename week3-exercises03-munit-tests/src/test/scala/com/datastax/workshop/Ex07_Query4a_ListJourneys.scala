package com.datastax.workshop

class Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4") {

  test("list journeys") {

    journeyRepo.findJourneys(SPACECRAFT).foreach { journey =>
      LOGGER.info("- Journey: {} Summary: {}", journey.id, journey.summary)
    }

    LOGGER.info("SUCCESS")
  }
}
