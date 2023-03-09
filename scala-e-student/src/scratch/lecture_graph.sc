import lecture.Edge

val e1 = Edge(1,2)
val e2 = Edge("abc","xyz")

e1.toSet
e2.toSet

val Edge(src,dst) = e2
src
dst

Edge.pairToEdge((12,13))


for{ Edge(src,dst) <- List(e1)} yield src


val mylist = List(3,4,5,6)

2::mylist
1::2::mylist

mylist.toVector
5 + 6
5.+(6)
2 :: mylist
mylist.::(2)
Set(1,2,3).+(6)

v :: path