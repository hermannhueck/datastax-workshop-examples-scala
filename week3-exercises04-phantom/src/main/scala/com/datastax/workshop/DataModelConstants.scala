package com.datastax.workshop

// import com.datastax.oss.driver.api.core.`type`.DataTypes._
// import com.datastax.oss.driver.api.querybuilder.SchemaBuilder._

// import com.datastax.oss.driver.api.core.CqlSession
// import com.datastax.oss.driver.api.core.cql.SimpleStatement
// import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder
// import com.datastax.oss.driver.api.querybuilder.QueryBuilder

/**
  * A good practice is to group all constants related to the Cassandra
  * data model to an interface to be used in classes.
  *
  * -> Removing a column will you a compilatinon error
  * -> Renaming a column at a single place
  * -> Easy to be used with QueryBuilder
  *
  * @author Cedrick LUNVEN (@clunven)
  */
object DataModelConstants {

  /** Constants for table todo_tasks */
  val TABLE_JOURNEY           = "spacecraft_journey_catalog"
  val JOURNEY_SPACECRAFT_NAME = "spacecraft_name"
  val JOURNEY_ID              = "journey_id"
  val JOURNEY_START           = "start"
  val JOURNEY_END             = "end"
  val JOURNEY_ACTIVE          = "active"
  val JOURNEY_SUMMARY         = "summary"

}
