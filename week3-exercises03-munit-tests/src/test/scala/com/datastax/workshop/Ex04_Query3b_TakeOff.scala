package com.datastax.workshop

import java.util.UUID

class Ex04_Query3b_TakeOff extends ExerciseBase("Exercise3") {

  test("takeoff the spacecraft") {

    LOGGER.info("9..8..7..6..5..4..3..2..1 Ignition")
    journeyRepo.takeoff(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now taken off", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }
}
