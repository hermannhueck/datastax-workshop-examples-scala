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

    @Test
    public void save_readings() throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            double speed = 300 + i + Math.random() * 10;
            double pressure = Math.random() * 20;
            double temperature = Math.random() * 300;
            double x = 13 + i, y = 14 + i, z = 36 + i;
            Instant readingTime = Instant.now();
            journeyRepo.log(UUID.fromString(JOURNEY_ID), SPACECRAFT, speed, pressure, temperature, x, y, z,
                    readingTime);
            Thread.sleep(200);
            LOGGER.info("{}/50 - travelling..", i);
        }
        LOGGER.info("Reading saved", JOURNEY_ID);
        LOGGER.info("SUCCESS");
        // select * from spacecraft_speed_over_time where
        // spacecraft_name='DragonCrew,SpaceX' AND
        // journey_id=b7fdf670-c5b8-11ea-9d41-49528c2e2634;
    }
}
