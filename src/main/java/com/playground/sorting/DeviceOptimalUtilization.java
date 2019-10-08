package com.playground.sorting;

import java.util.*;

/*
* Amazon online:
* There are a bunch of devices with different device capacity and a list of foreground and background app list.
* each app in list has 0 th index as app id and 1 index as utilization.
* we need to pick the optimal pair of ID for utilization.
* IDs in list of foreground apps are unique so as background apps, but they can have recurring ID across lists.
* IDs need not be sequential
*
* example 1 :
*      foreground apps : ID        UTILIZATION     Background apps : ID        UTILIZATION
*                        1         3                                 1         2
*                        2         5                                 2         8
*                        3         7
*                        4         10
* For a given device capacity of 7, there is only 1 pair  [2, 1] sums up to 6 which is just within capacity
*
* example 1 :
*      foreground apps : ID        UTILIZATION     Background apps : ID        UTILIZATION
*                        1         3                                 1         2
*                        2         5                                 2         3
*                        3         7                                 3         4
*                        4         10                                4         5
*
* For a given device capacity of 10, there are 2 pairs  [2, 4] sums up to 10
*                                                       [3, 2] sums up to 10
* */
public class DeviceOptimalUtilization {

    // Complete the freqQuery function below.
    static List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList){
        TreeMap<Integer, List<List<Integer>>> sumToIdPair = new TreeMap<>();

        for (List<Integer> fgList : foregroundAppList) {
            for (List<Integer> bgList : backgroundAppList) {
                int sum = fgList.get(1) + bgList.get(1);
                if (sum <= deviceCapacity) {
                    List<List<Integer>> pair = sumToIdPair.getOrDefault(sum, new ArrayList<>());
                    pair.add(Arrays.asList(fgList.get(0), bgList.get(0)));
                    sumToIdPair.put(sum, pair);
                }
            }
        }
        if (sumToIdPair.isEmpty()) {
            return Collections.singletonList(new ArrayList<>());
        }
        return sumToIdPair.lastEntry().getValue();
    }

    public static void main(String[] args) {
        System.out.println(optimalUtilization(7,
                Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7), Arrays.asList(4, 10)),
                Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 8))
        ));
        System.out.println(optimalUtilization(10,
                Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7), Arrays.asList(4, 10)),
                Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5))
        ));
    }
}
