package com.client.logic;

public class Score {

    private int score;
    private double footprint;
    private double footprintEco;
    private int greenstreak;

    /**
     * Constructor.
     *
     * @param score sets score as int
     * @param footprint sets footprint as double
     * @param footprintEco sets footprint eco as double
     * @param greenstreak sets greenstreak as int
     */
    public Score(int score, double footprint, double footprintEco, int greenstreak) {
        this.score = score;
        this.footprint = footprint;
        this.footprintEco = footprintEco;
        this.greenstreak = greenstreak;
    }

    /**
     * Empty Constructor.
     */
    public Score() {
        this.score = 0;
        this.footprint = 0;
        this.footprintEco = 0;
        this.greenstreak = 0;
    }

    public double getFootprint() {
        return footprint;
    }

    public void setFootprint(double footprint) {
        this.footprint = footprint;
    }

    public double getFootprintEco() {
        return footprintEco;
    }

    public void setFootprintEco(double footprintEco) {
        this.footprintEco = footprintEco;
    }

    public int getGreenstreak() {
        return greenstreak;
    }

    public void setGreenstreak(int greenstreak) {
        this.greenstreak = greenstreak;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Score)) {
            return false;
        }

        Score score1 = (Score) obj;

        if (getScore() != score1.getScore()) {
            return false;
        }
        if (Double.compare(score1.getFootprint(), getFootprint()) != 0) {
            return false;
        }
        if (Double.compare(score1.getFootprintEco(), getFootprintEco()) != 0) {
            return false;
        }
        return getGreenstreak() == score1.getGreenstreak();
    }

}
