name := "anorm221"

organization := "org.broadinstitute.gpp"

version := "2.2.1.2"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

//--------------------------------------------------------------------------------------------------
// The libraries that we depend on:
//--------------------------------------------------------------------------------------------------

libraryDependencies ++= Seq()
libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)

//--------------------------------------------------------------------------------------------------
// Tell sbt that we want to see stack traces automatically
//--------------------------------------------------------------------------------------------------

traceLevel in run := 0

//--------------------------------------------------------------------------------------------------
// Don't publish a logback.xml
//--------------------------------------------------------------------------------------------------

mappings in (Compile,packageBin) ~= { (ms: Seq[(File, String)]) =>
  ms filter { case (file, toPath) =>
    file.getName != "logback.xml"
  }
}

// Configure publishing to the nexus repository
publishTo in ThisBuild := {
  val nexus = "http://gppops1.broadinstitute.org:8081/repository/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "maven-snapshots")
  else
    Some("releases" at nexus + "maven-releases")
}

// Look up Nexus credentials from the user's .ivy2 directory
// For details on the contents of this file, see http://goo.gl/aYtqJ
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Disable parallel execution of tests (can cause problems with integration tests)
parallelExecution in Test := false
