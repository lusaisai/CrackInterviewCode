package CrackInterviewCode;

import junit.framework.TestCase;

import java.util.Arrays;

public class SortingAndSearchingTest extends TestCase {
    public SortingAndSearchingTest(String name) {
        super(name);
    }

    public void testMergeArray() throws Exception {
        int[] a = {1,10,20,0,0};
        int[] b = {5,15};
        int[] expected = {1,5,10,15,20};
        SortingAndSearching.mergeArray(a,b,2);
        assertTrue(Arrays.equals(a,expected));
    }

    public void testBinarySearchRotated() throws Exception {
        int[] in = {10,11,12,13,14,15,1,2,3,4,5,6,7,8,9};
        assertEquals( 8, SortingAndSearching.binarySearchRotated(in,3,0,14) );

    }
}
