package CrackInterviewCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This Class contains questions and solutions regarding bit manipulation.
 */
public class BitManipulation {

    /**
     * <h5>
     *     You are given two 32-bit numbers, N and M, and two bit positions, i and j Write a method to set all bits
     *     between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j) <br/>
     *     EXAMPLE:<br/>
     *     Input: N = 10010001010, M = 10101, i = 2, j = 6<br/>
     *     Output: N = 10011010110<br/>
     * </h5>
     * <p>
     *     One thing we should be aware of is that the numbers are 32-bit, so there are zeros padding in the left(high bits).
     *     So and operation might lose data because of zeroes. <br/>
     *     First, we shift small number left by i, ready to be 'OR' with big number, this requires the corresponding bits in
     *     big number all to be zeroes, which requires creating a mask, see details below in implementation.
     * </p>
     * @param big the bigger number
     * @param small the smaller number
     * @param i the lower position
     * @param j the higher position
     * @return the bigger number after set bit operation
     */
    public static int setBit( int big, int small, int i, int j ) {
        // 0b10101 ==> 0b1010100
        small <<= i;

        // 0b11111...11, totally 32 bits
        int mask = ~0;

        // 0b11111...10010000000
        mask = mask - ( (1<<j) - 1 );

        // 0b11111...10010000011
        mask = mask | ( (1<<i) - 1 );

        return big & mask | small;
    }

    /**
     * <h5>
     *     Print the binary representation of a decimal number
     * </h5>
     * <p>
     *     Let's talk about the integer part. One thing we should know is shift an integer right is the same as divide it
     *     by two and the reminder is what being shifted out. <br/>
     *     As per the fraction part, shift right is the same as multiply by two and the integer part is what shifted out
     *     , we keep doing this until the fraction part is 0 or it is endless. In this implementation, we will limit the loop
     *     into 32 times.
     * </p>
     * @param input the decimal number
     */
    public static void printBinaryRepr( double input ) {
        int integer = Integer.valueOf( String.valueOf(input).split("\\.")[0] );
        double fraction = Double.valueOf( "0." + String.valueOf(input).split("\\.")[1] );

        Stack<Integer> first = new Stack<Integer>();
        while (integer > 0) {
            first.push(integer % 2);
            integer >>= 1;
        }

        Queue<Integer> second = new LinkedList<Integer>();
        for ( int i=0; i < 32; i++ ) {
            fraction *= 2;
            if ( fraction == 1 ) {
                second.add(1);
                break;
            } else if ( fraction > 1 ) {
                second.add(1);
                fraction -= 1;
            } else {
                second.add(0);
            }
        }

        while ( !first.empty() ) {
            System.out.print(first.pop());
        }
        System.out.print(".");
        while ( !second.isEmpty() ) {
            System.out.print(second.poll());
        }
        System.out.println();

    }

}
