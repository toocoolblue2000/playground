package com.playground.recursion.dynamic.programming;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
Return an integer corresponding to the maximum product possible.

Example :

Input : [2, 3, -2, 4]
Return : 6

Possible with [2, 3]
 */
public class MaxProductSubarray {

    private static int maxProduct = Integer.MIN_VALUE;
    private static int maxProduct(final List<Integer> list) {
        int currIndex = 0;
        int currLen = 1;
        int listprod = list.stream().reduce(1, (a, b) -> a * b);
        while (currLen < list.size()) {
            int currProd = maxProduct(list.subList(currIndex, currIndex++ + currLen));
            if (maxProduct < currProd || maxProduct < listprod) {
                if (currProd < listprod) {
                    maxProduct = listprod;
                } else {
                    maxProduct = currProd;
                }
            }
            if ((currIndex + currLen) > list.size()) {
                currLen++;
                currIndex = 0;
            }
        }
        return maxProduct;
    }

    private static int min(int x, int y) { return x < y ? x : y; }
    private static int max(int x, int y) { return x > y ? x : y; }

    private static int maxProductBetter(final List<Integer> list) {
        int maxEndingHere = 1;
        int minEndingHere = 1;
        int maxProduct = 1;
        boolean flag = false;

        for (int i = 0; i < list.size(); i++) {

            int currVal = list.get(i);
            if (currVal > 0) {
                maxEndingHere = maxEndingHere * currVal;
                minEndingHere = min(minEndingHere * currVal, 1);
                flag = true;
            } else if (currVal == 0) {
                maxEndingHere = 1;
                minEndingHere = 1;
            } else {
                int temp = maxEndingHere;
                maxEndingHere = max(minEndingHere * currVal, 1);
                minEndingHere = temp * currVal;
            }
            if (maxProduct < maxEndingHere) {
                maxProduct = maxEndingHere;
            }
        }
        if (!flag && maxProduct == 1) {
            return 0;
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        assertEquals(6, maxProduct(Arrays.asList(2, 3, -2, 4)));
        assertEquals(6, maxProductBetter(Arrays.asList(2, 3, -2, 4)));

        assertEquals(180, maxProduct(Arrays.asList(6, -3, -10, 0, 2)));
        assertEquals(180, maxProductBetter(Arrays.asList(6, -3, -10, 0, 2)));
    }
}
