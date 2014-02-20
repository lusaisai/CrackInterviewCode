package CrackInterviewCode;

import java.util.*;

/**
 * This Class contains questions and solutions regarding trees and graphs.
 */
public class TreesAndGraphs {
    public static class MyNode {
        Integer data;
        MyNode left = null;
        MyNode right = null;
        MyNode parent = null;

        public MyNode(Integer data) {
            this.data = data;
        }

    }

    public static class MyBinaryTree {
        MyNode root;

        public MyBinaryTree(Integer rootData) {
            this.root = new MyNode(rootData);
        }

        public MyBinaryTree() {
            this.root = new MyNode(null);
        }

        public MyNode addLeft(MyNode parentNode, Integer data) {
            if ( parentNode.left != null ) {
                return parentNode.left;
            } else {
                MyNode leftNode = new MyNode(data);
                parentNode.left = leftNode;
                leftNode.parent = parentNode;
                return leftNode;
            }
        }

        public MyNode addRight(MyNode parentNode, Integer data) {
            if ( parentNode.right != null ) {
                return parentNode.right;
            } else {
                MyNode rightNode = new MyNode(data);
                parentNode.right = rightNode;
                rightNode.parent = parentNode;
                return rightNode;
            }
        }

        /**
         * <h5>
         *     You have two very large binary trees: T1, with millions of nodes, and T2,
         *     with hundreds of nodes Create an algorithm to decide if T2 is a subtree of T1
         * </h5>
         * <p>
         *     We can do it in brute force, travel thru t1, if t2's root is found, comparing their subtree to see if they
         *     are the same, if not, keep travelling to the depth of ( depth(t1) ( - depth(t1) - depth(t2) ) ). <br/>
         *     Or we can turn t1 into a suffix trie, then search t2 in the trie like string searching, a substring is
         *     a prefix of some suffixes. But there could be a memory issue since here we have millions of nodes. But this
         *     is fun, I'll implement this method later.
         * </p>
         * @param t the subtree candidate
         * @return if it is a subtree
         */
        public boolean isSubtree( MyBinaryTree t ) {
            return false;
        }

        public static int depth( MyNode node ) {
            if ( node.parent == null ) return 0;
            return 1 + depth(node.parent);
        }

        /**
         * <h5>
         *     Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree
         *     Avoid storing additional nodes in a data structure NOTE: This is not necessarily a binary search tree
         * </h5>
         * <p>
         *     We can go down the binary tree, if the node is not the first common ancestor, the two nodes will be on the
         *     same subtree of the node, then we keep going down till the two nodes lives in two sides, that node will be
         *     the first common ancestor. The tricky part is when we go down, we need to search the two nodes to determine
         *     which subtree they live, a binary search tree is good at searching, if it is not, the search performs badly
         *     as it requires traversing the subtrees.<br/>
         *     Here we're going to do it in another straightforward way. We first get their depth, and keep moving one up till
         *     till they have the same depth. Then, move them up together, when they meet, it is the first ancestor.
         * </p>
         * @param a one node
         * @param b the other node
         * @return their first common ancestor
         */
        public static MyNode firstCommonAncestor( MyNode a, MyNode b ) {
            while ( depth(a) > depth(b) ) {
                a = a.parent;
            }
            while ( depth(b) > depth(a) ) {
                b = b.parent;
            }

            while ( a != null && b != null ) {
                if ( a == b ) return a;
                a = a.parent;
                b = b.parent;
            }

            return null;
        }

        /**
         * <h5>
         *     You are given a binary tree in which each node contains a value Design an algorithm to print all paths
         *     which sum up to that value Note that it can be any path in the tree it does not have to start at the root
         * </h5>
         * <p>
         *     We can expand the node, minus the values at next level till it is not positive, it is basically a
         *     graph breadth first traversal, see bfs method below, I don't have to implement again.
         * </p>
         * @param node
         */
        public static void pathSumToMe(MyNode node) {

        }

        /**
         * <h5>
         *     Write an algorithm to find the 'next' node (i e , in-order successor) of a given node in a binary search
         *     tree where each node has a link to its parent.
         * </h5>
         * <p5>
         *     There are different different cases, see below comments.
         * </p5>
         * @param node the input node
         * @return the successor
         */
        public MyNode inOrderNext(MyNode node) {
            MyNode next;
            // if it has the right subtree, take the left most of right subtree
            if ( node.right != null && node.right.data != null ) {
                next = node.right;
                while ( next.left != null && next.left.data != null ) {
                    next = next.left;
                }
                return next;
            }

            // if it is a left child, take the parent
            // if it is a right child, go up till we find a left child's parent, otherwise finished
            while ( node.parent != null ) {
                next = node.parent;
                if ( node.parent.left == node ) {
                    return node.parent;
                } else {
                    node = next;
                }
            }

            return null;
        }

        /**
         * <h5>
         *     Given a binary search tree, design an algorithm which creates a linked list of all the nodes at each depth
         * </h5>
         * <p>
         *     This is basically a breadth first search, here we print it level by level rather than returning a linked list.
         *     Print can help test.
         * </p>
         */
        public void bfsPrint() {
            ArrayList<MyNode> level = new ArrayList<MyNode>();
            level.add(root);
            int i = 0;
            while ( !level.isEmpty() ) {
                System.out.print("Level " + i + ": ");
                ArrayList<MyNode> nextlevel = new ArrayList<MyNode>();
                for( MyNode n : level ) {
                    System.out.print(n.data + "\t");
                    if (n.left != null) nextlevel.add(n.left);
                    if (n.right != null) nextlevel.add(n.right);
                }
                level = nextlevel;
                System.out.println();
                i++;
            }
        }

        public MyBinaryTree orderedArrayIntoBST( int[] elements, MyNode root ) {
            addArrayToNode(elements,root,0,elements.length-1);
            return this;
        }

        /**
         * <h5>
         *     Given a sorted (increasing order) array, write an algorithm to create a binary search tree with minimal height.
         * </h5>
         * <p>
         *     We'll do this recursively, we put the middle value fo the array as parent, give the left array to its left
         *     child, right array to its right child. Then recursively do the same to these two arrays.
         * </p>
         * @param elements input sorted array
         * @param node the node where to attach the array
         * @param start start index
         * @param end end index
         */
        private void addArrayToNode(int[] elements, MyNode node, int start, int end) {
            int middle = (start+end)/2;
            node.data = elements[middle];

            MyNode leftNode = new MyNode(null);
            MyNode rightNode = new MyNode(null);
            node.left = leftNode;
            leftNode.parent = node;
            node.right = rightNode;
            rightNode.parent = node;
            if ( start <= middle - 1 ) {
                addArrayToNode(elements, leftNode, start, middle -1);
            }
            if ( middle + 1 <= end ) {
                addArrayToNode(elements, rightNode, middle+1, end);
            }


        }

        /**
         * <h5>
         *     Implement a function to check if a tree is balanced.
         * </h5>
         * <p>
         *     A tree is balanced when the difference between min depth and max depth is no more than 1.
         * </p>
         * @return if it is balanced
         */
        public boolean isBalanced( ) {
            return (maxDepth(root) - minDepth(root)) <= 1;
        }

        private static int minDepth(MyNode node) {
            if ( node == null ) return 0;
            return 1 +  Math.min( minDepth(node.left), minDepth(node.right) );
        }
        private static int maxDepth(MyNode node) {
            if ( node == null ) return 0;
            return 1 +  Math.max(maxDepth(node.left), maxDepth(node.right));
        }
    }

    public static class MyVertex {
        int data;
        List<MyVertex> opposites;

        public MyVertex(int data) {
            this.data = data;
            opposites = new LinkedList<MyVertex>();
        }

        public boolean link(MyVertex v) {
            if (  opposites.contains(v) ) {
                return false;
            } else {
                opposites.add(v);
                return true;
            }
        }
    }

    public static class MyGraph {
        List<MyVertex> vertexes = new LinkedList<MyVertex>();

        public boolean add(MyVertex v) {
            if ( vertexes.contains(v) ) {
                return false;
            } else {
                vertexes.add(v);
                return true;
            }
        }

        /**
         * <h5>
         *     Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
         * </h5>
         * @return if there is a route or not
         */
        public boolean bfs( MyVertex source, MyVertex target ) {
            Queue<MyVertex> level = new LinkedList<MyVertex>();

            HashMap<MyVertex,MyVertex> discovered = new HashMap<MyVertex,MyVertex>();

            level.add(source);
            discovered.put(source, source);

            while ( ! level.isEmpty() ) {
                Queue<MyVertex> nextlevel = new LinkedList<MyVertex>();
                for( MyVertex next : level ) {
                    System.out.print( next.data );
                    if ( next == target ) return true;
                    System.out.print( "-->" );
                    for( MyVertex v : next.opposites ) {
                        if ( ! discovered.containsKey(v) ) {
                            discovered.put( v, next );
                            nextlevel.add(v);
                        }
                    }
                }
                level = nextlevel;
            }

            System.out.print( "?" );
            return false;
        }
    }






}
