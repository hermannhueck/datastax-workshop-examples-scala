package com.datastax.workshop

import java.util.UUID

import org.joda.time.DateTime
import org.joda.time.Instant

import org.slf4j.LoggerFactory

import com.outworkers.phantom.dsl._

import com.datastax.driver.core.utils.UUIDs

case class Journey(
    spaceCraft: String,
    id: UUID,
    summary: String,
    active: Boolean,
    start: Option[Instant],
    end: Option[Instant]
)

case object Journey {

  def apply(
      spaceCraft: String,
      summary: String,
      active: Boolean,
      start: Option[Instant],
      end: Option[Instant]
  ): Journey =
    Journey(spaceCraft, UUIDs.timeBased, summary, active, start, end)
}

abstract class JourneyTable extends Table[JourneyTable, Journey] {

  private val LOGGER = LoggerFactory.getLogger("JourneyRepository")

  object spacecraft extends StringColumn with PartitionKey
  object id         extends UUIDColumn with ClusteringOrder with Descending
  object summary    extends StringColumn
  object active     extends BooleanColumn
  object start      extends OptionalDateTimeColumn
  object end        extends OptionalDateTimeColumn

  override def fromRow(row: Row): Journey =
    Journey(
      spacecraft(row),
      id(row),
      summary(row),
      active(row),
      start(row).map(_.toInstant),
      end(row).map(_.toInstant)
    )

  @annotation.nowarn("cat=deprecation")
  def create(spacecraft: String, summary: String, uuid: UUID = UUIDs.timeBased): UUID = {

    // val journey  = Journey(id, summary, null, null, spacecraft, false)
    // store(journey)
    // .consistencyLevel_=(ConsistencyLevel.QUORUM)
    insert
      .value(_.spacecraft, spacecraft)
      .value(_.id, uuid)
      .value(_.summary, summary)
      .value(_.active, false)
      .value(_.start, None)
      .value(_.end, None)
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .await()

    LOGGER.info("Journey with id '{}' has been inserted in DB", uuid)
    uuid
  }

  def findById(journeyId: UUID, spacecraft: String): Option[Journey] =
    select
      .where(j => j.spacecraft eqs spacecraft)
      .and(j => j.id eqs journeyId)
      .one()
      .await()

  def findAll(spacecraft: String): List[Journey] =
    select
      .where(j => j.spacecraft eqs spacecraft)
      .fetch()
      .await()

  @annotation.nowarn("cat=deprecation")
  def takeoff(journeyId: UUID, spacecraft: String): ResultSet = {
    update
      .where(j => j.spacecraft eqs spacecraft)
      .and(j => j.id eqs journeyId)
      .modify(_.active.setTo(true))
      .and(_.start.setTo(Option(DateTime.now)))
      .and(_.end.setTo(None))
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .await()
  }

  @annotation.nowarn("cat=deprecation")
  def landing(journeyId: UUID, spacecraft: String): ResultSet = {
    update
      .where(j => j.spacecraft eqs spacecraft)
      .and(j => j.id eqs journeyId)
      .modify(_.active.setTo(false))
      .and(_.end.setTo(Option(DateTime.now)))
      .consistencyLevel_=(ConsistencyLevel.QUORUM)
      .future()
      .await()
  }
}
