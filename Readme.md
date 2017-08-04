### Task
 
__Prerequisites__
Get the csv stock daily data for the past year. 
Here is the url example https://www.google.com/finance/historical?output=csv&q=GOOG  

__Task__
Write Scala functions that will return, feel free to adapt function signatures, these are just indicative
 
```
/* 1 - 1 year historic prices given a ticker */
`def dailyPrices(ticker: String) : List[Double]` 
 
/* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
`def returns(ticker:String) : Seq[Double]`
 
/* 3 – 1 year mean returns */
`def meanReturn(ticker:String): Double`

```
 
### Objectives
- solve the problem in an elegant and effective FP way 
- make fun along the way, use Scala.js, try something new with it

### Work process
Initially I thought of doing simple `scala.js` ui in top of this solution for demo purposes.
But it turns out to be a significant ~~pain in the a~~ frustration to force this thing to work. `CORS` issue is the one in particular.   
Due to time constraints I decided to skip this ui part and roll out CLI solution as it suffice to demonstrate working prototype.  

`fs2` streams has been used to accommodate for possible large data amounts in a reliable and fp-pure fashion. 

### Test and Run

- `sbt test`  runs all the tests
- `sbt run`   executes queries for `GOOG` ticket

### Known issues and todos
`val memo = new mutable.HashMap[String, Result[TicketMinimalData]]`  that has been used to cache http responses doesn't look very nice and is a big off of this picture but I feel that we need to have some sort of caching here cause 
- data that is queried is Day-aggregated.  Meaning that we have an update only next day.
- we have several sinks that relying on that data and we need to keep it somewhere to free ourselves from requesting it over and over again

__TODO:__  
- make a persistent storage to keep all that data out of the memory and stream-read from there  (initially I haven't plan on doing that since I wasn't sure how to handle this in a `scala.js` context)
- optimize data fetching to skip already fetched parts (since data is historical we are safe to assume it is immutable)  