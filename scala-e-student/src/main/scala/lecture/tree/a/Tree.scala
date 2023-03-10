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

// Implement the basic ADT for Int only

package lecture.tree.a

abstract class Tree

case class TreeNode(branches:List[Tree]) extends Tree

case class TreeLeaf(data:Int) extends Tree

object Tree {

  def main(argv:Array[String]):Unit = {
    val l3 = TreeLeaf(3)
    val l4 = TreeLeaf(4)
    val l5 = TreeLeaf(5)
    val l6 = TreeLeaf(6)

    val t3456 = TreeNode(List(
      TreeNode(List(l3,l4)),
      TreeNode(List(l5,l6))))

    val t = TreeNode(List(t3456,t3456,l6,l3))

    println(t)
  }
}
