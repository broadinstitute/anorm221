name := "anorm221"

val scala213 = "2.13.12"
val scala3 = "3.3.1"

ThisBuild / scalaVersion := scala213
ThisBuild / organization := "org.broadinstitute.gpp"
ThisBuild / versionScheme := Some("early-semver")

version := "2.2.1.8"

scalaVersion := scala213
crossScalaVersions := Seq(scala213, scala3)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
scalacOptions ++=
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq("-source:3.0-migration", "-explain")
      case _ => Seq.empty
    })
//--------------------------------------------------------------------------------------------------
// The libraries that we depend on:
//--------------------------------------------------------------------------------------------------

libraryDependencies +=
  (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"
      case _ =>"org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
  })

// Configure publishing to GitHub Packages:
ThisBuild / githubOwner := "broadinstitute"
githubRepository := "anorm221"

// Disable parallel execution of tests (can cause problems with integration tests)
Test / parallelExecution := false
