package com.datastax.workshop;

import java.util.UUID;
import java.util.List;

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
    public void read_measurements() {

        LOGGER.info("----- Speed Measurements -----");
        List<Row> rows = journeyRepo.findSpeedMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID));
        int offset = 0;
        for (Row row : rows) {
            logMeasurement("speed", ++offset, row);
        }

        LOGGER.info("----- Temperature Measurements -----");
        rows = journeyRepo.findTemperatureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID));
        offset = 0;
        for (Row row : rows) {
            logMeasurement("temperature", ++offset, row);
        }

        LOGGER.info("----- Pressure Measurements -----");
        rows = journeyRepo.findPressureMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID));
        offset = 0;
        for (Row row : rows) {
            logMeasurement("pressure", ++offset, row);
        }

        LOGGER.info("----- Location Measurements -----");
        rows = journeyRepo.findLocationMeasurements(SPACECRAFT, UUID.fromString(JOURNEY_ID));
        offset = 0;
        for (Row row : rows) {
            logLocationMeasurement(++offset, row);
        }

        LOGGER.info("SUCCESS");
    }

    private void logMeasurement(String kind, int index, Row row) {
        LOGGER.info("idx:{}, time={}, value={}", index, row.getInstant("reading_time"), row.getDouble(kind));
    }

    private void logLocationMeasurement(int index, Row row) {
        LOGGER.info("idx:{}, time={}, location(x={}, y={}, z={})", index, row.getInstant("reading_time"),
                row.getUdtValue("location").getDouble("x_coordinate"),
                row.getUdtValue("location").getDouble("y_coordinate"),
                row.getUdtValue("location").getDouble("z_coordinate"));
    }
}
