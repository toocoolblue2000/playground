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

public class RandomNumberGenerator {

    public static void main(String[] args) {
        while (true) {
            System.out.println(generateRandomIntInRange(10));
        }
    }

    static Long oldRandom = 0L;
    static Long CONSTANT = 353L;
    static long seed = System.nanoTime();
    public static int generateRandomIntInRange(int m) {
        return (oldRandom = ((seed * oldRandom) + CONSTANT) % m).intValue();
    }
}
