package com.playground.np.complete;

import java.awt.*;

public class SupplyChainTransportationProblem {

    /*
        6  8 10
        7 11 11
        4  5 12
    */
    private static int calculateMinumumCost(int[] supply, int[] demand, int[][] costMatrix) {
        Point point = getMinimum(costMatrix);
        int cost = 0;
        while (point != null) {
            if (supply[point.x] > demand[point.y]) {
                cost += costMatrix[point.x][point.y] * demand[point.y];
                demand[point.y] = 0;
                supply[point.x] = supply[point.x] - demand[point.y];
            } else {
                cost += costMatrix[point.x][point.y] * supply[point.x];
                supply[point.x] = 0;
                demand[point.y] = demand[point.y] - supply[point.x];
            }
            costMatrix[point.x][point.y] = -1;
            point = getMinimum(costMatrix);
        }

        return cost;
    }

    private static Point getMinimum(int[][] costMatrix) {
        int minCost = Integer.MAX_VALUE;
        Point minPoint = null;
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if ((costMatrix[i][j]) == -1) continue;
                if (costMatrix[i][j] < minCost) {
                    minCost = costMatrix[i][j];
                    minPoint = new Point(i, j);
                }
            }
        }
        return minPoint;
    }

    public static void main(String[] args) {
        System.out.println(calculateMinumumCost(
                new int[]{150, 250, 400},
                new int[]{300, 300, 200},
                new int[][]{
                        {6, 8,  10},
                        {7, 11, 11},
                        {4, 5,  12},
                }));
    }
}
