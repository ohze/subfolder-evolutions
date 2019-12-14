### Changelogs

##### 2.8.0
+ update sbt 1.3.4, sbt-sonatype 3.8.1, sbt-pgp 2.0.0
+ update play 2.6.7 -> 2.8.0
+ support scala 2.13 & drop support for scala 2.11 (play 2.8 drop support for scala 2.11)

##### 2.6.7
+ update play 2.6.7
+ cross compile for scala 2.12.4, 2.11.12
+ update sbt 1.0.3, sbt-coursier 1.0.0-RC13

##### 2.6.6
+ update play 2.6.6, scala 2.12.4
+ use sbt 1.0.2, sbt-coursier 1.0.0-RC12

##### 2.6.3
+ update play 2.6.3
+ cross compile for scala 2.11.11, 2.12.3
+ update sbt 0.13.16, sbt-sonatype 2.0, sbt-pgp 1.1.0. Use sbt-coursier
+ move source code to github.com/ohze/

##### 2.5.2
+ update play 2.5.2
+ drop support for scala 2.10.x

##### 2.4.6
+ update scala 2.11.8, sbt 0.13.11, play 2.4.6 & simplify build.sbt

##### 2.4.3
+ update play 2.4.3

##### 2.4.2_1
+ Workaround for issue #1 by adding to `application.conf`:

  ```
  play.modules.disabled += "play.api.db.evolutions.EvolutionsModule"
  ```

##### 2.4.2
initial release
