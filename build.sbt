name := "anorm221"

organization := "org.broadinstitute.gpp"

version := "2.2.1"

scalaVersion := "2.10.4"

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

// Configure publishing to the RNAi Nexus repository
publishTo <<= version { (v: String) =>
  val nexus = "http://rnaivm3.broadinstitute.org:10080/nexus/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots/")
  else
    Some("releases" at nexus + "content/repositories/releases")
}

// Look up Nexus credentials from the user's .ivy2 directory
// For details on the contents of this file, see http://goo.gl/aYtqJ
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Disable parallel execution of tests (can cause problems with integration tests)
parallelExecution in Test := false
