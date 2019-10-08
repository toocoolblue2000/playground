package com.playground.string;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String x = "Java Launch Helper";
        String y = "rocket Launch center";
        System.out.println(findLCS(x, y));
    }

    private static String findLCS(String x, String y) {
        int lcs[][] = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i < x.length() + 1; i++) {
            for (int j = 0; j < y.length() + 1; j++) {
                if (i==0 || j==0) {
                    lcs[i][j] = 0;
                } else if (x.charAt(i-1) == y.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        int index = lcs[x.length()][y.length()];

        char[] lcsChars = new char[index + 1];
        lcsChars[index] = ' ';

        int i = x.length();
        int j = y.length();

        while (i > 0 && j > 0) {
            if (x.charAt(i-1) == y.charAt(j-1)) {
                lcsChars[index-1] = x.charAt(i-1);
                index--;
                i--;
                j--;
            } else if (lcs[i][j-1] < lcs[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(Arrays.copyOfRange(lcsChars, 0, lcsChars.length - 1));
    }
}
