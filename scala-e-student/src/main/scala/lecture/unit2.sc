def sumIntegersByFold(ints: Seq[Int]): Int = {
  ints.fold(0)(_ + _)
}

sumIntegersByFold(List(1,2,-1,3,4,-5))
sumIntegersByFold(Vector(1,2,-1,3,4,-5))
sumIntegersByFold(1 to 10 by 2)

val ints = List(1,2,-1,3,4,-5)

def f(a:Int, b:Int):Int = {
  // we'd like to add a to the maximum of a and b.
  a + b.max(a)
}

ints.fold(0)(f)

ints.fold(0)((a,b) => a+b.max(a))
ints.fold(0)((a,b) => a+ 2*b )
ints.fold(0)( _ + 2*_ )
Vector(1,2,4,6,8,11,13).fold(0)( _ + 2*_ )


val strings:Seq[String] = Vector("hello", "world", "my",
                     "name", "is", "jim")

def g(acc:Int, str:String):Int = {
  println(s"  acc=$acc   str=$str")
  acc + str.length
}

strings.foldLeft(0)(g)

def count_vowels(acc:Int,letter:Char):Int = {
  acc + (if ("aeiou".contains(letter))
    1
  else
    0)
}

def count_vowels2(acc:Int,letter:Char):Int = {
  (letter match {
    case 'a' => 1
    case 'e' => 1
    case 'i' => 1
    case 'o' => 1
    case 'u' => 1
    case _ => 0
  }) + acc
}

def count_vowels3(acc:Int,letter:Char):Int = {
  (letter match {
    case 'a' |
         'e' |
         'i' |
         'o' |
         'u' => 1
    case _ => 0
  }) + acc
}

def count_vowels4(acc:Int,letter:Char):Int = {
  (if (letter == 'a' )
    1
  else if (letter == 'e')
    1
  else if (letter == 'i')
    1
  else if (letter == 'o')
    1
  else if (letter == 'u')
    1
  else
    0) + acc
}

"hello world".foldLeft(0)(count_vowels4)
