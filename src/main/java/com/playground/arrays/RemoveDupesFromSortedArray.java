package com.playground.arrays;

import java.util.Arrays;

/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example:
Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDupesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums.length<=1) return nums.length;
        int i = 0;
        int j = 1;
        while(j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[i+1] = nums[j];
                i++;
                j++;
            }

        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates(nums);
        System.out.println(i + "\t" + Arrays.toString(nums) + "\t" + Arrays.toString(Arrays.copyOf(nums, i)));
    }
}
