package CrackInterviewCode;


import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Stack;

public class StacksAndQueuesTest extends TestCase {
    public StacksAndQueuesTest(String name) {
        super(name);
    }


    public void testThreeStacks() throws Exception {
        StacksAndQueues.ThreeStacks s = new StacksAndQueues.ThreeStacks(9);
        s.push(0,1);
        s.push(1,2);
        s.push(1,3);
        s.push(1,4);
        s.push(1,5);
        s.push(1,6);
        s.push(2,7);
        s.printMe();
        s.push(2,8);
        s.printMe();
        System.out.println("Pop out " + s.pop(1) + " for stack " + 1);
        s.printMe();
        System.out.println("Pop out " + s.pop(1) + " for stack " + 1);
        s.printMe();
        s.push(0, 9);
        s.push(2, 10);
        s.printMe();
        System.out.println("peek at " + s.peek(2) + " for stack " + 2);
        s.printMe();
        System.out.println("===========================================");
    }

    public void testStackWithMin() throws Exception {
        StacksAndQueues.StackWithMin s = new StacksAndQueues.StackWithMin();
        s.push(5);
        s.push(4);
        s.push(6);
        s.push(3);
        s.push(7);
        assertEquals( 3, s.min() );
        s.printMe();
        s.pop();
        s.printMe();
        s.pop();
        s.printMe();
        System.out.println("===========================================");
    }

    public void testTowersOfHanoi() throws Exception {
        StacksAndQueues.TowersOfHanoi t = new StacksAndQueues.TowersOfHanoi(5);
        t.play();
        System.out.println("===========================================");
    }

    public void testMyQueue() throws Exception {
        StacksAndQueues.MyQueue mq = new StacksAndQueues.MyQueue();
        mq.add(1);
        mq.add(2);
        assertEquals(1, (int) mq.poll());
        mq.add(3);
        mq.add(4);
        mq.add(5);
        assertEquals(2, (int) mq.poll());
        assertEquals(3, (int) mq.poll());
        assertEquals( 4, (int)mq.poll() );

    }

    public void testSortStack() throws Exception {
        Stack<Integer> s = new Stack<Integer>();
        s.push(5);
        s.push(3);
        s.push(2);
        s.push(6);
        s.push(1);
        for ( int i :  StacksAndQueues.sortStack(s) ) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("===========================================");
    }
}
