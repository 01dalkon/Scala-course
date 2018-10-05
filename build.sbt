name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

libraryDependencies += guice
libraryDependencies += ehcache
libraryDependencies += ws

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.197"

//SE DEBE TENER EN CUENTA LA VERSIÓN DE LA BASE DE DATOS MYSQL  SELECT VERSION();
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
)

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.16"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "2.21.0" % Test





