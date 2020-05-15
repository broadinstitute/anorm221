name := "anorm221"

organization := "org.broadinstitute.gpp"

version := "2.2.1.5"

scalaVersion := "2.13.2"

crossScalaVersions := Seq("2.13.2")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

//--------------------------------------------------------------------------------------------------
// The libraries that we depend on:
//--------------------------------------------------------------------------------------------------

libraryDependencies ++= Seq()
libraryDependencies += scalaVersion("org.scala-lang" % "scala-compiler" % _).value
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

//--------------------------------------------------------------------------------------------------
// Tell sbt that we want to see stack traces automatically
//--------------------------------------------------------------------------------------------------

traceLevel in run := 0

//--------------------------------------------------------------------------------------------------
// Don't publish a logback.xml
//--------------------------------------------------------------------------------------------------

mappings in (Compile,packageBin) ~= { (ms: Seq[(File, String)]) =>
  ms filter { case (file, _) =>
    file.getName != "logback.xml"
  }
}

// Configure publishing to the nexus repository
publishTo in ThisBuild := {
  val nexus = "http://gppops1.broadinstitute.org:8081/repository/"
  if (isSnapshot.value)
    Some(("snapshots" at nexus + "maven-snapshots").withAllowInsecureProtocol(true))
  else
    Some(("releases" at nexus + "maven-releases").withAllowInsecureProtocol(true))
}

// Look up Nexus credentials from the user's .ivy2 directory
// For details on the contents of this file, see http://goo.gl/aYtqJ
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Disable parallel execution of tests (can cause problems with integration tests)
parallelExecution in Test := false
