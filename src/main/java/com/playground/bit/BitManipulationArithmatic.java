/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.bit;

public class BitManipulationArithmatic {

    public static void main(String[] args) {
        System.out.println("Add " + addition(93, 5));
        System.out.println("Sub " + subtraction(7, 60));
        System.out.println("Sub " + multiplication(9, 60));
    }

    private static int sum = 0, carry = 0;

    public static int addition(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            sum = a ^ b;
            carry = (a & b) << 1;
            return addition(sum, carry);
        }
    }

    public static int subtraction(int a, int b) {
        return addition(a, addition(~b, 1));
    }

    public static int multiplication(int a, int b) {
        for (int i = 0; i < b / 2; i++)
            sum = addition(sum, addition(a, a));
        return sum;
    }
}
