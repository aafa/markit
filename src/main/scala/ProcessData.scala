import com.github.tototoshi.csv._

import scala.collection.mutable
import scala.io.Source
import scalaj.http.Http

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
  val memo = new mutable.HashMap[String, List[TicketMinimalData]]

  def pricesURL(ticker: String): String = {
    s"https://www.google.com/finance/historical?output=csv&q=$ticker"
  }

  // we are passing sync request here to simplify things
  def query(ticker: String): List[TicketMinimalData] = {
    def fetchList: List[TicketMinimalData] = {
      Http(pricesURL(ticker))
        .execute(parser = { is =>
          // we're gonna have these fields: Date,Open,High,Low,Close,Volume
          CSVReader.open(Source.fromInputStream(is)).all().drop(1).collect {
            case date :: _ :: _ :: _ :: close :: _ :: Nil =>
              TicketMinimalData(date, close.toDouble)
          }
        })
        .body
    }

    // todo cache invalidation after some time?
    memo.getOrElseUpdate(ticker, fetchList)
  }

  //  /* 1 - 1 year historic prices given a ticker */
  def dailyPrices(ticker: String): List[Double] = query(ticker).map(_.close)

  //  /* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
  def returns(ticker: String): List[Double] = {
    val data = dailyPrices(ticker)
    data match {
      case head :: tail =>
        tail.zip(data).collect {
          case (today, yesterday) => (today - yesterday) / yesterday
        }
    }
  }

  //  /* 3 – 1 year mean returns */
  def meanReturn(ticker: String): Double = {
    val r = returns(ticker)
    r.sum / r.size
  }

}

object Main extends App {
  private val result = new ProcessData {}.query("GOOG")
  println(result)
}
