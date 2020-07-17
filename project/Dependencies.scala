import sbt._

object Dependencies {

  lazy val collectionCompatVersion   = "2.1.6"
  lazy val scalaAsyncVersion         = "0.10.0"
  lazy val shapelessVersion          = "2.3.3"
  lazy val catsEffectVersion         = "2.1.4"
  lazy val fs2Version                = "2.4.2"
  lazy val monixVersion              = "3.2.2"
  lazy val circeVersion              = "0.13.0"
  lazy val http4sVersion             = "0.21.6"
  lazy val doobieVersion             = "0.9.0"
  lazy val quillVersion              = "3.5.2"
  lazy val zioVersion                = "1.0.0-RC21-2"
  lazy val pprintVersion             = "0.5.9"
  lazy val refinedVersion            = "0.9.14"
  lazy val singletonOpsVersion       = "0.5.1"
  lazy val chimneyVersion            = "0.5.2"
  lazy val monocleVersion            = "2.0.5"
  lazy val quicklensVersion          = "1.6.0"
  lazy val newtypeVersion            = "0.4.4"
  lazy val munitVersion              = "0.7.9"
  lazy val scalaCheckVersion         = "1.14.3"
  lazy val phantomVersion            = "2.59.0" // Phantom Cassandra driver
  lazy val datastaxJavaDriverVersion = "4.7.2"  // Datastax java-driver for Cassandra
  lazy val junitJupiterVersion       = "5.6.2"
  lazy val junitPlatformVersion      = "1.6.2"
  lazy val logbackVersion            = "1.2.3"

  lazy val collectionCompat        = "org.scala-lang.modules"     %% "scala-collection-compat"      % collectionCompatVersion
  lazy val scalaAsync              = "org.scala-lang.modules"     %% "scala-async"                  % scalaAsyncVersion
  lazy val shapeless               = "com.chuusai"                %% "shapeless"                    % shapelessVersion
  lazy val catsEffect              = "org.typelevel"              %% "cats-effect"                  % catsEffectVersion
  lazy val fs2Core                 = "co.fs2"                     %% "fs2-core"                     % fs2Version
  lazy val fs2Io                   = "co.fs2"                     %% "fs2-io"                       % fs2Version
  lazy val fs2ReactiveStreams      = "co.fs2"                     %% "fs2-reactive-streams"         % fs2Version
  lazy val monixExecution          = "io.monix"                   %% "monix-execution"              % monixVersion
  lazy val monixEval               = "io.monix"                   %% "monix-eval"                   % monixVersion
  lazy val circeCore               = "io.circe"                   %% "circe-core"                   % circeVersion
  lazy val circeParser             = "io.circe"                   %% "circe-parser"                 % circeVersion
  lazy val circeGeneric            = "io.circe"                   %% "circe-generic"                % circeVersion
  lazy val circeGenericExtras      = "io.circe"                   %% "circe-generic-extras"         % circeVersion
  lazy val circeShapes             = "io.circe"                   %% "circe-shapes"                 % circeVersion
  lazy val circeOptics             = "io.circe"                   %% "circe-optics"                 % circeVersion
  lazy val circeJawn               = "io.circe"                   %% "circe-jawn"                   % circeVersion
  lazy val http4sCore              = "org.http4s"                 %% "http4s-core"                  % http4sVersion
  lazy val http4sDsl               = "org.http4s"                 %% "http4s-dsl"                   % http4sVersion
  lazy val http4sClient            = "org.http4s"                 %% "http4s-client"                % http4sVersion
  lazy val http4sServer            = "org.http4s"                 %% "http4s-server"                % http4sVersion
  lazy val http4sCirce             = "org.http4s"                 %% "http4s-circe"                 % http4sVersion
  lazy val http4sTesting           = "org.http4s"                 %% "http4s-testing"               % http4sVersion
  lazy val http4sBlazeServer       = "org.http4s"                 %% "http4s-blaze-client"          % http4sVersion
  lazy val http4sBlazeClient       = "org.http4s"                 %% "https-blaze-server"           % http4sVersion
  lazy val doobieCore              = "org.tpolecat"               %% "doobie-core"                  % doobieVersion
  lazy val doobiePostgres          = "org.tpolecat"               %% "doobie-postgres"              % doobieVersion
  lazy val doobieH2                = "org.tpolecat"               %% "doobie-h2"                    % doobieVersion
  lazy val doobieHikari            = "org.tpolecat"               %% "doobie-hikari"                % doobieVersion
  lazy val doobieFree              = "org.tpolecat"               %% "doobie-free"                  % doobieVersion
  lazy val doobieQuill             = "org.tpolecat"               %% "doobie-quill"                 % doobieVersion
  lazy val doobieSpecs2            = "org.tpolecat"               %% "doobie-specs2"                % doobieVersion
  lazy val doobieScalatest         = "org.tpolecat"               %% "doobie-scalatest"             % doobieVersion
  lazy val quillCore               = "io.getquill"                %% "quill-core"                   % quillVersion
  lazy val quillSql                = "io.getquill"                %% "quill-sql"                    % quillVersion
  lazy val quillJdbc               = "io.getquill"                %% "quill-jdbc"                   % quillVersion
  lazy val zio                     = "dev.zio"                    %% "zio"                          % zioVersion
  lazy val pprint                  = "com.lihaoyi"                %% "pprint"                       % pprintVersion
  lazy val refined                 = "eu.timepit"                 %% "refined"                      % refinedVersion
  lazy val singletonOps            = "eu.timepit"                 %% "singleton-ops"                % singletonOpsVersion
  lazy val chimney                 = "io.scalaland"               %% "chimney"                      % chimneyVersion
  lazy val monocleCore             = "com.github.julien-truffaut" %% "monocle-core"                 % monocleVersion
  lazy val monocleMacro            = "com.github.julien-truffaut" %% "monocle-macro"                % monocleVersion
  lazy val monocleLaw              = "com.github.julien-truffaut" %% "monocle-law"                  % monocleVersion
  lazy val monocleUnsafe           = "com.github.julien-truffaut" %% "monocle-unsafe"               % monocleVersion
  lazy val quickLens               = "com.softwaremill.quicklens" %% "quicklens"                    % quicklensVersion
  lazy val newtype                 = "io.estatico"                %% "newtype"                      % newtypeVersion
  lazy val munit                   = "org.scalameta"              %% "munit"                        % munitVersion
  lazy val scalaCheck              = "org.scalacheck"             %% "scalacheck"                   % scalaCheckVersion
  lazy val phantomConnectors       = "com.outworkers"             %% "phantom-connectors"           % phantomVersion
  lazy val phantomDsl              = "com.outworkers"             %% "phantom-dsl"                  % phantomVersion
  lazy val cassandraUnit           = "org.cassandraunit"           % "cassandra-unit"               % "4.3.1.0"
  lazy val metricsCore             = "io.dropwizard.metrics"       % "metrics-core"                 % "3.2.6"
  // lazy val metricsJmx         = "io.dropwizard.metrics"       % "metrics-jmx"             % "4.1.10.1"
  lazy val datastaxCore            = "com.datastax.oss"            % "java-driver-core"             % datastaxJavaDriverVersion
  lazy val datastaxQueryBuilder    = "com.datastax.oss"            % "java-driver-query-builder"    % datastaxJavaDriverVersion
  lazy val datastaxMapperRuntime   = "com.datastax.oss"            % "java-driver-mapper-runtime"   % datastaxJavaDriverVersion
  lazy val datastaxMapperProcessor = "com.datastax.oss"            % "java-driver-mapper-processor" % datastaxJavaDriverVersion
  lazy val junitInterface          = "com.novocode"                % "junit-interface"              % "0.11"
  lazy val junitPlatformRunner     = "org.junit.platform"          % "junit-platform-runner"        % junitPlatformVersion
  lazy val junitJupiterApi         = "org.junit.jupiter"           % "junit-jupiter-api"            % junitJupiterVersion
  lazy val junitJupiterEngine      = "org.junit.jupiter"           % "junit-jupiter-engine"         % junitJupiterVersion
  lazy val logback                 = "ch.qos.logback"              % "logback-classic"              % logbackVersion

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

  def coreDependencies(scalaVersion: String)               =
    Seq(
      // scalaCompiler(scalaVersion),
      // scalaReflect(scalaVersion),
      // scalaAsync,
      // shapeless,
      // monixEval,
      // fs2Core,
      // circeCore,
      // circeJawn,
      // http4sCore,
      // doobieCore,
      // doobiePostgres,
      // doobieH2,
      // doobieHikari,
      // doobieQuill,
      // doobieSpecs2,
      // doobieScalatest,
      // quillCore,
      // zio,
      // pprint,
      // refined,
      // singletonOps,
      // chimney,
      // monocleCore,
      // monocleMacro,
      // quickLens,
      // newtype,
      munit,
      phantomDsl,
      cassandraUnit,
      datastaxCore,
      // metricsCore,
      kindProjectorPlugin,
      betterMonadicForPlugin
    ) ++ Seq(
      scalaCheck
    ).map(_ % Test)

  def datastaxJavaDriverDependencies(scalaVersion: String) =
    Seq(
      datastaxCore,
      datastaxQueryBuilder,
      datastaxMapperRuntime,
      datastaxMapperProcessor,
      logback
    ) ++ Seq(
      junitInterface,
      junitPlatformRunner,
      junitJupiterApi,
      junitJupiterEngine
    ).map(_ % Test)

  def hutilDependencies(scalaVersion: String)              =
    Seq(
      scalaCompiler(scalaVersion),
      scalaReflect(scalaVersion),
      catsEffect
    )
}
