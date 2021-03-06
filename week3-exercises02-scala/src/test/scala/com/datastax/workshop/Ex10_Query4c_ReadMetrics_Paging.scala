package com.datastax.workshop

import java.util.UUID
import java.util.Iterator

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

import com.datastax.oss.driver.api.core.cql.{Row, SimpleStatement}

@RunWith(classOf[JUnitPlatform])
object Ex10_Query4c_ReadMetrics_Paging extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex10_Query4c_ReadMetrics_Paging {

  import Ex10_Query4c_ReadMetrics_Paging._

  @Test
  def read_a_dimension_paging(): Unit = {

    var stmt = SimpleStatement
      .builder("select * from spacecraft_speed_over_time where spacecraft_name=? AND journey_id=?")
      .addPositionalValue(SPACECRAFT)
      .addPositionalValue(UUID.fromString(JOURNEY_ID))
      .build

    // Set page to 10
    stmt = stmt.setPageSize(10)

    var rs                 = cqlSession.execute(stmt)
    val pagingStateAsBytes = rs.getExecutionInfo.getPagingState

    showPage(rows = rs.iterator, items = rs.getAvailableWithoutFetching, pageNumber = 1)

    // Here is if you NEXT THE DRIVERS WILL FETCH page 2
    // We can go directly to page2 with
    stmt = stmt.setPagingState(pagingStateAsBytes)
    rs = cqlSession.execute(stmt)

    showPage(rows = rs.iterator, items = rs.getAvailableWithoutFetching, pageNumber = 2)

    LOGGER.info("SUCCESS")
  }

  private def showPage(rows: Iterator[Row], items: Int, pageNumber: Int): Unit = {

    LOGGER.info("Page {}: {} items", pageNumber, items)

    (0 until items).foreach { _ =>
      val row = rows.next
      LOGGER.info("- time={}, value={}", row.getInstant("reading_time"), row.getDouble("speed"))
    }
  }
}
