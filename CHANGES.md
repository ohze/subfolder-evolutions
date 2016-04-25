### Changelogs

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
