package com.datastax.workshop

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(classOf[JUnitPlatform])
object Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex07_Query4a_ListJourneys {

  import Ex07_Query4a_ListJourneys._

  @Test
  def listJourneys(): Unit = {

    journeyRepo.findJourneys(SPACECRAFT).foreach { journey =>
      LOGGER.info("- Journey: {} Summary: {}", journey.id, journey.summary)
    }

    LOGGER.info("SUCCESS")
  }
}
