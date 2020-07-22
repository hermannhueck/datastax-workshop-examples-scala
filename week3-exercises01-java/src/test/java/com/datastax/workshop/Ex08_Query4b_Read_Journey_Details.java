package com.datastax.workshop;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex08_Query4b_Read_Journey_Details extends ExerciseBase {

    public Ex08_Query4b_Read_Journey_Details() {
        super("Exercise4");
    }

    @Test
    public void read_a_journey() {
        Optional<Journey> j = journeyRepo.find(UUID.fromString(JOURNEY_ID), SPACECRAFT);
        if (j.isPresent()) {
            LOGGER.info("Journey has been found");
            LOGGER.info("- Uid:\t\t {}", j.get().getId());
            LOGGER.info("- Spacecraft:\t {}", j.get().getSpaceCraft());
            LOGGER.info("- Summary:\t {}", j.get().getSummary());
            LOGGER.info("- Takeoff:\t {}", j.get().getStart());
            LOGGER.info("- Landing:\t {}", j.get().getEnd());
            LOGGER.info("SUCCESS");
        } else {
            LOGGER.info("Journey {} not found, check class 'Ex04_ReadParsePage' or DB", JOURNEY_ID);
            LOGGER.info("FAILURE");
        }
    }
}
