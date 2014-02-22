package CrackInterviewCode;

import junit.framework.TestCase;

public class RecursionTest extends TestCase {
    public RecursionTest(String name) {
        super(name);
    }

    public void testFibonacci() throws Exception {
        assertEquals( 55, Recursion.fibonacci(10) );
    }

    public void testRobotMove() throws Exception {
        assertEquals( 2, Recursion.robotMove(0,0,1,1) );
        assertEquals( 6, Recursion.robotMove(0,0,2,2) );
    }

    public void testPrintPar() throws Exception {
        for( String s : Recursion.getPar( 3, 3 ) ) {
            System.out.println(s);
        }
    }
}
