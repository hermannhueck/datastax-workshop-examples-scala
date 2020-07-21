package com.datastax.workshop;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex11_ClearTables extends ExerciseBase {

    public Ex11_ClearTables() {
        super("Exercise4");
    }

    @Test
    public void clear_tables() {

        journeyRepo.clearTables();

        LOGGER.info("SUCCESS");
    }
}
