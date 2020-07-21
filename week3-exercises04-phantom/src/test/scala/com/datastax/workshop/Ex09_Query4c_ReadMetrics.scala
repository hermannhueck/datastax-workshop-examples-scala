package com.datastax.workshop

import java.util.UUID

class Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4") {

  test("read a dimension") {

    LOGGER.info("----- Speed Measurements -----")
    JourneyDB
      .speedOverTime
      .findSpeedMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach {
        case measurement -> index =>
          LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.speed)
      }

    Thread.sleep(3000L)

    LOGGER.info("----- Pressure Measurements -----")
    JourneyDB
      .temperatureOverTime
      .findTemperatureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach {
        case measurement -> index =>
          LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.temperature)
      }

    Thread.sleep(3000L)

    LOGGER.info("----- Pressure Measurements -----")
    JourneyDB
      .pressureOverTime
      .findPressureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach {
        case measurement -> index =>
          LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.pressure)
      }

    Thread.sleep(3000L)

    LOGGER.info("----- Location Measurements -----")
    JourneyDB
      .locationOverTime
      .findLocationMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach {
        case measurement -> index =>
          LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.location)
      }

    LOGGER.info("SUCCESS")
  }
}
