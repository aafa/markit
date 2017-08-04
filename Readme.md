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

