package com.server.database;

import com.client.logic.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseProfileTest {

    @Mock
    DbAdapter dbAdapterMock;

    @Mock
    ResultSet resultSetMock;


    DatabaseProfile databaseProfile;

    @Before
    public void setUp() throws Exception {
        DbAdapter.setDbAdapter_instance(dbAdapterMock);
        databaseProfile = DatabaseProfile.getInstance();
        given(dbAdapterMock.query(anyString())).willReturn(resultSetMock);
    }

    @Test
    public void getInstance() {
        assertEquals(databaseProfile,DatabaseProfile.getInstance());
    }

    @Test
    public void getInstance1() {
        assertEquals(databaseProfile,databaseProfile.getInstance(dbAdapterMock));
    }

    @Test
    public void setDatabaseProfileInstance() {
        DatabaseProfile.setDatabaseProfileInstance(databaseProfile);
        assertEquals(databaseProfile, DatabaseProfile.getInstance());

    }


    @Test
    public void getTransportationSurvey() {
        TransportationSurvey expected = new TransportationSurvey();
        expected.setAveHoursFly(23);
        expected.setAveKmPerDay(4);
        expected.setMainModeTransport("horse");
        expected.setTypeOfCar("flying");

        try {
            given(resultSetMock.next()).willReturn(true);
            given(resultSetMock.getDouble("aveHoursFly")).willReturn(23d);
            given(resultSetMock.getDouble("aveKmPerDay")).willReturn(4d);
            given(resultSetMock.getString("mainModeTransport")).willReturn("horse");
            given(resultSetMock.getString("typeOfCar")).willReturn("flying");

            assertEquals(databaseProfile.getTransportationSurvey("Raul"), expected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFoodSurvey() {

        FoodSurvey expected = new FoodSurvey();
        expected.setAmountFoodCompost("compost");
        expected.setAmountFoodPackaged("packaged");
        expected.setAmountLocalFood("local");
        expected.setAmountOrganicFood("organic");
        expected.setAmountWastedFood("wasted");
        expected.setDietChoice("diet");

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getString("compost")).willReturn("compost");
            given(resultSetMock.getString("packaged")).willReturn("packaged");
            given(resultSetMock.getString("area")).willReturn("local");
            given(resultSetMock.getString("organic")).willReturn("organic");
            given(resultSetMock.getString("wasted")).willReturn("wasted");
            given(resultSetMock.getString("diet_choice")).willReturn("diet");

            assertEquals(databaseProfile.getFoodSurvey("Juan"), expected);
        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Test
    public void getEnergySurvey() {

        EnergySurvey expected = new EnergySurvey();
        expected.setAmountEnergy("amount");
        expected.setAmountRoommates(4);
        expected.setEfficientLighting(true);
        expected.setGreenEnergy(true);
        expected.setTypeHouse("type");

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getString("amountEnergy")).willReturn("amount");
            given(resultSetMock.getInt("amountRoomates")).willReturn(4);
            given(resultSetMock.getBoolean("efficientLighting")).willReturn(true);
            given(resultSetMock.getBoolean("greenEnergy")).willReturn(true);
            given(resultSetMock.getString("typeHouse")).willReturn("type");

            assertEquals(databaseProfile.getEnergySurvey("Juan"), expected);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

//    @Test
//    public void getLifeStyleSurvey() {
//
//        LifeStyleSurvey expected = new LifeStyleSurvey();
//        expected.setApplianceUse("appliance");
//        expected.setAveShowerTime(1);
//        expected.setHowOftenBigPurchase("often");
//        expected.setRecycle("yes");
//        expected.setWeeklyWaste("waste");
//
//        try {
//
//            given(resultSetMock.next()).willReturn(true);
//
//            given(resultSetMock.getString("applianceUse")).willReturn("appliance");
//            given(resultSetMock.getInt("aveShowerTime")).willReturn(1);
//            given(resultSetMock.getString("howOftenBigPurchase")).willReturn("often");
//            given(resultSetMock.getString("recycle")).willReturn("yes");
//            given(resultSetMock.getString("weeklyWaste")).willReturn("waste");
//
//            assertEquals(expected, databaseProfile.getLifeStyleSurvey("Juan"));
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//
//        }
//
//    }

    @Test
    public void getCarbonFootprint() {

        double expected = 1.0;

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getDouble("footprint")).willReturn(1.0);

            assertEquals(databaseProfile.getCarbonFootprint("Juan"), expected, 0.001);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Test
    public void getFootprintEco() {

        double expected = 1.0;

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getDouble("ecofootprint")).willReturn(1.0);

            assertEquals(databaseProfile.getFootprintEco("Juan"), expected, 0.001);

        } catch(SQLException e) {

            e.printStackTrace();

        }

    }

    @Test
    public void getScore() {

        int expected = 1;

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getInt("score")).willReturn(1);

            assertEquals(databaseProfile.getFootprintEco("Juan"), expected, 1);

        } catch(SQLException e) {

            e.printStackTrace();

        }
    }

    @Test
    public void getGreenStreak() {

        int expected = 1;

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getInt("greenStreak")).willReturn(1);

            assertEquals(databaseProfile.getGreenStreak("Juan"), expected, 1);

        } catch(SQLException e) {

            e.printStackTrace();

        }

    }

    @Test
    public void getAchievements() {

        Achievement expected = new Achievement();

        expected.setToprank(true);
        expected.setFiveDaysr(true);
        expected.setFirstFriend(true);
        expected.setFirstPost(true);
        expected.setLoggedin(true);
        expected.setLowEco(true);
        expected.setFivedaysv(true);
        expected.setHundKm(true);
        expected.setLocal(true);
        expected.setCarblsix(true);

        try {

            given(resultSetMock.next()).willReturn(true);

            given(resultSetMock.getBoolean("topRank")).willReturn(true);
            given(resultSetMock.getBoolean("firstPost")).willReturn(true);
            given(resultSetMock.getBoolean("firstFriend")).willReturn(true);
            given(resultSetMock.getBoolean("hundkm")).willReturn(true);
            given(resultSetMock.getBoolean("fiveDayRecycle")).willReturn(true);
            given(resultSetMock.getBoolean("lowEco")).willReturn(true);
            given(resultSetMock.getBoolean("fiveDaysv")).willReturn(true);
            given(resultSetMock.getBoolean("carblSix")).willReturn(true);
            given(resultSetMock.getBoolean("locaL")).willReturn(true);
            given(resultSetMock.getBoolean("loggeD")).willReturn(true);

            assertEquals(expected, databaseProfile.getAchievements("Juan"));

        }
        catch (SQLException e) {

            e.printStackTrace();

        }


    }

//    @Test
//    public void getProfile() {
//
//        Profile expected = new Profile();
//
//        EnergySurvey energy = new EnergySurvey("type",4,"wow",true,true);
//        TransportationSurvey transportation = new TransportationSurvey(1,"cool",3,"bird");
//        FoodSurvey food = new FoodSurvey("organic","diet","local","waste","package", "compost");
//        LifeStyleSurvey life = new LifeStyleSurvey("often","waste","recycle","appliance",1);
//        Score score = new Score(1,1.0,1.0,1);
//        Achievement achievement = new Achievement();
//
//        String[] friendList = {"Raul", "Pepe"};
//
//        expected.setEnergyProfile(energy);
//        expected.setTransportationProfile(transportation);
//        expected.setFoodProfile(food);
//        expected.setLifeStyleProfile(life);
//        expected.setScore(score);
//        expected.setAchievement(achievement);
//        expected.setFriendList(new ArrayList(Arrays.asList(friendList)));
//        expected.setName("Juan");
//
//
//        given(databaseProfile.getProfile("Juan", "Juan")).willReturn(expected);
//
//        assertEquals(databaseProfile.getProfile("Juan","Juan"), databaseProfile.getProfile("Juan","Juan"));
//
//
//    }
//
//    @Test
//    public void getScores() {
//
//        Score expected = new Score();
//
//        expected.setFootprintEco(0.0);
//        expected.setFootprint(0.0);
//        expected.setGreenstreak(0);
//        expected.setScore(0);
//
//
//            given(databaseProfile.getScores("Juan").getScore()).willReturn(0);
//            given(databaseProfile.getScores("Juan").getGreenstreak()).willReturn(0);
//            given(databaseProfile.getScores("Juan").getFootprint()).willReturn(0.0);
//            given(databaseProfile.getScores("Juan").getFootprintEco()).willReturn(0.0);
//
//        willReturn(expected).given(databaseProfile.getScores("Juan"));
//
//        assertEquals(expected, databaseProfile.getScores("Juan"));
//
//    }


}