package com.datastax.workshop

import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.connectors.ContactPoints

object DBConnection {

  @annotation.nowarn("cat=unused-privates")
  private val SECURE_CONNECT_BUNDLE = "secure-connect-killrvideocluster.zip"

  @annotation.nowarn("cat=unused-privates")
  private val USERNAME = "KVUser"

  @annotation.nowarn("cat=unused-privates")
  private val PASSWORD = "KVPassword"

  // use thes values for local Cassandra instance
  private val KEYSPACE = "killrvideo"

  private val HOSTS                  = Seq("localhost")
  val connector: CassandraConnection = ContactPoints(HOSTS).keySpace(KEYSPACE)
}
