package com.github.Eleonora8510

object Day2Views {
  def main(args: Array[String]):Unit ={
    println("Creating temporary Views")

    val numbers = (0 to 12).toArray
    println(numbers.mkString(","))
    // our bread and butter is map, filter plus other operations on our collections

    val squares = numbers.map(n => n*n)
    println(squares.mkString(","))

    val evenSquares = squares.filter(n => n%2 ==0)
    println(evenSquares.mkString(","))
    //often we want to chain these operations in a single line
    val firstEvenSquares = numbers.map(n => n*n).filter(n => n%2 ==0).take(3)// first 3 even squares
    println(firstEvenSquares.mkString(","))

    //the slight problem with above line is that each map and filter creates a new data collection in memory
    // this can be quite wasteful and slow operations down on larger dat sets
    // again not a problem with tiny data sets such as this one (pretty much anything under a million items)

    // the idea is to be lazy and use views (some analogy with SQL views) to avoid creating new collections until end
    // so we add view before we start calculations
    // and then we cast to whatever structure we need (Array, Vector, List etc) at the end
    val oddCubes = numbers.view.map(n => n*n*n).filter(n => n%2 == 1).slice(0,5).takeRight(3).toArray
    //so we took first 5 results and took 3 of the right ones from those 5
    println(oddCubes.mkString(","))

    //queries
    val firstOver10 = numbers.find(n=> n>10)//find first occurence matching our function predicate
    println(firstOver10.getOrElse(-1)) // -1 here is Else if we find nothing
    val firstOver20 = numbers.find(n=> n>20)
    println(firstOver20.getOrElse(-1))

    val result = numbers.find(n=> n<0).getOrElse(-1) // in this example -1 is a bit misleading
    // we might actually have -1 as an answer
    println(result)
    println(numbers.contains(3))

    // so combinations will give us iterator of all combinations from our collection of given size

    // we cast to Array or some other structure at th end no need to cast numbers immediately
    val myCombinations = evenSquares.combinations(2).toArray // so we have a 20 array
    for (combination <- myCombinations) {
      println(combination.mkString(","))
    }

    //now we get to the last of the 3 big functional methods - reduce
    //we've seen map and filter and those are pretty intuitive
    def add(x: Int, y: Int): Int = {
      val theSum = x + y
      println(s"received $x and $y, their sum is $theSum")
      theSum
    }

    // so with reduce we will have accumulator

    println(numbers.reduce(add))
    println(numbers.view.filter(n=> n%2 == 0).reduce(add)) // so we can use view if we want only certain values
// so reduce is called myCollection.size 1 times
  }
}
