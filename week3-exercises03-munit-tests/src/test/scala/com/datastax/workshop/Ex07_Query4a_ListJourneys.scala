package com.datastax.workshop

import com.datastax.oss.driver.api.core.cql._

class Ex07_Query4a_ListJourneys extends ExerciseBase("Exercise4") {

  /*
   * select * from spacecraft_journey_catalog
   * WHERE journey_id=47b04070-c4fb-11ea-babd-17b91da87c10
   * AND spacecraft_name='DragonCrew,SpaceX';
   */
  test("list journeys") {

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
