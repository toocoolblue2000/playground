package com.playground.string;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class Solution {
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length/2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

    public static int reverse(int x) {
        StringBuilder s = new StringBuilder(String.valueOf(Math.abs(x)));
        StringBuilder reverse = s.reverse();
        return Integer.parseInt((x < 0)? "-" + reverse.toString() : reverse.toString());
    }

    public static int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                return s.indexOf(entry.getKey());
            }
        }
        return -1;
    }

    public static boolean isAnagram(String s, String t) {
        char[] src = s.toCharArray();
        Arrays.sort(src);
        char[] target = t.toCharArray();
        Arrays.sort(target);
        return Arrays.equals(src, target);
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        char[] smod = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {

            if ((smod[i] >= 65 && smod[i] <= 90)) {
                smod[i] = (char) (smod[i] + 32);
            }

            if ((smod[j] >= 65 && smod[j] <= 90)) {
                smod[j] = (char) (smod[j] + 32);
            }

            if (!((smod[i] >= 48 && smod[i] <= 57) || (smod[i] >= 97 && smod[i] <= 122))) {
                i++;
                continue;
            }
            if (!((smod[j] >= 48 && smod[j] <= 57) || (smod[j] >= 97 && smod[j] <= 122))) {
                j--;
                continue;
            }

            if (smod[i] != smod[j]) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static int myAtoi(String str) {
        String trim = str.trim();
        boolean flag = false;
        if (trim.length() == 0) return 0;
        if (trim.charAt(0) == '+' || trim.charAt(0) == '-') {
            if (trim.length() > 1 && trim.charAt(1)  >= 48 && trim.charAt(1)<= 57) {
                if (trim.charAt(0) == '-') {
                    flag = true;
                }
                trim = trim.substring(1);
            } else {
                return 0;
            }
        }
        char[] chars = trim.toCharArray();
        if (chars[0] >= 48 && chars[0] <= 57) {
            int i = 0;
            while (i < chars.length) {
                if (chars[i] >= 48 && chars[i] <= 57) {
                    i++;
                } else {
                    break;
                }
            }
            try {
                return Integer.parseInt(flag? "-" + trim.substring(0, i) : trim.substring(0, i));
            } catch (NumberFormatException ex) {
                if (flag) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        char[] a = "hello".toCharArray();
        reverseString(a);
        assertArrayEquals("olleh".toCharArray(), a);

        assertEquals(123, reverse(321));

        assertEquals(0, firstUniqChar("leetcode"));
        assertEquals(2, firstUniqChar("loveleetcode"));

        assertTrue(isAnagram("anagram", "nagaram"));

        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome("race a car"));

        assertEquals(0, myAtoi(""));
        assertEquals(42, myAtoi("42"));
        assertEquals(-42, myAtoi("   -42"));
        assertEquals(4193, myAtoi("4193 with words"));
        assertEquals(0, myAtoi("words and 987"));
        assertEquals(Integer.MIN_VALUE, myAtoi("-91283472332"));
        assertEquals(0, myAtoi("-"));
        assertEquals(1, myAtoi("+1"));
        assertEquals(0, myAtoi("+-2"));
        assertEquals(0, myAtoi("-+1"));
        assertEquals(0, myAtoi("0-1"));
    }
}
