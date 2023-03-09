import scala.annotation.tailrec

def f(x:Double):Double = {
  2*x*x*x - 3*x*x + 4*x - 1
}

f(0)

@tailrec
def binSearch(left     :Double,
              right    :Double,
              f        :Double=>Double,
              threshold:Double,
              maxDepth :Int):Option[Double] = {
  println(s" maxDepth=$maxDepth  left=$left  right=$right   f(x) = ${f(left)}")
  if (maxDepth <= 0)
    None
  else if (f(left) < 0.0 && f(right) > 0.0) {
    if (right - left < threshold)
      Some(left)
    else {
      val mid = (right + left) / 2.0
      val fmid = f(mid)
      if (fmid < 0)
        binSearch(mid, right, f, threshold, maxDepth-1)
      else
        binSearch(left, mid, f, threshold, maxDepth-1)
    }
  }
  else if (f(right) < 0.0 && f(left) > 0)
    binSearch(right, left, f, threshold, maxDepth-1)
  else
    None
}

def binSearchTarget(left     :Double,
              right    :Double,
              f        :Double=>Double,
              target   :Double,
              threshold:Double,
              maxDepth :Int):Option[Double] = {
  binSearch(left,
            right,
            x => f(x) - target,
            threshold,
            maxDepth)
              }

binSearch(-100.0, 100.0, f, 0.01, 20)


binSearchTarget(-100.0, 100.0, f, 10.0,0.01, 20)
