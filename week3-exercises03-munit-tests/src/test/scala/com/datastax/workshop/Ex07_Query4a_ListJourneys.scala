package com.datastax.workshop

class Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4") {

  /*
   * select * from spacecraft_journey_catalog
   * WHERE journey_id=47b04070-c4fb-11ea-babd-17b91da87c10
   * AND spacecraft_name='DragonCrew,SpaceX';
   */
  test("list journeys") {

    journeyRepo.findJourneys(SPACECRAFT).foreach { journey =>
      LOGGER.info("- Journey: {} Summary: {}", journey.id, journey.summary)
    }

    LOGGER.info("SUCCESS")
  }
}
