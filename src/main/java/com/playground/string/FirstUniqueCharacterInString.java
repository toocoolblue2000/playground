/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.string;

import java.util.*;

public class FirstUniqueCharacterInString {

    public static void main(String[] args) {
        System.out.println(firstUniqueChar("loveleetcode"));
    }

    private static Map<Character, Integer> map = new LinkedHashMap<>();
    private static char firstUniqueChar(String str) {
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            Integer val = map.getOrDefault(c, 0);
            map.put(c, ++val);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '0';
    }
}
