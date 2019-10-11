package com.playground.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/*
Amazon onsite:
Find the product of all elements in array except current index. Do this in O(n) space and time complexity without using division

Example:
for array [1,   2, 3, 4] has a product of 1 * 2 * 3 * 4 = 24
output is [24, 12, 8, 6]
 */
public class ProductOfArrayExceptCurrIndex {

    public static int[] calculateProductArray(int[] array) {

        //product to the left of element [1, 1, 2, 6]
        int[] prodToLeft = new int[array.length];
        int tmp = 1;
        prodToLeft[0] = 1;
        for (int i = 1; i < array.length; i++) {
            tmp *= array[i - 1];
            prodToLeft[i] = tmp;
        }

        //product to the right of element  [24 , 12, 4, 1]
        tmp = 1;
        int[] prodToRight = new int[array.length];
        prodToRight[array.length - 1] = 1;
        for (int i = array.length - 2; i >= 0; i--) {
            tmp *= array[i + 1];
            prodToRight[i] = tmp;
        }

        int[] out = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            out[i] = prodToLeft[i] * prodToRight[i];
        }
        return out;
    }

    public static void main(String[] args) {
        assertArrayEquals(new int[]{24, 12, 8, 6}, calculateProductArray(new int[]{1, 2, 3, 4}));
    }
}
