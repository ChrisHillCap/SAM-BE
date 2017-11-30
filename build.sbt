name := """SAM-BE"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
val reactiveMongoVersion = "0.12.7-play26"


scalaVersion := "2.12.3"

routesGenerator := InjectedRoutesGenerator

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVersion

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/app.Routes
// play.sbt.app.Routes.RoutesKeys.routesImport += "com.example.binders._"
