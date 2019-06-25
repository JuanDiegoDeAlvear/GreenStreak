package com.client.logic;

public class Streak {

    /**
     * Calculates greenstreak score.
     *
     * @return double your greenstreak.
     */
    public static double progress(double greenstreak, double score) {

        double streak = score / greenstreak;

        System.out.println(streak);

        return streak;
    }

    public static double streak() {
        return 55.0;
    }
}
