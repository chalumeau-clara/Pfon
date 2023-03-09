import scala.annotation.tailrec

val l1 = List(1,2,3,4,5,2,6,2)
val v1 = Vector(10,20,11,21)
val a1 = Array(10,22,32,54)

v1(3)

a1(2)

val l2 = 4::l1
val l3 = 6::l1


l1 match {
  case Nil => "empty list"
  case a::as => s"head is $a  tail is $as"
  case a1::a2::as => "at least 2 elements"
}

v1 match {
  case Vector() => 1
  case Vector(a,b) => 2
  case Vector(a,b,c,d) => 4
  case _ => 3
}


def sumList(nums:List[Int]):Int = {
  nums match {
    case Nil => 0
    case a::as => a + sumList(as)
  }
}

def sumVector(nums:Vector[Int]) = {
  def recur(i:Int):Int ={
    if (i == nums.size)
      0
    else
      nums(i) + recur(i+1)
  }
  recur(0)
}

sumList(List(3,4,2,5,4,3,2))
sumVector(Vector(3,4,2,5,4,3,2))


def sumListTR(nums:List[Int]):Int = {
  @tailrec
  def recur(acc:Int, nums:List[Int]):Int = {
    nums match {
      case Nil => acc
      // tail recursion
      // tail call optimization
      case a :: as => recur(acc+a, as)
    }
  }
  recur(0,nums)
}

sumListTR(List(3,4,2,5,4,3,2))


def plus(acc:Int,item:Int):Int = {
  acc + item
}

l1.fold(0)((acc,item)=>acc+item)
l1.fold(0)(plus)
l1.fold(0)(_ + _)
l1.fold(1)((acc,item)=>acc*item)
l1.fold(1)(_ * _)

v1.fold(0)((acc,item)=>acc+item)

val strings = List("hello","my","name","is","jim")


strings.foldLeft(0)((acc,item) => acc+item.size)

val l4 = List(1,2,3,4,5,2,6,2)
// list of partial sums
// (1, 3, 6, 10, 15, 17, 23, 25)

l4.foldLeft(List[Int]())((acc:List[Int],item:Int) => acc match {
  case Nil => List(item)
  case a::as => (a + item) :: acc
}).reverse

l4.sum
