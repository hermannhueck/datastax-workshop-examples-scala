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

    // Retrieve the Journey just created
    journeyRepo.find(journeyId, spaceCraft) match {
      case None          =>
        LOGGER.info("Could not find Journey by space craft {} with id: {}", spaceCraft, journeyId);
      case Some(journey) =>
        LOGGER.info("Found Journey by space craft {} with id: {}", spaceCraft, journeyId);
        LOGGER.info("Journey: {}", journey);
    }
  }
}
