package com.datastax.workshop

import java.time.Instant
import java.util.UUID

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

class Ex05_Query3c_Travel extends munit.FunSuite {

  private val LOGGER                         = LoggerFactory.getLogger("Exercise3")
  private var cqlSession: CqlSession         = _
  private var journeyRepo: JourneyRepository = _

  override def beforeAll(): Unit = {
    cqlSession = createCqlSession(LOGGER)
    journeyRepo = new JourneyRepository(cqlSession)
  }

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  test("save readings") {

    for (i <- 0 until 50) {
      val speed       = 300 + i + Math.random * 10
      val pressure    = Math.random * 20
      val temperature = Math.random * 300
      val x           = 13 + i
      val y           = 14 + i
      val z           = 36 + i
      val readingTime = Instant.now
      journeyRepo
        .log(
          UUID.fromString(JOURNEY_ID),
          SPACECRAFT,
          speed,
          pressure,
          temperature,
          x.toDouble,
          y.toDouble,
          z.toDouble,
          readingTime
        )
      Thread.sleep(200)
      LOGGER.info("{}/50 - travelling..", i)
    }
    LOGGER.info("Reading saved", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }

  /* select * from spacecraft_speed_over_time
   * where spacecraft_name='DragonCrew,SpaceX'
   * AND journey_id=b7fdf670-c5b8-11ea-9d41-49528c2e2634;
   */
}
