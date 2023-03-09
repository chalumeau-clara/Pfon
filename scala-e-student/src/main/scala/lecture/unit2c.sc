def add(acc:Int,b:Int):Int = {
  acc + b
}

(0 to 100 by 3).fold(0)(add)
(0 to 100 by 3).fold(0)( _ + _ )
(0 to 100 by 3).fold(0)( (acc,b) => acc+b)
(0 to 100 by 3).fold(0){case (acc,b) => acc+b}


val ints = List(10,20,-4,-6,7,3)
List(10,30,26,20,27,30)

def f(acc:List[Int], b:Int):List[Int]={
  println(s"  b=$b  acc=$acc")
  acc match {
    case List() => List(b)
    case i::is => (i+b)::acc
  }
}

ints.foldLeft(List[Int]())(f).reverse

