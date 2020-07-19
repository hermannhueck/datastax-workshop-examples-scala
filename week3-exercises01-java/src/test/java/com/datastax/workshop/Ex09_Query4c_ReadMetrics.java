package com.datastax.workshop;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

@RunWith(JUnitPlatform.class)
public class Ex09_Query4c_ReadMetrics extends ExerciseBase {

    public Ex09_Query4c_ReadMetrics() {
        super("Exercise4");
    }

    @Test
    public void read_a_dimension() {

        SimpleStatement stmt = SimpleStatement
                .builder("select * from spacecraft_speed_over_time where spacecraft_name=? AND journey_id=?")
                .addPositionalValue(SPACECRAFT).addPositionalValue(UUID.fromString(JOURNEY_ID)).build();

        ResultSet rs = cqlSession.execute(stmt);

        int offset = 0;
        for (Row row : rs.all()) {
            LOGGER.info("idx:{}, time={}, value={}", ++offset, row.getInstant("reading_time"), row.getDouble("speed"));
        }
        LOGGER.info("SUCCESS");
    }
}
