package com.playground.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Suppose you have an array of strings 'src' and a string 'key':

src = {"minecraftgame", "intelligent", "innercrafttalent", "knife", "scissor", "stonecrafter"}
key = "craft"
Now return all the strings from the 'src' array that contains the key as substring in them. For example, for above case, the solution should be:

result = {"minecraftgame", "innercrafttalent", "stonecrafter"}
Because all the result starings have key i.e. "craft" in them as substring
https://www.youtube.com/watch?v=GTJr8OvyEVQ
 */
public class StringPatternMatchingBoyerMooreAndKMPAlgo {

    private static String[] findAllMatchingStringsBruteForce(String key, String[] strings) {
        List<String> list = new ArrayList<>();
        for (String str : strings) {
            if (str.contains(key)) {
                list.add(str);
            }
        }
        return list.toArray(new String[0]);
    }

    private static String[] findAllMatchingStringsKMP(String key, String[] strings) {
        List<String> matches = new ArrayList<>();
        int[] matchArray = getPreProcessingMatchArray(key);
        for (String s : strings) {
            int textIndex = 0;
            int keyIndex = 0;
            while (textIndex < s.length()) {
                if (key.charAt(keyIndex) == s.charAt(textIndex)) {
                    keyIndex++;
                    textIndex++;
                    if (keyIndex == key.length()) {
                        matches.add(s);
                        break;
                    }
                } else {
                    if (keyIndex != 0) {
                        keyIndex = matchArray[keyIndex - 1];
                    } else {
                        textIndex++;
                    }
                }
            }
        }
        return  matches.toArray(new String[0]);
    }

    /*
    for key abcdabcaa           a b c d a b c a a
    matching array will be      0 0 0 0 1 2 3 1 1
    for key aabaabaaa           a a b a a b a a a
    matching array will be      0 1 0 1 2 3 1 2 1
    for key craft               c r a f t
    matching array will be      0 0 0 0 0
     */
    private static int[] getPreProcessingMatchArray(String key) {
        int[] ints = new int[key.length()];
        ints[0] = 0;

        int slowIndex = 0;
        int fastIndex = 1;

        while (fastIndex < key.length()) {
            if (key.charAt(slowIndex) == key.charAt(fastIndex)) {
                ints[fastIndex] = slowIndex + 1;
                slowIndex++;
                fastIndex++;
            } else {
                if (slowIndex != 0) {
                    slowIndex = ints[slowIndex-1];
                } else {
                    fastIndex++;
                }
            }
        }
        return ints;
    }

    public static int findAllMatchingStringsBoyerMoore(String needle, String haystack) {

        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty() || needle.length() > haystack.length()) {
            return -1;
        }

        int needleSize = needle.length();

        int[] rightmostIndex = new int[256];

        Arrays.fill(rightmostIndex, -1);

        for (int i = 0; i < needleSize; i++) {
            rightmostIndex[needle.charAt(i)] = i;
        }

        for (int i = 0; i < haystack.length() - needleSize + 1; i++) {
            int atIndex = needleSize - 1;

            while (atIndex > -1 && haystack.charAt(i + atIndex) == needle.charAt(atIndex)) {
                atIndex--;
            }

            if (atIndex == -1) {
                return i;
            } else {
                i += Math.max(0, atIndex - rightmostIndex[haystack.charAt(i + atIndex)] - 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        assertArrayEquals(new String[]{"minecraftgame", "innercrafttalent", "stonecrafter"}, findAllMatchingStringsBruteForce("craft",
                new String[]{"minecraftgame", "intelligent", "innercrafttalent", "knife", "scissor", "stonecrafter"}));

        assertArrayEquals(new String[]{"minecraftgame", "innercrafttalent", "stonecrafter"}, findAllMatchingStringsKMP("craft",
                new String[]{"minecraftgame", "intelligent", "innercrafttalent", "knife", "scissor", "stonecrafter"}));

        assertArrayEquals(new String[]{"abcxabcdabxabcdabcaabcy"}, findAllMatchingStringsKMP("abcdabcaa",
                new String[]{"abcxabcdabxabcdabcaabcy"}));

        assertArrayEquals(new String[]{"aabaabaacaabaaaabaabaaa"}, findAllMatchingStringsKMP("aabaabaaa",
                new String[]{"aabaabaacaabaaaabaabaaa"}));

        assertEquals(14, findAllMatchingStringsBoyerMoore("craft", "mineacraftgame"));


    }
}
