import scala.math.{sin,abs}

def binSearch(left:Double,
              right:Double,
              f:Double=>Double,
              epsilon:Double,
              maxIter:Int):Option[Double] = {
  val mid = (right + left) / 2.0
  val fm = f(mid)

  if (f(left) > 0 && f(right) < 0)
    binSearch(left,
              right,
              x => -1 * f(x),
              epsilon,
              maxIter)
  else if (abs(fm) < epsilon)
    Some(mid)
  else if (maxIter < 0)
    None
  else if (f(left) < 0 && f(right)< 0)
    None
  else if (f(left) > 0 && f(right)> 0)
    None
  else if (fm < 0.0)
    binSearch(mid, right, f, epsilon, maxIter-1)
  else
    binSearch(left, mid, f, epsilon, maxIter-1)
}

def binSearchTarget(left:Double,
              right:Double,
              f:Double=>Double,
              targetValue:Double,
              epsilon:Double,
              maxIter:Int):Option[Double] = {
  binSearch(left,right,
            x => f(x) - targetValue,
            epsilon,
            maxIter)
}


def g(x:Double):Double = {
  sin(x)
}

val x0 = binSearchTarget(-3.0, 3.0, g, 0.1,0.00001,100)

x0 match {
  case None => println("no zero found for function")
  case Some(x) => println(s"function has target at $x: g(x)=${g(x)}")
}

