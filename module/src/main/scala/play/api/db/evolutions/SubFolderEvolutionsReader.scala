package play.api.db.evolutions

import javax.inject.{Inject, Singleton}
import play.api.{Configuration, Environment}

@Singleton
class SubFolderEvolutionsReader @Inject() (
    env: Environment,
    config: Configuration
) extends EnvironmentEvolutionsReader(env) {
  override def evolutions(db: String): Seq[Evolution] = {
    config
      .getOptional[Seq[String]](s"evolutions.$db.folders")
      .getOrElse(Nil)
      .flatMap(folder => super.evolutions(s"$db/$folder"))
      .zipWithIndex
      .map { case (ev, index) =>
        ev.copy(revision = index)
      }
  }
}
