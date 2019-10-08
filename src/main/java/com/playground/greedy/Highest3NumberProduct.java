package com.playground.greedy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Highest3NumberProduct {

    private static int maxp3(List<Integer> a) {
        Collections.sort(a);
        return Math.max(a.get(0) * a.get(1) * a.get(a.size() - 1),
                a.get(a.size() - 1) * a.get(a.size() - 2) * a.get(a.size() - 3));
    }

    public static void main(String[] args) {
        assertEquals(maxp3(Arrays.asList(1, 2, 3, 4)), 24);
        assertEquals(maxp3(Arrays.asList(0, -1, 3, 100, 70, 50)), 350000);
        assertEquals(maxp3(Arrays.asList( 0, -1, 3, 100, -70, -50)), 350000);
    }
}
