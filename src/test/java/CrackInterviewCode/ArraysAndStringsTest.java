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
}
