name := "used-kittens"

version := "1.0"

val gitHeadCommit = taskKey[String]("Determines the current git commit SHA")

val makeVersionProperties = taskKey[Seq[File]]("Make a version.properties file.")

gitHeadCommit := Process("git rev-parse HEAD").lines.head

makeVersionProperties := {
	val propFile = (resourceManaged in Compile).value / "version.properties"
	val content = s"version=${gitHeadCommit.value}"
	IO.write(propFile, content)
	Seq(propFile)
}

libraryDependencies ++= Seq(
	"junit" % "junit" % "4.11" % "test",
	"org.specs2" %% "specs2" % "1.14" % "test"
)