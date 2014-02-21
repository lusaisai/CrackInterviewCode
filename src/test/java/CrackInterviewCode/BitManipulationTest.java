package CrackInterviewCode;


import junit.framework.TestCase;

public class BitManipulationTest extends TestCase {
    public BitManipulationTest(String name) {
        super(name);
    }

    public void testSetBit() throws Exception {
        assertEquals( 0b10011010110, BitManipulation.setBit( 0b10010001010, 0b10101, 2, 6 ) );
    }

    public void testPrintBinaryRepr() throws Exception {
        BitManipulation.printBinaryRepr(123.456);
        BitManipulation.printBinaryRepr(789.5);
    }
}
