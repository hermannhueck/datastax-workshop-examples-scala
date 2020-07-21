package com.datastax.workshop

import java.util.UUID

import com.outworkers.phantom.dsl._

import org.joda.time.Instant

case class PressureOverTime(
    spaceCraft: String,
    journeyId: UUID,
    readingTime: Instant,
    pressureUnit: String,
    pressure: Double
)

abstract class PressureOverTimeTable extends Table[PressureOverTimeTable, PressureOverTime] {

  object spaceCraft   extends StringColumn with PartitionKey
  object journeyId    extends UUIDColumn with PartitionKey
  object readingTime  extends DateTimeColumn with ClusteringOrder with Descending
  object pressureUnit extends StringColumn
  object pressure     extends DoubleColumn

  override def fromRow(row: Row): PressureOverTime =
    PressureOverTime(
      spaceCraft(row),
      journeyId(row),
      readingTime(row).toInstant,
      pressureUnit(row),
      pressure(row)
    )

  @annotation.nowarn("cat=deprecation")
  def create(pot: PressureOverTime): ResultSet =
    insert
      .value(_.spaceCraft, pot.spaceCraft)
      .value(_.journeyId, pot.journeyId)
      .value(_.readingTime, pot.readingTime.toDateTime)
      .value(_.pressureUnit, pot.pressureUnit)
      .value(_.pressure, pot.pressure)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .await()

  def findPressureMeasurements(spaceCraft: String, journeyId: UUID): List[PressureOverTime] =
    select
      .where(j => j.spaceCraft eqs spaceCraft)
      .and(j => j.journeyId eqs journeyId)
      .fetch()
      .await()

  def clearTable(): ResultSet =
    truncate()
      .future()
      .await()
}
