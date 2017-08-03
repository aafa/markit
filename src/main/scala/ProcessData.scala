import com.github.tototoshi.csv._

import scala.collection.mutable
import scala.io.Source
import scalaj.http.Http
import fs2._

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
  val memo = new mutable.HashMap[String, Stream[Pure, TicketMinimalData]]

  def pricesURL(ticker: String): String = {
    s"https://www.google.com/finance/historical?output=csv&q=$ticker"
  }

  // we are passing sync request here to simplify things
  def query(ticker: String): Stream[Pure, TicketMinimalData] = {
    def csvString: String = Http(pricesURL(ticker)).asString.body

    // we're gonna have these fields: Date,Open,High,Low,Close,Volume
    def parse = CSVReader.open(Source.fromString(csvString))

    val fs2Stream = Stream
      .unfold(parse)((reader: CSVReader) => reader.readNext().map((_, reader)))
      .drop(1) // skip headers
      .map {
        case date :: _ :: _ :: _ :: close :: _ :: Nil =>
          TicketMinimalData(date, close.toDouble)
      }

    // todo cache invalidation after some time?
    // todo persist it out of memory, store in a file (scala.js howto handle?)
    memo.getOrElseUpdate(ticker, fs2Stream)
  }

  //  /* 1 - 1 year historic prices given a ticker */
  def dailyPrices(ticker: String): Stream[Pure, Double] =
    query(ticker).map(_.close)

  //  /* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
  def returns(ticker: String): Stream[Pure, Double] = {
    val data = dailyPrices(ticker)
    data.zip(data.drop(1)).collect {
      case (today, yesterday) => (today - yesterday) / yesterday
    }
  }

  //  /* 3 – 1 year mean returns */
  def meanReturn(ticker: String): Stream[Pure, Double] = {
    // using `cumulative moving average` formula (see https://www.wikiwand.com/en/Moving_average)
    returns(ticker).zipWithIndex.fold(0d) {
      case (cma, (pn, prevIndex)) =>
        val n1 = prevIndex + 1
        (pn + prevIndex * cma) / n1
    }
  }
}

object Main extends App {
  private val result = new ProcessData {}.query("GOOG")
  println(result.toList)
}
