import Helpers._
import fs2._

import scala.io.BufferedSource
import scala.util.Try

case class TicketMinimalData(date: String, close: Double) {
  override def toString = s"""TicketMinimalData("$date", $close)"""
}

class ProcessData {
  implicit lazy val strategy: Strategy = Strategy.fromExecutionContext(
    scala.concurrent.ExecutionContext.Implicits.global)

  def pricesURL(ticker: String): String = {
    s"https://www.google.com/finance/historical?output=csv&q=$ticker"
  }

  def csvLines(ticker: String): Result[String] = {
    Try {
      val source: BufferedSource =
        scala.io.Source.fromURL(pricesURL(ticker))

      val s: Stream[Task, String] = Stream
        .unfold(source)((reader: BufferedSource) =>
          if (reader.hasNext) Some((reader.next(), reader)) else None)
        .fold("")(_ + _)
        .through(text.lines)
      s
    }.getOrElse(Stream.fail[Task](new Exception("Ticket not found")))
  }

  def query(ticker: String): Result[TicketMinimalData] =
    csvLines(ticker)
      .drop(1) // skip headers
      .map(s => s.split(",").toList)
      .collect {
        case date :: _ :: _ :: _ :: close :: _ :: Nil =>
          TicketMinimalData(date, close.toDouble)
      }

  //  /* 1 - 1 year historic prices given a ticker */
  def dailyPrices(q: Result[TicketMinimalData]): Result[Double] =
    q.map(_.close)

  //  /* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
  def returns(q: Result[TicketMinimalData]): Result[Double] = {
    val data = dailyPrices(q)
    data.zip(data.drop(1)).collect {
      case (today, yesterday) => (today - yesterday) / yesterday
    }
  }

  //  /* 3 – 1 year mean returns */
  def meanReturn(q: Result[TicketMinimalData]): Result[Double] = {
    // using `cumulative moving average` formula (see https://www.wikiwand.com/en/Moving_average)
    returns(q).zipWithIndex.fold(0d) {
      case (cma, (pn, prevIndex)) =>
        val n1 = prevIndex + 1
        (pn + prevIndex * cma) / n1
    }
  }
}

object Main extends App {

  override def main(args: Array[String]) = {
    val ticket = if (args.length == 1) args.head else "GOOG"

    val processData = new ProcessData
    val query = processData.query(ticket).get // to cache the fetched data
    val qStream = Stream.emits(query)

    println(">>> dailyPrices for " + ticket)
    println(processData.dailyPrices(qStream).get)
    println(">>> returns for " + ticket)
    println(processData.returns(qStream).get)
    println(">>> meanReturn for " + ticket)
    println(
      processData
        .meanReturn(qStream)
        .get
        .head)

  }
}
