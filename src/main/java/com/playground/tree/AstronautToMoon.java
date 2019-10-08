package com.playground.tree;

import java.util.*;

/*
The member states of the UN are planning to send  people to the moon. They want them to be from different countries. You will be given a list of pairs of astronaut ID's. Each pair is made of astronauts from the same country. Determine how many pairs of astronauts from different countries they can choose from.

For example, we have the following data on 2 pairs of astronauts, and 4 astronauts total, numbered  through .

1   2
2   3
Astronauts by country are [0] and [1,2,3]. There are  pairs to choose from: [0,1] [0,2] [0,3] and [0,4].

Function Description

Complete the journeyToMoon function in the editor below. It should return an integer that represents the number of valid pairs that can be formed.

journeyToMoon has the following parameter(s):

n: an integer that denotes the number of astronauts
astronaut: a 2D array where each element  is a  element integer array that represents the ID's of two astronauts from the same country

Input Format:
The first line contains two integers  and , the number of astronauts and the number of pairs.
Each of the next  lines contains  space-separated integers denoting astronaut ID's of two who share the same nationality.

Constraints:
1 <= n <= 10^5
1 <= p <= 10^4

Output Format
An integer that denotes the number of ways to choose a pair of astronauts from different coutries.

Solution:
Breadth first search
Disjoint set
 */
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

        for (Set<Astronaut> astros : astrosByCountry) {
            out += sum * astros.size();
            sum += astros.size();
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
