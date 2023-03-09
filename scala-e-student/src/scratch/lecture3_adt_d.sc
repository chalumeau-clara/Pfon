abstract class Tree[A] {
  def leafData():List[A]
}

case class TreeNode[A](branches:List[Tree[A]]) extends Tree[A] {
  override def toString():String = {
    branches.map(_.toString).mkString("[",", ","]")
  }

  def leafData():List[A] = {
    branches.flatMap(b => b.leafData()) // or    branches.flatMap(_.leafData())
  }
}

case class TreeLeaf[A](data:A) extends Tree[A] {
  override def toString():String = data.toString

  def leafData():List[A] = List(data)
}

val t3 = TreeLeaf(3.0)
val t4 = TreeLeaf(4.0)
val t5 = TreeLeaf(5.0)
val t6 = TreeLeaf(6.0)

val t3456 = TreeNode(List(
  TreeNode(List(t3,t4)),
  TreeNode(List(t5,t6))))

val t = TreeNode(List(t3456,t3456,t6,t3))

t.leafData()

