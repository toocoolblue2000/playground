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
