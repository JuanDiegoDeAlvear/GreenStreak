package com.client;

import com.client.clientendpoints.AuthenticationEndpoints;
import com.client.clientendpoints.FriendEndpoints;
import com.client.clientendpoints.PostEndpoints;
import com.client.clientendpoints.ProfileEndpoints;
import com.client.clientendpoints.ScoreEndpoints;
import com.client.logic.Achievement;
import com.client.logic.ConsumptionPost;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodPost;
import com.client.logic.FoodSurvey;
import com.client.logic.FriendRelationship;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.PostCatalog;
import com.client.logic.Profile;
import com.client.logic.RankingEntry;
import com.client.logic.Score;
import com.client.logic.SignUpUser;
import com.client.logic.TransportationPost;
import com.client.logic.TransportationSurvey;
import com.client.logic.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static com.server.debug.DebugTools.debug;




public abstract class ServerCommunication {

    private static final String serverAddress = "http://localhost:8080";
    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Create a new post in db.
     *
     * @param post sets post using Consumption post
     * @return boolean
     */
    public static boolean uploadConsume(ConsumptionPost post) {
        return restTemplate.postForObject(serverAddress + PostEndpoints.POSTS
                        + PostEndpoints.ADD_CONSUMPTION,
                post, boolean.class);
    }

    /**
     * Create a new post in db.
     *
     * @param post sets post using Food post
     * @return boolean
     */
    public static boolean uploadfood(FoodPost post) {
        return restTemplate.postForObject(serverAddress + PostEndpoints.POSTS
                        + PostEndpoints.ADD_FOOD,
                post, boolean.class);
    }

    /**
     * Create a new post in db.
     *
     * @param post sets post using Transportation post
     * @return boolean
     */
    public static boolean uploadTrans(TransportationPost post) {
        return restTemplate.postForObject(serverAddress + PostEndpoints.POSTS
                        + PostEndpoints.ADD_TRANSPORT,
                post, boolean.class);
    }

    /**
     * Receive from com.server an array.
     *
     * @param username sets username using String
     * @return postCatalog
     */
    public static PostCatalog recievepostarray(String username) {
        System.out.println("Requesting posts from user: " + username);
        PostCatalog catalog = new PostCatalog();

        ConsumptionPost[] consumptionPosts = getConsumptionPosts(username);
        FoodPost[] foodPosts = getFoodPosts(username);
        TransportationPost[] transportationPosts = getTransportationPosts(username);

        catalog.getPostcatalog().addAll(Arrays.asList(consumptionPosts));
        catalog.getPostcatalog().addAll(Arrays.asList(foodPosts));
        catalog.getPostcatalog().addAll(Arrays.asList(transportationPosts));


        return catalog;

    }

    /**
     * fetchs all Consumption Posts from db.
     *
     * @param username sets username as String
     * @return List of consumption post
     */
    public static ConsumptionPost[] getConsumptionPosts(String username) {
        return restTemplate.getForObject(serverAddress + PostEndpoints.POSTS
                + PostEndpoints.GET_CONSUMPTION
                + "?username=" + username, ConsumptionPost[].class);
    }

    /**
     * fetchs all Food Posts from db.
     *
     * @param username sets username as String
     * @return List of food post
     */
    public static FoodPost[] getFoodPosts(String username) {
        return restTemplate.getForObject(serverAddress + PostEndpoints.POSTS
                + PostEndpoints.GET_FOOD
                + "?username=" + username, FoodPost[].class);
    }

    /**
     * fetchs all transportation Posts from db.
     *
     * @param username sets username as String
     * @return List of transportation post
     */
    public static TransportationPost[] getTransportationPosts(String username) {
        return restTemplate.getForObject(serverAddress + PostEndpoints.POSTS
                + PostEndpoints.GET_TRANSPORT
                + "?username=" + username, TransportationPost[].class);
    }

    /**
     * User verification.
     *
     * @param details user verification details.
     * @return returns whether the user is in the database.
     */
    public static boolean checkLogin(User details) {
        String password = hashText(details.getPassword());
        details.setPassword(password);

        System.out.println("Checking login details for: " + details.getusername()
                + " password: " + details.getPassword());
        boolean validated = restTemplate.postForObject(serverAddress
                + AuthenticationEndpoints.AUTHENTICATION
                + AuthenticationEndpoints.LOGIN, details, boolean.class);

        System.out.println("Recevied login validation value: " + validated);
        return validated;
    }

    /**
     * Sends the new Sign up user details to db.
     * @param details sets details as SignupUser with info.
     * @return boolean
     */
    public static boolean sendsignupuser(SignUpUser details) {
        details.setPassword(hashText(details.getPassword()));

        boolean validated = restTemplate.postForObject(serverAddress
                + AuthenticationEndpoints.AUTHENTICATION
                + AuthenticationEndpoints.SIGNUP, details, boolean.class);

        return validated;

    }


    /**
     * Tells the com.server the user wants to send a friend request.
     *
     * @param username1 the user that requests the friendship.
     * @param username2 the user that is requested friendship.
     * @return if the operation was successful.
     */
    public static boolean sendFriendRequest(String username1, String username2) {

        String[] usernames = {username1, username2};
        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.SEND_REQUEST,
                usernames, boolean.class);

    }

    /**
     * Returns all friends of the user.
     *
     * @param username the username of the user.
     * @return an ArrayList of usernames.
     */
    public static ArrayList<String> getFriends(String username) {

        String[] usernames = restTemplate.getForObject(serverAddress + FriendEndpoints.FRIEND
                + FriendEndpoints.GET_USERS_BY_STATUS + "/?name="
                + username + "&status=1", String[].class);

        assert usernames != null;
        return new ArrayList<String>(Arrays.asList(usernames));
    }


    /**
     * WIP Returns the usernames of users who have sent a friend request to the given user.
     *
     * @param username the user who received the requests.
     * @return the usernames of the users who sent the requests.
     */
    public static ArrayList<String> getReceivedRequests(String username) {

        String[] usernames = restTemplate.getForObject(serverAddress
                +  FriendEndpoints.FRIEND + FriendEndpoints.GET_RECEIVED_REQUESTS
                +  "/?name=" + username, String[].class);

        return new ArrayList<>(Arrays.asList(usernames));
    }

    /**
     * Checks if the users are friends and if true returns the second's profile.
     *
     * @param username1 the user requesting the profile.
     * @param username2 the use whose profile is being requested.
     * @return Profile
     */
    public static Profile getProfile(String username1, String username2) {
        Profile profile = restTemplate.getForObject(serverAddress + ProfileEndpoints.PROFILE
                + ProfileEndpoints.GET_PROFILE
                + "?request=" + username1 + "&target=" + username2, Profile.class);

        return profile;
    }

    /**
     * Accepts the friend request between the first and second user.
     *
     * @param username1 the user who accepts the request.
     * @param username2 the user who sent the request.
     * @return whether the operation was successful.
     */
    public static boolean acceptFriendRequest(String username1, String username2) {
        String[] usernames = {username1,username2};
        FriendRelationship request = new FriendRelationship(username1, username2, 1);
        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.ACCEPT_REQUEST,
                usernames, boolean.class);
    }

    /**
     * Declines the friend request between the first and second user.
     *
     * @param username1 the user who declines the request.
     * @param username2 the user who sent the request.
     * @return whether the operation was successful.
     */
    public static boolean declineFriendRequest(String username1, String username2) {
        String[] usernames = {username1, username2};

        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.REMOVE_FRIEND,
                usernames, boolean.class);
    }

    /**
     * Makes the first user block the second user.
     *
     * @param username1 the user blocking someone.
     * @param username2 the person being blocked.
     * @return whether the operation was successful.
     */
    public static boolean blockProfile(String username1, String username2) {
        FriendRelationship relationship = new FriendRelationship(username1, username2, 2);

        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.BLOCK_USER,
                relationship, boolean.class);
    }

    /**
     * Removes the friend relation between the first and second user.
     *
     * @param username1 the user that requests the removal.
     * @param username2 the user being removed as a friend.
     * @return String[] usernames = {username1,username2}.
     */
    public static boolean removeFriend(String username1, String username2) {
        String[] usernames = {username1, username2};

        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.REMOVE_FRIEND,
                usernames, boolean.class);
    }

    /**
     * Requests the friendship status between two users.
     *
     * @param username1 one of the users.
     * @param username2 the other user.
     * @return 0 - pending, 1 - accepted, 2 - blocked.
     */
    public static int getStatus(String username1, String username2) {
        String[] usernames = {username1, username2};

        return restTemplate.getForObject(serverAddress + FriendEndpoints.FRIEND
                + FriendEndpoints.GET_STATUS
                + "?user1=" + username1 + "&user2=" + username2, int.class);
    }

    /**
     * Cancels a friend request between the two users.
     *
     * @param username1 the user cancelling their friend request.
     * @param username2 the user receiving the original request.
     * @return whether the operation was successful.
     */
    public static boolean cancelRequest(String username1, String username2) {
        String[] usernames = {username1, username2};

        return restTemplate.postForObject(serverAddress + FriendEndpoints.FRIEND
                        + FriendEndpoints.REMOVE_FRIEND,
                usernames, boolean.class);
    }

    /**
     * Returns all known usernames starting with the given username string.
     *
     * @param username The (maybe) partially complete username.
     * @return All recorded usernames starting with the given string.
     */
    public static ArrayList<String> search(String username) {
        String[] usernames = restTemplate.getForObject(serverAddress + ProfileEndpoints.PROFILE
                + ProfileEndpoints.SEARCH_USERNAME + "?username=" + username, String[].class);
        assert usernames != null;
        ArrayList<String> result = new ArrayList<>(Arrays.asList(usernames));
        debug(result.toString());
        return result;
    }

    /**
     * checks if the username exists in db.
     *
     * @param username sets username using String
     * @return boolean
     */
    public static boolean usernameExists(String username) {
        return restTemplate.getForObject(serverAddress
                + AuthenticationEndpoints.AUTHENTICATION
                + AuthenticationEndpoints.USERNAME_EXISTS
                + "?username=" + username, boolean.class);
    }

    /**
     * check if email exists in db.
     *
     * @param email sets email using string
     * @return boolean
     */
    public static boolean emailExists(String email) {
        return restTemplate.getForObject(serverAddress
                + AuthenticationEndpoints.AUTHENTICATION
                + AuthenticationEndpoints.EMAIL_EXISTS
                + "?email=" + email, boolean.class);
    }

    /**
     * sends arequest to server for the user's sent requests.
     *
     * @param username sets username as String
     * @return array list
     */
    public static ArrayList<String> getSentRequests(String username) {
        String[] usernames = restTemplate.getForObject(serverAddress
                + FriendEndpoints.FRIEND
                + FriendEndpoints.GET_SENT_REQUESTS + "/?name="
                + username, String[].class);

        return new ArrayList<String>(Arrays.asList(usernames));
    }

    /**
     * Returns the usernames in order of score of the user's friends.
     *
     * @param username set username as String
     * @return  ArrayList of friend rankings
     */
    public static ArrayList<String> friendsRanking(String username) {
        String[] ranking = restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_FRIEND + "/?username=" + username, String[].class);

        return new ArrayList<>(Arrays.asList(ranking));
    }

    /**
     * Update foodsurvey.
     *
     * @param foodSurvey sets food survey as Foodsurvey
     * @param username sets username survey as String
     * @return boolean
     */
    public static boolean sendFoodSurvey(FoodSurvey foodSurvey, String username) {
        try {
            String surveyjson = mapper.writeValueAsString(foodSurvey);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_FOOD, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update Transport Survey.
     *
     * @param transportationSurvey sets Transportation survey as Transportationsurvey
     * @param username sets username survey as String
     * @return boolean
     */
    public static boolean sendTransportSurvey(TransportationSurvey transportationSurvey,
                                              String username) {
        try {
            String surveyjson = mapper.writeValueAsString(transportationSurvey);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_TRANSPORTATION, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update energy survey.
     *
     * @param energySurvey sets energy survey using Energy survey
     * @param username sets username as String
     * @return boolean
     */
    public static boolean sendEnergySurvey(EnergySurvey energySurvey, String username) {
        try {
            String surveyjson = mapper.writeValueAsString(energySurvey);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_ENERGY, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update lifestyle survey.
     *
     * @param lifeStyleSurvey sets Life Survey using LifeSurvey
     * @param username  sets username using String
     * @return  boolean
     */
    public static boolean sendLifeStyleSurvey(LifeStyleSurvey lifeStyleSurvey, String username) {
        try {
            String surveyjson = mapper.writeValueAsString(lifeStyleSurvey);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_LIFESTYLE, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * sends score to db and checks if it was successful.
     *
     * @param score sets score using Score
     * @return boolean
     */
    public static boolean sendScore(String username, Score score) {
        try {
            String surveyjson = mapper.writeValueAsString(score);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_SCORE, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * sends Achievment profile and checks if it was successful.
     *
     * @param achievement sets achievements using Achievment
     * @return boolean
     */
    public static boolean sendAchievements(String username, Achievement achievement) {
        try {
            String surveyjson = mapper.writeValueAsString(achievement);
            String[] request = {username,surveyjson};

            return restTemplate.postForObject(serverAddress + ProfileEndpoints.PROFILE
                    + ProfileEndpoints.UPDATE_ACHIEVEMENTS, request, boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the usernames of the first 100 people in the world ranking.
     *
     * @return .
     */
    public static ArrayList<RankingEntry> globalRankingScore() {
        RankingEntry[] ranking = restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                        + ScoreEndpoints.GET_RANKING_GLOBAL_SCORE, RankingEntry[].class);

        if (ranking != null) {
            return new ArrayList<>(Arrays.asList(ranking));
        } else {
            return null;
        }
    }

    /**
     * request global ranking eco footprint.
     *
     * @return ranking array
     */
    public static RankingEntry[] globalRankingEcofootprint() {
        return restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_GLOBAL_ECOPRINT, RankingEntry[].class);

    }


    /**
     * request globalranking footprint.
     *
     * @return ranking array
     */
    public static RankingEntry[] globalRankingCarbonfootprint() {
        return restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_GLOBAL_CARBONPRINT, RankingEntry[].class);

    }

    /**
     * gets Friends score ranking.
     *
     * @return ArrayList of ranking entry
     */
    public static ArrayList<RankingEntry> friendRankingScore() {
        RankingEntry[] ranking = restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_GLOBAL_SCORE, RankingEntry[].class);

        if (ranking != null) {
            return new ArrayList<>(Arrays.asList(ranking));
        } else {
            return null;
        }
    }

    /**
     * gets friend ranking eco carbon footprint.
     *
     * @return array of rank
     */
    public static RankingEntry[] friendRankingEcofootprint() {
        return restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_GLOBAL_ECOPRINT, RankingEntry[].class);

    }


    /**
     * gets friend ranking carbon footprint.
     *
     * @return array of rank
     */
    public static RankingEntry[] friendRankingCarbonfootprint() {
        return restTemplate.getForObject(serverAddress + ScoreEndpoints.SCORE
                + ScoreEndpoints.GET_RANKING_GLOBAL_CARBONPRINT, RankingEntry[].class);

    }


    private static String hashText(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }
}
