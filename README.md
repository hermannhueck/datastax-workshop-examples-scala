# Datastax Workshop Examples implemented in Scala
### (short name: datastax-workshop-examples-scala)

This project requires Java 8.
Before starting sbt check with: *java -version*

It uses the *sbt* build tool.

This project contains four sub projects - one built on the base of the previous one:

1. week3-exercises01-java:
   Improved version of the Java examples by Datastax following the DRY principle.
   I moved the common code of all tests into a commmon base class 'ExerciseBase'.
   Especially the methods annotated with @BeforeAll and @AfterAll have been moved to the base class.

2. week3-exercises02-scala:
   Translated all the Java code to Scala still using the datastax/java-driver
   to access the Cassandra database.

3. week3-exercises03-munit-tests:
   The third variation of the examples use the Scala test framwork MUnit to implement the tests.

4. week3-exercises04-phantom:
   Changed the examples to use the Phantom Scala driver to access Cassandra instead of the datastax/java-driver.
   Phantom provides it's own type-safe DSL to formulate statements and queries.
   Hence you no longer define CQL statements in strings but in the DSL checked by the Scala compiler.
   The Phantom driver is a wrapper around the datastax/java-driver.

### Resources:

- Datastax Java Driver
  - https://github.com/datastax/java-driver

- Phantom
  - https://github.com/outworkers/phantom
  - https://medium.com/outworkers/a-series-of-phantom-part1-getting-started-with-phantom-1014787bc550
