import scala.math.abs

def g(x:Double):Double = {
  3 * x*x*x - 3*x*x + 5
}


def binSearch(left:Double,
              right:Double,
              f:Double=>Double,
              epsilon:Double,
              maxDepth:Int):Option[Double] ={
  val mid = (right + left)/2.0
  if ( abs(f(mid)) < epsilon)
    Some(mid)
  else if (maxDepth <= 0)
    None
  else if (f(left) > 0 && f(right) > 0)
    None
  else if (f(left) < 0 && f(right) < 0)
    None
  else if (f(left) > 0 && f(right) <0)
    binSearch(left,right,
              x => -1*f(x),
              epsilon,
              maxDepth)
  else if (f(mid) < 0.0)
    binSearch(mid,right,f,epsilon,maxDepth-1)
  else
    binSearch(left,mid,f,epsilon,maxDepth-1)
}

val x0 = binSearch(-10.0, 10.0,
                   x => 12.0,
                   0.001,
                   100)
x0 match {
  case None => "no value found"
  case Some(x) => s"found g($x) = ${g(x)}"
}
