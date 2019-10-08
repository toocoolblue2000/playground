package com.playground.recursion.dynamic.programming;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Find the longest increasing subsequence of a given array of integers, A.


In other words, find a subsequence of array in which the subsequence’s elements are in strictly increasing order, and in which the subsequence is as long as possible.
This subsequence is not necessarily contiguous, or unique.
In this case, we only care about the length of the longest increasing subsequence.

example:
Input 1:
    A = [1, 2, 1, 5]

Output 1:
    3

Explanation 1:
    The sequence : [1, 2, 5]

Input 2:
    A = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]

Output 2:
    6

Explanation 2:
    The sequence : [0, 2, 6, 9, 13, 15] or [0, 4, 6, 9, 11, 15] or [0, 4, 6, 9, 13, 15]

*/

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        assertEquals(getLongestIncreasingSubsequence(Arrays.asList(1, 2, 1, 5)), 3);
        assertEquals(getLongestIncreasingSubsequence(Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)), 6);
        assertEquals(getLongestIncreasingSubsequence(Arrays.asList(3, 10, 2, 1, 20)), 3);
        assertEquals(getLongestIncreasingSubsequence(Arrays.asList(3, 2)), 1);
        assertEquals(getLongestIncreasingSubsequence(Arrays.asList(50, 3, 10, 7, 40, 80)), 4);
        System.out.println("Complete");
    }

    private static int getLongestIncreasingSubsequence(List<Integer> ints) {

        max_ref = 1;

        // The function _lis() stores its result in max
        subseq(ints, ints.size());

        // returns max
        return max_ref;
    }

    static int max_ref;

    static int subseq(List<Integer> arr, int n) {
        // base case
        if (n == 1)
            return 1;

        // 'max_ending_here' is length of LIS ending with arr[n-1]
        int res, max_ending_here = 1;

        /* Recursively get all LIS ending with arr[0], arr[1] ...
           arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and
           max ending with arr[n-1] needs to be updated, then
           update it */
        for (int i = 1; i < n; i++) {
            res = subseq(arr, i);
            if (arr.get(i - 1) < arr.get(n - 1) && res + 1 > max_ending_here) {
                max_ending_here = res + 1;
            }
        }

        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (max_ref < max_ending_here)
            max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }
}
