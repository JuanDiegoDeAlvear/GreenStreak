package com.client.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FriendRelationshipTest {

    FriendRelationship relationship1;
    FriendRelationship relationship2;

    @Before
    public void setUp() throws Exception {
        relationship1 = new FriendRelationship("Raul","Juan",2);
        relationship2 = new FriendRelationship("Juan","Sam",0);
    }

    @Test
    public void getSenderUsername() {
        assertEquals("Raul",relationship1.getSenderUsername());
    }

    @Test
    public void setSenderUsername() {
        relationship1.setSenderUsername("Marco");
        assertEquals("Marco",relationship1.getSenderUsername());
    }

    @Test
    public void getTargetUsername() {
        assertEquals("Juan",relationship1.getTargetUsername());
    }

    @Test
    public void setTargetUsername() {
        relationship1.setTargetUsername("Marco");
        assertEquals("Marco",relationship1.getTargetUsername());
    }

    @Test
    public void getStatus() {
        assertEquals(2,relationship1.getStatus());
    }

    @Test
    public void setStatus() {
        relationship1.setStatus(0);
        assertEquals(0,relationship1.getStatus());
    }

}