package com.datastax.workshop

import scala.concurrent.duration._

class Ex11_ClearTables extends ExerciseBase("Exercise4") {

  test("clear tables") {

    JourneyDB.clearTables().await(20.seconds)

    LOGGER.info("SUCCESS")
  }
}
