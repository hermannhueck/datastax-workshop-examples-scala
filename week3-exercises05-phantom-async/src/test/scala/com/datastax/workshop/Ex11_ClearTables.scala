package com.datastax.workshop

import scala.concurrent.duration._

class Ex11_ClearTables extends ExerciseBase("Exercise4") {

  test("clear tables") {

    // This doesn't clear the tables
    // ??????
    JourneyDB.clearTables().await(20.seconds)

    LOGGER.info("SUCCESS")
  }
}
