package CrackInterviewCode;


import junit.framework.TestCase;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListTest extends TestCase {
    public LinkedListTest(String name) {
        super(name);
    }

    public void testNth() throws Exception {
        LinkedList<Integer> a = new LinkedList<Integer>( Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8) );
        assertEquals( 6, (int)LinkedLists.nth( a, 3 ) );

    }

    public void testDeleteMiddle() throws Exception {
        LinkedList<Integer> a = new LinkedList<Integer>( Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9) );
        LinkedLists.deleteMiddle(a);
        assertTrue(new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9)).equals(a));
    }

    public void testAddNumbers() throws Exception {
        LinkedList<Integer> a = new LinkedList<Integer>( Arrays.asList( 1, 2, 3, 4, 5) );
        LinkedList<Integer> b = new LinkedList<Integer>( Arrays.asList( 6, 7, 8, 9) );
        LinkedList<Integer> sum = LinkedLists.addNumbers(a,b);
        assertTrue(new LinkedList<Integer>(Arrays.asList(7,9,1,4,6)).equals(sum));

    }

    public void testFindLoopStart() throws Exception {
        LinkedLists.MyLinkedList l = new LinkedLists.MyLinkedList(new LinkedLists.MyLinkedList.Node(1));
        LinkedLists.MyLinkedList.Node start = new LinkedLists.MyLinkedList.Node(3);
        l.add(new LinkedLists.MyLinkedList.Node(2));
        l.add(start);
        l.add(new LinkedLists.MyLinkedList.Node(4));
        l.add(new LinkedLists.MyLinkedList.Node(5));
        l.add(new LinkedLists.MyLinkedList.Node(6));
        l.add(new LinkedLists.MyLinkedList.Node(7));
        l.add(new LinkedLists.MyLinkedList.Node(8));
        l.add(start);

        assertEquals( 3, LinkedLists.findLoopStart(l) );

    }
}
