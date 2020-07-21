package com.datastax.workshop

import java.util.UUID

import scala.concurrent._
import scala.util.chaining._

import org.joda.time.Instant

class Ex05_Query3c_Travel extends ExerciseBase("Exercise3") {

  val numMesurements = 10

  test("save readings") {

    implicit val ec: ExecutionContext = ExecutionContext.global

    (0 until numMesurements)
      .toList
      .map(logJourney)
      .pipe(Future.sequence(_))
      .map { _ =>
        LOGGER.info("Sensor values saved", JOURNEY_ID)
        LOGGER.info("SUCCESS")
      }
  }

  def logJourney(i: Int)(
      implicit
      ec: ExecutionContext
  ): Future[Unit] = {
    val readingTime = Instant.now
    val speed       = 300 + i + Math.random * 10
    val pressure    = Math.random * 20
    val temperature = Math.random * 300
    val loc_x       = 13 + i
    val loc_y       = 14 + i
    val loc_z       = 36 + i
    JourneyDB
      .log(
        SPACECRAFT,
        UUID.fromString(JOURNEY_ID),
        readingTime,
        speed,
        pressure,
        temperature,
        loc_x.toDouble,
        loc_y.toDouble,
        loc_z.toDouble
      )
      .map { _ =>
        Thread.sleep(200)
        LOGGER.info("{}/{} - travelling ...", i, numMesurements)
      }
  }
}
