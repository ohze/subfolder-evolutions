subfolder-evolutions
====================
[![CI](https://github.com/ohze/subfolder-evolutions/actions/workflows/sbt-devops.yml/badge.svg)](https://github.com/ohze/subfolder-evolutions/actions/workflows/sbt-devops.yml)

[playframework](https://playframework.com) 2.6+ [DB evolutions](https://playframework.com/documentation/2.6.x/Evolutions)
that can manage sql script from multiple dependency libraries

### Usage
0. Install
```scala
// for play 2.8.x
libraryDependencies += "com.sandinh" %% "subfolder-evolutions" % "2.8.1"
// for play 2.7.x
libraryDependencies += "com.sandinh" %% "subfolder-evolutions_2.7" % "2.8.1"
// for play 2.8.x
libraryDependencies += "com.sandinh" %% "subfolder-evolutions_2.6" % "2.8.1"
```

1. Each library should place evolutions scripts in folder `evolutions/<db-name>/<subfolder-name>`
instead of place directly in `evolutions/<db-name>` as in the plain [evolutions](https://playframework.com/documentation/2.6.x/Evolutions).

  Example: A library `"sd" %% "pay" % "2.0.0"` has scripts `evolutions/default/pay/{1.sql, 2.sql}`

  The library should have the following config in `reference.conf` file:
`evolutions.default.folders += pay`

2. In the main play application :
  + add sbt libraryDependencies:
  ```scala
    name := "bank"
    libraryDependencies ++= Seq(
      "com.sandinh" %% "subfolder-evolutions" % "2.8.1",
      "sd" %% "pay" % "2.0.0"
    )
  ```

  + (similar,) place evolutions scripts in `conf/evolutions/default/bank/{1.sql, 2.sql, 3.sql}`

  + and add to `conf/application.conf`:
   ```hocon
   evolutions.default.folders += bank
   play.modules.disabled += "play.api.db.evolutions.EvolutionsModule"
   ```

3. `subfolder-evolutions` will magically do its job :D

#### publish guide
+ We use [sbt-devops](https://github.com/ohze/sbt-devops), so push tag `vM.N.P`  
  => auto build, test, publish version `M.N.P`
+ should add unit test
+ MUST update [CHANGES.md]!
+ MUST NOT publish manually

### Changelogs
see [CHANGES.md](CHANGES.md)

### Licence
This software is licensed under the [Apache 2 license](http://www.apache.org/licenses/LICENSE-2.0)

Copyright (C) 2011-2021 Sân Đình (https://sandinh.com)
