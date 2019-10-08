package com.playground.greedy;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Given two integer arrays A and B of size N.
There are N gas stations along a circular route, where the amount of gas at station i is A[i].

You have a car with an unlimited gas tank and it costs B[i] of gas to travel from station i
to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, … n-1, 0, 1, 2.. Completing the circuit means starting at i and
ending up at i again.
 */

public class GasStationCircularRun {

        // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int canCompleteCircuit(final List<Integer> a, final List<Integer> b) {

        int start = 0;
        int end = 0;
        int totalFuel = 0;
        int currFuel = 0;
        while (end < a.size()) {
            currFuel += a.get(end) - b.get(end);
            totalFuel += a.get(end) - b.get(end);

            if (currFuel >= 0) {
                end++;
            } else {
                start = end + 1;
                end++;
                currFuel = 0;
            }
        }

        if (totalFuel < 0) {
            return -1;
        } else {
            return start;
        }
    }

    public static void main(String[] args) {
        assertEquals(canCompleteCircuit(Arrays.asList(1,2,3,4,5), Arrays.asList(3,4,5,1,2)), 3);
        assertEquals(canCompleteCircuit(Arrays.asList(2,3,4), Arrays.asList(3,4,3)), -1);
        assertEquals(canCompleteCircuit(Arrays.asList(1,2), Arrays.asList(2,1)), 1);
    }

}

