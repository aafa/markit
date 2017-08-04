import fr.hmil.roshttp.HttpRequest
import fs2.{Stream, _}
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.ext.Ajax

import scala.collection.mutable
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

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
  type Result[T] = Stream[Task, T]
  implicit val strategy = Strategy.default
  val memo = new mutable.HashMap[String, Result[TicketMinimalData]]

  def pricesURL(ticker: String): String = {
    s"https://www.google.com/finance/historical?output=csv&q=$ticker"
  }

//  def csvString(ticker: String): Future[String] = HttpRequest(pricesURL(ticker)).get().map(_.body)
  def csvString(ticker: String): Future[String] = {
    println(s"fetching from ${pricesURL(ticker)}")
    Ajax.get(pricesURL(ticker)).map(_.response.toString)
  }

  // we are passing sync request here to simplify things
  def query(ticker: String): Result[TicketMinimalData] = {
    val fs2Stream: Result[TicketMinimalData] = Stream.eval(Task.fromFuture(csvString(ticker)))
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

//object Main extends App {
//  private val result = new ProcessData {}.query("GOOG")
//  private val value = Await.result(new ProcessData {}.csvString("GOOG"), 10.seconds)
//  println(value)
//}
