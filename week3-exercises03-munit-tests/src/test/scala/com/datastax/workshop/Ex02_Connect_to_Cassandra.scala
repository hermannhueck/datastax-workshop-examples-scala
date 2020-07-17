package com.datastax.workshop

import java.io.File
import java.nio.file.Paths

import scala.util.Using

import org.slf4j.LoggerFactory

import com.datastax.oss.driver.api.core.CqlSession

/**
  * EXERCISE 2 : Connect to Astra using zip bundle and credentials.
  *
  * @author Developer Advocate Team
  */
class Ex02_Connect_to_Cassandra extends munit.FunSuite {

  /** Logger for the class. */
  private val LOGGER = LoggerFactory.getLogger("Exercise2")

  test("Test connectivity to Astra") {
    LOGGER.info(startMarker)
    // Given
    assert(DBConnection.SECURE_CONNECT_BUNDLE.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.KEYSPACE.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.USERNAME.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.PASSWORD.nonEmpty, "Please fill DBConnection class constants")
    assert(
      new File(DBConnection.SECURE_CONNECT_BUNDLE).exists,
      s"File '${DBConnection.SECURE_CONNECT_BUNDLE}' has not been found\n" + "To run this sample you need to download the secure bundle file from ASTRA WebPage\n" + "More info here:"
    )
    LOGGER.info("File {} located", DBConnection.SECURE_CONNECT_BUNDLE)
    Using {
      CqlSession
        .builder
        .withCloudSecureConnectBundle(Paths.get(DBConnection.SECURE_CONNECT_BUNDLE))
        .withAuthCredentials(DBConnection.USERNAME, DBConnection.PASSWORD)
        .withKeyspace(DBConnection.KEYSPACE)
        .build
    } { cqlSession =>
      LOGGER.info("Connected with Keyspace {}", cqlSession.getKeyspace.get)
    }
    LOGGER.info("SUCCESS")
    LOGGER.info(stopMarker)
  }
}
