package play.api.db.evolutions

import play.api.{Configuration, Environment}
import play.api.inject.Module

class SubFolderEvolutionsModule extends Module {
  def bindings(environment: Environment, configuration: Configuration) = {
    Seq(
      bind[EvolutionsConfig].toProvider[DefaultEvolutionsConfigParser],
      bind[EvolutionsReader].to[SubFolderEvolutionsReader],
      bind[EvolutionsApi].to[DefaultEvolutionsApi],
      bind[ApplicationEvolutions]
        .toProvider[ApplicationEvolutionsProvider]
        .eagerly()
    )
  }
}
