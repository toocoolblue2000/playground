/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */
package com.playground.string;

public class RecursiveStringReverse {

    public static void main(String[] args) {
        System.out.println(reverse("Hello"));
    }

    private static String reverse(String s) {
        if (s.length() < 1) {
            return s;
        }
        return reverse(s.substring(1)) + s.charAt(0);
    }
}
