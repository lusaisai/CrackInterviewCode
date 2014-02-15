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

    /**
     * <h5>
     *     Write a method to replace all spaces in a string with '%20'.
     * </h5>
     * <p>
     *     Methods can be mutable or immutable, so, do we return a new char array or do it in place, do it
     *     in place can save space. However, it is unlikely we can do it in place because array is fixed size while we
     *     need more space for new chars. So, we'll return a new char array.<br/>
     *     And we will not use the String replace method or StringBuilder, we're trying to avoid higher level structures.<br/>
     *     We're gonna to in this way, first, scan the array once to find the number spaces, then we'll know
     *     what the size will be for the new array, second, we scan it again, when a space shows up, we put '%20' in the new array.
     * </p>
     * @param input the input character array with spaces
     * @return a new character array with spaces replaced by '%20'
     */
    public static char[] spaceReplace(char[] input) {
        int count = 0;
        for( char c : input ) {
            if ( c == ' ' ) count++;
        }

        char[] output = new char[input.length + 2*count];
        int i = 0;
        for( char c: input ) {
            if ( c == ' ' ) {
                output[i++] = '%';
                output[i++] = '2';
                output[i++] = '0';
            } else {
                output[i++] = c;
            }
        }

        return output;
    }

    /**
     * <h5>
     *     Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
     *     write a method to rotate the image by 90 degrees. Can you do this in place?
     * </h5>
     * <p>
     *     The question is asking about doing it in place. Forget that for now, what if we are allowed to
     *     create new rotated matrix? There's a interesting way of doing this, reading the two dimensional array
     *     in a different direction has the same effect as rotating an image. For example, if we want to rotate
     *     the image 90 degrees right. It is same as read the matrix column by column, from bottom up.<br/>
     *     We can do it in this way first, and then we can try to do it in place.
     * </p>
     * @param input the two dimensional array
     * @param n the dimension of the matrix
     * @return a new rotated matrix
     */
    public static int[][] rotateImage(int[][] input, int n) {
        int[][] output = new int[n][n];
        int x = 0;
        int y = 0;

        for ( int c = 0; c < n; c++ ) {
            for ( int r = n - 1; r >= 0; r-- ) {
                output[x][y] = input[r][c];
                y++;
            }
            x++;
            y = 0;
        }

        return output;
    }

    /**
     * Alternative way to rotate the image in place.<br/>
     * We need to rotate it layer by layer and swap the integers on one edge with another one by one
     * @param input the two dimensional array
     * @param n the dimension of the matrix
     */
    public static void rotateImage2(int[][] input, int n) {
        for( int layer = 0; layer < n/2; layer++ ) {
            for( int offset = 0; offset < n - 2*layer - 1; offset++ ) {
                int tmp = input[layer][layer+offset]; // the up edge
                input[layer][layer+offset] = input[n - 1 - layer - offset][layer];
                input[n - 1 - layer - offset][layer] = input[n - 1 - layer][n - 1 - layer - offset];
                input[n - 1 - layer][n - 1 - layer - offset] = input[layer+offset][n - 1 - layer];
                input[layer+offset][n - 1 - layer] = tmp;
            }
        }
    }


    /**
     * <h5>
     *     Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0
     * </h5>
     * <p>
     *     We need to go through the whole matrix once to know which rows and columns need to be set to zero.
     *     We can use two arrays to store this information and next go through the matrix again to set the zeroes
     *     based on the two arrays.
     * </p>
     * @param input the m times n matrix
     */
    public static void setZeroes(int[][] input) {
        boolean[] row = new boolean[input.length];
        boolean[] column = new boolean[input[0].length];
        Arrays.fill(row,false);
        Arrays.fill(column,false);

        for( int i=0; i < input.length; i++ ) {
            for( int j = 0; j < input[0].length; j++ ) {
                if ( input[i][j] == 0 ) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for( int i=0; i < input.length; i++ ) {
            for( int j = 0; j < input[0].length; j++ ) {
                if (row[i] || column[j]) {
                    input[i][j] = 0;
                }
            }
        }
    }

    /**
     * <h5>
     *     Assume you have a method isSubstring which checks if one word is a substring of another
     *     Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
     *     (i e , 'waterbottle' is a rotation of 'erbottlewat')
     * </h5>
     * <p>
     *     They're rotations given that:
     *     s1 and s2 has the same length and s1 is a substring of s2 + s2
     * </p>
     * @param s1 one string
     * @param s2 the other string
     * @return if they're rotations
     */
    public static boolean isRotation(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);

    }

}
