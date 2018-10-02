/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.recursion;

public class CoinChange {
    public static int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length - 1) return 1; // one denom remaining -> one way to do it
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1); // go to next denom
        }
        return ways;
    }

    public static int makeChange(int amount, int[] denoms) {
        return makeChange(amount, denoms, 0);
    }

    public static void main(String[] args) {
        int[] denoms = {25, 10, 5, 1};
        int ways = makeChange(300, denoms);
        System.out.println(ways);
    }
}
