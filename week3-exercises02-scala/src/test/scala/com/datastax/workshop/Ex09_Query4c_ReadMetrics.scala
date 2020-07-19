package com.datastax.workshop

import java.util.UUID

import org.junit.jupiter.api._
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

import com.datastax.oss.driver.api.core.cql.SimpleStatement

@RunWith(classOf[JUnitPlatform])
object Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex09_Query4c_ReadMetrics {

  import Ex09_Query4c_ReadMetrics._

  @Test
  def read_a_dimension(): Unit = {

    val stmt = SimpleStatement
      .builder("select * from spacecraft_speed_over_time where spacecraft_name=? AND journey_id=?")
      .addPositionalValue(SPACECRAFT)
      .addPositionalValue(UUID.fromString(JOURNEY_ID))
      .build

    val rs = cqlSession.execute(stmt)

    import scala.jdk.CollectionConverters._
    val rows = rs.all().asScala
    rows.zipWithIndex.foreach {
      case (row, index) =>
        LOGGER.info(
          "idx:{}, time={}, value={}",
          index,
          row.getInstant("reading_time"),
          row.getDouble("speed")
        )
    }

    LOGGER.info("SUCCESS")
  }
}
