name := "Akka Toy"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.2.1"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.2.1" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % "test"
