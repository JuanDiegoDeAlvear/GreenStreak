package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FoodPostTest {

    Date date = new Date(1/4/2019);

    FoodPost foodPost = new FoodPost("breakfast", "Vegetarian");
    FoodPost foodPostSame = new FoodPost("breakfast", "Vegetarian");
    FoodPost foodPostlunch = new FoodPost("lunch", "Vegetarian");
    FoodPost foodPostVegan = new FoodPost("breakfast", "Vegan");
    FoodPost empty = new FoodPost();
    FoodPost superfoodPost = new FoodPost("breakfast", "Vegetarian");
    SuperPost superPost = new SuperPost("N", date, "foodPost",12);
    Object obj = foodPost;

    SuperPost consumptionpost = new ConsumptionPost("recycle", true);

    @Test
    void setMeal() {
        FoodPost x = new FoodPost();
        FoodPost y = new FoodPost("breakfast" , "meat");
        x.setUsername("ziad");
        x.setMeal("lunch");
        assertEquals("lunch" , x.getMeal());
        assertEquals("breakfast", y.getMeal());
        assertEquals("ziad" , x.getUsername());
    }

    @Test
    void setType() {
        FoodPost fPost = new FoodPost();
        fPost.setType("vegetarian");
        assertEquals("vegetarian", fPost.getType());

    }

    @Test
    void equals(){
        assertTrue(foodPost.equals(foodPostSame));
        assertFalse(foodPost.equals(foodPostlunch));
        assertFalse(foodPost.equals(foodPostVegan));
        assertTrue(foodPost instanceof FoodPost);
        assertFalse(consumptionpost instanceof FoodPost);
        assertTrue(obj.equals(foodPost));
        assertNotEquals(foodPost, consumptionpost);
    }

    @Test
    void equals2(){
        Date date1 = new Date(5/5/2019);
        empty.setUsername("D");
        empty.setDate(date1);
        empty.setPostClass("empty");
        superfoodPost.setUsername("N");
        superfoodPost.setDate(date);
        superfoodPost.setPostClass("foodPost");
        assertFalse(superPost.equals(empty));
        assertFalse(empty.equals(superfoodPost));
        assertTrue(superPost.equals(superPost));
    }

}