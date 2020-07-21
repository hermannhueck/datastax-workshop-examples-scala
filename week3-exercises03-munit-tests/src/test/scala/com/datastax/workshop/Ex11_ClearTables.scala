package com.datastax.workshop

class Ex11_ClearTables extends ExerciseBase("Exercise4") {

  test("clear tables") {

    journeyRepo.clearTables()

    LOGGER.info("SUCCESS")
  }
}
