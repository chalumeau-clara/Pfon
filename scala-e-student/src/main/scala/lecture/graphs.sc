def checkEdge(n: Int, src: Int, dst: Int): Unit = {
  assert(src >= 0, s"edge src=$src must be >=0")
  assert(dst >= 0, s"edge dst=$dst must be >=0")
  assert(src < n, s"edge src=$src must be < n=$n")
  assert(dst < n, s"edge dst=$dst must be < n=$n")
}

def makeAdj_1(n: Int, edges: List[(Int, Int)]): Vector[Set[Int]] = {
  val adj = Vector.fill(n)(Set[Int]())

  @scala.annotation.tailrec
  def recur(edges: List[(Int, Int)], adj: Vector[Set[Int]]): Vector[Set[Int]] = {
    if (edges.isEmpty) // if (edges == List())
      adj
    else {
      val (src, dst) = edges.head
      checkEdge(n, src, dst)
      recur(edges.tail,
            adj.updated(src, adj(src) + dst)) // immutable, nondestructive operation
    }
  }

  recur(edges, adj)
}

makeAdj_1(3,List((0,1),(1,2),(0,2)))

def makeAdj_2(n: Int, edges: List[(Int, Int)]): Vector[Set[Int]] = {
  val adj = Vector.fill(n)(Set[Int]())

  @scala.annotation.tailrec
  def recur(edges: List[(Int, Int)], adj: Vector[Set[Int]]): Vector[Set[Int]] = {
    edges match {
      case List() => adj
      case (src, dst) :: es =>
        checkEdge(n, src, dst)
        recur(es,
              adj.updated(src, adj(src) + dst)) // immutable, nondestructive operation

    }
  }

  recur(edges, adj)
}

makeAdj_2(3,List((0,1),(1,2),(0,2)))

