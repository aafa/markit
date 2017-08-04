import fr.hmil.roshttp.HttpRequest
import fs2._
import monix.execution.Scheduler.Implicits.global
import scala.collection.mutable
import scala.concurrent.Future
import Helpers._

case class TicketData(date: String,
                      open: Double,
                      high: Double,
                      low: Double,
                      close: Double,
                      volume: Long)
case class TicketMinimalData(date: String, close: Double) {
  override def toString = s"""TicketMinimalData("$date", $close)"""
}

abstract class ProcessData {
  implicit lazy val strategy: Strategy = Strategy.fromExecutionContext(
    scala.concurrent.ExecutionContext.Implicits.global)

  type Result[T] = Stream[Task, T]
  val memo = new mutable.HashMap[String, Result[TicketMinimalData]]

  def pricesURL(ticker: String): String = {
    s"https://www.google.com/finance/historical?output=csv&q=$ticker"
  }

  def csvString(ticker: String): Future[String] =
    HttpRequest(pricesURL(ticker)).get().map(_.body)

  def query(ticker: String): Result[TicketMinimalData] = {
    def fs2Stream: Result[TicketMinimalData] = Stream
      .eval(Task.fromFuture(csvString(ticker)))
      .through(text.lines)
      .drop(1) // skip headers
      .map(s => s.split(",").toList)
      .collect {
        case date :: _ :: _ :: _ :: close :: _ :: Nil =>
          TicketMinimalData(date, close.toDouble)
      }

    // todo cache invalidation after some time?
    // todo persist it out of memory, store in a file (scala.js howto handle?)
    memo.getOrElseUpdate(ticker, fs2Stream)
  }

  //  /* 1 - 1 year historic prices given a ticker */
  def dailyPrices(ticker: String): Result[Double] =
    query(ticker).map(_.close)

  //  /* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
  def returns(ticker: String): Result[Double] = {
    val data = dailyPrices(ticker)
    data.zip(data.drop(1)).collect {
      case (today, yesterday) => (today - yesterday) / yesterday
    }
  }

  //  /* 3 – 1 year mean returns */
  def meanReturn(ticker: String): Result[Double] = {
    // using `cumulative moving average` formula (see https://www.wikiwand.com/en/Moving_average)
    returns(ticker).zipWithIndex.fold(0d) {
      case (cma, (pn, prevIndex)) =>
        val n1 = prevIndex + 1
        (pn + prevIndex * cma) / n1
    }
  }
}

object Main extends App {
  println(">>> dailyPrices for GOOG")
  println(new ProcessData {}.dailyPrices("GOOG").runLog.unsafeRunAsyncFuture().get)
  println(">>> returns for GOOG")
  println(new ProcessData {}.returns("GOOG").runLog.unsafeRunAsyncFuture().get)
  println(">>> meanReturn for GOOG")
  println(new ProcessData {}.meanReturn("GOOG").runLog.unsafeRunAsyncFuture().get.head)
}
