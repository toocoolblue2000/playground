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

public class TowersOfHanoii {

    static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod) {
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + from_rod + " to rod " + to_rod);
            return;
        }
        towerOfHanoi(n - 1, from_rod, aux_rod, to_rod);
        System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);
        towerOfHanoi(n - 1, aux_rod, to_rod, from_rod);
    }

    //  Driver method
    public static void main(String args[]) {
        int n = 4;
        towerOfHanoi(n, 'A', 'C', 'B');
    }
}
