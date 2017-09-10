import com.google.inject.CreationException
import org.specs2.mutable.Specification
import play.api.inject.guice.GuiceApplicationBuilder

class FakeAppSpec extends Specification {
  """FakeApplication (and `WithApplication`) can't start, even with this setting in reference.conf:
    | play.modules.disabled += "play.api.db.evolutions.EvolutionsModule"
    | """.stripMargin >> {
    GuiceApplicationBuilder().build() must not(throwAn[CreationException])
  }.pendingUntilFixed("See issue #1")
}
