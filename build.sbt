def play(id: String) = "com.typesafe.play" %% id % "2.8.0"
val evolutions = play("play-jdbc-evolutions")
val specs2 = play("play-specs2")

lazy val commonSettings = Seq(
  organization := "com.sandinh",
  version := "2.8.0",
  scalaVersion := "2.13.1",
  crossScalaVersions := Seq("2.12.10", "2.13.1"),
  scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-feature"),
  scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 12)) => Seq("-target:jvm-1.8")
    case _ => Nil
  })
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

lazy val `subfolder-evolutions-root` = project.in(file("."))
  .settings(commonSettings: _*)
  .settings(
    publishArtifact := false
  ).aggregate(module, sample)
