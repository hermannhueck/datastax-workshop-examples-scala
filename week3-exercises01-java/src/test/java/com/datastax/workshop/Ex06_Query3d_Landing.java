package com.datastax.workshop;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex06_Query3d_Landing extends ExerciseBase {

    public Ex06_Query3d_Landing() {
        super("Exercise3");
    }

    @Test
    public void landing_journey() {

        journeyRepo.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT);
        LOGGER.info("Journey {} has now landed", JOURNEY_ID);
        LOGGER.info("SUCCESS");
    }
}
