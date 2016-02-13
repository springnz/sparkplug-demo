// refer to project/Common.scala for shared settings and definitions
// refer to project/Dependencies.scala for dependency definitions
import Common._
import Dependencies._
import Release._
import xerial.sbt.Pack._

name := "sparkplug-demo"
scalaVersion := scalaVersionString
organization := organisationString

releaseVersionBump := sbtrelease.Version.Bump.Bugfix
releaseProcess := customReleaseProcess

// run the tests in series
concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)

def dep(project: Project) = project % "test->test;compile->compile"

lazy val demoPipelines = CreateProject("demo-pipelines", demoPipelineDependencies)

lazy val demoClient = CreateProject("demo-client", demoClientDependencies)
  .dependsOn(demoPipelines)
  .settings(fork := true) // required for OrientDB

lazy val main = project.in(file("."))
  .aggregate(demoPipelines, demoClient)
  .settings(Defaults.coreDefaultSettings ++ Seq(
    publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo"))),
    publishArtifact := false
  ))
  .settings(packAutoSettings)
  .settings(packGenerateWindowsBatFile := false)


