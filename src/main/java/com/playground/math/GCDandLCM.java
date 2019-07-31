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

import java.util.Arrays;

public class GCDandLCM {
    public static void main(String[] args) {

        int[] numbers = new int[]{2, 4, 6, 8, 10};
        System.out.println("GCD " + gcd(numbers));
        System.out.println("LCM " + lcm(numbers));
        System.out.println("GCD functional " + gcdFunctional(numbers));
        System.out.println("LCM functional " + lcmFunctional(numbers));

    }

    private static int gcd(int...numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        int result = numbers[0];
        for(int i = 1; i < numbers.length; i++) {
            result = gcd(result, numbers[i]);
        }
        return result;
    }

    private static int lcm(int...numbers) {
        if (numbers.length == 1) {
            return numbers[1];
        }
        int result = numbers[0];
        for(int i = 1; i < numbers.length; i++) {
            result = lcm(result, numbers[i]);
        }
        return result;
    }

    private static int gcdFunctional(int... numbers) {
        return Arrays.stream(numbers).reduce(0, GCDandLCM::gcd);
    }

    private static int lcmFunctional(int... numbers) {
        return Arrays.stream(numbers).reduce(1, (x, y) -> x * (y / gcd(x, y)));
    }

    /**
     * LCM(a, b) = (a * b) / gcd(a, b)
     *
     * LCM(a, b, c) != (a * b * c) / gcd(a, b, c)
     *
     * LCM(a, b, c, ...) = LCM(a, LCM(b, c))
     *                   = LCM(LCM(a, b), c))
     */
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * gcd(a, b, c...) = gcd(gcd(a, b), c))
     *                 = gcd(a, gcd(b, c))
     */
    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
