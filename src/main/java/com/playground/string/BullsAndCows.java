package com.playground.string;

class BullsAndCows {
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] counts = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }  else {
                if(counts[secret.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                if(counts[guess.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        getHint("123450", "123654");
    }
}