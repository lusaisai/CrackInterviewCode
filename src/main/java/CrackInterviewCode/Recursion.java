package CrackInterviewCode;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class contains questions and solutions regarding recursion.
 */
public class Recursion {
    /**
     * <h5>
     *     Write a method to generate the nth Fibonacci number.
     * </h5>
     * <p>
     *     We can do recursion or iteration. Recursion is more easy to understand, after all it can be easily defined as
     *     f(n) = f(n-1) + f(n-2). <br/>
     *     But the iteration way has a better performance, the recursion needs to enter in function call stack, twice, but
     *     we'll implement the recursion way here.
     * </p>
     * @param index the position of the fibonacci series
     * @return the value at the position
     */
    public static int fibonacci(int index) {
        if ( index < 0 ) throw new IndexOutOfBoundsException();
        if ( index == 0 ) return 0;
        if ( index == 1 ) return 1;
        return fibonacci(index-1) + fibonacci(index-2);
    }

    /**
     * <h5>
     *     Imagine a robot sitting on the upper left hand corner of an NxN grid The robot can only move in two directions:
     *     right and down How many possible paths are there for the robot to reach to position(x,y)?
     * </h5>
     * <p>
     *     The number of path equals to the path at the its right position(if it can go right) plus the path at the its
     *     down position(if it can go down)
     * </p>
     * @param a x coordinate of target position
     * @param b y coordinate of target position
     * @param x x coordinate of target position
     * @param y y coordinate of target position
     * @return number of paths
     */
    public static int robotMove( int a, int b, int x, int y ) {
        if ( a == x && b == y ) return 1;
        if ( a > x || b > y ) throw new IllegalStateException();
        if ( a == x ) return robotMove( a, b+1, x, y );
        if ( b == y ) return robotMove( a+1, b, x, y );
        return robotMove( a, b+1, x, y ) + robotMove( a+1, b, x, y );
    }

    /**
     * <h5>
     *     Implement an algorithm to print all valid (e g , properly opened and closed) combinations of n-pairs of parentheses.
     * </h5>
     * <p>
     *     Still, define it as recursion, just remember that left parentheses must be insert first when there are same number of
     *     left and right parentheses.
     * </p>
     * @param left number of left parentheses left
     * @param right number of right parentheses left
     * @return list of combinations
     */
    public static List<String> getPar(int left, int right) {
        List<String> possibilities = new ArrayList<>();
        if ( left == 0 && right ==0 ) {
            possibilities.add("");
            return possibilities;
        }
        if ( left == right ) {
            for( String s : getPar( left - 1, right ) ) {
                possibilities.add('(' + s);
            }
        } else if ( left == 0 ) {
            for( String s : getPar( left, right - 1 ) ) {
                possibilities.add(')' + s);
            }
        } else {
            for( String s : getPar( left, right - 1 ) ) {
                possibilities.add(')' + s);
            }
            for( String s : getPar( left - 1, right ) ) {
                possibilities.add('(' + s);
            }
        }
        return possibilities;
    }





}
