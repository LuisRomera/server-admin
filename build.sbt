name := "server-admin"

version := "0.1"

scalaVersion := "2.12.8"

val typesafeVersion = "1.4.1"

val akkaVersion = "2.6.17"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)