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
