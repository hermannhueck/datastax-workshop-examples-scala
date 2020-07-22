package com.datastax.workshop

import java.util.UUID

import com.datastax.oss.driver.api.core.cql.Row

class Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4") {

  test("read measurements") {

    LOGGER.info("----- Speed Measurements -----")
    journeyRepo
      .findSpeedMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach { case row -> index => logMeasurement("speed", index, row) }

    LOGGER.info("----- Temperature Measurements -----")
    journeyRepo
      .findTemperatureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach { case row -> index => logMeasurement("temperature", index, row) }

    LOGGER.info("----- Pressure Measurements -----")
    journeyRepo
      .findPressureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach { case row -> index => logMeasurement("pressure", index, row) }

    LOGGER.info("----- Location Measurements -----")
    journeyRepo
      .findLocationMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID))
      .zipWithIndex
      .foreach { case row -> index => logLocationMeasurement(index, row) }

    LOGGER.info("SUCCESS")
  }

  def logMeasurement(kind: String, index: Int, row: Row): Unit =
    LOGGER.info(
      "idx:{}, time={}, value={}",
      index,
      row.getInstant("reading_time"),
      row.getDouble(kind)
    )

  def logLocationMeasurement(index: Int, row: Row): Unit = {
    LOGGER.info(
      "idx:{}, time={}, location(x={}, y={}, z={})",
      index,
      row.getInstant("reading_time"),
      row.getUdtValue("location").getDouble("x_coordinate"),
      row.getUdtValue("location").getDouble("y_coordinate"),
      row.getUdtValue("location").getDouble("z_coordinate")
    )
  }
}
