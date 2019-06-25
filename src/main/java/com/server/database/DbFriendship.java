package com.server.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DbFriendship {

    /**
     * Initializer.
     */
    private static DbFriendship dbFriendshipInstance = null;

    /**
     * Initializer.
     */
    private DbAdapter dbAdapter;

    /**
     * Constructor.
     *
     * @param dbAdapter dbAdapter instance.
     */
    private DbFriendship(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    /**
     * Getter.
     *
     * @return This method will return a dbFriendshipInstance.
     */
    public static DbFriendship getInstance() {
        if (dbFriendshipInstance == null) {
            dbFriendshipInstance = new DbFriendship(DbAdapter.getInstance());
        }
        return dbFriendshipInstance;
    }

    /**
     * Getter.
     *
     * @param dbAdapter This is an instance.
     * @return Returns an object instance.
     */
    public static DbFriendship getInstance(DbAdapter dbAdapter) {
        if (dbFriendshipInstance == null) {
            dbFriendshipInstance = new DbFriendship(dbAdapter);
        }
        dbFriendshipInstance.setDbAdapter(dbAdapter);
        return dbFriendshipInstance;
    }

    public static void setDbFriendshipInstance(DbFriendship dbFriendshipInstance) {
        DbFriendship.dbFriendshipInstance = dbFriendshipInstance;
    }

    public void setDbAdapter(DbAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    /**
     * Stores the status of the recent friends request in the database
     * (Literally stores the request).
     *
     * @param firstUsername  This is the user ID of one of the two users involved.
     *                       It has to be the one with the smallest userID between the two of them.
     * @param secondUsername This is the user ID of one of the two users involved.
     *                       It has to be the one with the smallest userID between the two of them.
     * @param status         This is the status of the request. 0: pending, 1: accepted, 2:blocked.
     * @param actionUsername This states which of the two users has done the last
     *                       update on the friendship.
     * @return This method will return whether the request has been stored successfully or not.
     */
    public boolean friendRequest(String firstUsername, String secondUsername, int status,
                                 String actionUsername) {

        try {

            String query = "INSERT INTO friendship(\"firstUsername\", \"secondUsername\", status,"
                    + " \"actionUsername\")"
                    + "VALUES(?,?,?,?)";

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);
            st.setString(1, firstUsername);
            st.setString(2, secondUsername);
            st.setInt(3, status);
            st.setString(4, actionUsername);

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    /**
     * This method will enable the user that has received a friend request to accept it.
     *
     * @param firstUsername  This is the smallest user ID of the two users.
     * @param secondUsername This is the largest user ID of the two users.
     * @param actionUsername This is the ID of he user that has accepted the request.
     * @return retu
     */
    public boolean setStatus(String firstUsername, String secondUsername, int status,
                             String actionUsername) {

        try {

            String query = "UPDATE friendship SET status =  \'"
                    + status + "\', \"actionUsername\" = \'"
                    + actionUsername + "\'"
                    + "WHERE \"firstUsername\" = \'" + firstUsername
                    + "\' AND  \"secondUsername\" = \'" + secondUsername + "\'";

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    /**
     * This method will return the current status of the friend request.
     *
     * @param firstUsername  This is the userID of the current user.
     * @param secondUsername This is the userID of the other user.
     * @return Should return the current status of the friendship relationship within GreenStreak.
     */

    public int getStatus(String firstUsername, String secondUsername) {

        try {

            String query = "SELECT status FROM friendship WHERE \"firstUsername\" = \'"
                    + firstUsername + "\' AND  \"secondUsername\" = \'" + secondUsername + "\'";

            ResultSet results = DbAdapter.getInstance().query(query);

            if (results.next()) {

                int result = results.getInt("status");


                return result;
            }

            return -1;

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;          //If -1 is returned it means that there is no
            // friendship relationship between the users at all.

        }

    }

    /**
     * This method will return all the UserIDs of all UserOneID's friends.
     *
     * @param username This is the username of the current user.
     * @return The method is supposed to return all of the friends UserIDs.
     */
    private ResultSet getUsersByStatusResultSet(String username, int status) {

        int count = 0;


        String query = "SELECT * FROM friendship WHERE (\"firstUsername\" = \'" + username + "\' "
                + "OR \"secondUsername\" = \'" + username + "\') AND status = \'" + status + "\'";

        ResultSet data = DbAdapter.getInstance().query(query);

        return data;
    }

    /**
     * This method will return all the UserIDs of all UserOneID's friends.
     *
     * @param username This is the username of the current user.
     * @return The method is supposed to return all of the friends UserIDs.
     */
    ArrayList<String> getUsersByStatus(String username, int status) {

        int count = 0;

        try {

            ResultSet data = getUsersByStatusResultSet(username, status);

            ArrayList<String> result = new ArrayList<>();

            String username1;
            String username2;
            String usernameAction;

            while (data.next()) {

                username1 = data.getString("firstUsername");
                username2 = data.getString("secondUsername");
                usernameAction = data.getString("actionUsername");

                count = count++;
                System.out.println("Creating list of friends");

                if (!username1.equals(username)) {
                    result.add(username1);

                } else {

                    result.add(username2);

                }

            }

            return result;


        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }
    }

    /**
     * This method will remove a user from your friendships.
     *
     * @param firstUsername  Shortest name.
     * @param secondUsername Longest name.
     * @return This method will return s boolean value.
     */
    boolean removeFirend(String firstUsername, String secondUsername) {

        try {
            String query = "DELETE FROM public.friendship WHERE \"firstUsername\" = \'"
                    + firstUsername + "\' AND \"secondUsername\" = \'" + secondUsername + "\'";

            PreparedStatement st = dbAdapter.conn.prepareStatement(query);

            st.executeUpdate();
            st.close();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    /**
     * This method will return the requests sent by the user.
     *
     * @param username This is the user's name.
     * @return The method will return an arrayList of the requests.
     */
    public ArrayList<String> getSentRequests(String username) {
        ArrayList<String> result = new ArrayList<>();

        ResultSet data = getUsersByStatusResultSet(username, 0);

        try {
            String username1;
            String username2;
            String usernameAction;
            while (data.next()) {
                username1 = data.getString("firstUsername");
                username2 = data.getString("secondUsername");
                usernameAction = data.getString("actionUsername");

                if (usernameAction.equals(username)) {
                    if (username1.equals(username)) {
                        result.add(username2);
                    } else {
                        result.add(username1);
                    }
                }
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method will return the received quests.
     *
     * @param username This is the user's username.
     * @return The method returns an ArrayList.
     */
    public ArrayList<String> getReceivedRequests(String username) {
        ArrayList<String> result = new ArrayList<>();

        ResultSet data = getUsersByStatusResultSet(username, 0);

        try {
            String username1;
            String username2;
            String usernameAction;

            while (data.next()) {
                username1 = data.getString("firstUsername");
                username2 = data.getString("secondUsername");
                usernameAction = data.getString("actionUsername");

                if (!usernameAction.equals(username)) {
                    if (username1.equals(username)) {
                        result.add(username2);
                    } else {
                        result.add(username1);
                    }
                }
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
