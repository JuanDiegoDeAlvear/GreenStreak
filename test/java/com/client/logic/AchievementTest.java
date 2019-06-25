package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {

    Achievement achievement = new Achievement();

    @Test
    void setFirstPost() {
        achievement.setFirstPost(true);
        assertEquals(achievement.isFirstPost(), true);
    }

    @Test
    void setFirstFriend() {
        achievement.setFirstFriend(true);
        assertEquals(achievement.isFirstFriend(), true);
    }

    @Test
    void setHundKm() {
        achievement.setHundKm(true);
        assertEquals(achievement.isHundKm(), true);
    }

    @Test
    void setFiveDaysr() {
        achievement.setFiveDaysr(true);
        assertEquals(achievement.isFiveDaysr(), true);
    }


    @Test
    void setToprank() {
        achievement.setToprank(true);
        assertEquals(achievement.isToprank(), true);
    }

    @Test
    void setLowEco() {
        achievement.setLowEco(true);
        assertEquals(achievement.isLowEco(), true);
    }

    @Test
    void setLoggedin() {
        achievement.setLoggedin(true);
        assertEquals(achievement.isLoggedin(), true);
    }

    @Test
    void setFivedaysv() {
        achievement.setFivedaysv(true);
        assertEquals(achievement.isFivedaysv(), true);
    }

    @Test
    void setLocal() {
        achievement.setLocal(true);
        assertEquals(achievement.isLocal(), true);
    }
}