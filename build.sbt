lazy val coreSettings = Seq(
  version := "2.4.2-SNAPSHOT",
  scalaVersion := "2.11.7",
  crossScalaVersions := Seq(scalaVersion.value, "2.10.5"),

  name := "subfolder-evolutions",
  organization := "com.sandinh"
)


lazy val root = project.in(file("."))
  .settings(coreSettings: _*)
  .settings(
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-Yinline-warnings"/*, "-optimise"*/),

    resolvers += Resolver.bintrayRepo("scalaz", "releases"),

    libraryDependencies ++= Seq(
      evolutions
    ),

    //misc - to mute intellij warning when load sbt project
    dependencyOverrides ++= Set(
      "org.scala-lang.modules"  %% "scala-parser-combinators" % "1.0.4", // % Optional
      "org.scala-lang.modules"  %% "scala-xml" % "1.0.4", // % Optional
      "org.scala-lang" % "scala-reflect" % scalaVersion.value // % Optional
    )
  )
