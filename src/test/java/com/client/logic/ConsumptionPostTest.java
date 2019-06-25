package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ConsumptionPostTest {

    Date date = new Date(2/2/2019);

    ConsumptionPost emptyPost = new ConsumptionPost("", true);
    ConsumptionPost nullPost = new ConsumptionPost(null, true);
    ConsumptionPost cPost = new ConsumptionPost("Recycle", true);
    SuperPost fPost = new FoodPost("Lunch", "meat");
    ConsumptionPost cPost3 = new ConsumptionPost("Recycle", true);
    ConsumptionPost cPost4 = new ConsumptionPost("Recycle", false);

    SuperPost superPost1 = new SuperPost("Noor", date, "consumptionPost",0);
    SuperPost superPost2 = new SuperPost("Noor", date, "consumptionPost",0);


    @Test
    void setPostClass(){
        SuperPost superPost = new SuperPost("Noor", date, "consumptionPost",0);
        ConsumptionPost super2 = new ConsumptionPost();
        super2.setPostClass("notConsumptionPost");
        assertEquals("consumptionPost", superPost.getPostClass());
        assertEquals("notConsumptionPost", super2.getPostClass());
    }

    @Test
    void getConsume() {
         ConsumptionPost cPost = new ConsumptionPost();
         ConsumptionPost cPost2 = new ConsumptionPost("boughtLocal", true);
         cPost.setConsume("recycle");
         assertEquals("recycle", cPost.getConsume());
         assertEquals("boughtLocal", cPost2.getConsume());

    }

    @Test
    void getState() {
        ConsumptionPost cPost = new ConsumptionPost();
        cPost.setDate(date);
        cPost.setState(true);
        assertTrue(cPost.getState());
        assertEquals(date , cPost.getDate());
        assertEquals("ConsumptionPost",cPost.getPostClass());
    }

    @Test
    void equals(){
        assertTrue(cPost instanceof ConsumptionPost);
        assertFalse(fPost instanceof ConsumptionPost);
        assertNotEquals(cPost, fPost);
        assertEquals(cPost, cPost3);
        assertNotEquals(cPost, cPost4);
        assertNotEquals(emptyPost, nullPost);
        assertEquals(superPost1, superPost2);
    }


    @Test
    void equals2() {
        Date date = new Date(5/6/2018);
        ConsumptionPost empty = new ConsumptionPost();
        cPost.setUsername("Noor");
        cPost.setDate(date);
        cPost.setPostClass("con");
        empty.setUsername("Damstra");
        empty.setDate(date);
        empty.setPostClass("not");
        empty.setConsume("Recycle");
        empty.setState(true);
        assertFalse(cPost.equals(empty));
    }

    @Test
    void equals3(){
        ConsumptionPost cPost = new ConsumptionPost("Recycle", true);
        ConsumptionPost obj = cPost;
        assertTrue(obj.equals(cPost));
    }
}