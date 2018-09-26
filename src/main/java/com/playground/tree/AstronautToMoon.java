/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.tree;

import java.util.*;

public class AstronautToMoon {

    static class Astronaut {
        public Astronaut(int id) {
            this.id = id;
        }
        int id;
        Set<Astronaut> friends = new HashSet<>();
    }
    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
        Map<Integer, Astronaut> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new Astronaut(i));
        }
        for (int i = 0; i < astronaut.length; i++) {
            Astronaut astronaut1 = map.get(astronaut[i][0]);
            Astronaut astronaut2 = map.get(astronaut[i][1]);
            astronaut1.friends.add(astronaut2);
            astronaut2.friends.add(astronaut1);
        }
        boolean[] visited = new boolean[n];
        int nextUnvisited = 0;
        List<Set<Astronaut>> astrosByCountry = new ArrayList<>();
        do {
            Queue<Astronaut> queue = new ArrayDeque<>();
            queue.offer(map.get(nextUnvisited));
            Set<Astronaut> astroSet = new HashSet<>();
            while (!queue.isEmpty()) {
                Astronaut curr = queue.poll();
                astroSet.add(curr);
                for (Astronaut friend : curr.friends) {
                    if (!visited[friend.id]) {
                        queue.add(friend);
                    }
                }
                visited[curr.id] = true;
            }
            astrosByCountry.add(astroSet);
            nextUnvisited = nextUnvisited(visited);
        } while (nextUnvisited != -1);

        long out = 0;
        int sum = 0;
        for (int i = 0; i < astrosByCountry.size(); i++) {
            out += sum * (astrosByCountry.get(i).size());
            sum += astrosByCountry.get(i).size();
        }

        return out;
    }

    static int nextUnvisited(boolean[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (!nodes[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(journeyToMoon(5, new int[][]{
                {0, 1},
                {2, 3},
                {0, 4}
        }));
        System.out.println(journeyToMoon(4, new int[][]{
                {0, 1}
        }));
        System.out.println(journeyToMoon(100000, new int[][]{
                {1, 2},
                {3, 4}
        }));
        System.out.println(journeyToMoon(100, new int[][]{
                {0 ,11},
                {2 ,4},
                {2 ,95},
                {3 ,48},
                {4 ,85},
                {4 ,95},
                {5 ,67},
                {5 ,83},
                {5 ,42},
                {6 ,76},
                {9 ,31},
                {9 ,22},
                {9 ,55},
                {10,61},
                {10,38},
                {11,96},
                {11,41},
                {12,60},
                {12,69},
                {14,80},
                {14,99},
                {14,46},
                {15,42},
                {15,75},
                {16,87},
                {16,71},
                {18,99},
                {18,44},
                {19,26},
                {19,59},
                {19,60},
                {20,89},
                {21,69},
                {22,96},
                {22,60},
                {23,88},
                {24,73},
                {27,29},
                {30,32},
                {31,62},
                {32,71},
                {33,43},
                {33,47},
                {35,51},
                {35,75},
                {37,89},
                {37,95},
                {38,83},
                {39,53},
                {41,84},
                {42,76},
                {44,85},
                {45,47},
                {46,65},
                {47,49},
                {47,94},
                {50,55},
                {51,99},
                {53,99},
                {56,78},
                {66,99},
                {71,78},
                {73,98},
                {76,88},
                {78,97},
                {80,90},
                {83,95},
                {85,92},
                {88,99},
                {88,94},
        }));
    }

}
