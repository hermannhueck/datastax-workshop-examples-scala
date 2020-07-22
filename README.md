# Datastax Workshop Examples implemented in Scala
### (short name: datastax-workshop-examples-scala)


This project contains five sub projects - one built on the base of the previous one:

1. **week3-exercises01-java:**
   Improved version of the Java examples by Datastax following the DRY principle.
   I moved the common code of all tests into a commmon base class 'ExerciseBase'.
   Especially the methods annotated with @BeforeAll and @AfterAll have been moved to the base class.

2. **week3-exercises02-scala:**
   Translated all the Java code to Scala still using the datastax/java-driver
   to access the Cassandra database.

3. **week3-exercises03-munit-tests:**
   The third variation of the examples rewrites the test implementations done with *JUnit*
   and *JUnitPlatform* with the Scala test framwork *MUnit*.
   (I prefer *MUnit* in favour of *ScalaTest* and *Specs2*. See resource link below.)

4. **week3-exercises04-phantom:**
   Changed the examples to use the Phantom Scala driver to access Cassandra instead of the datastax/java-driver.
   No more CQL strings! Phantom provides it's own type-safe DSL to formulate statements and queries.
   Hence you no longer define CQL statements as strings but in a DSL checked by the Scala compiler.
   The Phantom driver is a wrapper around the 3.x version of the datastax/java-driver.

5. **week3-exercises05-phantom-async:**
   Changed the Cassandra access methods to return *scala.concurrent.Future[A]* instead of *A*.
   This also requires a rewrite in the tests when sequencing several *Future*s with *flatMap*
   or with for-comprehensions (which are syntactic sugar for *flatMap/map*).
   *MUnit* tests run asynchronously when the 'test' method returns a *Future*.

### Resources:

- Datastax Java Driver
  - https://github.com/datastax/java-driver

- Phantom Scala Driver:
  - https://github.com/outworkers/phantom
  - https://medium.com/outworkers/a-series-of-phantom-part1-getting-started-with-phantom-1014787bc550

- MUnit test framework:
  - https://scalameta.org/munit/

### Compile, run, test

This project requires Java 8 in the class path.
Before starting sbt check with: *java -version*

It uses *sbt* - the Scala build tool - to build the project.

Two options, how to compile, run and test the examples
- use cloud development with Gitpod
- use local development on your machine
  
#### 1. Instructions for cloud development with Gitpod
- Klick on https://gitpod.io/#https://github.com/hermannhueck/datastax-workshop-examples-scala
- Wait until the project development environment is loaded
- Klick on *import build* when prompted
- Upload your secure-connect-bundle (e.g. *secure-connect-killrvideocluster.zip*)
  into the project root to access your own Astra instance
- Continue with 3.

** Possible Problems:**
- Sometimes Metals is too fast detecting the sbt project. The other tools are not yet
downloaded and installed. In this case run again: *Metals: Import build* ]
- If the initial tool installation (automatic by *gitpod_init.sh*) fails,
  try to run *./gitpod_init.sh* again manually in the terminal window.

#### 2. Instructions for local development on your machine,
- Clone this repo
- Install sbt on your machine: https://www.scala-sbt.org/1.x/docs/Setup.html
- Install VSCode on your machine
- Open VSCode in the cloned project directory
- In VSCode install the *Metals* extension for Scala development (language server for Scala)
- Metals detects the the build file *build.sbt* and prompts you to import the build
- Klick on *import build* when prompted
- In a terminal window *cd* to the local project directory (the project root)
- Provide your secure-connect-bundle (e.g. *secure-connect-killrvideocluster.zip*)
  in the project root to access your own Astra instance
- Continue with 3.

#### 3. Instructions for both, local and cloud development
- Start the *sbt* command to get an interactive sbt shell (use *help* for more info)
- The following commands are to be entered behind the *sbt* prompt:
- Compile everything: *test:compile*
- Move to one of the sub projects, e.g.: *project week3-exercise02-scala*
- Run a single test using the *testOnly* command, e.g.: *testOnly com.datastax.workshop.Ex02_Connect_to_Cassandra*
