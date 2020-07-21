package com.datastax.workshop

import java.util.UUID

import scala.concurrent._

class Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4") {

  test("read a dimension") {

    implicit val ec: ExecutionContext = ExecutionContext.global

    LOGGER.info("----- Speed Measurements -----")
    Thread.sleep(3000L)
    JourneyDB
      .speedOverTime
      .findSpeedMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .map {
        _.zipWithIndex.foreach {
          case measurement -> index =>
            LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.speed)
        }
      }
      .flatMap { _ =>
        LOGGER.info("----- Temperature Measurements -----")
        Thread.sleep(3000L)
        JourneyDB
          .temperatureOverTime
          .findTemperatureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
          .map {
            _.zipWithIndex.foreach {
              case measurement -> index =>
                LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.temperature)
            }
          }
      }
      .flatMap { _ =>
        LOGGER.info("----- Pressure Measurements -----")
        Thread.sleep(3000L)
        JourneyDB
          .pressureOverTime
          .findPressureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
          .map {
            _.zipWithIndex.foreach {
              case measurement -> index =>
                LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.pressure)
            }
          }
      }
      .flatMap { _ =>
        LOGGER.info("----- Location Measurements -----")
        Thread.sleep(3000L)
        JourneyDB
          .locationOverTime
          .findLocationMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
          .map {
            _.zipWithIndex.foreach {
              case measurement -> index =>
                LOGGER.info("idx:{}, time={}, value={}", index, measurement.readingTime, measurement.location)
                LOGGER.info("SUCCESS")
            }
          }
      }
  }
}
