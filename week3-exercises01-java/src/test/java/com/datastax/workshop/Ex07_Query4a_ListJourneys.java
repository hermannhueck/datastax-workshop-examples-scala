package com.datastax.workshop;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

@RunWith(JUnitPlatform.class)
public class Ex07_Query4a_ListJourneys extends ExerciseBase {

    public Ex07_Query4a_ListJourneys() {
        super("Exercise4");
    }

    /*
     * select * from spacecraft_journey_catalog WHERE
     * journey_id=47b04070-c4fb-11ea-babd-17b91da87c10 AND
     * spacecraft_name='DragonCrew,SpaceX';
     */
    @Test
    public void listJourneys() {

        SimpleStatement stmt = SimpleStatement
                .builder("select * from spacecraft_journey_catalog where spacecraft_name=?")
                .addPositionalValue(SPACECRAFT).build();

        ResultSet rs = cqlSession.execute(stmt);
        for (Row row : rs.all()) {
            LOGGER.info("- Journey: {} Summary: {}", row.getUuid("journey_id"), row.getString("summary"));
        }

        LOGGER.info("SUCCESS");
    }
}
