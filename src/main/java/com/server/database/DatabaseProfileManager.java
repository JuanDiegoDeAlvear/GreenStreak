package com.server.database;

import com.client.logic.Achievement;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.Profile;
import com.client.logic.RankingEntry;
import com.client.logic.Score;
import com.client.logic.TransportationSurvey;

import java.util.ArrayList;


/**
 * This is a singleton class.
 */
public class DatabaseProfileManager {

    private static DatabaseProfileManager databaseProfileManagerInstance;

    private static DatabaseProfile databaseProfile;

    private DatabaseProfileManager(DatabaseProfile databaseProfile) {
        this.databaseProfile = databaseProfile;
    }

    /**
     * This method will return an instance.
     *
     * @return Returns instance.
     */
    public static DatabaseProfileManager getInstance() {
        if (databaseProfileManagerInstance == null) {
            databaseProfileManagerInstance =
                    new DatabaseProfileManager(DatabaseProfile.getInstance());
        }
        return databaseProfileManagerInstance;
    }

    /**
     * Getter.
     *
     * @param databaseProfile Tis is the object.
     * @return Returns an instance.
     */
    public static DatabaseProfileManager getInstance(DatabaseProfile databaseProfile) {
        if (databaseProfileManagerInstance == null) {
            databaseProfileManagerInstance = new DatabaseProfileManager(databaseProfile);
        }

        setDatabaseProfile(databaseProfile);
        return databaseProfileManagerInstance;
    }

    /**
     * Getter.
     *
     * @param username1 This is username.
     * @param username2 This is username.
     * @return Returns a profile object.
     */
    public Profile getProfile(String username1, String username2) {

        return databaseProfile.getProfile(username1, username2);

    }


    /**
     * Setter.
     *
     * @param databaseProfile Object.
     */
    public static void setDatabaseProfile(DatabaseProfile databaseProfile) {
        DatabaseProfileManager.databaseProfile = databaseProfile;
    }

    /**
     * Updates energy survey.
     *
     * @param username Username.
     * @param survey Survey to update.
     * @return Returns boolean.
     */
    public boolean updateEnergySurvey(String username, EnergySurvey survey) {
        return databaseProfile.updateEnergySurvey(username, survey);
    }

    /**
     * Updates energy survey.
     *
     * @param username Username.
     * @param survey Survey to update.
     * @return Returns boolean.
     */
    public boolean updateFoodSurvey(String username, FoodSurvey survey) {
        return databaseProfile.updateFoodSurvey(username, survey);
    }

    /**
     * Updates energy survey.
     *
     * @param username Username.
     * @param survey Survey to update.
     * @return Returns boolean.
     */
    public boolean updateLifeStyleSurvey(String username, LifeStyleSurvey survey) {
        return databaseProfile.updateLifeStyleSurvey(username, survey);
    }

    /**
     * Updates energy survey.
     *
     * @param username Username.
     * @param survey Survey to update.
     * @return Returns boolean.
     */
    public boolean updateTransportationSurvey(String username, TransportationSurvey survey) {
        return databaseProfile.updateTransportation(username, survey);
    }

    /**
     * Updates scores.
     *
     * @param username Username.
     * @param score Score to update.
     * @return Returns boolean.
     */
    public boolean updateScore(String username, Score score) {
        return databaseProfile.updateScore(username, score);
    }

    /**
     * Updates Achievements.
     *
     * @param username Username.
     * @param achievement Achievement to update.
     * @return Returns boolean.
     */
    public boolean updateAchievements(String username, Achievement achievement) {
        return databaseProfile.updateAchievemnts(username, achievement);
    }

    /**
     * Gets the global rankings.
     *
     * @param attribute Attribute to compare users with.
     * @return Returns RankingEnergy[].
     */
    public RankingEntry[] getGlobalRanking(String attribute) {
        ArrayList<RankingEntry> ranking = databaseProfile.getGlobalRanking(attribute);
        return ranking.toArray(new RankingEntry[ranking.size()]);
    }

    /**
     * Gets the ranking of the user compared to friends.
     *
     * @param username Username.
     * @param attribute Attribute to compare users with.
     * @return Returns RankingEntry[].
     */
    public RankingEntry[] getFriendRanking(String username, String attribute) {

        ArrayList<RankingEntry> ranking = databaseProfile.getFriendRanking(username,attribute);
        return ranking.toArray(new RankingEntry[ranking.size()]);

    }




}
