package com.datastax.workshop

import scala.concurrent._
import scala.concurrent.duration._

import org.slf4j.LoggerFactory

import hutil.stringformat._

import com.outworkers.phantom.dsl._

abstract class ExerciseBase(exerciseName: String) extends munit.FunSuite {

  // ===> WE WILL USE THIS VALUES EVERYWHERE
  protected var SPACECRAFT = "Crew Dragon Endeavour,SpaceX"
  protected var JOURNEY_ID = "90163870-c5d6-11ea-b11f-c30e2b038000"

  protected val LOGGER = LoggerFactory.getLogger(exerciseName)

  val startMarker = "===== Start exercise ========================".yellow
  val stopMarker  = "===== Exercise terminated ===================".yellow

  override def beforeAll(): Unit = {
    LOGGER.info(startMarker)
    LOGGER.info("=====>>> Setting up CassandraConnection ...".magenta)
    Await.ready(JourneyDB.createAsync(), 20.seconds)
    // Await.ready(JourneyDB.truncateAsync(), 20.seconds)
    LOGGER.info("=====>>> CassandraConnection Setup COMPLETE".magenta)
  }

  override def afterAll(): Unit = {
    LOGGER.info("=====>>> Shutting down CassandraConnection ...".magenta)
    // JourneyDB.truncate()
    JourneyDB.shutdown()
    // Thread.sleep(10000L)
    LOGGER.info("=====>>> CassandraConnection Shutdown COMPLETE".magenta)
    LOGGER.info(stopMarker)
  }
}
