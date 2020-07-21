package com.datastax.workshop

import java.util.UUID

import org.joda.time.Instant

import com.outworkers.phantom.dsl._

class JourneyDB(override val connector: CassandraConnection) extends Database[JourneyDB](connector) {

  object journeys            extends JourneyTable with connector.Connector
  object speedOverTime       extends SpeedOverTimeTable with connector.Connector
  object pressureOverTime    extends PressureOverTimeTable with connector.Connector
  object temperatureOverTime extends TemperatureOverTimeTable with connector.Connector
  object locationOverTime    extends LocationOverTimeTable with connector.Connector

  def log(
      spaceCraft: String,
      journeyId: UUID,
      readingTime: Instant,
      speed: Double,
      pressure: Double,
      temperature: Double,
      loc_x: Double,
      loc_y: Double,
      loc_z: Double
  ): ResultSet = {
    speedOverTime.create(SpeedOverTime(spaceCraft, journeyId, readingTime, "km/h", speed))
    pressureOverTime.create(PressureOverTime(spaceCraft, journeyId, readingTime, "°C", pressure))
    temperatureOverTime.create(TemperatureOverTime(spaceCraft, journeyId, readingTime, "Pa", temperature))
    val location = Map("loc_x" -> loc_x, "loc_y" -> loc_y, "loc_z" -> loc_z)
    locationOverTime.create(LocationOverTime(spaceCraft, journeyId, readingTime, "AU", location))
  }

  def clearTables(): Unit = {
    speedOverTime.clearTable()
    pressureOverTime.clearTable()
    temperatureOverTime.clearTable()
    locationOverTime.clearTable()
    journeys.clearTable()
    ()
  }
}

object JourneyDB extends JourneyDB(DBConnection.connector)
