package com.client.logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculationsTest {


    private FoodSurvey food = new FoodSurvey("All", "Vegan", "Almost all", "Above average", "Above average", "None");
    private TransportationSurvey transport = new TransportationSurvey(10, "Small car", 10, "Public transport");
    private EnergySurvey energy = new EnergySurvey("Apartment", 5, "Below average", true, true);
    private LifeStyleSurvey life = new LifeStyleSurvey("More than 5", "Less than 15kg", "Glass", "10x a week", 5);

    private FoodSurvey food1 = new FoodSurvey("Most", "Vegetarian", "Above average", "Average", "Average", "About a quarter");
    private TransportationSurvey transport1 = new TransportationSurvey(10, "Medium car", 10, "Car");
    private EnergySurvey energy1 = new EnergySurvey("Small house", 5, "Average", false, false);
    private LifeStyleSurvey life1 = new LifeStyleSurvey("Around 2 - 4", "15kg - 30kg", "Paper", "5 - 10x a week", 5);

    private FoodSurvey food2 = new FoodSurvey("Some", "Low meat and dairy", "Average", "Below average", "Below average", "Half");
    private TransportationSurvey transport2 = new TransportationSurvey(10, "Medium car", 10, "Walking/Biking");
    private EnergySurvey energy2 = new EnergySurvey("Medium house", 5, "Above average", true, true);
    private LifeStyleSurvey life2 = new LifeStyleSurvey("Maybe once", "30kg - 45kg", "Plastic", "1 - 5x a week", 5);

    private FoodSurvey food3 = new FoodSurvey("None", "Average meat and dairy", "Very little", "Very little", "Very little", "All food waste");
    private TransportationSurvey transport3 = new TransportationSurvey(10, "Medium car", 10, "Motorcycle/Moped");
    private EnergySurvey energy3 = new EnergySurvey("Large house", 5, "Average", true, true);
    private LifeStyleSurvey life3 = new LifeStyleSurvey("Very rarely", "45kg - 60kg", "Metal", "Not applicable", 5);

    private TransportationSurvey transport4 = new TransportationSurvey(10, "Large car", 10, "Car");
    private TransportationSurvey transport5 = new TransportationSurvey(10, "Electric car", 10, "Car");
    private TransportationSurvey transport6 = new TransportationSurvey(10, "Small car", 10, "Car");

    private FoodSurvey food4 = new FoodSurvey("None", "Heavy meat and dairy", "Very little", "Very little", "Very little", "All food waste");

    private FoodSurvey foodx = new FoodSurvey("Algl", "Vefggan", "Almogfst all", "Abovegf average", "Abovfge average", "Nfgone");
    private TransportationSurvey transportx = new TransportationSurvey(10, "Smallgf car", 10, "Publgfic transport");
    private EnergySurvey energyx = new EnergySurvey("Apargftment", 5, "Belogfw average", true, true);
    private LifeStyleSurvey lifex = new LifeStyleSurvey("Moregf than 5", "Less thagfgfn 15kg", "Glagfss", "10xgf a week", 5);

    @Test
    public void foodCompostTest() {
        assertEquals(Calculations.foodCompost(food), 0.25, 0);
        assertEquals(Calculations.foodCompost(food1), 0.1875, 0);
        assertEquals(Calculations.foodCompost(food2), 0.125, 0);
        assertEquals(Calculations.foodCompost(food3), 0, 0);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);


    }

    @Test
    public void foodDietTest() {
        assertEquals(Calculations.foodDiet(food), 0, 0);
        assertEquals(Calculations.foodDiet(food1), 0.1, 0);
        assertEquals(Calculations.foodDiet(food2), 0.25, 0);
        assertEquals(Calculations.foodDiet(food3), 0.4, 0);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);


    }

    @Test
    public void foodLocalTest() {
        assertEquals(Calculations.foodLocal(food), 0.1, 0);
        assertEquals(Calculations.foodLocal(food1), 0.2, 0);
        assertEquals(Calculations.foodLocal(food2), 0.3, 0);
        assertEquals(Calculations.foodLocal(food3), 0.5, 0);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);


    }

    @Test
    public void foodTotalTest() {
        assertEquals(Calculations.foodTotal(food), 1.045, 0);
        assertEquals(Calculations.foodTotal(food1), 1.0875, 0);
        assertEquals(Calculations.foodTotal(food2), 1.2375, 0);
        assertEquals(Calculations.foodTotal(food3), 1.32, 0.1);
        assertEquals(Calculations.foodTotal(food4), 1.48, 0.1);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);


    }

    @Test
    public void foodPackTest() {
        assertEquals(Calculations.foodPack(food), 0.6, 0);
        assertEquals(Calculations.foodPack(food1), 0.4, 0);
        assertEquals(Calculations.foodPack(food2), 0.2, 0);
        assertEquals(Calculations.foodPack(food3), 0.05, 0);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);

    }

    @Test
    public void foodOrganicTest() {
        assertEquals(Calculations.foodOrganic(food), 0, 0);
        assertEquals(Calculations.foodOrganic(food1), 0.2, 0);
        assertEquals(Calculations.foodOrganic(food2), 0.5, 0);
        assertEquals(Calculations.foodOrganic(food3), 0.7, 0);
        assertEquals(Calculations.foodCompost(foodx), 0, 0);

    }

    @Test
    public void transportFlyTest() {
        assertEquals(Calculations.transportFly(transport), 2.5, 0);
        assertEquals(Calculations.transportFly(transport1), 2.5, 0);

    }

    @Test
    public void transportTotalTest() {
        assertEquals(Calculations.transportTotal(transport), 2.95, 0.1);
        assertEquals(Calculations.transportTotal(transport1), 3.084, 0.1);
        assertEquals(Calculations.transportTotal(transport2), 2.7, 0.1);
        assertEquals(Calculations.transportTotal(transport3), 2.992, 0.1);
        assertEquals(Calculations.transportTotal(transport4), 3.376, 0.1);
        assertEquals(Calculations.transportTotal(transport5), 2.757, 0.1);
        assertEquals(Calculations.transportTotal(transport6), 2.986, 0.1);
        assertEquals(Calculations.transportTotal(transportx), 2.7, 0);
    }

    @Test
    public void energy() {
        assertEquals(Calculations.energy(energy), 0.035, 0.1);
        assertEquals(Calculations.energy(energy1), 0.238, 0.1);
        assertEquals(Calculations.energy(energy2), 0.055, 0.1);
        assertEquals(Calculations.energy(energy3), 0.08, 0.1);
        assertEquals(Calculations.energy(energyx), 0, 0);

    }

    @Test
    public void totalCarbon() {
        assertEquals(Calculations.totalCarbon(food, transport, energy), 4.02, 0.1);
        assertEquals(Calculations.totalCarbon(food1, transport1, energy1), 4.4095, 0.1);
        assertEquals(Calculations.totalCarbon(food2, transport2, energy2), 3.99, 0.1);
        assertEquals(Calculations.totalCarbon(food3, transport3, energy3), 4.39, 0.1);
        assertEquals(Calculations.totalCarbon(foodx, transportx, energyx), 2.7, 0);
    }

    @Test
    public void footPrintFood() {
        assertEquals(Calculations.footPrintFood(food), 0.12, 0.1);
        assertEquals(Calculations.footPrintFood(food1), 0.3, 0.1);
        assertEquals(Calculations.footPrintFood(food2), 0.5, 0.1);
        assertEquals(Calculations.footPrintFood(food3), 0.7, 0.1);
        assertEquals(Calculations.footPrintFood(foodx), 0, 0);

    }

    @Test
    public void footPrintTransport() {
        assertEquals(Calculations.footPrintTransport(transport), 1.4, 0.1);
        assertEquals(Calculations.footPrintTransport(transport1), 1.5, 0.1);
        assertEquals(Calculations.footPrintTransport(transport2), 1.1, 0.1);
        assertEquals(Calculations.footPrintTransport(transport3), 1.41, 0.1);
        assertEquals(Calculations.footPrintTransport(transport4), 1.55, 0.1);
        assertEquals(Calculations.footPrintTransport(transport5), 1.4, 0.1);
        assertEquals(Calculations.footPrintTransport(transport6), 1.4, 0.1);
        assertEquals(Calculations.footPrintTransport(transportx), 1.1, 0);


    }

    @Test
    public void footPrintEnergy() {
        assertEquals(Calculations.footPrintEnergy(energy), 0.87, 0.1);
        assertEquals(Calculations.footPrintEnergy(energy1), 1.0, 0.1);
        assertEquals(Calculations.footPrintEnergy(energy2), 0.32, 0.1);
        assertEquals(Calculations.footPrintEnergy(energy3), 0.57, 0.1);
        assertEquals(Calculations.footPrintEnergy(energyx), 0.07, 0);

    }

    @Test
    public void footPrintLife() {
        assertEquals(Calculations.footPrintLife(life), 3.05, 0.1);
        assertEquals(Calculations.footPrintLife(life1), 2.15, 0.1);
        assertEquals(Calculations.footPrintLife(life2), 1.3, 0.1);
        assertEquals(Calculations.footPrintLife(life3), 1.25, 0.1);
        assertEquals(Calculations.footPrintLife(lifex), 0.05, 0);

    }

    @Test
    public void footTotal() {
        assertEquals(Calculations.totalEco(food, transport, energy, life), 5.44, 0.1);
    }
}
