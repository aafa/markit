import org.scalatest.{FunSpecLike, Matchers, MustMatchers}

class TestProcessData extends ProcessData{
  override def query(ticker: String) = {
    fs2.Stream(TicketMinimalData("1-Aug-17", 930.83), TicketMinimalData("31-Jul-17", 930.5), TicketMinimalData("28-Jul-17", 941.53), TicketMinimalData("27-Jul-17", 934.09), TicketMinimalData("26-Jul-17", 947.8), TicketMinimalData("25-Jul-17", 950.7), TicketMinimalData("24-Jul-17", 980.34), TicketMinimalData("21-Jul-17", 972.92), TicketMinimalData("20-Jul-17", 968.15), TicketMinimalData("19-Jul-17", 970.89), TicketMinimalData("18-Jul-17", 965.4), TicketMinimalData("17-Jul-17", 953.42), TicketMinimalData("14-Jul-17", 955.99), TicketMinimalData("13-Jul-17", 947.16), TicketMinimalData("12-Jul-17", 943.83), TicketMinimalData("11-Jul-17", 930.09), TicketMinimalData("10-Jul-17", 928.8), TicketMinimalData("7-Jul-17", 918.59), TicketMinimalData("6-Jul-17", 906.69), TicketMinimalData("5-Jul-17", 911.71), TicketMinimalData("3-Jul-17", 898.7), TicketMinimalData("30-Jun-17", 908.73), TicketMinimalData("29-Jun-17", 917.79), TicketMinimalData("28-Jun-17", 940.49), TicketMinimalData("27-Jun-17", 927.33), TicketMinimalData("26-Jun-17", 952.27))
  }
}

class TickerSpec extends FunSpecLike with MustMatchers{
  val tdp = new TestProcessData

  it("should count daily values"){
    tdp.query("").toVector.size must === (26)
    tdp.dailyPrices("").toVector.size must === (26)
    tdp.dailyPrices("").toVector.head must === (930.83)

    println(tdp.returns("").toVector)
    tdp.returns("").toVector.head must === ((930.83 - 930.5) / 930.5)
    tdp.returns("").toVector.drop(1).head must === ((930.5 - 941.53) / 941.53)
    tdp.returns("").toVector.last must === ((927.33 - 952.27) / 952.27)
  }

  it("should calculate mean average correctly"){
    new ProcessData{
      override def returns(ticker: String) = fs2.Stream(1,2,6)
      meanReturn("").toVector.head must === (3.0)
    }
  }

  it("should calculate mean returns"){
    tdp.meanReturn("").toVector.head must === (-8.26877806322153E-4)
  }
}
