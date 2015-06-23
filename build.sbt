name := "implicit-propagation"

version := "0.1"

crossPaths := false

organization := "nl.cwi"

scalaVersion := "2.11.6"

libraryDependencies <++= (scalaVersion)(sv =>
    Seq("org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
		"junit" % "junit" % "4.11",
		"org.scala-lang" % "scala-reflect" % "2.11.6",
    	"org.scala-lang" % "scala-compiler" % "2.11.6",
    	"org.scala-lang" % "scala-library" % "2.11.6"
	)
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)
