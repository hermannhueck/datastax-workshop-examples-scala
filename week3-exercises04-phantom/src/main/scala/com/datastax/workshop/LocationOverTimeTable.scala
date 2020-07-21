package com.datastax.workshop

import java.util.UUID

import com.outworkers.phantom.dsl._

import org.joda.time.Instant

case class LocationOverTime(
    spaceCraft: String,
    journeyId: UUID,
    readingTime: Instant,
    locationUnit: String,
    location: Map[String, Double]
)

abstract class LocationOverTimeTable extends Table[LocationOverTimeTable, LocationOverTime] {

  object spaceCraft   extends StringColumn with PartitionKey
  object journeyId    extends UUIDColumn with PartitionKey
  object readingTime  extends DateTimeColumn with ClusteringOrder with Descending
  object locationUnit extends StringColumn
  // UDT are not supported in OSS Phantom
  object location     extends MapColumn[String, Double]

  override def fromRow(row: Row): LocationOverTime =
    LocationOverTime(
      spaceCraft(row),
      journeyId(row),
      readingTime(row).toInstant,
      locationUnit(row),
      location(row)
    )

  @annotation.nowarn("cat=deprecation")
  def create(pot: LocationOverTime): ResultSet =
    insert
      .value(_.spaceCraft, pot.spaceCraft)
      .value(_.journeyId, pot.journeyId)
      .value(_.readingTime, pot.readingTime.toDateTime)
      .value(_.locationUnit, pot.locationUnit)
      .value(_.location, pot.location)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .await()

  def findLocationMeasurements(spaceCraft: String, journeyId: UUID): List[LocationOverTime] =
    select
      .where(j => j.spaceCraft eqs spaceCraft)
      .and(j => j.journeyId eqs journeyId)
      .fetch()
      .await()
}
