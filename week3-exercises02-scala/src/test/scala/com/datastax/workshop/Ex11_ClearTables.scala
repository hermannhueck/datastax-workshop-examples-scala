package com.datastax.workshop

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(classOf[JUnitPlatform])
object Ex11_ClearTables extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex11_ClearTables {

  import Ex11_ClearTables._

  @Test
  def clear_tables(): Unit = {

    journeyRepo.clearTables()

    LOGGER.info("SUCCESS")
  }
}
