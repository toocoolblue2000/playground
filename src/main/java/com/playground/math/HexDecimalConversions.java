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

public class HexDecimalConversions {

    private static final char[] numbers = {'0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f'};

    int convertFromBase(String number, int base) {
        if (base < 2 || (base > 10 && base != 16)) return -1;
        int value = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Arrays.binarySearch(numbers, number.charAt(i));
            if (digit < 0 || digit >= base) {
                return -1;
            }
            int exp = number.length() - 1 - i;
            value += digit * Math.pow(base, exp);
        }
        return value;
    }
}
