import org.scalatest.{FunSpecLike, MustMatchers, Tag}
import Helpers._

class TcpClientIntegrationSpec extends FunSpecLike with MustMatchers{
  it("should get data from google") { pending
    val t = new ProcessData{}.csvLines("GOOG").get

    t.nonEmpty must be (true)
    println(t)
  }
}
