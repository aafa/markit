import org.scalatest.{FunSpecLike, Matchers, MustMatchers}

class TestProcessData extends ProcessData{
  override def query(ticker: String) = {
    List(TicketMinimalData("1-Aug-17", 930.83), TicketMinimalData("31-Jul-17", 930.5), TicketMinimalData("28-Jul-17", 941.53), TicketMinimalData("27-Jul-17", 934.09), TicketMinimalData("26-Jul-17", 947.8), TicketMinimalData("25-Jul-17", 950.7), TicketMinimalData("24-Jul-17", 980.34), TicketMinimalData("21-Jul-17", 972.92), TicketMinimalData("20-Jul-17", 968.15), TicketMinimalData("19-Jul-17", 970.89), TicketMinimalData("18-Jul-17", 965.4), TicketMinimalData("17-Jul-17", 953.42), TicketMinimalData("14-Jul-17", 955.99), TicketMinimalData("13-Jul-17", 947.16), TicketMinimalData("12-Jul-17", 943.83), TicketMinimalData("11-Jul-17", 930.09), TicketMinimalData("10-Jul-17", 928.8), TicketMinimalData("7-Jul-17", 918.59), TicketMinimalData("6-Jul-17", 906.69), TicketMinimalData("5-Jul-17", 911.71), TicketMinimalData("3-Jul-17", 898.7), TicketMinimalData("30-Jun-17", 908.73), TicketMinimalData("29-Jun-17", 917.79), TicketMinimalData("28-Jun-17", 940.49), TicketMinimalData("27-Jun-17", 927.33), TicketMinimalData("26-Jun-17", 952.27))
  }
}

class TickerSpec extends FunSpecLike with MustMatchers{
  val tdp = new TestProcessData

  it("should count daily values"){
    tdp.query("").size must === (26)
    tdp.dailyPrices("").size must === (26)
    tdp.dailyPrices("").head must === (930.83)

    println(tdp.returns(""))
    tdp.returns("").head must === ((930.5 - 930.83) / 930.83)
    tdp.returns("").drop(1).head must === ((941.53 - 930.5) / 930.5)
    tdp.returns("").last must === ((952.27 - 927.33) / 927.33)
  }

  it("should calculate mean returns"){
    tdp.meanReturn("") must === (0.0009955847190971708)
  }
}
