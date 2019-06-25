package com.server.database;

import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.TransportationPost;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import static com.server.debug.DebugTools.debug;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbImplementTest {

    @Mock
    DbAdapter dbAdapter_mock;

    @Mock
    Connection conn_mock;

    @Mock
    PreparedStatement preparedStatement_mock;

    @Mock
    ResultSet mocked_set;

    private DbImplement dbImplement;

    @Before
    public void setUp() throws Exception {
        when(conn_mock.prepareStatement(any())).thenReturn(preparedStatement_mock);
        when(dbAdapter_mock.getConn()).thenReturn(conn_mock);

        dbImplement = DbImplement.getInstance(dbAdapter_mock);
    }

    @Test
    public void getInstance() {
        assertEquals(dbImplement, DbImplement.getInstance());
    }

    @Test
    public void searchUsername() {

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Raul");
        expected.add("RaulSantana");
        expected.add("Raul123");
        expected.add("Raul41");

        try {

            given(mocked_set.next()).willReturn(true,true,true,true,false);
            given(mocked_set.getString(1)).willReturn("Raul","RaulSantana","Raul123","Raul41");
            when(dbAdapter_mock.query(preparedStatement_mock)).thenReturn(mocked_set);

            assertEquals(expected, dbImplement.searchUsername("Raul"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFoodPosts() {
        ArrayList<FoodPost> expected = new ArrayList<>();
        FoodPost post1 = new FoodPost("Breakfast","Vegetarian");
        FoodPost post2 = new FoodPost("Lunch","Meat");
        FoodPost post3 = new FoodPost("Breakfast","Meat");

        expected.add(post1);
        expected.add(post2);
        expected.add(post3);

        try {

            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getString("meal")).willReturn("Breakfast","Lunch","Breakfast");
            given(mocked_set.getString("type")).willReturn("Vegetarian","Meat","Meat");

            when(dbAdapter_mock.query(anyString())).thenReturn(mocked_set);

            assertEquals(expected,DbImplement.getInstance().getFoodPosts("username","Raul"));
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }

        //expected.add()
    }

    @Test
    public void getConsumptionPosts() {
        ArrayList<ConsumptionPost> expected = new ArrayList<>();
        ConsumptionPost post1 = new ConsumptionPost("Was cools",true);
        ConsumptionPost post2 = new ConsumptionPost("Saved kittens",false);
        ConsumptionPost post3 = new ConsumptionPost("Recycled",true);

        expected.add(post1);
        expected.add(post2);
        expected.add(post3);

        try {

            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getString("consume")).willReturn("Was cools","Saved kittens","Recycled");
            given(mocked_set.getBoolean("state")).willReturn(true,false,true);

            when(dbAdapter_mock.query(anyString())).thenReturn(mocked_set);

            assertEquals(expected,DbImplement.getInstance().getConsumptionPost("username","Raul"));
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }

        //expected.add()
    }

    @Test
    public void getTransportationPosts() {
        ArrayList<TransportationPost> expected = new ArrayList<>();
        TransportationPost post1 = new TransportationPost(20,"On piggy back");
        TransportationPost post2 = new TransportationPost(1234,"Walking backwards");
        TransportationPost post3 = new TransportationPost(42,"Bicycle");

        expected.add(post1);
        expected.add(post2);
        expected.add(post3);

        try {

            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getDouble("kilometers")).willReturn(20d,1234d,42d);
            given(mocked_set.getString("type")).willReturn("On piggy back","Walking backwards","Bicycle");

            when(dbAdapter_mock.query(anyString())).thenReturn(mocked_set);

            assertEquals(expected,DbImplement.getInstance().getTransportationPost("username","Raul"));
        } catch (SQLException e) {
            fail();
            e.printStackTrace();
        }

        //expected.add()
    }

    @Test
    public void getTransportationPostFromSet() {
        TransportationPost post2 = new TransportationPost(23l, "Public Transport");
        post2.setUsername("Raul");

        try {
            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getDouble("kilometers")).willReturn(23d,42d);
            given(mocked_set.getString("type")).willReturn("Public Transport","Bike");
            given(mocked_set.getString("username")).willReturn("Raul","Juan");

            assertEquals(post2,dbImplement.transportationPostFromSet(mocked_set));
        }catch (SQLException e){
            fail();
        }
    }

    @Test
    public void getConsumptionPostFromSet() {
        ConsumptionPost post2 = new ConsumptionPost("Recycle", true);
        post2.setUsername("Raul");

        try {
            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getString("consume")).willReturn("Recycle","LocalFood");
            given(mocked_set.getBoolean("state")).willReturn(true,false);
            given(mocked_set.getString("username")).willReturn("Raul","Juan");

            assertEquals(post2,dbImplement.consumptionPostFromSet(mocked_set));
        }catch (SQLException e){
            fail();
        }
    }

    @Test
    public void foodPostFromSetFromSet(){
        Timestamp stamp = new Timestamp(new Date().getTime());
        FoodPost expected = new FoodPost("Breakfast", "Vegetarian");
        expected.setDate(stamp);
        expected.setUsername("Raul");

        try {
            given(mocked_set.next()).willReturn(true,true,true,false);
            given(mocked_set.getString("meal")).willReturn("Breakfast","Dinner");
            given(mocked_set.getString("type")).willReturn("Vegetarian","Meat");
            given(mocked_set.getString("username")).willReturn("Raul","Juan");
            given(mocked_set.getTimestamp("date")).willReturn(stamp);


            FoodPost actual = dbImplement.foodPostFromSet(mocked_set);

            debug(expected.toString());
            debug(actual.toString());

            assertEquals(expected, actual);
        }catch (SQLException e){
            fail();
        }
    }
}