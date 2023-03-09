class Tree

case class TreeNode(branches:List[Tree]) extends Tree {
  override def toString():String = {
    branches.map(_.toString).mkString("[",", ","]")
  }
}

case class TreeLeaf(data:Int) extends Tree {
  override def toString():String = data.toString
}

TreeLeaf(3)
TreeLeaf(4)
TreeNode(List(TreeLeaf(3),TreeLeaf(4)))
