def f(acc:Int, c:Char):Int = {
  acc + (if("aeiou".contains(c)) 1 else 0)
}

"hello world".foldLeft(0)(f)
"hello world".foldLeft(0)((acc,c) => acc + (if("aeiou".contains(c)) 1 else 0))


def g(acc:List[Int],x:Int):List[Int] = {
  acc match {
    case List() => List(x)
    case a::_ => (a+x):: acc
  }
}

List(1, 2, 3, 2, -1, 0, -1)
  .foldLeft(List[Int]())(g)
  .reverse

List(1, 2, 3, 2, -1, 0, -1)
  .foldLeft(List[Int]()) {
    (acc, x) =>
      acc match {
        case List() => List(x)
        case a :: _ => (a + x) :: acc
      }
  }
  .reverse




