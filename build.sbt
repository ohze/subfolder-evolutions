lazy val module = projectMatrix
  .settings(
    name := "subfolder-evolutions",
    libraryDependencies ++= play("jdbc-evolutions").value,
  )
  .playAxis(play26, Seq(scala211, scala212))
  .playAxis(play27, Seq(scala211, scala212, scala213))
  .playAxis(play28, Seq(scala212, scala213))

lazy val sample = projectMatrix
  .settings(
    skipPublish,
    name := "sample-app",
    libraryDependencies ++= play("specs2" -> Test).value
  )
  .playAxis(play26, Seq(scala211, scala212))
  .playAxis(play27, Seq(scala211, scala212, scala213))
  .playAxis(play28, Seq(scala212, scala213))
  .dependsOn(module)

lazy val `subfolder-evolutions-root` = project
  .in(file("."))
  .settings(skipPublish)
  .aggregate(module.projectRefs ++ sample.projectRefs: _*)

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
