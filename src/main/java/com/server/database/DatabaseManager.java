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
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * This is a Singleton class.
 */
@Service
public class DatabaseManager {

    /**
     * Class variables and class instance.
     */
    private static DatabaseManager databaseManagerInstance = null;

    private DbImplement dbImplement;

    /**
     * Constructor and instance accessor.
     */
    private DatabaseManager(DbImplement dbImplement) {
        this.dbImplement = dbImplement;
    }

    /**
     * Add Javadoc.
     *
     * @param dbImplement DBImplement
     */

    public void setDb_implement(DbImplement dbImplement) {
        this.dbImplement = dbImplement;
    }

    /**
     * Getter
     *
     * @param dbImplement This is the instance of databaseManager.
     * @return The method will return an object instance.
     */
    public static DatabaseManager getInstance(DbImplement dbImplement) {
        if (databaseManagerInstance == null) {
            databaseManagerInstance = new DatabaseManager(dbImplement);
        }

        databaseManagerInstance.setDb_implement(dbImplement);
        return databaseManagerInstance;
    }

    /**
     * Add javadoc.
     *
     * @return Databasemanager.
     */
    public static DatabaseManager getInstance() {
        if (databaseManagerInstance == null) {
            databaseManagerInstance = new DatabaseManager(DbImplement.getInstance());
        }

        return databaseManagerInstance;
    }

    /**
     * Getters and setters.
     */
    public DbImplement getDbImplement() {
        return dbImplement;
    }

    /**
     * Setter.
     *
     * @param databaseManagerInstance Instance to set.
     */
    public static void setDatabaseManagerInstance(DatabaseManager databaseManagerInstance) {
        DatabaseManager.databaseManagerInstance = databaseManagerInstance;
    }


    /**
     * The method searches the username of a given username to check if it exists.
     *
     * @param username The username we want to check.
     * @return The method will return a String[].
     */
    public  String[] searchUsername(String username) {
        ArrayList<String> usernames = dbImplement.searchUsername(username);
        return usernames.toArray(new String[usernames.size()]);
    }

    /**
     * The method will delete a given user.
     *
     * @param username The user's username.
     * @return The method will return a boolean value.
     */
    public  boolean deleteUser(String username) {
        return dbImplement.deleteUser(username);
    }



    /**
     * Gets FoodPost entries in database searching by the given
     * attribute(s) and the containing value(s).
     *
     * @param attribute This is the name of the column we are trying to get the information from
     * @param value     This is the value of the the attribute we are trying to modify.
     * @return Return the list pf posts
     */
    public  FoodPost[] getFoodPosts(String attribute, String value) {

        ArrayList<FoodPost> result = dbImplement.getFoodPosts(attribute, value);

        return result.toArray(new FoodPost[result.size()]);
    }


    /**
     * Gets TransportationPost entries in database searching by the given
     * attribute(s) and containing given value(s).
     *
     * @param attribute This is the name of the column we are trying to get the information from
     * @param value     This is the value of the the attribute we are trying to modify.
     * @return Return the list pf posts
     */
    public  TransportationPost[] getTransportationPosts(String attribute, String value) {
        ArrayList<TransportationPost> result = dbImplement.getTransportationPost(attribute, value);

        return result.toArray(new TransportationPost[result.size()]);
    }


    /**
     * Gets ConsumptionPost entries in database searching by the given
     * attribute(s) containing the given value(s).
     *
     * @param attribute This is the name of the column we are trying to get the information from
     * @param value     This is the value of the the attribute we are trying to modify.
     * @return Return the list pf posts
     */
    public  ConsumptionPost[] getConsumptionPosts(String attribute, String value) {
        ArrayList<ConsumptionPost> result = dbImplement.getConsumptionPost(attribute, value);

        return result.toArray(new ConsumptionPost[result.size()]);


    }


    /**
     * Adds ConsumptionPost to the database and returns true if operation was successful.
     *
     * @param post This is the consumption post related information
     * @return whether the operation was successful
     */
    public  boolean addConsumptionPost(ConsumptionPost post) {

        boolean result = dbImplement.addConsumptionPost(post);
        return result;

    }

    /**
     * Adds TransportationPost to the database and returns true if operation was successful.
     *
     * @param post This is the transportation post related information
     * @return whether the operation was successful
     */
    public  boolean addTransportationPost(TransportationPost post) {

        boolean result = dbImplement.addTransportationPost(post);
        return result;

    }

    /**
     * Adds FoodPost to the database and returns true if operation was successful.
     *
     * @param post This is the food post related information
     * @return whether the operation was successful
     */
    public  boolean addFoodPost(FoodPost post) {

        boolean result = dbImplement.addFoodPost(post);
        return result;

    }

    /**
     * User verification.
     *
     * @param details sets deatils as user
     * @return boolean
     */
    public boolean checkLogin(User details) {

        boolean result = dbImplement.checkLogin(details);

        return result;

    }

    /**
     * This method will insert all of the user's primary information.
     *
     * @param details This are the details that we are trying to insert in the database.
     * @return The method will return a boolean value.
     */
    public  boolean signUpUser(SignUpUser details) {
        boolean result = dbImplement.signupUser(details);
        return result;
    }

    /**
     * Returns whether the given attribute contains the given value in the User table.
     * @param attribute the attribute being checked (eg: username).
     * @param value the value being checked (eg:Raul).
     * @return whether the value is in the database under the attribute.
     */
    public boolean existsUser(String attribute,String value) {
        return dbImplement.existsUser(attribute,value);
    }

    /**
     * Add Javadoc.
     *
     * @param username as string
     * @param survey as energysurvey
     * @return boolean
     */
    public boolean updateEnergySurvey(String username, EnergySurvey survey) {

        return DatabaseProfile.getInstance().updateEnergySurvey(username, survey);

    }

    /**
     * Add Javadoc.
     *
     * @param username as string
     * @param survey as foodsurvey
     * @return boolean
     */
    public boolean updateFoodSurvey(String username, FoodSurvey survey) {

        return DatabaseProfile.getInstance().updateFoodSurvey(username, survey);

    }

    /**
     * Add Javadoc.
     *
     * @param username as string
     * @param survey as transport survey
     * @return boolean
     */
    public boolean updateTransportationSurvey(String username, TransportationSurvey survey) {

        return DatabaseProfile.getInstance().updateTransportation(username, survey);

    }

    /**
     * Add Javadoc.
     * @param username as string
     * @param survey as Lifestyle survey
     * @return boolean
     */
    public boolean updateLifestyleSurvey(String username, LifeStyleSurvey survey) {

        return DatabaseProfile.getInstance().updateLifeStyleSurvey(username, survey);

    }

}
