lazy val coreSettings = Seq(
  version := "2.4.2_1",
  scalaVersion := "2.11.7",
  crossScalaVersions := Seq(scalaVersion.value, "2.10.5"),
  organization := "com.sandinh"
)

lazy val otherSettings = Seq(
  scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-Yinline-warnings"/*, "-optimise"*/),

  //misc - to mute intellij warning when load sbt project
  dependencyOverrides ++= Set(
    "org.scala-lang.modules"  %% "scala-parser-combinators" % "1.0.4", // % Optional
    "org.scala-lang.modules"  %% "scala-xml" % "1.0.4", // % Optional
    "org.scala-lang" % "scala-reflect" % scalaVersion.value // % Optional
  )
)

lazy val module = project.in(file("module"))
  .settings(coreSettings ++ otherSettings: _*)
  .settings(
    name := "subfolder-evolutions",
    libraryDependencies += evolutions
  )

lazy val sample = project.in(file("sample"))
  .enablePlugins(PlayScala)
  .settings(coreSettings ++ otherSettings: _*)
  .settings(
    name := "sample-app",
    resolvers += Resolver.bintrayRepo("scalaz", "releases"),
    libraryDependencies += specs2 % Test
  ).dependsOn(module)

lazy val root = project.in(file("."))
  .settings(coreSettings: _*)
  .settings(
    publishArtifact := false
  ).aggregate(module, sample)
