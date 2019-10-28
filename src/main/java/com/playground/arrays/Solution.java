package com.playground.arrays;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Solution {

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
           if(digits[i] == 9) {
               digits[i] = 0;
           } else {
               digits[i]++;
               return digits;
           }
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    public static int[] moveZeroes(int[] nums) {
        int noOfNonZeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[noOfNonZeros++] = nums[i];
            }
        }

        for (int i = noOfNonZeros; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (diff > 0) {
                map.put(diff, i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                return new int[]{i, map.get(nums[i])};
            }
        }
        return null;
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[10][10]; //row[0][5] = true means row 0 has number 5
        boolean[][] col = new boolean[10][10]; //col[0][5] = true means column 0 has number 5
        boolean[][] squ = new boolean[10][10]; //squ[0][5] = true means square 0 has 5 number in it. 0 being top left subsquare and 8 being bottom right sub square

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int number = Character.getNumericValue(board[i][j]);
                int square = ((i / 3) * 3) + (j / 3);
                if (col[i][number] == true
                        || row[j][number] == true
                        || squ[square][number] == true) {
                    return false;
                }
                col[i][number] = true;
                row[j][number] = true;
                squ[square][number] = true;
            }
        }
        return true;
    }

    public static void mergeArray(int[] nums1, int m, int[] nums2, int n) {
        int i = nums1.length;
        while (i > 0) {
            if(m != 0 && n != 0 && nums1[m - 1] > nums2[n - 1]) {
                nums1[--i] = nums1[--m];
            } else if(m != 0 && n != 0 && nums1[m - 1] <= nums2[n - 1]) {
                nums1[--i] = nums2[--n];
            }
            if (m == 0) {
                while (n > 0) {
                    nums1[--i] = nums2[--n];
                }
            }
            if (n == 0) {
                while (m > 0) {
                    nums1[--i] = nums1[--m];
                }
            }
        }
    }

    public static void main(String[] args) {
        assertArrayEquals(new int[]{1,2,3,5}, plusOne(new int[]{1,2,3,4}));
        assertArrayEquals(new int[]{2,0,0,0}, plusOne(new int[]{1, 9, 9, 9}));
        assertArrayEquals(new int[]{1,0,0,0}, plusOne(new int[]{9, 9, 9}));

        assertArrayEquals(new int[]{1,2,4,23,0,0,0}, moveZeroes(new int[]{0, 0, 1, 2, 0, 4, 23}));

        assertArrayEquals(new int[]{0, 1}, twoSum(new int[]{2, 7, 11, 15}, 9));

        assertTrue(isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        }));

        assertFalse(isValidSudoku(new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        }));

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        mergeArray(nums1, 3, new int[]{2,5,6}, 3);
        assertArrayEquals(new int[]{1,2,2,3,5,6}, nums1);
        nums1 = new int[]{1};
        mergeArray(nums1, 1, new int[]{}, 0);
        assertArrayEquals(new int[]{1}, nums1);
    }
}
