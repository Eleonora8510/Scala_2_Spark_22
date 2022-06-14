package com.github.Eleonora8510

object Day1SparseStringsExercise {
  //https://www.hackerrank.com/challenges/sparse-arrays/problem
  //There is a collection of input strings and a collection of query strings.
  // For each query string, determine how many times it occurs in the list of input strings.
  // Return an array of integers as results

  val strings = Array("Valdis", "alus", "aldaris", "Aiviekste", "Bauska", "Valdis")
  val queries = Array("Valdis", "Bauska", "Cesis")



  val numbers = (0 to 12).toArray
  val evenCount = numbers.count(_ % 2 ==0)

  println(strings.count(el => el == "Valdis"))
  println(strings.count(_ == "Valdis"))
  println(s"There are $evenCount even  numbers in ${numbers.mkString(",")} array")
  //val result = Array(2,1,0)


  def getStringMatches(strings: Array[String], queries:Array[String]):Array[Int] = {

    //    //Array(1,2,0) // correct for the above but FIXME

    //val arrayOfMatches = queries.map(qry => strings.count(entry => entry ==qry))
    // arrayOfMatches
    queries.map(qry => strings.count(_ ==qry))
  }

  val results = getStringMatches(strings, queries)

  println(results.mkString(","))
  //
  val grouped = strings.grouped(3).toArray // this just divides
  grouped.foreach(el => println(el.mkString(",")))

  val groupedBy = strings.groupBy(_.hashCode)
  groupedBy.foreach(el => println(el))

  /**
   * TOO long a function name, unless really needed stick to max 3 or 4 words, ideally 2
   * @param strings
   * @param queries
   * @return
   */
  def getStringMatchesOptimizedForLargeQueries(strings:Array[String], queries:Array[String]):Array[Int] = {
    //initialize Mutable Map
    val mutableMap = collection.mutable.Map[String,Int]()

    //key idea is that we can acess and modify values by key very very quickly even in a large map
    //if key exists we increase the value by 1
    //if key does not exist we set the value to 1
    //advantage of this approach we only need to go through strings once
    //not multiple times with each count in the above exampl
    for (text <- strings) {
      if (mutableMap.contains(text)) mutableMap(text) += 1
      else mutableMap(text) = 1
    }
    //    for ((text, cnt) <- mutableMap ) {
    //      println(s"$text occurs $cnt times")
    //    }
    //so using getOrElse we can provide a default value
    //  println(mutableMap.getOrElse("Valdis", 0))
    //  println(mutableMap.getOrElse("Līga", 0))
    val solution = queries.map(qry => mutableMap.getOrElse(qry, 0))
    solution
  }



//  //initialize
//  val mutableMap = collection.mutable.Map[String, Int]()
//
//
//  //key idea is that we can access and modify values by key very very quickly even in a large map
//  //if key exists we increase the value by 1
//  // if key does not exists we set the value to 1
//  // advantage of this approach we only need to go throught strings once
//  // not multiple times with eahc count in the above example
//  for (text <- strings) {
//    if (mutableMap.contains(text)) mutableMap(text) += 1
//    else mutableMap(text) = 1
//  }

//  for ((text, cnt) <- mutableMap) {
//    println(s"$text occurs $cnt times")
//  }
//
//  println(mutableMap.getOrElse("Valdis",0))
//  println(mutableMap.getOrElse("Liga",0))
//
//  val solution = queries.map(qry => mutableMap.getOrElse(qry, 0))
//  println(solution.mkString(","))

  val alpha = 'a' to 'z'
  for (letter <-alpha){
    println(s"$letter hashes to ${letter.hashCode()}")
  }

  println("abba".hashCode)

}
