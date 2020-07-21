package com.datastax.workshop

class Ex02_Connect_to_Cassandra extends ExerciseBase("Exercise2") {

  test("Test connectivity to Astra") {

    LOGGER.info("Connected to Keyspace {}", JourneyDB.space.name)
    LOGGER.info("SUCCESS")
  }
}
