package com.server.database;

import com.client.logic.Achievement;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.Profile;
import com.client.logic.RankingEntry;
import com.client.logic.Score;
import com.client.logic.TransportationSurvey;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * This is a singleton class.
 */
public class DatabaseProfile {


    /**
     * Initialize.
     */
    private static DatabaseProfile databaseProfileInstance = null;

    /**
     * Instance.
     */
    private DbAdapter dbAdapter;

    /**
     * Constructor.
     *
     * @param dbAdapter This is teh instance.
     */
    private DatabaseProfile(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;

    }

    /**
     * Getter.
     *
     * @return The method will return an instance of an object.
     */
    public static DatabaseProfile getInstance() {
        if (databaseProfileInstance == null) {
            databaseProfileInstance = new DatabaseProfile(DbAdapter.getInstance());
        }
        return databaseProfileInstance;
    }

    /**
     * Getter.
     *
     * @param dbAdapter This is the dbAdapterInstance.
     * @return Returns the instance.
     */
    public static DatabaseProfile getInstance(DbAdapter dbAdapter) {
        if (databaseProfileInstance == null) {
            databaseProfileInstance = new DatabaseProfile(dbAdapter);
        }
        databaseProfileInstance.setDbAdapter(dbAdapter);
        return databaseProfileInstance;
    }


    public void setDbAdapter(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    /**
     * Setter.
     *
     * @param databaseProfileInstance DatabaseProfileInstance.
     */
    public static void setDatabaseProfileInstance(DatabaseProfile databaseProfileInstance) {
        DatabaseProfile.databaseProfileInstance = databaseProfileInstance;
    }



    /**
     * Gets the transportation survey of the desired user
     *
     * @param username Username of the user whose information we want.
     * @return Returns the survey.
     */
    public TransportationSurvey getTransportationSurvey(String username) {

        String query = "SELECT * FROM transportation WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            TransportationSurvey result = new TransportationSurvey();

            if (data.next()) {

                result.setAveHoursFly((int) data.getDouble("aveHoursFly"));
                result.setAveKmPerDay((int) data.getDouble("aveKmPerDay"));
                result.setMainModeTransport(data.getString("mainModeTransport"));
                result.setTypeOfCar(data.getString("typeOfCar"));

                return result;

            }

            return null;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }
    }

    /**
     * This method will return a foodSurvey.
     *
     * @param username This is the user's username.
     * @return The method will return a foodSurvey.
     */
    public FoodSurvey getFoodSurvey(String username) {

        String query = "SELECT * FROM food WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            FoodSurvey result = new FoodSurvey();

            if (data.next()) {

                result.setAmountOrganicFood(data.getString("organic"));
                result.setAmountLocalFood(data.getString("area"));
                result.setAmountWastedFood(data.getString("wasted"));
                result.setAmountFoodCompost(data.getString("compost"));
                result.setAmountFoodPackaged(data.getString("packaged"));
                result.setDietChoice(data.getString("diet_choice"));


                return result;
            }

            return null;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

    }

    /**
     * This method will return the energySurvey.
     *
     * @param username This is the user's username.
     * @return The method will return an energySurvey.
     */
    public EnergySurvey getEnergySurvey(String username) {

        String query = "SELECT * FROM energy WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);


            EnergySurvey result = new EnergySurvey();

            if (data.next()) {

                result.setTypeHouse(data.getString("typeHouse"));
                result.setAmountRoommates(data.getInt("amountRoomates"));
                result.setGreenEnergy(data.getBoolean("greenEnergy"));
                result.setEfficientLighting(data.getBoolean("efficientLighting"));
                result.setAmountEnergy(data.getString("amountEnergy"));

                return result;
            }

            return null;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

    }

    /**
     * This method will return the lifeStyle survey.
     *
     * @param username This is the user's username.
     * @return The method will return the lifestyle survey.
     */
    public LifeStyleSurvey getLifeStyleSurvey(String username) {

        String query = "SELECT * FROM lifestyle WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            LifeStyleSurvey result = new LifeStyleSurvey();

            if (data.next()) {

                result.setHowOftenBigPurchase(data.getString("howOftenBigPurchase"));
                result.setWeeklyWaste(data.getString("weeklyWaste"));
                result.setRecycle(data.getString("recycle"));
                result.setApplianceUse(data.getString("applianceUse"));
                result.setAveShowerTime(data.getInt("aveShowerTime"));

                return result;
            }

            return null;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

    }


    /**
     * This method will return the value of the carbon footprint of the user.
     *
     * @param username This is the name of the user.
     * @return Returns the value of the carbon footprint. -1 if no footprint data
     */
    public double getCarbonFootprint(String username) {

        String query = "SELECT footprint "
                      +  "FROM scores "
                      +  "WHERE username = \'"  +  username  +  "\'";


        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            if (data.next()) {
                return data.getDouble("footprint");
            }

            return -1;

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        }

    }

    /**
     * This method will return the val of the eco footprint of the user.
     *
     * @param username This is the username of that user.
     * @return The method will return a double value for the footprint.
     */
    public double getFootprintEco(String username) {

        String query = "SELECT ecofootprint "
                      +  "FROM scores "
                      +  "WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            if (data.next()) {
                return data.getDouble("ecofootprint");
            }

            return -1;

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        }

    }

    /**
     * This method will return the user's score.
     *
     * @param username This is the username of the user.
     * @return The method will return an int value of the score.
     */
    public int getScore(String username) {

        String query = "SELECT score "
                      +  "FROM scores "
                      +  "WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            if (data.next()) {
                return data.getInt("score");
            }

            return -1;

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        }

    }

    /**
     * This method Will return the greenStreak value.
     *
     * @param username This is the username of that user.
     * @return This method will return an integer value.
     */
    public int getGreenStreak(String username) {

        String query = "SELECT greenstreak "
                      +  "FROM scores "
                      +  "WHERE username = \'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);

            if (data.next()) {
                return data.getInt("greenstreak");
            }

            return -1;

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

        }

    }

    /**
     * This method will return the information regarding the achievements info from the user.
     *
     * @param username This is the username of the user whose information we are displaying.
     * @return The method will return an achievement instance.
     */
    public Achievement getAchievements(String username) {

        String query = "SELECT * FROM achievements WHERE username =\'"  +  username  +  "\'";

        try {

            ResultSet data = dbAdapter.getInstance().query(query);


            if (data.next()) {

                Achievement result = new Achievement();

                result.setFirstPost(data.getBoolean("firstPost"));
                result.setFirstFriend(data.getBoolean("firstFriend"));
                result.setHundKm(data.getBoolean("hundkm"));
                result.setFiveDaysr(data.getBoolean("fiveDayRecycle"));
                result.setToprank(data.getBoolean("topRank"));
                result.setLowEco(data.getBoolean("lowEco"));
                result.setFivedaysv(data.getBoolean("fiveDaysv"));
                result.setCarblsix(data.getBoolean("carblSix"));
                result.setLocal(data.getBoolean("locaL"));
                result.setLoggedin(data.getBoolean("loggeD"));

                return result;
            }

            return null;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

    }

    /*
    ------------------------------------------------------------------------------------------------

                                                  GETTING PROFILE METHODS

    ------------------------------------------------------------------------------------------------
     */


    /**
     *This method will return a profile object.
     *
     * @param username1 Username of user1.
     * @param username2 Username of user2.
     * @return Returns profile instance.
     */
    public Profile getProfile(@NotNull String username1, String username2) {


        if (username1.equals(username2) || FriendDatabaseManager.getInstance()
                .getStatus(username1,username2) == 1) {

            Profile profile = new Profile();

            EnergySurvey energyProfile = DatabaseProfile.getInstance().getEnergySurvey(username2);
            TransportationSurvey transportationSurvey =
                    DatabaseProfile.getInstance().getTransportationSurvey(username2);
            FoodSurvey foodSurvey = DatabaseProfile.getInstance().getFoodSurvey(username2);
            LifeStyleSurvey lifeStyleSurvey =
                    DatabaseProfile.getInstance().getLifeStyleSurvey(username2);


            profile.setEnergyProfile(energyProfile);
            profile.setTransportationProfile(transportationSurvey);
            profile.setFoodProfile(foodSurvey);
            profile.setLifeStyleProfile(lifeStyleSurvey);


            profile.setScore(getScores(username1));
            profile.setName(username1);

            String[] friendList =
                    FriendDatabaseManager.getInstance().getUsersByStatus(username1, 1);
            profile.setFriendList(new ArrayList(Arrays.asList(friendList)));

            profile.setAchievement(getAchievements(username1));

            return profile;
        } else {
            System.out.println("Profile request denied");
            return null;
        }


    }

    /**
     * This method will get the scores information from the user.
     *
     * @param username This is the users username.
     * @return The method will return a score object.
     */
    public  Score getScores(String username) {

        Score score = new Score();

        score.setScore(getScore(username));
        score.setGreenstreak(getGreenStreak(username));
        score.setFootprint(getCarbonFootprint(username));
        score.setFootprintEco(getFootprintEco(username));

        return score;


    }



    /*
    -----------------------------------------------------------------------------------------------

                                        UPDATE METHODS

    -----------------------------------------------------------------------------------------------
     */


    /**
     * This method will store the energySurvey information into the database.
     *
     * @param username This is the username of the user whose survey we are storing.
     * @param survey   This is the survey that we are trying to store.
     * @return This method will return a boolean value.
     */
    public boolean updateEnergySurvey(String username, @NotNull EnergySurvey survey) {

        String query = "UPDATE energy "
                +  "SET \"typeHouse\" = ?, \"amountRoomates\" = ?,"
                + " \"greenEnergy\" = ?, \"efficientLighting\""
                + " = ?, username = ?, \"amountEnergy\" =? "
                +  "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.setString(1, survey.getTypeHouse());
            st.setInt(2, survey.getAmountRoommates());
            st.setBoolean(3, survey.getGreenEnergy());
            st.setBoolean(4, survey.getEfficientLighting());
            st.setString(5, username);
            st.setString(6, survey.getAmountEnergy());


            st.executeUpdate();
            st.close();

            return true;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    /**
     * This method will store the foodSurvey into the database.
     *
     * @param username This is the user's name.
     * @param survey   This is the survey we store.
     * @return The method will return a boolean value.
     */
    public boolean updateFoodSurvey(String username, @NotNull FoodSurvey survey) {

        String query = "UPDATE food "
                +  "SET \"organic\" = ?, \"area\" = ?, \"wasted\" = ?,"
                + " \"packaged\" = ?,\"compost\" = ?, \"diet_choice\" =?, \"username\" = ? "
                +  "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.setString(1, survey.getAmountOrganicFood());
            st.setString(2, survey.getAmountLocalFood());
            st.setString(3, survey.getAmountWastedFood());
            st.setString(4, survey.getAmountFoodPackaged());
            st.setString(5, survey.getAmountFoodCompost());
            st.setString(6, survey.getDietChoice());
            st.setString(7, username);

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }


    /**
     * This method will store the lifestyle survey.
     *
     * @param username This is the user's username.
     * @param survey   This is the survey we are trying to store.
     * @return This method will return a boolean value.
     */
    public boolean updateLifeStyleSurvey(String username, @NotNull LifeStyleSurvey survey) {

        String query = "UPDATE lifestyle"
                + "SET \"howOftenBigPurchase\" = ?, \"weeklyWaste\" = ?, \"recycle\" = ?, "
                + " \"applianceUse\" = ?, \"aveShowerTime\" = ?, \"username\" = ? "
                + "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.setString(1, survey.getHowOftenBigPurchase());
            st.setString(2, survey.getWeeklyWaste());
            st.setString(3, survey.getRecycle());
            st.setString(4, survey.getApplianceUse());
            st.setInt(5, survey.getAveShowerTime());
            st.setString(6, username);

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    /**
     * This method will store the transportation post.
     *
     * @param username The user's username.
     * @param survey   The survey we are trying to store.
     * @return This method will return a boolean value.
     */
    public boolean updateTransportation(String username, @NotNull TransportationSurvey survey) {

        String query = "UPDATE transportation "
                +  "SET \"aveHoursFly\" = ?, \"aveKmPerDay\" = ?,"
                + " \"typeOfCar\" = ?, \"mainModeTransport\" = ?, \"username\" = ? "
                +  "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.setDouble(1, survey.getAveHoursFly());
            st.setDouble(2, survey.getAveKmPerDay());
            st.setString(3, survey.getTypeOfCar());
            st.setString(4, survey.getMainModeTransport());
            st.setString(5, username);

            st.executeUpdate();
            st.close();

            return true;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    /**
     * This method will store the achievement information for the user.
     *
     * @param username    The username of the user.
     * @param achievement The achievement information of the user.
     * @return The method will return a boolean value.
     */
    public boolean updateAchievemnts(String username, @NotNull Achievement achievement) {

        String query = "UPDATE achievements SET \"firstPost\" = ?,"
                + " \"firstFriend\" = ?, \"hundkm\" = ?, "
                + "\"fiveDayRecycle\" = ?, \"topRank\" = ?, \"lowEco\" = ?,"
                + " \"username\" = ?, \"fiveDaysv\" = ?, \"carblSix\" = ?,"
                + " \"locaL\" = ?, \"loggeD\" = ?"
                + "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.getConn().prepareStatement(query);

            st.setBoolean(1, achievement.isFirstPost());
            st.setBoolean(2, achievement.isFirstFriend());
            st.setBoolean(3, achievement.isHundKm());
            st.setBoolean(4, achievement.isFiveDaysr());
            st.setBoolean(5, achievement.isToprank());
            st.setBoolean(6, achievement.isLowEco());
            st.setString(7, username);
            st.setBoolean(8, achievement.isFivedaysv());
            st.setBoolean(9, achievement.isCarblsix());
            st.setBoolean(10, achievement.isLocal());
            st.setBoolean(11, achievement.isLoggedin());

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    /**
     * This method will update the score information of the user.
     *
     * @param username This is the username of the user.
     * @param score    This is the score object that contains the updated information.
     * @return The method will return a boolean value.
     */
    public boolean updateScore(String username, @NotNull Score score) {

        String query = "UPDATE scores "
                 +  "SET greenstreak = ?, score = ?, footprint = ?, ecofootprint = ?, username = ?"
                 +  "WHERE username = \'"  +  username  +  "\'";

        try {

            PreparedStatement st = dbAdapter.getConn().prepareStatement(query);

            st.setInt(1, score.getGreenstreak());
            st.setInt(2, score.getScore());
            st.setDouble(3, score.getFootprint());
            st.setDouble(4, score.getFootprintEco());
            st.setString(5,username);

            st.executeUpdate();
            st.close();

            return true;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    /*
    -----------------------------------------------------------------------------------------------

                                        INSERTION METHODS

    -----------------------------------------------------------------------------------------------
     */

    /**
     * This method will store the achievement information for the user.
     *
     * @param username    The username of the user.
     * @param achievement The achievement information of the user.
     * @return The method will return a boolean value.
     */
    public boolean insertAchievemnts(String username, @NotNull Achievement achievement) {

        String query = "INSERT INTO achievements (\"firstPost\", "
                + "\"firstFriend\", \"hundkm\", \"fiveDayRecycle\", \"topRank\", \"lowEco\","
                +  " username, \"fiveDaysv\", \"carblSix\", \"locaL\", \"loggeD\") "
                +  "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement st = dbAdapter.getConn().prepareStatement(query);

            st.setBoolean(1, achievement.isFirstPost());
            st.setBoolean(2, achievement.isFirstFriend());
            st.setBoolean(3, achievement.isHundKm());
            st.setBoolean(4, achievement.isFiveDaysr());
            st.setBoolean(5, achievement.isToprank());
            st.setBoolean(6, achievement.isLowEco());
            st.setString(7, username);
            st.setBoolean(8, achievement.isFivedaysv());
            st.setBoolean(9, achievement.isCarblsix());
            st.setBoolean(10, achievement.isLocal());
            st.setBoolean(11, achievement.isLoggedin());


            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }

    /**
     * This method ill return a global ranking object.
     *
     * @param attribute This is the attribute where we are basing the ranking.
     * @return The method will return an arrayList.
     */
    public ArrayList<RankingEntry> getGlobalRanking(String attribute) {

        ArrayList<RankingEntry> result = new ArrayList<>();

        String query = "SELECT * FROM scores ORDER BY "  +  attribute  +  " DESC LIMIT 10";

        try {
            ResultSet data = dbAdapter.query(query);

            while (data.next()) {
                String username = data.getString("username");
                int score = data.getInt(attribute);
                result.add(new RankingEntry(username,score));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /**
     * This method will get the ranking of the friends.
     *
     * @param username This is the friend's username.
     * @param attribute This is the attribute we want to compare.
     * @return The method will return an arrayList of objects.
     */
    public ArrayList<RankingEntry> getFriendRanking(String username, String attribute) {
        ArrayList<RankingEntry> result = new ArrayList<>();

        String query = "SELECT *\n"
                + "FROM scores\n"
                + "WHERE (username) IN (SELECT \"firstUsername\"\n"
                + "                       FROM friendship \n"
                + "                       WHERE (\"firstUsername\"= \'" + username + "\'\n"
                + "                               OR \"secondUsername\" = \'" + username + "\')\n"
                + "                               AND status = 1)\n"
                + "               OR (username) IN (SELECT \"secondUsername\"\n"
                + "                               FROM friendship \n"
                + "                               WHERE (\"firstUsername\" = \'" + username + "\'\n"
                + "                               OR \"secondUsername\" = \'" + username + "\')\n"
                + "                               AND status = 1)\n"
                + "ORDER BY \'" + attribute + "\' DESC";

        try {
            ResultSet data = dbAdapter.query(query);
            while (data.next()) {
                String name = data.getString("username");
                int score = data.getInt(attribute);
                result.add(new RankingEntry(name,score));
            }
            return result;
        } catch (SQLException e) {
            return null;
        }
    }

}


