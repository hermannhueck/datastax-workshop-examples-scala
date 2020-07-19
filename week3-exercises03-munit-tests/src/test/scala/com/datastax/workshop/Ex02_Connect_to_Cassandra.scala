package com.datastax.workshop

import java.io.File

/**
  * EXERCISE 2 : Connect to Astra using zip bundle and credentials.
  *
  * @author Developer Advocate Team
  */
class Ex02_Connect_to_Cassandra extends ExerciseBase("Exercise2") {

  test("Test connectivity to Astra") {

    assert(DBConnection.SECURE_CONNECT_BUNDLE.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.KEYSPACE.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.USERNAME.nonEmpty, "Please fill DBConnection class constants")
    assert(DBConnection.PASSWORD.nonEmpty, "Please fill DBConnection class constants")
    assert(
      new File(DBConnection.SECURE_CONNECT_BUNDLE).exists,
      s"File '${DBConnection.SECURE_CONNECT_BUNDLE}' has not been found\n" + "To run this sample you need to download the secure bundle file from ASTRA WebPage\n" + "More info here:"
    )
    LOGGER.info("File {} located", DBConnection.SECURE_CONNECT_BUNDLE)

    LOGGER.info("Connected to Keyspace {}", cqlSession.getKeyspace.get)
    LOGGER.info("SUCCESS")
  }
}
