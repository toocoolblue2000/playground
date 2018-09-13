package com.playground.math;

class Factorial {

    int factorial(int n) {
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    public static void main(String args[]) {
        Factorial obj = new Factorial();
        int num = 5;
        System.out.println("Factorial of " + num + " is " + obj.factorial(num));
    }
}