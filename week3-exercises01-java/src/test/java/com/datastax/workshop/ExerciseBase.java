package com.datastax.workshop;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

public abstract class ExerciseBase implements DataModelConstants {

  protected static String exerciseName = "";

  protected static String SPACECRAFT = "Crew Dragon Endeavour,SpaceX";
  protected static String JOURNEY_ID = "90163870-c5d6-11ea-b11f-c30e2b038000";

  protected static Logger LOGGER = LoggerFactory.getLogger(exerciseName);
  protected static CqlSession cqlSession = null;
  protected static JourneyRepository journeyRepo = null;

  protected ExerciseBase(String exerciseName) {
    ExerciseBase.exerciseName = exerciseName;
    LOGGER = LoggerFactory.getLogger(exerciseName);
  }

  protected static CqlSessionBuilder sessionBuilder() {
    return CqlSession.builder().withCloudSecureConnectBundle(Paths.get(DBConnection.SECURE_CONNECT_BUNDLE))
        .withAuthCredentials(DBConnection.USERNAME, DBConnection.PASSWORD).withKeyspace(DBConnection.KEYSPACE);
  }

  protected static String startMarker = "===== Start exercise ========================";
  protected static String stopMarker = "===== Exercise terminated ===================";

  protected static CqlSession createCqlSession(Logger logger) {
    logger.info(startMarker);
    return sessionBuilder().build();
  }

  protected static void closeCqlSession(CqlSession cqlSession, Logger logger) {
    if (null != cqlSession)
      cqlSession.close();
    logger.info(stopMarker);
  }

  @BeforeAll
  protected static void initConnection() {
    cqlSession = createCqlSession(LOGGER);
    journeyRepo = new JourneyRepository(cqlSession);
  }

  @AfterAll
  protected static void closeConnectionToCassandra() {
    closeCqlSession(cqlSession, LOGGER);
  }
}
