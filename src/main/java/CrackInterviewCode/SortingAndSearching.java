package CrackInterviewCode;

public class SortingAndSearching {
    /**
     * <h5>
     *     You are given two sorted arrays, A and B, and A has a large enough buffer at the end to hold B Write a method
     *     to merge B into A in sorted order.
     * </h5>
     * <p>
     *     This is basically the merge part of merge sort. We just need to do in place rather than creating a new array.
     *     We starting from end to begin, fill data from position a.length - 1 in array a.
     * </p>
     * @param a the bigger array
     * @param b the smaller array
     * @param aEnd the original end position inside array.
     */
    public static void mergeArray( int[] a, int[] b, int aEnd ) {
        int fillIn = a.length - 1;
        int bEnd = b.length - 1;

        while ( bEnd >= 0 ) {
            if ( b[bEnd] >= a[aEnd] ) {
                a[fillIn] = b[bEnd];
                bEnd--;
            } else {
                a[fillIn] = a[aEnd];
                aEnd--;
            }
            fillIn--;
        }
    }

    /**
     * <h5>
     *     Given  a sorted  array  of  n  integers  that  has  been  rotated  an  unknown  number  of times,
     *     give anO(log n) algorithm that finds an element in the array You may assume that the array was originally
     *     sorted in increasing order.
     * </h5>
     * <p>
     *     We can still do as binary search, key point is when we choose a middle index, at least one side is a sorted array.
     * </p>
     * @param input the rotated sorted array
     * @param target to integer to search
     * @param startIndex the index start with
     * @param endIndex the index end
     * @return the index, -1 if not found
     */
    public static int binarySearchRotated(int[] input, int target, int startIndex, int endIndex) {
        if ( startIndex > endIndex ) return -1;
        int middleIndex = (startIndex+endIndex)/2;
        if ( input[middleIndex] == target ) return middleIndex;
        if ( input[middleIndex] >= input[startIndex] ) {// left side is sorted
            if ( target >= input[startIndex] && target <= input[middleIndex] ) {
                return binarySearchRotated(input, target, startIndex, middleIndex-1);
            } else {
                return binarySearchRotated(input, target, middleIndex+1, endIndex);
            }
        } else {//right side is sorted
            if ( target >= input[middleIndex] && target <= input[endIndex] ) {
                return binarySearchRotated(input, target, middleIndex+1, endIndex);
            } {
                return binarySearchRotated(input, target, startIndex, middleIndex-1);
            }
        }

    }
}
