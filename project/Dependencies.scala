import sbt._

object Dependencies {

  lazy val catsEffectVersion         = "2.1.4"
  lazy val munitVersion              = "0.7.9"
  lazy val scalaCheckVersion         = "1.14.3"
  lazy val phantomVersion            = "2.59.0" // Phantom Cassandra driver
  lazy val datastaxJavaDriverVersion = "4.8.0"  // Datastax java-driver for Cassandra
  lazy val junitJupiterVersion       = "5.6.2"
  lazy val junitPlatformVersion      = "1.6.2"
  lazy val logbackVersion            = "1.2.3"

  lazy val catsEffect              = "org.typelevel"     %% "cats-effect"                  % catsEffectVersion
  lazy val munit                   = "org.scalameta"     %% "munit"                        % munitVersion
  lazy val scalaCheck              = "org.scalacheck"    %% "scalacheck"                   % scalaCheckVersion
  lazy val phantomConnectors       = "com.outworkers"    %% "phantom-connectors"           % phantomVersion
  lazy val phantomDsl              = "com.outworkers"    %% "phantom-dsl"                  % phantomVersion
  lazy val cassandraUnit           = "org.cassandraunit"  % "cassandra-unit"               % "4.3.1.0"
  lazy val datastaxCore            = "com.datastax.oss"   % "java-driver-core"             % datastaxJavaDriverVersion
  lazy val datastaxQueryBuilder    = "com.datastax.oss"   % "java-driver-query-builder"    % datastaxJavaDriverVersion
  lazy val datastaxMapperRuntime   = "com.datastax.oss"   % "java-driver-mapper-runtime"   % datastaxJavaDriverVersion
  lazy val datastaxMapperProcessor = "com.datastax.oss"   % "java-driver-mapper-processor" % datastaxJavaDriverVersion
  lazy val junitInterface          = "com.novocode"       % "junit-interface"              % "0.11"
  lazy val junitPlatformRunner     = "org.junit.platform" % "junit-platform-runner"        % junitPlatformVersion
  lazy val junitJupiterApi         = "org.junit.jupiter"  % "junit-jupiter-api"            % junitJupiterVersion
  lazy val junitJupiterEngine      = "org.junit.jupiter"  % "junit-jupiter-engine"         % junitJupiterVersion
  lazy val logback                 = "ch.qos.logback"     % "logback-classic"              % logbackVersion

  lazy val kindProjectorVersion    = "0.11.0"
  lazy val betterMonadicForVersion = "0.3.1"

  // https://github.com/typelevel/kind-projector
  lazy val kindProjectorPlugin    = compilerPlugin(
    compilerPlugin("org.typelevel" % "kind-projector" % kindProjectorVersion cross CrossVersion.full)
  )
  // https://github.com/oleg-py/better-monadic-for
  lazy val betterMonadicForPlugin = compilerPlugin(
    compilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForVersion)
  )

  def scalaCompiler(scalaVersion: String) = "org.scala-lang" % "scala-compiler" % scalaVersion
  def scalaReflect(scalaVersion: String)  = "org.scala-lang" % "scala-reflect"  % scalaVersion

  def datastaxJavaDriverDependencies =
    Seq(
      datastaxCore,
      datastaxQueryBuilder,
      datastaxMapperRuntime,
      datastaxMapperProcessor,
      logback
    )

  def phantomDependencies =
    Seq(
      phantomDsl,
      logback
    )

  def junitTestDependencies                   =
    Seq(
      junitInterface,
      junitPlatformRunner,
      junitJupiterApi,
      junitJupiterEngine
    ).map(_ % Test)

  def munitTestDependencies                   =
    Seq(
      munit
    ).map(_ % Test)

  def hutilDependencies(scalaVersion: String) =
    Seq(
      scalaCompiler(scalaVersion),
      scalaReflect(scalaVersion),
      catsEffect
    )
}
