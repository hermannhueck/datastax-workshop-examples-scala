package com.datastax.workshop

class Ex11_ClearTables extends ExerciseBase("Exercise4") {

  test("clear tables") {

    JourneyDB.clearTables()

    LOGGER.info("SUCCESS")
  }
}
