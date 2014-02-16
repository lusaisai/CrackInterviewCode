package CrackInterviewCode;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This Class contains questions and solutions regarding linked lists.
 */
public class LinkedLists {
    /**
     * <h5>
     *     Implement an algorithm to find the nth to last element of a singly linked list.
     * </h5>
     * <p>
     *     I would assume a better implementation of linked list has an instance variable which tracks the
     *     number of elements in the list so that it can provide O(1) access when asked for size. In this case,
     *     the question is to simply get the size, say s, and go to the position of s - n. It costs O(s-n). <br/>
     *     If the list does not have the size, we need to iterate the list once to get the size first,
     *     then do the same thing. It costs O(s+s-n).<br/>
     *     The book suggests to use two pointers, one starts at position 0, another starts at position n - 1. They move
     *     forward together, once the second one reaches the end, the first one gets the element we want, it still costs
     *     O(s+s-n).<br/>
     *     To simplify the code, We'll be using the java standard implementation of linked list, using the size() method to get the size,
     *     and use the get method to do the iteration to a specified position.
     * </p>
     * @param input the linked list
     * @param n nth to last element
     * @param <E> element type
     * @return the nth to last element of the linked list
     */
    public static <E> E nth( LinkedList<E> input, int n ) {
        return input.get( input.size() - n );
    }

    /**
     * <h5>
     *     Implement an algorithm to delete a node in the middle of a single linked list, given only access to that node
     *     <br/>EXAMPLE<br/>
     *     Input: the node 'c' from the linked list a->b->c->d->e<br/>
     *     Result: nothing is returned, but the new linked list looks like a->b->d->e<br/>
     * </h5>
     * <p>
     *     The tricky part is having access to that node only. Normally, to delete a node, we need to access the previous
     *     node, modify its next pointer to point to the node the to-be-deleted one is pointing.<br/>
     *     Now, it's a singly linked list, we can only access the next one. we can copy the data from the next one to this
     *     node, and delete the next node, it has the same effect. Again, delete the next node means to change the current node
     *     to point what the next node is pointing at. In the code, we'll simply use java's list iterator remove method.
     * </p>
     * @param input the linked list
     * @param <E> element type
     */
    public static <E> void deleteMiddle( LinkedList<E> input ) {
        ListIterator<E> a = input.listIterator( input.size()/2 );
        a.next();
        E data = a.next();

        ListIterator<E> b = input.listIterator( input.size()/2 );
        b.next();
        b.set(data);
        b.next();
        b.remove();
    }

    /**
     * <h5>
     *     You have two numbers represented by a linked list, where each node contains a single digit The digits
     *     are stored in reverse order, such that the 1's digit is at the head of the list Write a function
     *     that adds the two numbers and returns the sum as a linked list
     *     EXAMPLE
     *     Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
     *     Output: 8 -> 0 -> 8
     * </h5>
     * <p>
     *     We can simply do it in the same way how we do the math manually, adding the numbers one by one and remember to
     *     carry 1 to next digit if the sum is greater than or equal to 10.
     * </p>
     * @param a a linked list of number
     * @param b another linked list of number
     * @return sum in the form of linked list of number
     */
    public static LinkedList<Integer> addNumbers( LinkedList<Integer> a, LinkedList<Integer> b ) {
        LinkedList<Integer> sum = new LinkedList<Integer>();
        int carry = 0;
        ListIterator<Integer> al = a.listIterator();
        ListIterator<Integer> bl = b.listIterator();

        while ( al.hasNext() || bl.hasNext() ) {
            int x = 0;
            int y = 0;
            int s;
            if( al.hasNext() ) x = al.next();
            if( bl.hasNext() ) y = bl.next();
            s = x + y + carry;
            if ( s >= 10 ) {
                carry = 1;
                s -= 10;
            }
            sum.add(s);
        }

        return sum;
    }

    /**
     * <h5>
     *     Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.<br/>
     *     DEFINITION<br/>
     *     Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node,
     *     so as to make a loop in the linked list<br/>
     *     EXAMPLE<br/>
     *     Input: A -> B -> C -> D -> E -> C [the same C as earlier]<br/>
     *     Output: C<br/>
     * </h5>
     * <p>
     *     I was thinking of using a hash, when going through the list, store them in the hash, once find an element already
     *     in the hash, it is a loop and it is also the start of the loop.<br/>
     *     The book provides a clever solution. We can use two pointers, one moves 1 step ahead, another moves two step ahead
     *     they will finally meet if there's a loop. And start point is k steps more from the meeting point, k is the same as
     *     the steps before entering the loop. I would suggest some google on the web for elaboration. <br/>
     *     I created a simple linked list for help here.
     * </p>
     * @param input a circular linked list
     * @return the start point
     */
    public static int findLoopStart( MyLinkedList input ) {
        MyLinkedList.Node a = input.head;
        MyLinkedList.Node b = input.head;
        while (true) {
            a = a.next;
            b = b.next.next;
            if ( a == b  ) break;
        }

        a = input.head;
        while (true) {
            a = a.next;
            b = b.next;
            if ( a == b  ) break;
        }

        return a.value;
    }

    /**
     * A simple linked list
     */
    public static class MyLinkedList {
        /**
         * The node of the list
         */
        public static class Node{
            int value;
            Node next;

            public Node(int value) {
                this.value = value;
                this.next = null;
            }

        }
        Node head;
        Node tail;

        public MyLinkedList(Node n) {
            this.head = n;
            this.tail = n;
        }

        public void add(Node n) {
            this.tail.next = n;
            this.tail = n;
        }

    }

}
