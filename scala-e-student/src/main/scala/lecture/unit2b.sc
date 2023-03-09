
def f1(n:Int):Int = {
  if (n>0)
    f1(n-1)
  else
    0
}


val x = Vector(1,2,3)
val y = List(1,2,3)
val z = 1 until 20 by 2

def sumIntegersByFold(ints: List[Int]): Int = {
  ints.fold(0)(_ + _)
}

def sumIntegersByFold(ints: Vector[Int]): Int = {
  ints.fold(0)(_ + _)
}

def sumIntegersByFold(ints: Seq[Int]): Int = {
  ints.fold(0)(_ + _)
}

sumIntegersByFold(List(1,2,3,4,5))
sumIntegersByFold(Vector(1,2,3,4,5))
sumIntegersByFold(1 to 5)



val ints = List(1,2,3,-1,-5,7,11)

def f(acc:Int,b:Int):Int = {
  acc * b
}

ints.fold(1)(f)


def f(acc:String,b:String):String = {
  acc + b
}
val strings = List("hello", "my", "name", "is","jim")

strings.fold("")(f)

def g(acc:Int,b:String):Int = {
  println(s"   acc=$acc  b=$b")
  acc + b.length
}
strings.foldLeft(0)(g)


def h(acc:Int, c:Char):Int = {
  val isvowel = "aeiou".contains(c)
  if (isvowel)
    acc + 1
  else
    acc
}
"hello world".foldLeft(0)(h)