val projectName        = "datastax-workshop-examples-scala"
val projectDescription = "Datastax Workshop Examples implemented in Scala"
val projectVersion     = "0.1.0"

val scala213 = "2.13.3"

lazy val commonSettings =
  Seq(
    version := projectVersion,
    scalaVersion := scala213,
    publish / skip := true,
    scalacOptions ++= ScalacOptions.defaultScalacOptions,
    Compile / console / scalacOptions := ScalacOptions.consoleScalacOptions,
    Test / console / scalacOptions := ScalacOptions.consoleScalacOptions,
    semanticdbEnabled := true,
    semanticdbVersion := "4.3.16",                                                // scalafixSemanticdb.revision,
    scalafixDependencies ++= Seq("com.github.liancheng" %% "organize-imports" % "0.3.1-RC3"),
    Test / parallelExecution := false,
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "100"), // -s = -minSuccessfulTests
    testFrameworks += new TestFramework("munit.Framework"),
    initialCommands :=
      s"""|
          |import scala.util.chaining._
          |println
          |""".stripMargin
  )

import Dependencies._

lazy val root = (project in file("."))
  .aggregate(
    `week3-exercises01-java`,
    `week3-exercises02-scala`,
    `week3-exercises03-munit-tests`,
    `week3-exercises04-phantom`
  )
  .settings(commonSettings)
  .settings(
    name := "root",
    description := "root project",
    sourceDirectories := Seq.empty
  )

lazy val `week3-exercises01-java` = (project in file("week3-exercises01-java"))
  .dependsOn(hutil)
  .settings(commonSettings)
  .settings(
    name := "week3-exercises01-java",
    description := "Workshop Examples (week3) implemented in Java using the datastax/java-driver for Cassandra",
    libraryDependencies ++= datastaxJavaDriverDependencies ++ junitTestDependencies
  )

lazy val `week3-exercises02-scala` = (project in file("week3-exercises02-scala"))
  .dependsOn(hutil)
  .settings(commonSettings)
  .settings(
    name := "week3-exercises02-scala",
    description := "Workshop Examples (week3) implemented in Scala using the datastax/java-driver for Cassandra",
    libraryDependencies ++= datastaxJavaDriverDependencies ++ junitTestDependencies
  )

lazy val `week3-exercises03-munit-tests` = (project in file("week3-exercises03-munit-tests"))
  .dependsOn(hutil)
  .settings(commonSettings)
  .settings(
    name := "week3-exercises03-munit-tests",
    description := "Workshop Examples (week3) implemented in Scala using the datastax/java-driver for Cassandra and MUnit for tests",
    libraryDependencies ++= datastaxJavaDriverDependencies ++ munitTestDependencies
  )

lazy val `week3-exercises04-phantom` = (project in file("week3-exercises04-phantom"))
  .dependsOn(hutil)
  .settings(commonSettings)
  .settings(
    name := "week3-exercises04-phantom",
    description := "Workshop Examples (week3) implemented in Scala using the datastax/java-driver for Cassandra and MUnit for tests",
    libraryDependencies ++= phantomDependencies ++ munitTestDependencies,
    scalacOptions -= "-Werror"
  )

lazy val hutil = (project in file("hutil"))
  .settings(commonSettings)
  .settings(
    name := "hutil",
    description := "Hermann's Utilities",
    libraryDependencies ++= Dependencies.hutilDependencies(scalaVersion.value)
  )

// GraphBuddy support
// resolvers += Resolver.bintrayRepo("virtuslab", "graphbuddy")
// addCompilerPlugin("com.virtuslab.semanticgraphs" % "scalac-plugin" % "0.0.10" cross CrossVersion.full)
// scalacOptions += "-Yrangepos"
