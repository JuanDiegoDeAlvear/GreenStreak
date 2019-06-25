package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransportationPostTest {

    Date d = new Date (34);

    
    TransportationPost go = new TransportationPost(12,"km");
    TransportationPost go3 = new TransportationPost(12,"km");
    TransportationPost go2 = go ;
    TransportationSurvey go4 = new TransportationSurvey ();

    SuperPost poster1 = new SuperPost ("",d,"postclass" ,12);
    SuperPost poster2 = new SuperPost ("",d,"postclass" ,12);
    SuperPost poster3 = poster1 ;
    SuperPost poster4 = new SuperPost () ;

    SignUpUser u = new SignUpUser ();



    @Test
    void setKilometer() {
        TransportationPost tPost = new TransportationPost();
        TransportationPost tPost2 = new TransportationPost(20.3, "bike");
        tPost.setKilometer(10.5);
        assertEquals(10.5, tPost.getKilometer());
        assertEquals(20.3, tPost2.getKilometer());
    }

    @Test
    void setType() {
        TransportationPost tPost3 = new TransportationPost();
        tPost3.setType("car");
        assertEquals("car", tPost3.getType());
    }

    @Test
    void equals() {
    assertTrue (go2.equals (go));
    assertFalse (go.equals (go4));
    assertFalse (go.equals (poster2));


    }
    @Test
    void equalss(){
        go3.setUsername ("bla");
        go.setUsername(null);
        assertFalse (go.equals (go3));
        assertFalse (go3.equals (go));
        go.setUsername ("idk");
        assertFalse (go.equals (go3));

    }
    @Test
    void equals3(){
        Date date = new Date (23/23/23);
        Date date2 = new Date ();
        go3.setDate (date2);
        go.setDate (null);
        assertFalse (go.equals (go3));
        assertFalse (go3.equals (go));
        go.setDate (date);
        assertFalse (go.equals (go3));

    }

    @Test
    void equals4(){
        go.setKilometer (0);
        assertFalse (go.equals (go3));
        assertFalse (go3.equals (go));
        go.setKilometer (100);
        go3.setKilometer (12);
        assertFalse (go.equals (go3));

    }

    @Test
    void equals5(){
        go.setType (null);
        assertFalse (go.equals (go3));
        assertFalse (go3.equals (go));
        go.setType ("ids");
        go3.setType ("idk");
        assertFalse (go.equals (go3));

    }

//    @Test
//    void equals6(){
//        go.se (null);
//        assertFalse (go.equals (go3));
//        assertFalse (go3.equals (go));
//        go.setType ("ids");
//        go3.setType ("idk");
//        assertFalse (go.equals (go3));
//
//    }
//    @Test
//    void equals7(){
//        go.setUsername (null);
//        go3.setUsername ("me");
//        assertFalse (go.equals (go3));
//
//    }
    @Test
    void equals8(){
        ArrayList<String> ix = new ArrayList<> ();
        assertTrue (poster1.equals (poster2));
        assertTrue (poster1.equals (poster3));
        assertFalse (poster1.equals (poster4));
        assertFalse (poster1.equals (u));
        assertFalse (poster1.equals (ix));


    }
    @Test
    void equals9(){
        poster1.setUsername (null);
        assertFalse (poster1.equals (poster2));
        assertFalse (poster2.equals (poster1));
        poster1.setUsername ("mam");
        assertFalse (poster1.equals (poster2));

    }
    @Test
    void equals10(){
        poster1.setPostClass (null);
        assertFalse (poster1.equals (poster2));
        assertFalse (poster2.equals (poster1));
        poster1.setPostClass ("mam");
        assertFalse (poster1.equals (poster2));
        assertEquals ("mam" , poster1.getPostClass());
    }
    @Test
    void equals11(){
        Date dd = new Date (343) ;
        poster1.setDate (null);
        assertFalse (poster1.equals (poster2));
        assertFalse (poster2.equals (poster1));
        poster1.setDate (dd);
        assertFalse (poster1.equals (poster2));
    }


}