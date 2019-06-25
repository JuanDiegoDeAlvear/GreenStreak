package com.client.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RankingEntryTest {

    private RankingEntry entry1;
    private RankingEntry entry2;
    private RankingEntry entry3;

    @Before
    public void setUp() throws Exception {
        entry1 = new RankingEntry("Raul",1000);
        entry2 = new RankingEntry("Raul",1000);
        entry3 = new RankingEntry("Juan",1);
    }

    @Test
    public void defaultConstructor(){
        entry1 = new RankingEntry();
        assertEquals(entry1, new RankingEntry(null,0));
    }

    @Test
    public void getUsername() {
        assertEquals("Raul",entry1.getUsername());
    }

    @Test
    public void setUsername() {
        entry1.setUsername("TheBest");
        assertEquals("TheBest",entry1.getUsername());
    }

    @Test
    public void getScore() {
        assertEquals(1000,entry1.getScore());
    }

    @Test
    public void setScore() {
        entry1.setScore(2000);
        assertEquals(2000,entry1.getScore());
    }

    @Test
    public void equals() {
        assertEquals(entry1,entry2);
    }

    @Test
    public void notEquals() {
        assertNotEquals(entry1,entry3);
    }

    @Test
    public void toStringTest() {
        assertEquals("RankingEntry{username=\'Raul\', score=1000}",entry1.toString());
    }
}