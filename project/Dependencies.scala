import sbt._

object Dependencies {
  val sparkPlugVersion = "0.2.7-SNAPSHOT"
  val sparkVersion = "1.5.1"
  val akkaVersion = "2.3.12"


  // Spark
  val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion % Provided

  // Other libraries
  val scalaz = "org.scalaz" %% "scalaz-core" % "7.1.3"
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  val betterFiles = "com.github.pathikrit" %% "better-files" % "2.14.0"

  // logging
  val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.10"
  val logBackClassic = "ch.qos.logback" % "logback-classic" % "1.1.3"
  val logBackCore = "ch.qos.logback" % "logback-core" % "1.1.3"
  val logBackDependencies = Seq(logBackClassic, logBackCore)

  val sparkPlugCore = "springnz" %% "sparkplug-core" % sparkPlugVersion changing ()
  val sparkPlugExamples = "springnz" %% "sparkplug-examples" % sparkPlugVersion changing ()
  val sparkPlugExecutor = "springnz" %% "sparkplug-executor" % sparkPlugVersion changing ()
  val sparkPlugExtras = "springnz" %% "sparkplug-extras" % sparkPlugVersion changing ()
  val sparkPlugLauncher = "springnz" %% "sparkplug-launcher" % sparkPlugVersion changing ()

  // Share test
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % Test
  val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.12.4" % Test

  val sparkCoreDependencies = Seq(scalaz, sparkCore)
  val sparkPlugDependencies = Seq(sparkPlugCore, sparkPlugExtras)

  val sharedCompileDependencies = Seq(slf4jApi, betterFiles)
  val sharedTestDependencies = Seq(scalaTest, scalaCheck) ++ logBackDependencies
  val sharedDependencies = sharedCompileDependencies ++ sharedTestDependencies

  val sparkCoreLibDependencies = Seq(scalaLogging) ++ sparkCoreDependencies

  val sparkExtraLibDependencies = sparkCoreLibDependencies ++
    sparkPlugDependencies ++
    sharedDependencies

  val demoPipelineDependencies = sparkExtraLibDependencies ++ sharedDependencies

  val demoClientDependencies = Seq(sparkPlugLauncher, sparkPlugExecutor, sparkPlugExamples) ++ sharedDependencies

  // Dependency overrides
  val nettyOverride = "io.netty" % "netty" % "3.8.0.Final"
  val jacksonOverride = "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4"
  val dependencyOverridesSet = Set(nettyOverride, jacksonOverride)

}
