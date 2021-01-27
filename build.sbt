name := "anorm221"

organization := "org.broadinstitute.gpp"

version := "2.2.1.6"

scalaVersion := "2.13.4"

crossScalaVersions := Seq("2.13.4")

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

// Configure publishing to GitHub Packages:
githubTokenSource := TokenSource.Environment("GITHUB_TOKEN") ||
                     TokenSource.GitConfig("github.token")
githubOwner := "broadinstitute"
githubRepository := "anorm221"

// Disable parallel execution of tests (can cause problems with integration tests)
parallelExecution in Test := false
