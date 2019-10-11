package com.playground.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

You need to do this in place.

Note that if you end up using an additional array, you will only receive partial score.

Example:

If the array is

[
    [1, 2],
    [3, 4]
]
Then the rotated array becomes:

[
    [3, 1],
    [4, 2]
]

Solution:
There are two steps :

Find transpose of matrix.
Reverse columns of the transpose.
Illustration of above steps :

Let the given matrix be
1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 16

First we find transpose.
1 5 9 13
2 6 10 14
3 7 11 15
4 8 12 16

Then we reverse elements of every column.
4 8 12 16
3 7 11 15
2 6 10 14
1 5  9 13
 */
public class RotateArrayAndMatrix {

    private static void rotate(int[][] array) {
        transpose(array);
        reverseArray(array, 0, array.length);
    }

    private static void transpose(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array[i].length; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = temp;
            }
        }
    }

    private static <T> void reverseArray(T[] t, int start, int end) {
        while(end > start) {
            T temp = t[start];
            t[start] = t[end - 1];
            t[end - 1] = temp;
            end--;
            start++;
        }
    }

    private static void rotate(Integer[] nums, int k) {
        int simplifiedRotation = k % nums.length;
        reverseArray(nums, 0, nums.length);
        reverseArray(nums, 0, simplifiedRotation);
        reverseArray(nums, simplifiedRotation, nums.length);
    }

    public static void main(String[] args) {

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(array);
        assertArrayEquals(array, new int[][]{
            { 3, 6, 9},
            { 2, 5, 8},
            { 1, 4, 7}
        });

        array = new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };
        rotate(array);
        assertArrayEquals(array, new int[][]{
                {4,  8, 12, 16},
                {3,  7, 11, 15},
                {2,  6, 10, 14},
                {1,  5,  9, 13}
        });

        Integer[] arr1D = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        rotate(arr1D, 34);
        assertArrayEquals(new Integer[]{6, 7, 8, 9, 0, 1, 2, 3, 4, 5}, arr1D);
    }
}
