package com.playground.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/
public class MajorityElement {

    private static int majorityElement(final List<Integer> a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : a) {
            map.put(in, map.getOrDefault(in, 0) + 1);
        }

        int maxCount = 0;
        Integer maxNum = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxNum = entry.getKey();
            }
        }
        return maxNum != null ? maxNum : 0;
    }

    public static void main(String[] args) {
        assertEquals(majorityElement(Arrays.asList(2,1,2)), 2);
        assertEquals(majorityElement(Arrays.asList(2,1,1)), 1);
        assertEquals(majorityElement(Arrays.asList(100)), 100);
        assertEquals(majorityElement(Arrays.asList(1,2,3,4,5,5,6,10, 10, 10, 10, 10, 10, 10, 10)), 10);
    }
}
