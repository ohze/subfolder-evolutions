import PlayAxis._

lazy val module = projectMatrix
  .settings(name := "subfolder-evolutions")
  .customRow(
    scalaVersions = Seq(scala211, scala212),
    axisValues = Seq(play26, VirtualAxis.jvm),
    settings = Seq(
      moduleName := name.value + "_2_6",
      libraryDependencies += play26.evolutions,
    ),
  )
  .customRow(
    scalaVersions = Seq(scala211, scala212, scala213),
    axisValues = Seq(play27, VirtualAxis.jvm),
    settings = Seq(
      moduleName := name.value + "_2_7",
      libraryDependencies += play27.evolutions,
    ),
  )
  .customRow(
    scalaVersions = Seq(scala212, scala213),
    axisValues = Seq(play28, VirtualAxis.jvm),
    settings = Seq(
      moduleName := name.value,
      libraryDependencies += play28.evolutions,
    ),
  )

lazy val sample = projectMatrix
  .settings(skipPublish)
  .settings(name := "sample-app")
  .customRow(
    scalaVersions = Seq(scala211, scala212),
    axisValues = Seq(play26, VirtualAxis.jvm),
    settings = Seq(
      libraryDependencies += play26.specs2 % Test,
    ),
  )
  .customRow(
    scalaVersions = Seq(scala211, scala212, scala213),
    axisValues = Seq(play27, VirtualAxis.jvm),
    settings = Seq(
      libraryDependencies += play27.specs2 % Test,
    ),
  )
  .customRow(
    scalaVersions = Seq(scala212, scala213),
    axisValues = Seq(play28, VirtualAxis.jvm),
    settings = Seq(
      libraryDependencies += play28.specs2 % Test,
    ),
  )
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
