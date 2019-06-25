package com.server.database;


import com.client.logic.ConsumptionPost;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodPost;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.SignUpUser;
import com.client.logic.TransportationPost;
import com.client.logic.TransportationSurvey;
import com.client.logic.User;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This is a Singleton class.
 */
public class DbImplement { //A good idea might be creating a different class per relation

    /**
     * Class variables and Class instance.
     */
    private static DbImplement dbImplementInstance = null;

    private DbAdapter dbAdapter;


    /**
     * Constructor.
     *
     * @param dbAdapter instance.
     */
    private DbImplement(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    /**
     * Getters and setters.
     */

    /**
     * Getter.
     *
     * @return Returns the dbadapter instance.
     */
    public DbAdapter getDbAdapter() {
        return dbAdapter;
    }

    /**
     * Setter.
     *
     * @param dbAdapter Instance.
     */
    public void setDbAdapter(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }


    /**
     * This method will set a dbImplementInstance.
     *
     * @param dbImplement dbImplement object.
     */
    public static void setInstance(DbImplement dbImplement) {
        dbImplementInstance = dbImplement;
    }

    /**
     * This method will return a dbImplementInstance.
     *
     * @return Returns a dbImplementInstance.
     */
    public static DbImplement getInstance() {
        if (dbImplementInstance == null) {
            dbImplementInstance = new DbImplement(DbAdapter.getInstance());
        }

        return dbImplementInstance;
    }

    /**
     * This mwthod will get a dbImplement instance.
     *
     * @param dbAdapter sbAdapter object.
     * @return Returns a dbImplementInstance.
     */
    public static DbImplement getInstance(DbAdapter dbAdapter) {
        if (dbImplementInstance == null) {
            dbImplementInstance = new DbImplement(dbAdapter);
        }
        dbImplementInstance.setDbAdapter(dbAdapter);
        return dbImplementInstance;
    }


    /*
   ------------------------------------------------------------------------------------------------

                                                SURVEY & PROFILE METHODS

   ------------------------------------------------------------------------------------------------
    */

    /**
     * This method will insert all of the user's primary information.
     *
     * @param details This are the details that we are trying to insert in the database.
     * @return The method will return a boolean value.
     */
    public boolean signupUser(SignUpUser details) {

        System.out.println("Inserting user data for signup");
        boolean result = false;

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT "
                    + "INTO usuario (username, email, password)"
                    + "VALUES (?,?,?)");

            st.setString(1, details.getUsername());
            st.setString(2, details.getEmail());
            st.setString(3, details.getPassword());

            st.executeUpdate();
            st.close();



            result = foodDataInsertion(details.getFoodProfile(),details.getUsername());
            result &= energyDataInsertion(details.getEnergyProfile(),details.getUsername());
            result &= transportationDataInsertion(details.getTransportationProfile(),
                    details.getUsername());
            result &= lifeStyleDataInsertion(details.getLifeStyleProfile(), details.getUsername());

            result &= insertDefaultScore(details.getUsername());
            result &= insertDefaultAchievement(details.getUsername());

            return result;


        } catch (SQLException e) {

            e.printStackTrace();
            return result;

        }


    }

    /**
     * This method will insert the default score when signup occurs.
     *
     * @param username This is the user's username.
     * @return The method will return a boolean value if it is successful.
     */
    private boolean insertDefaultScore(String username) {

        String query = "INSERT INTO scores (greenstreak, score, footprint, ecofootprint, username)"
                + " VALUES(0,0,0,0,\'" + username + "\')";

        try {

            PreparedStatement st = dbAdapter.getConn().prepareStatement(query);

            st.executeUpdate();
            st.close();

            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }

    }

    /**
     * This method will insert the default values for the achievements.
     *
     * @param username This is the username of the user.
     * @return This method will return a boolean value.
     */
    private boolean insertDefaultAchievement(String username) {

        String query = "INSERT INTO achievements"
                + "(\"firstPost\", \"firstFriend\", \"hundkm\","
                + " \"fiveDayRecycle\", \"topRank\", \"lowEco\", \"username\", "
                + "\"fiveDaysv\", \"carblSix\", \"locaL\", \"loggeD\") VALUES(false , false ,false,"
                + "false,false,false,\'" + username
                + "\',false,false,false,false)";

        try {

            PreparedStatement st = dbAdapter.getConn().prepareStatement(query);

            st.executeUpdate();
            st.close();

            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    //Adding information to the energy data of the user

    /**
     * Iserts the data regarding energy.
     *
     * @param survey energy survey for lifeStyle.
     * @param username This is the user's username.
     * @return The method will return a boolean value.
     */
    public boolean energyDataInsertion(EnergySurvey survey, String username) {

        System.out.println("Pleae insert all of the energy data.");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO energy "
                    + "VALUES (?,?,?,?,?,?)");

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

    //Adding information to the food data of the user

    /**
     * Inserts all of the food related information about the user.
     *
     * @param survey   Food survey.
     * @param username This is the username of the user, whose data we are inserting.
     * @return The method will return a boolean value based on whethjer it is successful.
     */
    public boolean foodDataInsertion(FoodSurvey survey, String username) {

        System.out.println("Pleae insert all of the diet data.");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO food "
                    + "VALUES (?,?,?,?,?,?,?)");

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

    //Adding information to the transportation data of the user

    /**
     * Inserts the required information about the user transportation-wise.
     *
     * @param username Username of the user.
     * @param survey the TransportationSurvey object.
     * @return The method will return a boolean value.
     */
    public boolean transportationDataInsertion(TransportationSurvey survey, String username) {

        System.out.println("Please insert all of the transport data.");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO transportation "
                    + "VALUES (?,?,?,?,?)");

            st.setFloat(1, survey.getAveHoursFly());
            st.setFloat(2, survey.getAveKmPerDay());
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
     * This method inserts the user data regarding his/her lifestyle.
     *
     * @param survey   This is the survey containing the information.
     * @param username This is the username whose information we are sending to the database.
     * @return This method will return a boolean depending on whether it carried out the task.
     */
    public boolean lifeStyleDataInsertion(LifeStyleSurvey survey, String username) {

        System.out.println("Please insert all of the lifestyle data.");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO lifestyle "
                    + "VALUES (?,?,?,?,?,?)");

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
     * This method offers the chance to search the username without fully knowing it.
     *
     * @param username String representing the known part of the username.
     * @return This method will return an ArrayList of Strings.
     */
    public ArrayList<String> searchUsername(String username) {

        ArrayList<String> usernames = new ArrayList<>();

        try {
            PreparedStatement st = dbAdapter.getConn().prepareStatement("SELECT username "
                    + "FROM usuario "
                    + "WHERE username LIKE \'%" + username + "%\' ");

            ResultSet result = dbAdapter.query(st);

            while (result.next()) {
                usernames.add(result.getString(1));
            }

            return usernames;

        } catch (SQLException e) {

            return null;
        }
    }

    /**
     * This method will delete a user from the database.
     *
     * @param username This is the username of the users whose account we will delete.
     * @return Will return a boolean depending on whether it successfully carries out the task.
     */
    public boolean deleteUser(String username) {


        try {
            PreparedStatement st = dbAdapter.conn.prepareStatement("DELETE * "
                    + "FROM usuario "
                    + "WHERE username = \'" + username + "\'");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /*
    ------------------------------------------------------------------------------------------------

                                                    POST METHODS ADDERS

    ------------------------------------------------------------------------------------------------
    */


    /**
     * Adds ConsumptionPost to the database and returns true if operation was successful.
     *
     * @param post post.
     * @return whether the operation was successful.
     */
    public boolean addConsumptionPost(ConsumptionPost post) {

        System.out.println("Please insert the requiired data for the consumption post");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO "
                    + "consumption_post(username, consume, state, date)"
                    + "VALUES (?,?,?,current_timestamp )");

            st.setString(1, post.getUsername());
            st.setString(2, post.getConsume());
            st.setBoolean(3, post.getState());

            st.executeUpdate();
            st.close();

            return true;


        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    /**
     * Adds TransportationPost to the database and returns true if operation was successful.
     *
     * @param post Transportation post.
     * @return whether the operation was successful.
     */
    public boolean addTransportationPost(TransportationPost post) {

        System.out.println("Please insert the required data for the transportation post");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO "
                    + "transportation_post(username, kilometers, type, date)"
                    + "VALUES (?,?,?,current_timestamp )");

            st.setString(1, post.getUsername());
            st.setString(3, post.getType());
            st.setDouble(2, post.getKilometer());

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    /**
     * Adds FoodPost to the database and returns true if operation was successful.
     *
     * @param post Food post.
     * @return whether the operation was successful.
     */
    public boolean addFoodPost(FoodPost post) {

        System.out.println("Please write down the required information for the food post");

        try {

            PreparedStatement st = dbAdapter.conn.prepareStatement("INSERT INTO "
                    + "food_post(username, meal, type, date)"
                    + "VALUES (?,?,?,current_timestamp)");

            st.setString(1, post.getUsername());
            st.setString(2, post.getMeal());
            st.setString(3, post.getType());

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    /*
    ------------------------------------------------------------------------------------------------

                                                  USER VERIFICATION METHODS

    ------------------------------------------------------------------------------------------------
    */

    /**
     * User verification.
     *
     * @param details user verification details.
     * @return returns whether the user is in the database.
     */
    public boolean checkLogin(User details) {

        System.out.println("Please send the required information to verify user");

        try {

            String username = details.getusername();
            String password = details.getPassword(); // Tiene que mandar 'juan' y 'hola' no jua
            String query = "SELECT username, password"
                    + " FROM usuario "
                    + " WHERE username = \'" + username + "\' AND password = \'" + password + "\'";

            System.out.println("dbAdapter.connected to PostgreSQL database!");
            System.out.println("Reading records...");
            ResultSet resultSet = dbAdapter.query(query);

            if (resultSet.next()) {

                return true;

            }

            return false;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    /**
     * Returns whether the given attribute contains the given value in the User table.
     * @param attribute the attribute being checked (eg: username).
     * @param value the value being checked (eg:Raul).
     * @return whether the value is in the database under the attribute.
     */
    public boolean existsUser(String attribute, String value) {

        System.out.println("Checking if the user exists.");

        try {

            String query = "SELECT \'" + attribute + "\' FROM usuario"
                    + " WHERE \'" + attribute + "\' = \'" + value + "\'";

            dbAdapter.stnt = dbAdapter.conn.createStatement();

            System.out.println("dbAdapter.connected to PostgreSQL database!: Reading records..");

            ResultSet resultSet = dbAdapter.query(query);

            if (resultSet.next()) {
                return true;
            }

            resultSet.close();
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
    ------------------------------------------------------------------------------------------------

                                                POSTS METHODS GETTERS

    ------------------------------------------------------------------------------------------------
    */

    /**
     * Returns FoodPost from data in selected row in ResultSet set.
     *
     * @param data resultSet.
     * @return reutrns null if exception an object if it works.
     */
    public FoodPost foodPostFromSet(@NotNull ResultSet data) {
        FoodPost result = new FoodPost();
        System.out.println("Creating food post");

        try {
            result.setMeal(data.getString("meal"));
            result.setType(data.getString("type"));
            result.setDate(data.getTimestamp("date"));
            result.setUsername(data.getString("username"));
            result.setPostClass("food");
            //      result.setPoints(data.getInt("points"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }


    /**
     * Returns TransportationPost object from the data within the database.
     *
     * @param data resultSet.
     * @return returns null if there is an exception.
     */
    public TransportationPost transportationPostFromSet(@NotNull ResultSet data) {

        TransportationPost result = new TransportationPost();
        System.out.println("Creating transportation post");

        try {

            result.setKilometer(data.getDouble("kilometers"));
            result.setType(data.getString("type"));
            result.setDate(data.getTimestamp("date"));
            result.setUsername(data.getString("username"));
            result.setPostClass("transportation");
            //  result.setPoints(data.getInt("points"));

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

        return result;

    }


    /**
     * Returns ConsumptionPost object from the data retrieved from the database.
     *
     * @param data resultSet.
     * @return returns null if there is an exception.
     */
    public ConsumptionPost consumptionPostFromSet(@NotNull ResultSet data) {

        ConsumptionPost result = new ConsumptionPost();

        try {

            result.setConsume(data.getString("consume"));
            result.setState(data.getBoolean("state"));
            result.setDate(data.getTimestamp("date"));
            result.setUsername(data.getString("username"));
            result.setPostClass("ConsumptionPost");
            //    result.setPoints(data.getInt("points"));

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }

        return result;

    }


    /**
     * Getting the posts related to post.
     *
     * @param attribute name of the attribute.
     * @param value     value of the attribute.
     * @return Returns an ArrayList of FoodPosts.
     */
    public ArrayList<FoodPost> getFoodPosts(String attribute, String value) {

        String query = "SELECT * FROM food_post  WHERE " + attribute + "= \'" + value + "\'";
        ResultSet data = dbAdapter.query(query);

        try {

            ArrayList<FoodPost> result = new ArrayList<>();

            while (data.next()) {
                System.out.println("Creating Food Post");
                result.add(foodPostFromSet(data));
            }

            return result;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     * Returns the transportation post.
     *
     * @param attribute name of the column(attribute).
     * @param value     actual value of the attribute.
     * @return Returns na ArrayList of TransportationPosts.
     */
    public ArrayList<TransportationPost> getTransportationPost(String attribute, String value) {

        String query = "SELECT * FROM transportation_post  WHERE " + attribute
                + " = \'" + value + "\'";
        ResultSet data = dbAdapter.query(query);

        try {

            ArrayList<TransportationPost> result = new ArrayList<>();

            while (data.next()) {
                System.out.println("Creating Transportation Post");
                result.add(transportationPostFromSet(data));
            }

            return result;

        } catch (SQLException | NullPointerException e) {

            e.printStackTrace();
            return null;

        }


    }


    /**
     * Return the consumption post.
     *
     * @param attribute name of the column.
     * @param value     actual value of the attribute.
     * @return Returns an arryList of consumptionPosts.
     */
    public ArrayList<ConsumptionPost> getConsumptionPost(String attribute, String value) {

        String query = "SELECT * FROM consumption_post  WHERE " + attribute + " = \'"
                + value + "\'";
        System.out.println("Querying for: " + query);
        ResultSet data = dbAdapter.query(query);

        try {
            //FIX THIS

            ArrayList<ConsumptionPost> result = new ArrayList<>();

            while (data.next())  {
                System.out.println("Creating consumption Post");
                result.add(consumptionPostFromSet(data));
            }

            return result;

        } catch (SQLException | NullPointerException e) {

            e.printStackTrace();
            return null;

        }


    }



     

}

