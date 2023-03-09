import scala.annotation.tailrec

val edges = List((0,1),(1,2),(0,2),(1,3),(2,4),(3,5),(4,6),(5,0),(2,6),(2,5))







def incr(adj:Map[Int,Set[Int]],e:(Int,Int)):Map[Int,Set[Int]] ={
  e match {
    case (src,dst) => adj + (src -> (adj.getOrElse(src,Set()) + dst ))
  }
}

val adj = edges.foldLeft(Map[Int,Set[Int]]())(incr)


val path = List(0,2)

def extend1(path:List[Int], adj:Map[Int,Set[Int]]):List[List[Int]] =  {
  path match {
    case v::vs => (for { neighbor <- adj.getOrElse(v,Set())
                         if ! path.contains(neighbor)
                        } yield neighbor::path ).toList
  }
}

for { path <- extend1(List(0,2),adj)}
   println(path.reverse)

var setdist = res.getOrElse(dist1, Set())
collectVertices(done + v1, newDoneSet, nextdistance, nextToDO ++ diffe, res + (dist1 -> (nextSet ++ setdist)), adj(node) diff done diff newDoneSet, v1)


