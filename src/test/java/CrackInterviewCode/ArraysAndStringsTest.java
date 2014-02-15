package CrackInterviewCode;

import junit.framework.TestCase;

import java.util.Arrays;


public class ArraysAndStringsTest extends TestCase {

    public ArraysAndStringsTest(String name) {
        super(name);
    }

    public void testHasDuplicate() throws Exception {
        assertEquals( true, ArraysAndStrings.hasDuplicate("1234567890-=][poiuyhgfrdewqav1") );
        assertEquals( false, ArraysAndStrings.hasDuplicate("1234567890-=][poiuyhgfrdewqav") );

        assertEquals( true, ArraysAndStrings.hasDuplicate2("1234567890-=][poiuyhgfrdewqav1") );
        assertEquals( false, ArraysAndStrings.hasDuplicate2("1234567890-=][poiuyhgfrdewqav") );
    }

    public void testReverseCString() throws Exception {
        char[] origin = { 'a','r','r','a','y','\0','d','u','m','m','y' };
        char[] back = ArraysAndStrings.reverseCString(origin);
        char[] expect = { 'y','a','r','r','a','\0' };
        assertTrue(Arrays.equals(expect,back));

        char[] reverseInPlace = { 'y','a','r','r','a','\0','d','u','m','m','y' };
        ArraysAndStrings.reverseCString2(origin);
        assertTrue(Arrays.equals(reverseInPlace, origin));

    }

    public void testIsAnagram() throws Exception {
        assertTrue( ArraysAndStrings.isAnagram( "reversed", "reserved" ) );
        assertTrue( ArraysAndStrings.isAnagram( "triangle", "integral" ) );
        assertFalse( ArraysAndStrings.isAnagram( "cccddd", "ccceee" ) );

    }

    public void testSpaceReplace() throws Exception {
        char[] origin = { 'a','r',' ','a','y'};
        char[] back = ArraysAndStrings.spaceReplace(origin);
        char[] expect = { 'a','r','%','2','0','a','y'};
        assertTrue(Arrays.equals(expect,back));
    }

    public void testRotateImage() throws Exception {
        int[][] origin = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int[][] back = ArraysAndStrings.rotateImage(origin, 3);
        printMatrix(back);

        origin = new int[][]{
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5}
        };
        printMatrix(origin);
        ArraysAndStrings.rotateImage2(origin,5);
        printMatrix(origin);
    }

    public void testSetZeroes() throws Exception {
        int[][] origin = new int[][]{
                {1,2,3,4,5},
                {1,2,3,0,5},
                {1,2,0,4,5},
                {1,0,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5}
        };
        ArraysAndStrings.setZeroes(origin);
        printMatrix(origin);

    }

    public void testIsRotation() throws Exception {
        String a = "waterbottle";
        String b = "erbottlewat";
        String c = "waterbottla";
        assertTrue( ArraysAndStrings.isRotation(a,b) );
        assertFalse( ArraysAndStrings.isRotation(a,c) );

    }

    public static void printMatrix( int[][] input ) {
        for (int[] anInput : input) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(anInput[j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("<----------------->");
    }
}
