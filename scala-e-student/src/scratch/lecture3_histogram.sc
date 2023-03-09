val data = Vector("a","b","c","b","a","d")
  .groupBy(identity)
  .map{case (k,v) => k -> v.size}

data.groupBy(_._2)
data.groupBy(_._2).map{case (k,v) => k -> v.map(_._1)}

val byFreq = data.groupBy(_._2)
  .map{case (k,v) => k -> v.map(_._1).toSet}


byFreq.keys
byFreq.map(_._1).toSet
byFreq.map{case (k,v) => k}.toSet

val mostCommonFrequency = byFreq.keys.reduceOption(scala.math.max)
val leastCommonFrequency = byFreq.keys.reduceOption(scala.math.min)

mostCommonFrequency.map{byFreq}.getOrElse(Set())
None.map{byFreq}.getOrElse(Set())

// what about toString