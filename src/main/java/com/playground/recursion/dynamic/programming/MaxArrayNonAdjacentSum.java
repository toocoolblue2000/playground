package com.playground.recursion.dynamic.programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Maximum sum such that no two elements are adjacent
Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that no 2 numbers in the sequence should be adjacent in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).Answer the question in most efficient way.

Examples :

Input : arr[] = {5, 5, 10, 100, 10, 5}
Output : 110 //5, 100, 5

Input : arr[] = {1, 2, 3}
Output : 4 //1,3

Input : arr[] = {1, 20, 3}
Output : 20 //20
Recommended: Please solve it on “PRACTICE ” first, before moving on to the solution.
Algorithm:
Loop for all elements in arr[] and maintain two sums incl and excl where incl = Max sum including the previous element and excl = Max sum excluding the previous element.

Max sum excluding the current element will be max(incl, excl) and max sum including the current element will be excl + current element (Note that only excl is considered because elements cannot be adjacent).

At the end of the loop return max of incl and excl.

Example:

  arr[] = {-2, 1, 3, -4, 5}
Step 1:------------

  incl = -2
  excl = 0

  For i = 1 (current element is 1)
  incl =  (excl + arr[i])  = -1
  excl =  max(-1, 0) = 0

Step 2:------------

  incl = -1
  excl = 0

  For i = 2 (current element is 3)
  incl =  (excl + arr[i]) = 3
  excl =  max(3, 0) = 3

Step 3:------------

  incl = -1
  excl = 3

  For i = 3 (current element is -4)
  incl = (excl + arr[i]) = -1
  excl = max(3, -1) = 3

Step 4:------------

  incl = -2
  excl = 0

  For i = 4 (current element is 5)
  incl = (excl + arr[i]) = 65
  excl =  max(45, 15) = 45

Step 5:------------

  incl = -2
  excl = 0

  For i = 5 (current element is 35)
  incl =  (excl + arr[i]) = 80
  excl =  max(65, 45) = 65

And 35 is the last element. So, answer is max(incl, excl) =  80

 */
public class MaxArrayNonAdjacentSum {

    private static int findMaxNonAdjacentSum(int[] ints) {
        int incl = ints[0];
        int excl = 0;
        int exclNew;

        for (int i = 1; i < ints.length; i++) {
            exclNew = (incl > excl) ? incl : excl;
            incl = excl + ints[i];
            excl = exclNew;
        }
        return (incl > excl) ? incl : excl;
    }

    public static void main(String[] args) {
        assertEquals(findMaxNonAdjacentSum(new int[]{-2, 1, 3, -4, 5}), 8);
        assertEquals(findMaxNonAdjacentSum(new int[]{3, 5, -7, 8, 10}), 15);
        assertEquals(findMaxNonAdjacentSum(new int[]{5, 5, 10, 100, 10, 5}), 110);
    }
}
