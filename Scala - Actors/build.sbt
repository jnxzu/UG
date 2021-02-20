name := "actors"

version := "1.0.0"

scalaVersion := "2.13.4"

libraryDependencies ++= {
  val akkaVersion = "2.6.10"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
  )
}
