package com.playground.recursion.dynamic.programming;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearch {

    /*
    Time complexity: O(log(n))
    Space complexity O(n)
     */
    private static int binarySearch(int[]  array, int startIndex, int endIndex, int find) {

        if (array == null || array.length < 2) {
            return 0;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;

        if (array[mid] == find) {
            return mid;
        } else if (array[mid] > find) {
            return binarySearch(array, startIndex, mid, find);
        } else {
            return binarySearch(array, mid, endIndex, find);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 3, 3, 3, 5, 5, 7, 7, 8, 8, 9, 9, 23, 42, 57, 61, 65, 556, 567};
        assertEquals(binarySearch(array, 0, array.length, 9), 12);
    }
}

