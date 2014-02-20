package CrackInterviewCode;

import junit.framework.TestCase;

public class TreesAndGraphsTest extends TestCase {
    public TreesAndGraphsTest(String name) {
        super(name);
    }

    public void testIsBalanced() throws Exception {
        TreesAndGraphs.MyBinaryTree t = new TreesAndGraphs.MyBinaryTree(0);
        TreesAndGraphs.MyNode n1 = t.addLeft(t.root, 1);
        t.addLeft(n1, 3);
        t.addRight(n1, 4);
        assertFalse(t.isBalanced());
        t.bfsPrint();
        t.addRight(t.root, 2);
        assertTrue( t.isBalanced() );
        t.bfsPrint();
    }

    public void testBfs() throws Exception {
        TreesAndGraphs.MyVertex a = new TreesAndGraphs.MyVertex(1);
        TreesAndGraphs.MyVertex b = new TreesAndGraphs.MyVertex(2);
        TreesAndGraphs.MyVertex c = new TreesAndGraphs.MyVertex(3);
        TreesAndGraphs.MyVertex d = new TreesAndGraphs.MyVertex(4);
        TreesAndGraphs.MyVertex e = new TreesAndGraphs.MyVertex(5);
        a.link(b);
        a.link(c);
        b.link(c);
        c.link(a);
        a.link(d);
        d.link(e);

        TreesAndGraphs.MyGraph g = new TreesAndGraphs.MyGraph();
        g.add(a);
        g.add(b);
        g.add(c);
        g.add(d);
        g.add(e);

        assertTrue( g.bfs(c,e) );
        System.out.println();
        assertFalse(g.bfs(d, a));
        System.out.println();

    }

    public void testOrderedArrayIntoBST() throws Exception {
        TreesAndGraphs.MyBinaryTree t = new TreesAndGraphs.MyBinaryTree();
        t.orderedArrayIntoBST(new int[]{1, 2, 3, 4, 5}, t.root);
        t.bfsPrint();
    }

    public void testInOrderNext() throws Exception {
        TreesAndGraphs.MyBinaryTree t = new TreesAndGraphs.MyBinaryTree();
        t.orderedArrayIntoBST(new int[]{1,2,3,4,5,6,7,8,9}, t.root);
        t.bfsPrint();
        assertEquals( 8, (int)t.inOrderNext( t.root.right ).data );
        assertEquals( 5, (int)t.inOrderNext( t.root.left.right.right ).data );

    }

    public void testFirstCommonAncestor() throws Exception {
        TreesAndGraphs.MyBinaryTree t = new TreesAndGraphs.MyBinaryTree();
        t.orderedArrayIntoBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, t.root);
        t.bfsPrint();
        assertEquals(5, (int) TreesAndGraphs.MyBinaryTree.firstCommonAncestor(t.root.left.right, t.root.right.right.right).data);
        assertEquals( 2, (int) TreesAndGraphs.MyBinaryTree.firstCommonAncestor(t.root.left.left, t.root.left.right.right).data);
    }
}
