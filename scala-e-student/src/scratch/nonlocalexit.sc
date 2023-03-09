val data = Seq(1,2,3,2,3,4,3,4,5,4,5,6)

def f(x:Int):Int = {
  println(s"x=$x  y=${2*x+3}")
  2*x+3
}


def cousinOfFind[A,B](seq:Seq[A],f:A=>B,pred:B=>Boolean):Option[(A,B)] = {
  seq.view.map(e => (e, f(e))).find{case (_, y) => pred(y)}
}

cousinOfFind(data,
             f,
             (x => x == 11):Int=>Boolean)

cousinOfFind(Set(1,2,3,4,5,6),
             f,
             (x => x == 11):Int=>Boolean)
