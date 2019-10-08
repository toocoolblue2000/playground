package com.playground.arrays;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Divide and Conquer
~~~~~~~~~~~~~~~~~~
Find the contiguous subarray within an array, A of length N which has the largest sum.

Input Format:

The first and the only argument contains an integer array, A.
Output Format:

Return an integer representing the maximum possible sum of the contiguous subarray.
Constraints:

1 <= N <= 1e6
-1000 <= A[i] <= 1000
For example:

Input 1:
    A = [1, 2, 3, 4, -10]

Output 1:
    10

Explanation 1:
    The subarray [1, 2, 3, 4] has the maximum possible sum of 10.

Input 2:
    A = [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Output 2:
    6

Explanation 2:
    The subarray [4,-1,2,1] has the maximum possible sum of 6.
 */
public class SumOfMaxContiguousSubArray {

    public static int maxSubArray(final List<Integer> A) {
        if(A == null || A.isEmpty()) return 0;

        int maxSum =  A.get(0);
        int runningSum = A.get(0);
        int len = A.size();
        int current;
        int sumWithCurrent;
        for(int i = 1; i < len; i++){
            current = A.get(i);
            sumWithCurrent = runningSum + current;

            // break continuity and start new running sum from current element
            // hint: sum with -ve runningSum can be less than the current
            if(current > sumWithCurrent){
                runningSum = current;
            } else{
                runningSum = sumWithCurrent;
            }

            // update max
            if(runningSum > maxSum) {
                maxSum = runningSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        assertEquals(10, maxSubArray(Arrays.asList(1, 2, 3, 4, -10)));
        assertEquals(6, maxSubArray(Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4)));
    }
}
