package com.datastax.workshop

import java.util.UUID

import org.junit.jupiter.api._
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

import com.datastax.oss.driver.api.core.cql.Row

@RunWith(classOf[JUnitPlatform])
object Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex09_Query4c_ReadMetrics {

  import Ex09_Query4c_ReadMetrics._

  @Test
  def read_measurements(): Unit = {

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
