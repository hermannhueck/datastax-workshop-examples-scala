package com.datastax.workshop;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Ex03_Query3a_Insert_Journey extends ExerciseBase {

    public Ex03_Query3a_Insert_Journey() {
        super("Exercise3");
    }

    @Test
    public void insert_a_journey() {

        String spaceCraft = "Crew Dragon Endeavour,SpaceX";
        String journeySummary = "Bring Astronauts to ISS";

        UUID journeyId = journeyRepo.create(spaceCraft, journeySummary);

        LOGGER.info("Journey created : {}", journeyId);
        LOGGER.info("SUCCESS");

        Optional<Journey> journey = journeyRepo.find(journeyId, spaceCraft);
        if (!journey.isPresent()) {
            LOGGER.info("Could not find Journey by space craft {} with id: {}", spaceCraft, journeyId);
        } else {
            LOGGER.info("Found Journey by space craft {} with id: {}", spaceCraft, journeyId);
            LOGGER.info("Journey: {}", journey.get());
        }
    }
}
