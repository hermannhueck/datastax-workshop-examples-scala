package com.datastax.workshop

import java.util.UUID

import com.datastax.oss.driver.api.core.cql._

class Ex09_Query4c_ReadMetrics extends ExerciseBase("Exercise4") {

  test("read a dimension") {

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
