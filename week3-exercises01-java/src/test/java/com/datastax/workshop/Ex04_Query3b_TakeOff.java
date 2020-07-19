package com.datastax.workshop;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex04_Query3b_TakeOff extends ExerciseBase {

    public Ex04_Query3b_TakeOff() {
        super("Exercise3");
    }

    @Test
    public void takeoff_the_spacecraft() {

        LOGGER.info("9..8..7..6..5..4..3..2..1 Ignition");
        journeyRepo.takeoff(UUID.fromString(JOURNEY_ID), SPACECRAFT);
        LOGGER.info("Journey {} has now taken off", JOURNEY_ID);
        LOGGER.info("SUCCESS");
    }
}
