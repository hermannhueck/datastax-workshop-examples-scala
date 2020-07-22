package com.datastax.workshop;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex05_Query3c_Travel extends ExerciseBase {

    public Ex05_Query3c_Travel() {
        super("Exercise3");
    }

    int numMeasurements = 10;

    @Test
    public void save_readings() throws InterruptedException {

        for (int i = 0; i < numMeasurements; i++) {
            logJourney(i);
        }
        LOGGER.info("Reading saved", JOURNEY_ID);
        LOGGER.info("SUCCESS");
    }

    private void logJourney(int i) throws InterruptedException {
        Instant readingTime = Instant.now();
        double speed = 300 + i + Math.random() * 10;
        double pressure = Math.random() * 20;
        double temperature = Math.random() * 300;
        double loc_x = 13 + i, loc_y = 14 + i, loc_z = 36 + i;

        journeyRepo.log(SPACECRAFT, UUID.fromString(JOURNEY_ID), readingTime, speed, pressure, temperature, loc_x,
                loc_y, loc_z);

        Thread.sleep(200);
        LOGGER.info("{}/{} - travelling ...", i, numMeasurements);
    }
}
