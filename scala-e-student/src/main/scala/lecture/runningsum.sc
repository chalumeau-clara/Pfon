val data = List(10,11,3,-1,2,4,17,-5)
// we wish to produce the running sum.
List(0,1,4,3,5,9,26,21)

def incremental_sum(acc:List[Int],item:Int):List[Int] = {
  println(s"  item=$item  acc=$acc")
  val calculating = acc match {
    case List() => List(item)
    case i::is => (i + item)::acc
  }
  println(s"    calculating = $calculating")
  calculating
}

data.foldLeft(List[Int]())(incremental_sum).reverse

