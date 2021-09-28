import com.google.common.util.concurrent.ExecutionError
import com.google.inject.CreationException
import org.specs2.mutable.Specification
import play.api.inject.guice.GuiceApplicationBuilder
import play.core.PlayVersion

class FakeAppSpec extends Specification {
  private def javaVersion: Int = scala.sys
    .props("java.specification.version")
    .split('.')
    .dropWhile(_ == "1")
    .head
    .toInt
  private def isPlay26 = PlayVersion.current.startsWith("2.6.")

  """FakeApplication (and `WithApplication`) can't start, even with this setting in reference.conf:
    | play.modules.disabled += "play.api.db.evolutions.EvolutionsModule"
    | """.stripMargin >> {

    if (isPlay26 && javaVersion >= 16) {
      // ExecutionError: Fatal execution error, caused by java.lang.ExceptionInInitializerError
      // CAUSED BY
      // InaccessibleObjectException: Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
      //   throws java.lang.ClassFormatError accessible:
      //   module java.base does not "opens java.lang" to unnamed module @672af8eb (ReflectUtils.java:52)
      GuiceApplicationBuilder().build() must throwAn[ExecutionError]
    }.toResult
    else {
      GuiceApplicationBuilder().build() must not(throwAn[CreationException])
    }.pendingUntilFixed("See issue #1")
  }
}
