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

    public static class MyTree{
        MyNode root;

        public MyTree(Integer rootData) {
            this.root = new MyNode(rootData);
        }

        public MyTree() {
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

        public MyTree orderedArrayIntoBST( int[] elements, MyNode root ) {
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
