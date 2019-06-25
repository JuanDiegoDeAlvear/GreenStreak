package com.client.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class LifeStyleSurveyTest {

    LifeStyleSurvey lifeStyleSurvey = new LifeStyleSurvey("Never", "none", "no", "none", 15);
    LifeStyleSurvey lifeStyleSurveySame = new LifeStyleSurvey("Never", "none", "no", "none", 15);
    LifeStyleSurvey lifeStyleSurveyPurchase = new LifeStyleSurvey("Purchase", "none", "no", "none", 15);
    LifeStyleSurvey lifeStyleSurveyWaste = new LifeStyleSurvey("Never", "Waste", "no", "none", 15);
    LifeStyleSurvey lifeStyleSurveyRecycle = new LifeStyleSurvey("Never", "none", "recycle", "none", 15);
    LifeStyleSurvey lifeStyleSurveyAppliance = new LifeStyleSurvey("Never", "none", "no", "appliance", 15);
    LifeStyleSurvey lifeStyleSurvey20 = new LifeStyleSurvey("Never", "none", "no", "none", 20);
    Object foodSurvey = new FoodSurvey("none", "Vegan", "none", "none", "none", "none");
    LifeStyleSurvey obj = lifeStyleSurvey;

    @Test
    public void getHowOftenBigPurchase() {
        LifeStyleSurvey X = new LifeStyleSurvey();
        LifeStyleSurvey Y = new LifeStyleSurvey("GH" , "PH" , "CH" , "LH" , 2);
        X.setHowOftenBigPurchase("twice");
        assertEquals("twice",X.getHowOftenBigPurchase());
        assertEquals("GH",Y.getHowOftenBigPurchase());
    }

    @Test
    public void getWeeklyWaste() {
        LifeStyleSurvey X = new LifeStyleSurvey();
        X.setWeeklyWaste("Thee");
        assertEquals("Thee",X.getWeeklyWaste());
    }


    @Test
    public void getRecycle() {
        LifeStyleSurvey X = new LifeStyleSurvey();
        X.setRecycle("oh");
        assertEquals("oh",X.getRecycle());
    }



    @Test
    public void getApplianceUse() {
        LifeStyleSurvey X = new LifeStyleSurvey();
        X.setApplianceUse("fg");
        assertEquals("fg",X.getApplianceUse());
    }



    @Test
    public void getAveShowerTime() {
        LifeStyleSurvey X = new LifeStyleSurvey();
        X.setAveShowerTime(4);
        assertEquals(4,X.getAveShowerTime());
    }

    @Test
    public void equals(){
        //assertTrue(lifeStyleSurvey.equals(lifeStyleSurveyAppliance));
        assertFalse(lifeStyleSurvey.equals(lifeStyleSurvey20));
        assertFalse(lifeStyleSurvey.equals(lifeStyleSurveyPurchase));
        assertFalse(lifeStyleSurvey.equals(lifeStyleSurveyRecycle));
        assertFalse(lifeStyleSurvey.equals(lifeStyleSurveyWaste));
        //assertTrue(lifeStyleSurvey.equals(lifeStyleSurveySame));
        assertTrue(obj.equals(lifeStyleSurvey));
        assertTrue(lifeStyleSurvey instanceof LifeStyleSurvey);
        assertFalse(foodSurvey instanceof LifeStyleSurvey);
        assertNotEquals(foodSurvey, lifeStyleSurvey);
    }

}