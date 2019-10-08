
package com.playground.greedy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Input Format:

The first and the only argument contains N integers in an array A.
Output Format:

Return an integer, representing the minimum candies to be given.

 */
public class DistributeCandy {

    private static int candyNeeded(List<Integer> childGrades) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : childGrades) {
            map.put(in, map.getOrDefault(in, 0) + 1);
        }
        int i = 1;
        int sumCandy = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sumCandy += entry.getValue() * i++;
        }

        return sumCandy;
    }

    public static void main(String[] args) {
        assertEquals(candyNeeded(Arrays.asList(1, 2)), 3);
        assertEquals(candyNeeded(Arrays.asList(1, 5, 2, 1)), 7);
        assertEquals(candyNeeded(Arrays.asList(5, 5, 4, 3, 2, 1)), 20);
    }
}
