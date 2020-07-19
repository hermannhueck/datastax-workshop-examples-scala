package com.datastax.workshop

class Ex03_Query3a_Insert_Journey extends ExerciseBase("Exercise3") {

  test("insert a journey") {

    val spaceCraft     = "Crew Dragon Endeavour,SpaceX"
    val journeySummary = "Bring Astronauts to ISS"

    // Inserting a new journey
    val journeyId = journeyRepo.create(spaceCraft, journeySummary)

    // Validate that journey has been created
    LOGGER.info("Journey created : {}", journeyId)
    LOGGER.info("SUCCESS")

    // retrieve the journey
    journeyRepo.find(journeyId, spaceCraft) match {
      case None          =>
        LOGGER.info("Could not find Journey by space craft {} with id: {}", spaceCraft, journeyId);
      case Some(journey) =>
        LOGGER.info("Found Journey by space craft {} with id: {}", spaceCraft, journeyId);
        LOGGER.info("Journey: {}", journey);
    }
  }

  /*
   * select * from spacecraft_journey_catalog WHERE
   * journey_id=47b04070-c4fb-11ea-babd-17b91da87c10 AND
   * spacecraft_name='DragonCrew,SpaceX';
   */
}
