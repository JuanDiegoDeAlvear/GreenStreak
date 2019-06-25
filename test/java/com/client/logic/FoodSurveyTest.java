package com.client.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodSurveyTest {

    FoodSurvey foodSurvey = new FoodSurvey("none", "Vegan", "none", "none", "none", "none");
    FoodSurvey foodSurveySame = new FoodSurvey("none", "Vegan", "none", "none", "none", "none");
    FoodSurvey foodSurveyOrganic = new FoodSurvey("Organic", "Vegan", "none", "none", "none", "none");
    FoodSurvey foodSurveyMeat = new FoodSurvey("none", "Meat", "none", "none", "none", "none");
    FoodSurvey foodSurveyLocal = new FoodSurvey("none", "Vegan", "Local", "none", "none", "none");
    FoodSurvey foodSurveyWasted = new FoodSurvey("none", "Vegan", "none", "Wasted", "none", "none");
    FoodSurvey foodSurveyPackaged = new FoodSurvey("none", "Vegan", "none", "none", "Packaged", "none");
    FoodSurvey foodSurveyCompost = new FoodSurvey("none", "Vegan", "none", "none", "none", "Compost");
    Object energySurvey = new EnergySurvey("Small", 6, "aLot", true, true);
    FoodSurvey obj = foodSurvey;

    @Test
    public void setAmountOrganicFood() {
        FoodSurvey X = new FoodSurvey();
        X.setAmountOrganicFood("Maybe");
        assertEquals("Maybe",X.getAmountOrganicFood());
    }

    @Test
    public void setDietChoice() {
        FoodSurvey X = new FoodSurvey();
        FoodSurvey Y = new FoodSurvey("organic" , "diet" , "local"
                , "waste" , "packed" , "compost");
        X.setDietChoice("NEW");
        assertEquals("NEW", X.getDietChoice());
        assertEquals("diet", Y.getDietChoice());

    }

    @Test
    public void setAmountLocalFood() {
        FoodSurvey X = new FoodSurvey();
        X.setAmountLocalFood("NEW");
        assertEquals("NEW" , X.getAmountLocalFood());


    }

    @Test
    public void setAmountWastedFood() {
        FoodSurvey X = new FoodSurvey();
        X.setAmountWastedFood("NEW");
        assertEquals("NEW" , X.getAmountWastedFood());
    }

    @Test
    public void setAmountFoodPackaged() {
        FoodSurvey X = new FoodSurvey();
        X.setAmountFoodPackaged("NEW");
        assertEquals("NEW" , X.getAmountFoodPackaged());
    }

    @Test
    public void setAmountFoodCompost() {
        FoodSurvey X = new FoodSurvey();
        X.setAmountFoodCompost("NEW");
        assertEquals("NEW" , X.getAmountFoodCompost());
    }

    @Test
    public void equals() {
        assertTrue(foodSurvey.equals(foodSurveySame));
        assertFalse(foodSurvey.equals(foodSurveyOrganic));
        assertFalse(foodSurvey.equals(foodSurveyCompost));
        assertFalse(foodSurvey.equals(foodSurveyLocal));
        assertFalse(foodSurvey.equals(foodSurveyMeat));
        assertFalse(foodSurvey.equals(foodSurveyPackaged));
        assertFalse(foodSurvey.equals(foodSurveyWasted));
        assertTrue(foodSurvey instanceof FoodSurvey);
        assertFalse(energySurvey instanceof FoodSurvey);
        assertNotEquals(energySurvey, foodSurvey);
        assertEquals(obj, foodSurvey);
    }
}