package com.datastax.workshop

import java.util.UUID

import scala.concurrent.Future

import com.outworkers.phantom.dsl._

import org.joda.time.Instant

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
  ): Future[Unit] =
    for {
      _       <- speedOverTime.create(SpeedOverTime(spaceCraft, journeyId, readingTime, "km/h", speed))
      _       <- pressureOverTime.create(PressureOverTime(spaceCraft, journeyId, readingTime, "°C", pressure))
      _       <- temperatureOverTime.create(TemperatureOverTime(spaceCraft, journeyId, readingTime, "Pa", temperature))
      location = Map("loc_x" -> loc_x, "loc_y" -> loc_y, "loc_z" -> loc_z)
      _       <- locationOverTime.create(LocationOverTime(spaceCraft, journeyId, readingTime, "AU", location))
    } yield ()

  def clearTables(): Future[Unit] =
    for {
      _ <- speedOverTime.clearTable()
      _ <- pressureOverTime.clearTable()
      _ <- temperatureOverTime.clearTable()
      _ <- locationOverTime.clearTable()
      _ <- journeys.clearTable()
    } yield ()
}

object JourneyDB extends JourneyDB(DBConnection.connector)
