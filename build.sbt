lazy val commonSettings = Seq(
  version := "2.4.6",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.11.8", "2.10.6"),
  organization := "com.sandinh",
  scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-Yinline-warnings"/*, "-optimise"*/)
)

lazy val module = project
  .settings(commonSettings: _*)
  .settings(
    name := "subfolder-evolutions",
    libraryDependencies += evolutions
  )

lazy val sample = project
  .settings(commonSettings: _*)
  .settings(
    name := "sample-app",
    publishArtifact := false,
    resolvers += Resolver.bintrayRepo("scalaz", "releases"),
    libraryDependencies += specs2 % Test
  ).dependsOn(module)

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    publishArtifact := false
  ).aggregate(module, sample)
