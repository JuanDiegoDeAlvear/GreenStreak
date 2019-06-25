package com.client.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransportationSurveyTest {

    TransportationPost ziad = new TransportationPost(12,"km");

    TransportationSurvey Yf = new TransportationSurvey(1 , "bus", 2 ,"BIG" );
    TransportationSurvey YS = Yf;
    TransportationSurvey Y = new TransportationSurvey(1 , "bus", 2 ,"BIG" );
    TransportationSurvey YY = new TransportationSurvey(1 , "bas", 2 ,"BIG" );
    TransportationSurvey YYY = new TransportationSurvey(1 , "bus", 3 ,"BIG" );
    TransportationSurvey YYYY = new TransportationSurvey(1 , "bus", 2 ,"obig" );
    TransportationSurvey YYYYY = new TransportationSurvey(5 , null, 2 ,"BIG" );
    TransportationSurvey X = new TransportationSurvey();
    TransportationSurvey XX = new TransportationSurvey();
    TransportationSurvey XXX = new TransportationSurvey(0 , null ,0, null);
    TransportationSurvey XXXX = new TransportationSurvey(0 , "" ,0, "");






    @Test
    public void setAveHoursFly() {
        TransportationSurvey X = new TransportationSurvey();
        TransportationSurvey Y = new TransportationSurvey(1 , "bus", 2 ,"BIG" );
        X.setAveHoursFly(23);
        assertEquals(23,X.getAveHoursFly());
        assertEquals(1,Y.getAveHoursFly());
    }

    @Test
    public void setTypeOfCar() {
        TransportationSurvey X = new TransportationSurvey();
        X.setTypeOfCar("Big");
        assertEquals("Big",X.getTypeOfCar());
    }

    @Test
    public void setAveKmPerDay() {
        TransportationSurvey X = new TransportationSurvey();
        X.setAveKmPerDay(23);
        assertEquals(23,X.getAveKmPerDay());
    }

    @Test
    public void setMainModeTransport() {
        TransportationSurvey X = new TransportationSurvey();
        X.setMainModeTransport("BUS");
        assertEquals("BUS",X.getMainModeTransport());
    }

    @Test
    public void equals() {
        assertFalse (Y.equals (YY));
        assertFalse (Y.equals (YYY));
        assertFalse (Y.equals (YYYYY));
        assertFalse (Y.equals (YYYY));
        assertFalse (X.equals (Y));
        assertTrue (XX.equals (X));
        assertTrue (Yf.equals (Y));
        assertTrue (XXX.equals (XX));
        assertFalse (XXXX.equals (XX));
        assertTrue(Yf.equals(YS));
        assertFalse (Y.equals (ziad));




    }
}