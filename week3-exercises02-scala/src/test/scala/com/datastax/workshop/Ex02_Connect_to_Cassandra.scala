package com.datastax.workshop

import java.io.File

import org.junit.jupiter.api._
import Assertions._
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(classOf[JUnitPlatform])
object Ex02_Connect_to_Cassandra extends ExerciseBase("Exercise2")

@RunWith(classOf[JUnitPlatform])
class Ex02_Connect_to_Cassandra {

  import Ex02_Connect_to_Cassandra._

  @Test
  def should_connect_to_Astra(): Unit = {

    assertTrue(DBConnection.SECURE_CONNECT_BUNDLE.nonEmpty, "Please fill DBConnection class constants")
    assertTrue(DBConnection.KEYSPACE.nonEmpty, "Please fill DBConnection class constants")
    assertTrue(DBConnection.USERNAME.nonEmpty, "Please fill DBConnection class constants")
    assertTrue(DBConnection.PASSWORD.nonEmpty, "Please fill DBConnection class constants")
    assertTrue(
      new File(DBConnection.SECURE_CONNECT_BUNDLE).exists,
      "File '" + DBConnection.SECURE_CONNECT_BUNDLE + "' has not been found\n" + "To run this sample you need to download the secure bundle file from ASTRA WebPage\n" + "More info here:"
    )
    LOGGER.info("File {} located", DBConnection.SECURE_CONNECT_BUNDLE)

    LOGGER.info("Connected with Keyspace {}", cqlSession.getKeyspace.get)
    LOGGER.info("SUCCESS")
  }
}
