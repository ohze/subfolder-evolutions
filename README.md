subfolder-evolutions
====================
[![Build Status](https://travis-ci.org/giabao/subfolder-evolutions.svg)](https://travis-ci.org/giabao/subfolder-evolutions)

[playframework](https://playframework.com) 2.4+ [DB evolutions](https://playframework.com/documentation/2.4.x/Evolutions)
that can manage sql script from multiple dependency libraries

### Usage
1. Each library should place evolutions scripts in folder evolutions/<db-name>/<subfolder-name>
instead of place directly in evolutions/<db-name> as in the plain [evolutions](https://playframework.com/documentation/2.4.x/Evolutions).

  Example: A library `"sd" %% "pay" % "1.0.0"` has scripts `evolutions/default/pay/{1.sql, 2.sql}`

  The library should have the following config in `reference.conf` file:
`evolutions.default.folders += pay`

2. In the main play application :
  + add sbt libraryDependencies:
  ```
    name := "bank"
    libraryDependencies ++= Seq(
      "com.sandinh" %% "subfolder-evolutions" % "2.4.2_1",
      "sd" %% "pay" % "1.0.0"
    )
  ```

  + (similar,) place evolutions scripts in `conf/evolutions/default/bank/{1.sql, 2.sql, 3.sql}`

  + and add to `conf/application.conf`:
   ```
   evolutions.default.folders += bank
   play.modules.disabled += "play.api.db.evolutions.EvolutionsModule"
   ```

3. `subfolder-evolutions` will magically do its job :D

### Changelogs
see [CHANGES.md](CHANGES.md)

### Licence
This software is licensed under the Apache 2 license:
http://www.apache.org/licenses/LICENSE-2.0

Copyright (C) 2011-2015 Sân Đình (http://sandinh.com)
