import org.specs2.mutable.Specification
import play.api.{Configuration, Environment}

class DisabledModuleSpec extends Specification {
  "play.api.db.evolutions.EvolutionsModule must be disabled" >> {
    val env = Environment.simple()
    val configuration = Configuration.load(env)

    //play.api.inject.Modules.locate at
    //loadModules at
    //play.api.inject.guice.GuiceApplicationBuilder.applicationModule
    val includes = configuration.getStringSeq("play.modules.enabled").getOrElse(Seq.empty)
    val excludes = configuration.getStringSeq("play.modules.disabled").getOrElse(Seq.empty)
    val moduleClassNames = includes.toSet -- excludes

    moduleClassNames must contain("play.api.db.evolutions.SubFolderEvolutionsModule")

    moduleClassNames must not(contain("play.api.db.evolutions.EvolutionsModule"))
  }.pendingUntilFixed("See issue #1")
}
