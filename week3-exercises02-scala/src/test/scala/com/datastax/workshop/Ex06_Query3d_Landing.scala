package com.datastax.workshop

import java.util.UUID

import org.junit.jupiter.api.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(classOf[JUnitPlatform])
object Ex06_Query3d_Landing extends ExerciseBase("Exercise3")

@RunWith(classOf[JUnitPlatform]) class Ex06_Query3d_Landing {

  import Ex06_Query3d_Landing._

  @Test
  def landing_journey(): Unit = {

    journeyRepo.landing(UUID.fromString(JOURNEY_ID), SPACECRAFT)
    LOGGER.info("Journey {} has now landed", JOURNEY_ID)
    LOGGER.info("SUCCESS")
  }
}
