package com.client.logic;

import java.util.Objects;

public class RankingEntry {
    private String username;
    private int score;

    public RankingEntry() {
        username = null;
        score = 0;
    }

    public RankingEntry(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(obj instanceof RankingEntry)) {
            return false;
        }
        RankingEntry that = (RankingEntry) obj;
        return getScore() == that.getScore()
                && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public String toString() {
        return "RankingEntry{"
                + "username='" + username + '\''
                + ", score=" + score + '}';
    }
}
