package CrackInterviewCode;

import java.util.Arrays;

/**
 * This Class contains questions and solutions regarding arrays and strings.
 */
public class ArraysAndStrings {

    /**
     * <h5>
     *     Implement an algorithm to determine if a string has all unique characters.
     *     What if you can not use additional data structures?
     * </h5>
     *
     * <p>
     *     One way is to iterate through the string, for each character, compare it with all previous characters.
     *     It requires 1+2+3+ ... + n times' comparison, that results in O(n2) running time.<br/>
     *     Another way is to sort the string, the duplicate characters will always be adjacent.
     *     It requires O(nlogn) for sort, plus O(n) times' comparison, still O(nlogn).<br/>
     *     Both works but with a bad performance. A better way is to use a hash, if we're allowed.
     *     Then we can iterate through the string, check the character in the hash to see if it already exists, store it if not.
     *     Hash provides O(1) time storing and searching, so this provides o(n) running time.<br/>
     *     But the question says we cannot use additional data structures. How do we do it? Say if the string is
     *     not unicode, just ascii characters, there are only 256 possible values, we can use an array of size 256,
     *     making it work like a hash, the character stores in the position of its ascii value. This method is the implementation in this case.<br/>
     *     An even better performed way is to use bit, every bit can act as a flag, from byte to bit, it saves the space by 8 times. see method hasDuplicate2.
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

    /**
     * An alternative way for detecting duplicate characters using bit to save space.
     * @param s The input string to be detected
     * @return if it has duplicate characters or not
     */
    public static boolean hasDuplicate2(String s) {
        int[] bucket = new int[8]; // java int data type is 32-bit, 32 * 8 is 256, I will use the rightmost bit to store ascii 0, leftmost for ascii 255
        Arrays.fill(bucket, 0);

        char[] input = s.toCharArray();
        for( char c : input ) {
            int index = 7 - (int) c / 32; // the position in the bucket array
            int shift = (int) c % 32; // the number of positions in the 32 bit(in the direction from right to left)
            if ( ( bucket[index] & (1 << shift) ) > 0 ) {
                return  true;
            } else {
                bucket[index] |= (1 << shift);
            }
        }

        return false;
    }

    /**
     * <h5>
     *     Write code to reverse a C-Style String.
     * </h5>
     * <p>
     *     C language does not have a String data type, so called string is actually called character string,
     *     aka, a string of characters, represented as a character array, a null character indicates the end
     *     of the string. <br/>
     *     So this method will scan through a character array till a null character and return a character array
     *     which is reversed and ended with a null character. <br/>
     *     Doing it in-place can save space.
     * </p>
     * @param input the C-Style String represented in a character array
     * @return a reversed character array
     */
    public static char[] reverseCString(char[] input) {
        int index = 0;

        for( char c : input ) {
            if ( c == '\0' ) {
                break;
            } else {
                index++;
            }
        }

        char[] output = new char[index+1];
        output[index--] = '\0';
        for ( int i = 0; index >= 0; i++, index-- ) {
            output[i] = input[index];
        }

        return output;
    }

    /**
     * An alternative method to reverse the C-Style String in place.
     * @param input the C-Style String represented in a character array
     */
    public static void reverseCString2(char[] input) {
        int index = 0;

        for( char c : input ) {
            if ( c == '\0' ) {
                break;
            } else {
                index++;
            }
        }

        index--;
        int i = 0;
        while ( i < index ) {
            char tmp = input[i];
            input[i] = input[index];
            input[index] = tmp;
            i++;
            index--;
        }
    }

    /**
     * <h5>
     *     Write a method to decide if two strings are anagrams or not.
     * </h5>
     * <p>
     *     Firstly, we need to define what the anagrams is. The two strings should have the same length,
     *     have the same set of characters and the occurrences of the characters are the same. <br/>
     *     Similar to hasDuplicate, we assume they are ascii, we can create two int array to keep occurrence times
     *     of each character, then compare the two array, they'll be the same in case of anagrams.
     * </p>
     * @param a the string to compare
     * @param b the string to compare
     * @return if they are anagrams or not
     */
    public static boolean isAnagram( String a, String b ) {
        if (a.length() != b.length()) return false;

        int[] aCount = new int[256];
        Arrays.fill(aCount, 0);
        for ( char c : a.toCharArray() ) {
            aCount[(int)c]++;
        }

        int[] bCount = new int[256];
        Arrays.fill(bCount, 0);
        for ( char c : b.toCharArray() ) {
            bCount[(int)c]++;
        }

        return Arrays.equals(aCount,bCount);
    }


}
