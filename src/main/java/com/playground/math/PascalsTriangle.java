package com.playground.math;

import java.util.Arrays;

public class PascalsTriangle {
    public static int[] pascalsTriangleNthRow(int n) {
        int[] array = new int[]{1};
        while (n-->1) {
            array = pascalsNextRow(array);
        }
        return array;
    }

    private static int[] pascalsNextRow(int[] array) {
        int[] out = new int[array.length+1];
        out[0] = 1;
        out[array.length - 1] = 1;
        for (int i = 1; i < array.length - 1; i++) {
            out[i] = array[i-1] + array[i];
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(pascalsTriangleNthRow(1)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(2)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(3)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(4)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(5)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(6)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(7)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(8)));
        System.out.println(Arrays.toString(pascalsTriangleNthRow(9)));
    }
}
