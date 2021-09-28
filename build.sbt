def play(id: String) = "com.typesafe.play" %% id % "2.8.0"
val evolutions = play("play-jdbc-evolutions")
val specs2 = play("play-specs2")

lazy val commonSettings = Seq(
  scalaVersion := scala213,
  crossScalaVersions := Seq(scala212, scala213),
)

lazy val module = project
  .settings(commonSettings)
  .settings(
    name := "subfolder-evolutions",
    libraryDependencies += evolutions
  )

lazy val sample = project
  .settings(commonSettings)
  .settings(
    name := "sample-app",
    publishArtifact := false,
    libraryDependencies += specs2 % Test
  ).dependsOn(module)

lazy val `subfolder-evolutions-root` = project.in(file("."))
  .settings(commonSettings)
  .settings(
    publishArtifact := false
  ).aggregate(module, sample)

inThisBuild(
  Seq(
    versionScheme := Some("semver-spec"),
    developers := List(
      Developer(
        "thanhbv",
        "Bui Viet Thanh",
        "thanhbv@sandinh.net",
        url("https://sandinh.com")
      )
    )
  )
)
