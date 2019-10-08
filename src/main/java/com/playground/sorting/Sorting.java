package com.playground.sorting;

import java.util.Arrays;

public class Sorting {
    public static void main(String args[]) {
        long[] elements = {9, 5, 61, 3, 65, 8, 3, 5, 7, 9, 3, 42, 556, 567, 57, 8, 23, 3, 7, 1};
        mergeSort(elements);
        System.out.println(Arrays.toString(elements));
        elements = new long[]{9, 5, 61, 3, 65, 8, 3, 5, 7, 9, 3, 42, 556, 567, 57, 8, 23, 3, 7, 1};
        quickSort(elements);
        System.out.println(Arrays.toString(elements));
    }

    /*
    Time complexity:
        Best: O(n log(n))
        Average: O(n log(n))
        Worst: O(n log(n))
    Space Complexity:
        O(n)
     */
    public static void mergeSort(long[] elements) {
        if (elements.length < 2) { //array with single element is returned
            return;
        }
        int mid = elements.length / 2;

        long[] left = Arrays.copyOfRange(elements, 0, mid);
        long[] right = Arrays.copyOfRange(elements, mid , elements.length);

        mergeSort(left);
        mergeSort(right);
        mergeSort(left, right, elements);
    }

    private static void mergeSort(long[] left, long[] right, long[] elements) {
        int leftIndex = 0;
        int rightIndex = 0;
        int elementIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                elements[elementIndex++] = left[leftIndex++];
            } else {
                elements[elementIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            elements[elementIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            elements[elementIndex++] = right[rightIndex++];
        }
    }

    /*
        Time complexity:
            Best: O(n log(n))
            Average: O(n log(n))
            Worst: O(n^2)
        Space Complexity:
            O(log n)
     */
    public static void quickSort(long[] elements) {
        if (elements == null || elements.length < 2) {//array with null or single element is returned
            return;
        }
        quickSort(elements, 0, elements.length - 1);
    }

    private static void quickSort(long[] elements, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;
        long pivot = elements[startIndex + (endIndex - startIndex)/2];
        while (i <= j) {
            while (elements[i] < pivot) {
                i++;
            }
            while (elements[j] > pivot) {
                j--;
            }
            if (i <= j) {
                long temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
                i++;
                j--;
            }
        }

        if (startIndex < j) {
            quickSort(elements, startIndex, j);
        }
        if (i < endIndex) {
            quickSort(elements, i, endIndex);
        }
    }
}