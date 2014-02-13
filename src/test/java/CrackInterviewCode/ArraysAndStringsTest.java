package CrackInterviewCode;

import junit.framework.TestCase;

/**
 * Created by lusaisai on 14-2-13.
 */
public class ArraysAndStringsTest extends TestCase {

    public ArraysAndStringsTest(String name) {
        super(name);
    }

    public void testHasDuplicate() throws Exception {
        assertEquals( true, ArraysAndStrings.hasDuplicate("1234567890-=][poiuyhgfrdewqav1") );
        assertEquals( false, ArraysAndStrings.hasDuplicate("1234567890-=][poiuyhgfrdewqav") );
        assertEquals( false, ArraysAndStrings.hasDuplicate("/*-789456123+") );
    }
}
