package com.client.logic;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {
    Date x = new Date();
    User XX = new User("", "" );
    User YY = new User ("hi" , "bye");
    User YYY = new User ("hi" , "bye");
    User YYYY = new User ("hi" , "byye");
    User ZZZ  = new User ("hii" , "bye");
    SuperPost mn = new SuperPost ("",x,"",0);



    @Test
    public void getusername() {
        User X = new User();
        X.setUsername("Ziad");
        assertEquals("Ziad" , X.getusername());
    }

    @Test
    public void getPassword() {
        User X = new User("id" , "pass" );
        User Y = new User();
        Y.setPassword("passmatch");
        assertEquals("pass",X.getPassword());
        assertEquals("passmatch",Y.getPassword());

    }

    @Test
    public void equals() {
        assertFalse(XX.equals(YY));
        assertFalse(YY.equals(ZZZ));
        assertTrue(YY.equals(YYY));
        assertFalse(ZZZ.equals(mn));
        assertFalse(YYYY.equals(YY));
    }
}