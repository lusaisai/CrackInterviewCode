package CrackInterviewCode;

import java.util.Arrays;

/**
 * This Class contains questions and solutions regarding arrays and strings
 * Created by lusaisai on 14-2-13.
 */
public class ArraysAndStrings {

    /**
     * <h3>
     *     Implement an algorithm to determine if a string has all unique characters.
     *     What if you can not use additional data structures?
     * </h3>
     *
     * <p>
     *     One way is to iterate through the string, for each character, compare it with all previous characters.
     *     It requires 1+2+3+ ... + n times' comparison, that results in O(n2) running time.
     *     Another way is to sort the string, the duplicate characters will always be adjacent.
     *     It requires O(nlogn) for sort, plus O(n) times' comparison, still O(nlogn).
     *     Both works but with a bad performance. A better way is to use a hash, if we're allowed.
     *     Then we can iterate through the string, check the character in the hash to see if it already exists, store it if not.
     *     Hash provides O(1) time storing and searching, so this provides o(n) running time.
     *     But the question says we cannot use additional data structures. How do we do it? Say if the string is
     *     not unicode, just ascii characters, there are only 256 possible values, we can use an array of size 256,
     *     making it work like a hash, the character stores in the position of its ascii value. This method is the implementation in this case.
     *     An even better performed way is to use bit, every bit can act as a flag, from byte to bit, it saves the space by 8 times.
     * </p>
     * @param s The input string to be detected
     * @return if it has duplicate characters or not
     */
    public static boolean hasDuplicate(String s) {
        boolean[] bucket = new boolean[256];
        Arrays.fill(bucket, false);

        char[] input = s.toCharArray();

        for( char c : input ) {
            if ( bucket[(int)c] ) {
                return true;
            } else {
                bucket[(int)c] = true;
            }
        }

        return false;
    }

}
