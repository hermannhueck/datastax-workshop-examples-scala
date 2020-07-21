package com.datastax.workshop

import java.util.UUID

import scala.concurrent.Future

import com.outworkers.phantom.dsl._

import org.joda.time.Instant

case class SpeedOverTime(
    spaceCraft: String,
    journeyId: UUID,
    readingTime: Instant,
    speedUnit: String,
    speed: Double
)

abstract class SpeedOverTimeTable extends Table[SpeedOverTimeTable, SpeedOverTime] {

  object spaceCraft  extends StringColumn with PartitionKey
  object journeyId   extends UUIDColumn with PartitionKey
  object readingTime extends DateTimeColumn with ClusteringOrder with Descending
  object speedUnit   extends StringColumn
  object speed       extends DoubleColumn

  override def fromRow(row: Row): SpeedOverTime =
    SpeedOverTime(
      spaceCraft(row),
      journeyId(row),
      readingTime(row).toInstant,
      speedUnit(row),
      speed(row)
    )

  @annotation.nowarn("cat=deprecation")
  def create(sot: SpeedOverTime): Future[ResultSet] =
    insert
      .value(_.spaceCraft, sot.spaceCraft)
      .value(_.journeyId, sot.journeyId)
      .value(_.readingTime, sot.readingTime.toDateTime)
      .value(_.speedUnit, sot.speedUnit)
      .value(_.speed, sot.speed)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()

  def findSpeedMeasurements(spaceCraft: String, journeyId: UUID): Future[List[SpeedOverTime]] =
    select
      .where(j => j.spaceCraft eqs spaceCraft)
      .and(j => j.journeyId eqs journeyId)
      .fetch()

  def clearTable(): Future[ResultSet] =
    truncate()
      .future()
}
