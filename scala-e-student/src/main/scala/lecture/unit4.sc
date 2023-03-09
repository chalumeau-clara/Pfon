import scala.math.abs

def binSearch(left:Double,
              right:Double,
              f:Double=>Double,
              epsilon:Double):Double = {

  if (f(left) > 0.0 && f(right) < 0.0)
    binSearch(left,
              right,
              x => -1*f(x),
              epsilon)
  else {
    def recur(left:Double,right:Double):Double = {
      assert(f(left) <= 0.0)
      assert(f(right) >= 0.0)
      val mid = (left + right) / 2.0
      val fm = f(mid)
      if (abs(fm) < epsilon)
        mid
      else if (fm < 0.0)
        recur(mid,right)
      else
        recur(left,mid)
    }
    recur(left,right)
  }
}

def g(x:Double):Double = {
  3 * x*x*x - 2*x*x + 4
}

println(binSearch(-10.0,10.0,g,0.001))
