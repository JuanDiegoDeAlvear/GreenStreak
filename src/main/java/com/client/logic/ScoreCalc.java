package com.client.logic;

public class ScoreCalc {

    /**
     * Calcs the score for car.
     *
     * @param dist distance traveled.
     * @return score.
     */
    public static int carScore(int dist) {
        int score;
        score = dist * 2;
        return score;
    }

    /**
     * Calcs the score for car.
     *
     * @param dist distance traveled.
     * @return score.
     */
    public static int bikeScore(int dist) {
        int score;
        score = dist * 10;
        return score;
    }

    /**
     * Calcs the score for car.
     *
     * @param dist distance traveled.
     * @return score.
     */
    public static int publicScore(int dist, TransportationSurvey transport) {
        int score = 0;
        if (transport.getMainModeTransport().equals("car")) {
            score = dist;
        } else {
            score = dist * -1;
        }
        return score;
    }

    /**
     * Calcs the score for car.
     *
     * @param dist distance traveled.
     * @return score.
     */
    public static int planeScore(int dist) {
        int score;
        score = dist * 50;
        return score;
    }

    /**
     * Calcs score for meals.
     *
     * @param food type of food
     * @return score.
     */
    public static int foodScore(String food) {
        int score = 0;

        if (food.equals("Vegan")) {
            score = 250;
        }
        if (food.equals("Vegetarian")) {
            score = 150;
        }
        if (food.equals("Meat")) {
            score = -50;
        }
        return score;
    }

}


