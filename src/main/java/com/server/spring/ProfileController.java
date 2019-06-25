package com.server.spring;

import com.client.logic.Achievement;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.Profile;
import com.client.logic.Score;
import com.client.logic.TransportationSurvey;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.server.debug.DebugTools.debug;

import com.server.database.DatabaseManager;
import com.server.database.DatabaseProfileManager;
import com.server.endpoints.ProfileEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;



@RestController
@RequestMapping(ProfileEndpoints.PROFILE)
public class ProfileController {

    private ObjectMapper om = new ObjectMapper();

    /**
     *  Returns the Profile in the database with the given username.
     *
     * @param request fill in raul.
     * @param target fill in raul.
     * @return profile.
     */
    @GetMapping(value = ProfileEndpoints.GET_PROFILE)
    public Profile getProfile(@RequestParam String request, @RequestParam String target) {
        debug("Received request for profile with username: " + request + " from user " + target );
        return DatabaseProfileManager.getInstance().getProfile(request,target);
    }

    /**
     * Returns all the Profiles which start with the possibly incomplete username.
     *
     * @param username as a string
     * @return a string array
     */
    @GetMapping(value = ProfileEndpoints.SEARCH_USERNAME)
    public String[] searchUsername(@RequestParam String username) {
        System.out.println("Searching for: " + username);
        return DatabaseManager.getInstance().searchUsername(username);
    }

    /**
     * fill in raul.
     *
     * @param request as a String array
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_ENERGY)
    public boolean updateEnergy(@RequestBody String[] request) {
        try {
            EnergySurvey survey = om.readValue(request[1],EnergySurvey.class);
            return DatabaseProfileManager.getInstance().updateEnergySurvey(request[0],
                    survey);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fill in raul.
     *
     * @param request as a String array
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_FOOD)
    public boolean updateFood(@RequestBody String[] request) {
        try {
            FoodSurvey survey = om.readValue(request[1],FoodSurvey.class);
            return DatabaseProfileManager.getInstance().updateFoodSurvey(request[0],
                    survey);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fill in raul.
     *
     * @param request as a string array
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_LIFESTYLE)
    public boolean updateLifestyle(@RequestBody String[] request) {
        try {
            LifeStyleSurvey survey = om.readValue(request[1],LifeStyleSurvey.class);
            return DatabaseProfileManager.getInstance().updateLifeStyleSurvey(request[0],
                    survey);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fill in raul.
     *
     * @param request as a string array
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_TRANSPORTATION)
    public boolean updateTransportation(@RequestBody String[] request) {
        try {
            TransportationSurvey survey = om.readValue(request[1],TransportationSurvey.class);
            return DatabaseProfileManager.getInstance().updateTransportationSurvey(request[0],
                    survey);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fill in raul.
     *
     * @param request as a string array
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_SCORE)
    public boolean updateScore(@RequestBody String[] request) {
        try {
            Score score = om.readValue(request[1],Score.class);
            debug(score.toString());
            return DatabaseProfileManager.getInstance().updateScore(request[0],
                    score);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fill in raul.
     *
     * @param request as an array string
     * @return boolean
     */
    @PostMapping(ProfileEndpoints.UPDATE_ACHIEVEMENTS)
    public boolean updateAchievements(@RequestBody String[] request) {
        try {
            Achievement achievement = om.readValue(request[1],Achievement.class);
            return DatabaseProfileManager.getInstance().updateAchievements(request[0],
                    achievement);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
