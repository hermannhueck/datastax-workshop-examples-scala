package com.datastax.workshop

import java.util.UUID

class Ex06_Query3d_Landing extends ExerciseBase("Exercise3") {

  test("landing journey") {

    journeyRepo.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now landed", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }
}
