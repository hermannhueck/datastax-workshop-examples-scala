package com.datastax.workshop;

import java.util.List;

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

    @Test
    public void listJourneys() {

        List<Journey> journeys = journeyRepo.findJourneys(SPACECRAFT);

        for (Journey journey : journeys) {
            LOGGER.info("- Journey: {} Summary: {}", journey.getId(), journey.getSummary());
        }

        LOGGER.info("SUCCESS");
    }
}
