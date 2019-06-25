package com.client.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SignUpUserTest {

    EnergySurvey eSurvey = new EnergySurvey("A",6,"aLot",true,true);
    FoodSurvey fSurvey = new FoodSurvey("some", "vegan",
            "aLot", "none",
            "some", "some");
    TransportationSurvey tSurvey = new TransportationSurvey(5, "Electric", 50, "car");
    LifeStyleSurvey lSurvey = new LifeStyleSurvey("Once a month", "some", "yes", "aLot", 15);
    TransportationSurvey tSurvey2 = new TransportationSurvey(6, "Electric", 50, "car");
    FoodSurvey fSurvey2 = new FoodSurvey("some", "vegan",
            "aLot", "maybe",
            "some", "some");
    EnergySurvey eSurvey2 = new EnergySurvey("B",6,"aLot",true,true);
    LifeStyleSurvey lSurvey2 = new LifeStyleSurvey("Onh", "some", "yes", "aLot", 15);


    SignUpUser s1 = new SignUpUser("HEY", "HEY" , "HEY" ,eSurvey , fSurvey , tSurvey , lSurvey );
    SignUpUser s2 = s1;
    SignUpUser s3 = new SignUpUser("","","",null , null , tSurvey , lSurvey);
    SignUpUser s4 = new SignUpUser ();
    SignUpUser s5 = new SignUpUser("HEY", "HEY" , "HEY" ,eSurvey , fSurvey , tSurvey , lSurvey );


    @Test
    public void setUsername(){
        SignUpUser NW = new SignUpUser("HEY", "HEY" , "HEY" ,eSurvey , fSurvey , tSurvey , lSurvey );
        NW.setUsername("HEY");
        assertEquals("HEY", NW.getUsername());

    }
    @Test
    public void setPassword(){
        SignUpUser NW = new SignUpUser();
        NW.setPassword("HEY");
        assertEquals("HEY", NW.getPassword());


    }
    @Test
    public void setEmail(){
        SignUpUser NW = new SignUpUser();
        NW.setEmail("HEY");
        assertEquals("HEY", NW.getEmail());


    }
    @Test
    void getTransportationProfile() {
        SignUpUser NW = new SignUpUser();
        NW.setTransportationProfile(tSurvey);
        assertEquals(tSurvey, NW.getTransportationProfile());
    }

    @Test
    void getLifeStyleProfile() {
        SignUpUser NW = new SignUpUser();
        NW.setLifeStyleProfile(lSurvey);
        assertEquals(lSurvey, NW.getLifeStyleProfile());
    }
    @Test
    void getEnergyProfile() {
        SignUpUser NW = new SignUpUser();
        NW.setEnergyProfile(eSurvey);
        assertEquals(eSurvey, NW.getEnergyProfile());
    }

    @Test
    void getFoodProfile() {
        SignUpUser NW = new SignUpUser();
        NW.setFoodProfile(fSurvey);
        assertEquals(fSurvey, NW.getFoodProfile());

    }

    @Test
    void equals() {
        assertTrue (s1.equals (s5));
        assertTrue (s1.equals (s2));
        assertFalse (s1.equals (eSurvey));

    }
    @Test
    void equals2() {
        assertFalse (s1.equals (s4));
        assertFalse (s2.equals (s3));
        s1.setPassword (null);
        assertFalse (s1.equals (s5));
        s1.setEmail (null);
        assertFalse (s1.equals (s5));
    }
    @Test
    void equals3() {
        s4.setUsername ("me");
        assertFalse (s4.equals (s1));
        s5.setPassword (null);
        assertFalse (s1.equals (s5));
        s5.setEmail (null);
        assertFalse (s1.equals (s5));

    }
    @Test
    void equals4() {
        s1.setUsername (null);
        assertFalse (s1.equals (s5));
        assertFalse (s5.equals (s1));
        s1.setEnergyProfile (eSurvey2);
        assertFalse (s5.equals (s1));
        s5.setEmail ("lolo");
        assertFalse (s1.equals (s5));
        s5.setPassword ("olol");
        assertFalse (s1.equals (s5));

    }
    @Test
    void equals6() {
        s1.setTransportationProfile (null);
        assertFalse (s1.equals (s5));
        assertFalse (s5.equals (s1));
        s1.setTransportationProfile (tSurvey2);
        assertFalse (s5.equals (s1));
    }
    @Test
    void equals5() {
        s1.setFoodProfile (null);
        assertFalse (s1.equals (s5));
        assertFalse (s5.equals (s1));
        s1.setFoodProfile (fSurvey2);
        assertFalse (s5.equals (s1));

    }
    @Test
    void equals7() {
        s1.setLifeStyleProfile (null);
        assertFalse (s1.equals (s5));
        assertFalse (s5.equals (s1));
        s1.setLifeStyleProfile (lSurvey2);
        assertFalse (s5.equals (s1));

    }
    @Test
    void equals8() {
        s1.setEnergyProfile (null);
        assertFalse (s1.equals (s5));
        assertFalse (s5.equals (s1));
        s1.setEnergyProfile (eSurvey2);
        assertFalse (s5.equals (s1));

    }



}
