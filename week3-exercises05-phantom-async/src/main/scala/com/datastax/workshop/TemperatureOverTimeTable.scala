package com.datastax.workshop

import java.util.UUID

import scala.concurrent.Future

import com.outworkers.phantom.dsl._

import org.joda.time.Instant

case class TemperatureOverTime(
    spaceCraft: String,
    journeyId: UUID,
    readingTime: Instant,
    temperatureUnit: String,
    temperature: Double
)

abstract class TemperatureOverTimeTable extends Table[TemperatureOverTimeTable, TemperatureOverTime] {

  object spaceCraft      extends StringColumn with PartitionKey
  object journeyId       extends UUIDColumn with PartitionKey
  object readingTime     extends DateTimeColumn with ClusteringOrder with Descending
  object temperatureUnit extends StringColumn
  object temperature     extends DoubleColumn

  override def fromRow(row: Row): TemperatureOverTime =
    TemperatureOverTime(
      spaceCraft(row),
      journeyId(row),
      readingTime(row).toInstant,
      temperatureUnit(row),
      temperature(row)
    )

  @annotation.nowarn("cat=deprecation")
  def create(tot: TemperatureOverTime): Future[ResultSet] =
    insert
      .value(_.spaceCraft, tot.spaceCraft)
      .value(_.journeyId, tot.journeyId)
      .value(_.readingTime, tot.readingTime.toDateTime)
      .value(_.temperatureUnit, tot.temperatureUnit)
      .value(_.temperature, tot.temperature)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()

  def findTemperatureMeasurements(spaceCraft: String, journeyId: UUID): Future[List[TemperatureOverTime]] =
    select
      .where(j => j.spaceCraft eqs spaceCraft)
      .and(j => j.journeyId eqs journeyId)
      .fetch()

  def clearTable(): Future[ResultSet] =
    truncate()
      .future()
}
