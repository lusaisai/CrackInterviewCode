package CrackInterviewCode;

import junit.framework.TestCase;

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
}
