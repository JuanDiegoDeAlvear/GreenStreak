package com.server.database;

import com.client.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseManagerTest {

    @Mock
    DbImplement dbImplement_mock;

    private DatabaseManager databaseManager;


    @Before
    public void setUp() throws Exception {

        databaseManager = DatabaseManager.getInstance(dbImplement_mock);
    }

    @Test
    public void getInstanceReturnExistingInstance() {
        assertEquals(databaseManager, DatabaseManager.getInstance());
    }

    @Test
    public void searchUsername() {
        ArrayList<String> givenStrings = new ArrayList<>();
        givenStrings.add("RaulSantana");
        givenStrings.add("RaulASdaz");

        String[] expected = {"RaulSantana", "RaulASdaz"};

        when(dbImplement_mock.searchUsername(anyString())).thenReturn(givenStrings);

        assertArrayEquals(databaseManager.searchUsername("Raul"),expected);
    }

    @Test
    public void deleteUser(){
        when(dbImplement_mock.deleteUser(anyString())).thenReturn(true);
        assertTrue(databaseManager.deleteUser("Juan"));
    }

    @Test
    public void searchUsernameWrong() {
        ArrayList<String> givenStrings = new ArrayList<>();
        givenStrings.add("RaulSantana");
        givenStrings.add("RaulASdaz");

        String[] wrong = {"RaulSantana"};

        when(dbImplement_mock.searchUsername(anyString())).thenReturn(givenStrings);

        assertFalse(databaseManager.searchUsername("Raul").equals(wrong));
    }

    @Test
    public void getFoodPosts() {
        ArrayList<FoodPost> givenPosts = new ArrayList<>();
        FoodPost post1 = new FoodPost("breakfast","vegetarian");
        FoodPost post2 = new FoodPost("dinner","vegan");

        givenPosts.add(post1);
        givenPosts.add(post2);

        FoodPost[] expected = {post1,post2};

        when(dbImplement_mock.getFoodPosts(anyString(),anyString())).thenReturn(givenPosts);

        assertArrayEquals(expected,databaseManager.getFoodPosts("username", "Raul"));
    }

    @Test
    public void getTransportationPosts() {

        ArrayList<TransportationPost> givenPosts = new ArrayList<>();
        TransportationPost post1 = new TransportationPost(23,"bike");
        TransportationPost post2 = new TransportationPost(12,"foot");

        givenPosts.add(post1);
        givenPosts.add(post2);

        TransportationPost[] expected = {post1,post2};

        when(dbImplement_mock.getTransportationPost(anyString(),anyString())).thenReturn(givenPosts);

        assertArrayEquals(expected,databaseManager.getTransportationPosts("username", "Raul"));
    }

    @Test
    public void getConsumptionPosts() {

        ArrayList<ConsumptionPost> givenPosts = new ArrayList<>();
        ConsumptionPost post1 = new ConsumptionPost("recycled",false);
        ConsumptionPost post2 = new ConsumptionPost("idk",true);

        givenPosts.add(post1);
        givenPosts.add(post2);

        ConsumptionPost[] expected = {post1,post2};

        when(dbImplement_mock.getConsumptionPost(anyString(),anyString())).thenReturn(givenPosts);

        assertArrayEquals(expected,databaseManager.getConsumptionPosts("username", "Raul"));
    }

    @Test
    public void addConsumptionPost() {
        when(dbImplement_mock.addConsumptionPost(anyObject())).thenReturn(true);
        assertTrue(databaseManager.addConsumptionPost(new ConsumptionPost("recycled", false)));
    }

    @Test
    public void addFoodPost() {
        when(dbImplement_mock.addFoodPost(anyObject())).thenReturn(true);
        assertTrue(databaseManager.addFoodPost(new FoodPost("lunch", "vegan")));
    }

    @Test
    public void addTransportationPost() {
        when(dbImplement_mock.addTransportationPost(anyObject())).thenReturn(true);
        assertTrue(databaseManager.addTransportationPost(new TransportationPost(3, "car")));
    }

    @Test
    public void checkLogin() {
        when(dbImplement_mock.checkLogin(anyObject())).thenReturn(true);
        assertTrue(databaseManager.checkLogin(new User()));
    }

    @Test
    public void signUpUser() {
        when(dbImplement_mock.signupUser(anyObject())).thenReturn(false);
        assertFalse(databaseManager.signUpUser(new SignUpUser()));
    }
}