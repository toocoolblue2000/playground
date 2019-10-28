package com.playground.string;
import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.*;
import java.util.stream.Collectors;

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

    public static String countAndSay(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        if (chars.length == 1) {
            return "1" + chars[0];
        }
        String s = "";
        int times = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                times++;
            } else {
                s += times + "" + chars[i-1];
                times = 1;
            }
        }
        s += times + "" + chars[chars.length - 1];
        return s;
    }

    public static int romanToInt(String s) {
        if (s.isEmpty()) {
            return 0;
        } else if (s.startsWith("M")) {
            return 1000 + romanToInt(s.substring(1));
        } else if (s.startsWith("CM")) {
            return 900 + romanToInt(s.substring(2));
        } else if (s.startsWith("D")) {
            return 500 + romanToInt(s.substring(1));
        } else if (s.startsWith("CD")) {
            return 400 + romanToInt(s.substring(2));
        } else if (s.startsWith("C")) {
            return 100 + romanToInt(s.substring(1));
        } else if (s.startsWith("XC")) {
            return 90 + romanToInt(s.substring(2));
        } else if (s.startsWith("L")) {
            return 50 + romanToInt(s.substring(1));
        } else if (s.startsWith("XL")) {
            return 40 + romanToInt(s.substring(2));
        } else if (s.startsWith("X")) {
            return 10 + romanToInt(s.substring(1));
        } else if (s.startsWith("IX")) {
            return 9 + romanToInt(s.substring(2));
        } else if (s.startsWith("V")) {
            return 5 + romanToInt(s.substring(1));
        } else if (s.startsWith("IV")) {
            return 4 + romanToInt(s.substring(2));
        } else if (s.startsWith("III")) {
            return 3 + romanToInt(s.substring(3));
        } else if (s.startsWith("II")) {
            return 2 + romanToInt(s.substring(2));
        } else if (s.startsWith("I")) {
            return 1 + romanToInt(s.substring(1));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0;
        int high = nums1.length;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = ((nums1.length + nums2.length + 1) / 2) - mid1;

            int maxLeft1 = (mid1 == 0)? Integer.MIN_VALUE : nums1[mid1 - 1];
            int minRight1 = (mid1 == nums1.length)? Integer.MAX_VALUE : nums1[mid1];
            int maxLeft2 = (mid2 == 0)? Integer.MIN_VALUE : nums2[mid2 - 1];
            int minRight2 = (mid2 == nums2.length)? Integer.MAX_VALUE : nums2[mid2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((nums1.length + nums2.length) % 2 == 0) {
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    return avg(Math.max(maxLeft1, maxLeft2), Math.min(minRight1, minRight2));
                }
            } else if (maxLeft1 > minRight2) {
                high = mid1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0;
    }

    private static double avg(double max, double min) {
        return (max + min) / 2;
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = 0;
        int len =  s.length();
        int[] index = new int[128];
        for (int i = 0, j = 0; i < len; i++) {
            j = Math.max(index[s.charAt(i)], j);
            n = Math.max(n, i-j + 1);
            index[s.charAt(i)] = i + 1;
        }
        return n;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < commonPrefix.length(); j++) {
                if (j >= strs[i].length()) {
                    commonPrefix = commonPrefix.substring(0, j);
                    continue;
                }
                if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
                    commonPrefix = commonPrefix.substring(0, j);
                    break;
                }
            }
        }
        return commonPrefix;
    }

    private static Map<Character, char[]> map = new HashMap<Character, char[]>(){{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};
    public static List<String> letterCombinations(String digits) {
        List<String> combos = new ArrayList<>();
        if (!digits.isEmpty()) {
            return letterCombinations(digits.substring(1), digits.charAt(0), combos);
        }
        return combos;
    }

    public static List<String> letterCombinations(String digits, Character construct, List<String> combos) {
        char[] chars = map.get(construct);
        List<String> newCombos = new ArrayList<>();
        if (combos.isEmpty()) {
            for (char c : chars) {
                newCombos.add(String.valueOf(c));
            }
        } else {
            for (String combo : combos) {
                for (char c : chars) {
                    newCombos.add(combo + c);
                }
            }
        }
        if (digits.length() > 0) {
            return letterCombinations(digits.substring(1), digits.charAt(0), newCombos);
        }
        return newCombos;
    }

    public boolean isValidParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.isEmpty()) return false;
                    if (c != stack.pop()) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }


    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        TreeSet<String> suggestionSet = new TreeSet<>();
        boolean isLeaf;
        public TrieNode() {}
        public TrieNode(char c){
            this.c = c;
        }
    }

    HashMap<Character, TrieNode> charMap = new HashMap<>();
    private TrieNode root = new TrieNode();
    public List<List<String>> maxThreeSuggestion(int numProducts, List<String> suggestions, String input) {
        for (String suggest : suggestions) {
            insert(suggest);
        }


        List<List<String>> out = new ArrayList<>();
        for (int i = 2; i <= input.length(); i++) {
            TrieNode t = searchNode(input.substring(0, i));
            if (t != null) {
                out.add(t.suggestionSet.stream().limit(3).collect(Collectors.toList()));
            }
        }
        return out;
    }

    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){
                t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
            t.suggestionSet.add(word);
            children = t.children;

            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;
        }
    }

    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
        return t;
    }


    public static int minDominoRotations(int[] A, int[] B) {
        int[] aClone = A.clone();
        int[] bClone = B.clone();
        int flaps = 0;
        boolean isASame = true;
        boolean isBSame = true;
        int i = 1;
        while (i < aClone.length && (isASame || isBSame)) {
            if (isASame && aClone[i - 1] != aClone[i]) {
                if (aClone[i-1] == bClone[i]) {
                    swap(aClone, bClone, i);
                    flaps++;
                } else {
                    isASame = false;
                }
            }
            if (isBSame && bClone[i - 1] != bClone[i]) {
                if (bClone[i-1] == aClone[i]) {
                    swap(aClone, bClone, i);
                    flaps++;
                } else {
                    isBSame = false;
                }
            }
            i++;
            if (!isASame && !isBSame) {
                return -1;
            }
        }
        return flaps;
    }

    private static void swap(int[] a, int[] b, int i) {
        int temp = a[i];
        a[i] = b[i];
        b[i] = temp;
    }

    public String licenseKeyFormatting(String S, int K) {
        int len = S.length();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (len --> 0) {
            if (S.charAt(len) != '-') {
                builder.insert(0, Character.toUpperCase(S.charAt(len)));
                i++;
                if (i == K && len != 0 && containsChar(S.substring(0, len))) {
                    builder.insert(0, '-');
                    i = 0;
                    len++;
                }
            }
        }
        return builder.toString();
    }

    private boolean containsChar(String substring) {
        for (char c : substring.toCharArray()) {
            if (c != '-') {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        assertEquals(2, minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}));
        assertEquals(-1, minDominoRotations(new int[]{3,5,1,2,3}, new int[]{3,6,3,3,4}));
        assertEquals(0, minDominoRotations(new int[]{1,1,1,1,1}, new int[]{5,5,5,5,5}));

        System.out.println(new Solution().maxThreeSuggestion(5, Arrays.asList("bags", "baggage", "banner", "box", "cloths"), "bags"));

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

        assertEquals("11", countAndSay(1));
        assertEquals("21", countAndSay(11));
        assertEquals("1211", countAndSay(21));
        assertEquals("111221", countAndSay(1211));
        assertEquals("312211", countAndSay(111221));

        assertEquals(3, romanToInt("III"));
        assertEquals(4, romanToInt("IV"));
        assertEquals(9, romanToInt("IX"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
        assertEquals(3045, romanToInt("MMMXLV"));

        assertEquals(3.5, findMedianSortedArrays(new int[]{-5, 3, 6, 12, 15}, new int[]{-12, -10, -6, -3, 4, 10}));

        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, lengthOfLongestSubstring("aaaaaa"));

        assertEquals("fl", longestCommonPrefix(new String[]{"flower","flow","flight"}));

        assertEquals(Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf"), letterCombinations("23"));
    }
}
