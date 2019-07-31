/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.math;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class NClosestPointsToTheOrigin {
    public static void main(String[] args) throws Exception {
        Point[] points = getRandomPoints(10, 10);
        Point[] nearPoints = getNPointsNearToPoint(new Point(0,0), points, 5);
        System.out.println(Arrays.deepToString(nearPoints));

        System.out.println(findBestFit(new int[]{1,1,1,1}, 3));
        System.out.println(findBestFit(new int[]{1,2,3,4}, 6));
        System.out.println(findBestFit(new int[]{1,3,5,7}, 7));
        System.out.println(findBestFit(new int[]{2,2,30,31}, 32));

    }

    private static Point[] getNPointsNearToPoint(Point originPoint, Point[] points, int numberOfPoints) {
        Map<Integer, Double> indexAndDistanceMap = new HashMap<>(points.length);
        for (int i = 0; i < points.length; i++) {
            double x = originPoint.getX() - points[i].getX();
            double y = originPoint.getY() - points[i].getY();
            double distance = Math.sqrt((x * x) + (y * y));
            indexAndDistanceMap.put(i, distance);
        }
        List<Point> sorted = indexAndDistanceMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(numberOfPoints)
                .map(e -> points[e.getKey()])
                .collect(Collectors.toList());
        return sorted.toArray(new Point[0]);
    }

    private static Point[] getRandomPoints(int n, int pointsRange) {
        Point[] points = new Point[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            points[i] = new Point(random.nextInt(pointsRange), random.nextInt(pointsRange));
        }
        return points;
    }

    private static List<Integer> findBestFit(int[] availableSlotArray, int reqMapping) throws Exception {

        for (int j = availableSlotArray.length; j > 1; j--) {
            List<Integer> indixes = new ArrayList<>();
            int currSum = 0;
            for (int i = j - 1; i >= 0; i--) {
                if (availableSlotArray[i] > reqMapping) {
                    continue;
                } else {
                    if ((currSum + availableSlotArray[i]) <= reqMapping) {
                        currSum += availableSlotArray[i];
                        indixes.add(i);
                        if (currSum == reqMapping) {
                            Collections.reverse(indixes);
                            return indixes;
                        }
                    }
                }
            }
        }
        throw new Exception("Need more device pair for mapping");
    }

    private static void findBestFit(int[] availableSlotArray, int reqMapping, int maxHostVpc, List<Integer> existingTransitArray,
                                    List<Integer> createTransitArray) {
        int currSum = 0;
        for (int i = 0; i < availableSlotArray.length; i++) {
            if (currSum < reqMapping && availableSlotArray[i] != maxHostVpc) {
                currSum += availableSlotArray[i];
                existingTransitArray.add(i);
            } else if (currSum >= reqMapping) {
                break;
            } else if (availableSlotArray[i] == maxHostVpc) {
                createTransitArray.add(i);
                existingTransitArray.clear();
                currSum = reqMapping - availableSlotArray[i];
                availableSlotArray[i] = 0;
                findBestFit(availableSlotArray, currSum, maxHostVpc, existingTransitArray, createTransitArray);
            }
        }

    }
}


