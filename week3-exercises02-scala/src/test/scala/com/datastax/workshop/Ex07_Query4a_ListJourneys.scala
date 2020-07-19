package com.datastax.workshop

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

import com.datastax.oss.driver.api.core.cql.SimpleStatement

@RunWith(classOf[JUnitPlatform])
object Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4")

@RunWith(classOf[JUnitPlatform])
class Ex07_Query4a_ListJourneys {

  import Ex07_Query4a_ListJourneys._

  /*
   * select * from spacecraft_journey_catalog
   * WHERE journey_id=47b04070-c4fb-11ea-babd-17b91da87c10
   * AND spacecraft_name='DragonCrew,SpaceX';
   */
  @Test
  def listJourneys(): Unit = {

    val stmt = SimpleStatement
      .builder("select * from spacecraft_journey_catalog where spacecraft_name=?")
      .addPositionalValue(SPACECRAFT)
      .build

    val rs = cqlSession.execute(stmt)

    import scala.jdk.CollectionConverters._
    rs.all().asScala.foreach { row =>
      LOGGER.info("- Journey: {} Summary: {}", row.getUuid("journey_id"), row.getString("summary"))
    }

    LOGGER.info("SUCCESS")
  }
}
