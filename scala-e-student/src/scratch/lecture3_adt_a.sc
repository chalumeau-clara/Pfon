class Tree

case class TreeNode(branches:List[Tree]) extends Tree

case class TreeLeaf(data:Int) extends Tree

TreeLeaf(3)
TreeLeaf(4)
TreeNode(List(TreeLeaf(3),TreeLeaf(4)))
