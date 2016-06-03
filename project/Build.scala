import sbt._
import Keys._

object BuildSettings {
  val paradiseVersion = "2.1.0-M5"
  val buildSettings = Defaults.defaultSettings ++ Seq(
    crossPaths := false,
    organization := "nl.cwi",
    version := "0.1.0",
    scalacOptions ++= Seq(),
    scalaVersion := "2.11.6",
    crossScalaVersions := Seq("2.10.2", "2.10.3", "2.10.4", "2.10.5", "2.11.0", "2.11.1", "2.11.2", "2.11.3", "2.11.4", "2.11.5", "2.11.6"),
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
  )
}

object MyBuild extends Build {
  import BuildSettings._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings ++ Seq(
      run <<= run in Compile in core
    )
  ) aggregate(macros, core)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies <++= (scalaVersion)(sv =>
    Seq("org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
    "junit" % "junit" % "4.11" % "test",
    "org.scala-lang" % "scala-reflect" % "2.11.6",
      "org.scala-lang" % "scala-compiler" % "2.11.6",
      "org.scala-lang" % "scala-library" % "2.11.6"
  )
),
      libraryDependencies ++= (
        if (scalaVersion.value.startsWith("2.10")) List("org.scalamacros" %% "quasiquotes" % paradiseVersion)
        else Nil
      )
    )
    
  )
  
  //testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")
  
  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings ++ Seq(
        libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test")
  ) dependsOn(macros)
}
