lazy val commonSettings = Seq(
  version := "2.5.2",
  scalaVersion := "2.11.8",
  organization := "com.sandinh",
  scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-feature", "-target:jvm-1.8", "-Ybackend:GenBCode")
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
    libraryDependencies += specs2 % Test
  ).dependsOn(module)

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    publishArtifact := false
  ).aggregate(module, sample)
