// Copyright (c) 2020,21 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

// generalize from Tree of Int to Tree of any type

package lecture.tree.b

abstract class Tree[A]

case class TreeNode[A](branches:List[Tree[A]]) extends Tree[A]

case class TreeLeaf[A](data:A) extends Tree[A]

object Tree {
  
  def main(argv:Array[String]):Unit = {
    val l3 = TreeLeaf(3.0)
    val l4 = TreeLeaf(4.0)
    val l5 = TreeLeaf(5.0)
    val l6 = TreeLeaf(6.0)

    val t3456 = TreeNode(List(
      TreeNode(List(l3,l4)),
      TreeNode(List(l5,l6))))

    val t1 = TreeNode(List(t3456,t3456,l6,l3))
    println(t1)

    val t2 = TreeNode(List(TreeLeaf(1),TreeLeaf(2),TreeLeaf(3)))
    println(t2)

    val t3 = TreeNode(List(TreeLeaf("one"),TreeLeaf("two"),TreeLeaf("three")))
    println(t3)
  }
}
