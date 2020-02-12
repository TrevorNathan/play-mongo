name := """play-mongo"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"
val reactiveMongoVer = "0.18.7-play27"

libraryDependencies += guice
libraryDependencies +=
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

//This dependency does not import:
//libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.12.4"
libraryDependencies +=
  "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVer

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

import play.sbt.routes.RoutesKeys

RoutesKeys.routesImport +=
  "play.modules.reactivemongo.PathBindables._"