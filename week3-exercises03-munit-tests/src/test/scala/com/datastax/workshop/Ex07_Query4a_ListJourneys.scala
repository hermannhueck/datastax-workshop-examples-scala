package com.datastax.workshop

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql._

class Ex07_Query4a_ListJourneys extends munit.FunSuite {

  private val LOGGER                 = LoggerFactory.getLogger("Exercise4")
  private var cqlSession: CqlSession = _

  override def beforeAll(): Unit =
    cqlSession = createCqlSession(LOGGER)

  override def afterAll(): Unit =
    closeCqlSession(cqlSession, LOGGER)

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
