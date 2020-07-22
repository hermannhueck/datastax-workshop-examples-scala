package com.datastax.workshop;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.datastax.oss.driver.api.core.cql.*;

@RunWith(JUnitPlatform.class)
public class Ex10_Query4c_ReadMetrics_Paging extends ExerciseBase {

    public Ex10_Query4c_ReadMetrics_Paging() {
        super("Exercise4");
    }

    @Test
    public void read_a_dimension_paging() {

        SimpleStatement stmt = SimpleStatement
                .builder("select * from spacecraft_speed_over_time where spacecraft_name=? AND journey_id=?")
                .addPositionalValue(SPACECRAFT).addPositionalValue(UUID.fromString(JOURNEY_ID)).build();

        // Set page to 10
        stmt = stmt.setPageSize(10);

        ResultSet rs = cqlSession.execute(stmt);
        ByteBuffer pagingStateAsBytes = rs.getExecutionInfo().getPagingState();

        showPage(rs.iterator(), rs.getAvailableWithoutFetching(), 1);

        // Here is if you NEXT THE DRIVERS WILL FETCH page 2
        // We can go directly to page2 with
        stmt = stmt.setPagingState(pagingStateAsBytes);
        rs = cqlSession.execute(stmt);

        showPage(rs.iterator(), rs.getAvailableWithoutFetching(), 2);

        LOGGER.info("SUCCESS");
    }

    private void showPage(Iterator<Row> rows, int items, int pageNumber) {

        LOGGER.info("Page {}: {} items", pageNumber, items);

        for (int i = 0; i < items; i++) {
            Row row = rows.next();
            LOGGER.info("- time={}, value={}", row.getInstant("reading_time"), row.getDouble("speed"));
        }
    }
}
