package com.datastax.workshop

import java.util.UUID

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql._

class Ex09_Query4c_ReadMetrics extends munit.FunSuite {

  private val LOGGER                 = LoggerFactory.getLogger("Exercise4")
  private var cqlSession: CqlSession = _

  override def beforeAll(): Unit =
    cqlSession = createCqlSession(LOGGER)

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

  test("read a dimension") {

    val stmt = SimpleStatement
      .builder("select * from spacecraft_speed_over_time where spacecraft_name=? AND journey_id=?")
      .addPositionalValue(SPACECRAFT)
      .addPositionalValue(UUID.fromString(JOURNEY_ID))
      .build

    val rs     = cqlSession.execute(stmt)
    // we fetch everything
    var offset = 0
    import scala.jdk.CollectionConverters._
    rs.all().asScala.foreach { row =>
      LOGGER.info(
        "idx:{}, time={}, value={}", {
          offset += 1; offset
        },
        row.getInstant("reading_time"),
        row.getDouble("speed")
      )
    }
    LOGGER.info("SUCCESS")
  }
}
