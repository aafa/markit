import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.{document, window}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.annotation.JSExport

object MainApp extends JSApp {

  @JSExport
  def main(): Unit = {
    println("Starting 'markit' demo ...")

    def show(s: String) = {
      val p = document.createElement("p")
      val text = document.createTextNode(s)
      p.appendChild(text)
      document.body.appendChild(p)
    }

    val result = new ProcessData {}.dailyPrices("GOOG")
//    val result = new ProcessData with TestCsvData{}.dailyPrices("GOOG")

    val eventualDatas = result.runLog.unsafeRunAsyncFuture()
    eventualDatas.onSuccess{
      case value => show(value.toString())
    }

    eventualDatas.onFailure{
      case f => show(f.getLocalizedMessage)
    }

  }

}
