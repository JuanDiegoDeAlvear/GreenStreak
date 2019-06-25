package com.client.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {


    @Test
    void getScore() {
        Score score = new Score();
        score.setScore(800);
        assertEquals(800, score.getScore());
    }

    @Test
    void getFootprint() {
        Score score = new Score();
        score.setFootprint(50.2);
        assertEquals(50.2, score.getFootprint());
    }

    @Test
    void getFootprintEco() {
        Score score = new Score();
        score.setFootprintEco(55.2);
        assertEquals(55.2, score.getFootprintEco());
    }

    @Test
    void getGreenstreak() {
        Score score = new Score();
        score.setGreenstreak(50);
        assertEquals(50, score.getGreenstreak());
    }
    


}