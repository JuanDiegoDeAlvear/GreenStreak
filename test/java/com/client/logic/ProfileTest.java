package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    TransportationSurvey tSurvey2 = new TransportationSurvey(6, "Electric", 50, "car");
    FoodSurvey fSurvey2 = new FoodSurvey("some", "vegan",
            "aLot", "maybe",
            "some", "some");
    EnergySurvey eSurvey2 = new EnergySurvey("B",6,"aLot",true,true);
    LifeStyleSurvey lSurvey2 = new LifeStyleSurvey("Onh", "some", "yes", "aLot", 15);

    EnergySurvey eSurvey = new EnergySurvey("A",6,"aLot",true,true);
    FoodSurvey fSurvey = new FoodSurvey("some", "vegan", "aLot", "none", "some", "some");
    TransportationSurvey tSurvey = new TransportationSurvey(5, "Electric", 50, "car");
    LifeStyleSurvey lSurvey = new LifeStyleSurvey("Once a month", "some", "yes", "aLot", 15);
    Profile profile3 = new Profile("Ziad", eSurvey, fSurvey, tSurvey, lSurvey);
    Profile profile4 = new Profile("Ziad", eSurvey, fSurvey, tSurvey, lSurvey);
    Profile profile5 = profile3 ;
    Profile profile6 = new Profile ();

    ArrayList<String> friendlist1 = new ArrayList<> ();
    ArrayList<String> friendlist2 = new ArrayList<> ();







    @Test
    void getName() {
        Profile profile = new Profile("Noor", eSurvey, fSurvey, tSurvey, lSurvey);
        Profile profile2 = new Profile();
        profile2.setName("Noor");
        assertEquals("Noor", profile2.getName());
        assertEquals("Noor", profile.getName());
    }



    @Test
    void getEnergyProfile() {
        Profile profile = new Profile();
        profile.setEnergyProfile(eSurvey);
        assertEquals(eSurvey, profile.getEnergyProfile());
    }

    @Test
    void getFoodProfile() {
        Profile profile = new Profile();
        profile.setFoodProfile(fSurvey);
        assertEquals(fSurvey, profile.getFoodProfile());
    }

    @Test
    void getTransportationProfile() {
        Profile profile = new Profile();
        profile.setTransportationProfile(tSurvey);
        assertEquals(tSurvey, profile.getTransportationProfile());
    }

    @Test
    void getLifeStyleProfile() {
        Profile profile = new Profile();
        profile.setLifeStyleProfile(lSurvey);
        assertEquals(lSurvey, profile.getLifeStyleProfile());
    }
    @Test
    void equals1(){
        assertTrue (profile3.equals (profile5));
        assertTrue (profile3.equals (profile4));
        assertFalse (profile3.equals (eSurvey));
        assertFalse (profile3.equals (profile6));
    }
    @Test
    void equals2(){
        profile3.setName (null);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setName ("hi");
        assertFalse (profile3.equals (profile4));
    }
    @Test
    void equals6(){
        profile3.setLifeStyleProfile (null);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setLifeStyleProfile (lSurvey2);
        assertFalse (profile3.equals (profile4));
    }
    @Test
    void equals7(){
        profile3.setEnergyProfile (null);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setEnergyProfile (eSurvey2);
        assertFalse (profile3.equals (profile4));
    }
    @Test
    void equals8(){
        profile3.setFoodProfile (null);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setFoodProfile (fSurvey2);
        assertFalse (profile3.equals (profile4));
    }
    @Test
    void equals9(){
        profile3.setTransportationProfile (null);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setTransportationProfile (tSurvey2);
        assertFalse (profile3.equals (profile4));
    }

    @Test
    void equals11(){
        friendlist1.add ("HEY");
        friendlist2.add ("BYE");
        friendlist2.add ("NOO");
        profile3.setFriendList (null);
        profile4.setFriendList (friendlist1);
        assertFalse (profile3.equals (profile4));
        assertFalse (profile4.equals (profile3));
        profile3.setFriendList (friendlist2);
        assertFalse (profile3.equals (profile4));
    }

    @Test
    void removefriend(){
        profile3.addFriend ("mal");
        profile3.addFriend ("fal");
        assertEquals ("mal has been removed" , profile3.removeFriend ("mal"));
        assertEquals ("lal was not a friend to "+profile3.getName () , profile3.removeFriend ("lal"));
        assertEquals ("lolo has been added to "+profile3.getName (),profile3.addFriend ("lolo"));
    }




}