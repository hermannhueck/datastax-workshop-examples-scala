package com.datastax.workshop

import java.time.Instant
import java.util.UUID

class Ex05_Query3c_Travel extends ExerciseBase("Exercise3") {

  val numMeasurements = 10

  test("save readings") {

    for (i <- 0 until numMeasurements) {
      logJourney(i)
    }

    LOGGER.info("Sensor values saved", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }

  def logJourney(i: Int): Unit = {
    val readingTime = Instant.now
    val speed       = 300 + i + Math.random * 10
    val pressure    = Math.random * 20
    val temperature = Math.random * 300
    val loc_x       = 13 + i
    val loc_y       = 14 + i
    val loc_z       = 36 + i
    journeyRepo
      .log(
        SPACECRAFT,
        UUID.fromString(JOURNEY_ID),
        readingTime,
        speed,
        pressure,
        temperature,
        loc_x.toDouble,
        loc_y.toDouble,
        loc_z.toDouble
      )

    Thread.sleep(200)
    LOGGER.info("{}/{} - travelling ...", i, numMeasurements)
  }
}
