package com.client.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnergySurveyTest {

    EnergySurvey eSurveyFull = new EnergySurvey("Small", 6, "aLot", true, true);
    EnergySurvey eSurveyFull2 = new EnergySurvey("Small", 6, "aLot", true, true);
    EnergySurvey eSurveyFullBig = new EnergySurvey("Big", 6, "aLot", true, true);
    EnergySurvey eSurveyFull5 = new EnergySurvey("Small", 5, "aLot", true, true);
    EnergySurvey eSurveyFullnone = new EnergySurvey("Small", 6, "none", true, true);
    EnergySurvey eSurveyFullfalse1 = new EnergySurvey("Small", 6, "aLot", false, true);
    EnergySurvey eSurveyFullfalse2 = new EnergySurvey("Small", 6, "aLot", true, false);
    EnergySurvey empty = new EnergySurvey();
    EnergySurvey empty2 = new EnergySurvey();
    EnergySurvey emptySurvey = new EnergySurvey("", 0, "", false, false);
    EnergySurvey nullSurvey = new EnergySurvey(null, 0, null, false, false);
    Object obj = new LifeStyleSurvey("f", "f", "f", "f", 5);


    @Test
    public void setTypeHouse() {
        EnergySurvey X = new EnergySurvey();
        X.setTypeHouse("XXX");
        assertEquals("XXX",X.getTypeHouse());
    }

    @Test
    public void setAmountRoommates() {
        EnergySurvey X = new EnergySurvey("typeofhouse" ,
                2 , "amount of energy" ,
                true , true );
        X.setAmountRoommates(4);
        assertEquals(4, X.getAmountRoommates());
    }

    @Test
    public void setAmountEnergy() {
        EnergySurvey X = new EnergySurvey("typeofhouse" ,
                2 , "amount of energy" ,
                true , true );
        X.setAmountEnergy("AAB");
        assertEquals("AAB" , X.getAmountEnergy());
    }

    @Test
    public void setGreenEnergy() {
        EnergySurvey X = new EnergySurvey("typeofhouse" ,
                2 , "amount of energy" ,
                true , true );
        X.setGreenEnergy(false);
        assertEquals(false,X.getGreenEnergy());
    }

    @Test
    public void setEfficientLighting() {
        EnergySurvey X = new EnergySurvey("typeofhouse" ,
                2 , "amount of energy" ,
                true , true );
        X.setEfficientLighting(false);
        assertEquals(false,X.getEfficientLighting());
    }

    @Test
    public void equals(){
        assertFalse(eSurveyFull.equals(eSurveyFull5));
        assertFalse(eSurveyFull.equals(eSurveyFullBig));
        assertFalse(eSurveyFull.equals(eSurveyFullnone));
        assertFalse(eSurveyFull.equals(eSurveyFullfalse1));
        assertFalse(eSurveyFull.equals(eSurveyFullfalse2));
        assertEquals(empty, empty2);
    }

    @Test
    public void equals2(){
      assertFalse(obj instanceof EnergySurvey);
      assertTrue(eSurveyFull instanceof EnergySurvey);
      assertTrue(!(obj.equals(eSurveyFull)));
      assertNotEquals(obj, eSurveyFull);

    }

    @Test
    public void equals3(){
        EnergySurvey energySurvey1 = new EnergySurvey("Small", 6, "aLot", true, true);
        EnergySurvey obj = energySurvey1;
        assertEquals(obj, energySurvey1);
    }

}