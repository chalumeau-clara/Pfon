abstract class Tree {
  def leafData():List[Int]
}

case class TreeNode(branches:List[Tree]) extends Tree {
  override def toString(): String = {
    branches.map(_.toString).mkString("[", ", ", "]")
  }

  def leafData(): List[Int] = {
    branches.flatMap(b => b.leafData()) // or    branches.flatMap(_.leafData())
  }
}

case class TreeLeaf(data:Int) extends Tree {
  override def toString():String = data.toString

  def leafData():List[Int] = List(data)
}

val t3 = TreeLeaf(3)
val t4 = TreeLeaf(4)
val t5 = TreeLeaf(5)
val t6 = TreeLeaf(6)

val t3456 = TreeNode(List(
  TreeNode(List(t3,t4)),
  TreeNode(List(t5,t6))))

val t = TreeNode(List(t3456,t3456,t6,t3))

t.leafData()

