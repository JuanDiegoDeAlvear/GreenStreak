package com.server.database;

import java.util.ArrayList;

public class FriendDatabaseManager {

    private static FriendDatabaseManager friendDatabaseManagerInstance = null;

    private DbFriendship dbFriendship;

    /**
     * Constructor.
     *
     * @param dbFriendship dbFriendship.
     */
    private FriendDatabaseManager(DbFriendship dbFriendship) {
        this.dbFriendship = dbFriendship;
    }

    /**
     * Setter.
     *
     * @param friendDatabaseManagerInstance Instance.
     */
    public static void setFriendDatabaseManagerInstance(FriendDatabaseManager
                                                                friendDatabaseManagerInstance) {
        FriendDatabaseManager.friendDatabaseManagerInstance = friendDatabaseManagerInstance;
    }

    /**
     * Getter.
     *
     * @return Returns an instance
     */
    public static FriendDatabaseManager getInstance() {
        if (friendDatabaseManagerInstance == null) {
            friendDatabaseManagerInstance = new FriendDatabaseManager(DbFriendship.getInstance());
        }
        return friendDatabaseManagerInstance;
    }


    /**
     * This method will return an array containing the usernames sorted by size.
     *
     * @param username1 username of the user carrying out the action.
     * @param username2 username of the user that receives the request.
     * @return returns an array with the sorted usernames.
     */
    public String[] sortUsernames(String username1, String username2) {

        String[] sortedUsernames = new String[2];

        if ((username1.compareTo(username2)) < 0) {

            sortedUsernames[0] = username1;
            sortedUsernames[1] = username2;

            return sortedUsernames;

        } else {

            sortedUsernames[0] = username2;
            sortedUsernames[1] = username1;

            return sortedUsernames;
        }


    }

    /**
     * Sets the relationship as user1 requests friends with user2.
     *
     * @param username1 the user sending the request.
     * @param username2 the user receiving the request.
     * @return whether the operation was successful.
     */
    public boolean sendRequest(String username1, String username2) {

        String[] usernames = sortUsernames(username1, username2);

        String firstUsername = usernames[0];
        String secondUsername = usernames[1];


        return dbFriendship.friendRequest(firstUsername, secondUsername, 0, username1);
    }

    /**
     * Returns the useranmes of all the users that comply with the status.
     * 0: friend requests, 1: friends, 2: blocked
     *
     * @param username the user who has the friends
     * @return the usermanes of the friends
     */
    public String[] getUsersByStatus(String username, int status) {

        ArrayList<String> data = dbFriendship.getUsersByStatus(username, status);

        String[] result = data.toArray(new String[data.size()]);

        return result;
    }


    /**
     * Sets the status between the first and the second user to .
     * whatever it is desired by the first user.
     *
     * @param username1 The username of who accepts the friend request.
     * @param username2 The username of who's friend request is accepted.
     * @param status    This will be the desired status of the relationship by username 1.
     * @return whether the operation was successful.
     */
    public boolean setStatus(String username1, String username2, int status) {


        String[] usernames = sortUsernames(username1, username2);

        String firstUsername = usernames[0];
        String secondUsername = usernames[1];


        return dbFriendship.setStatus(firstUsername, secondUsername, status, username1);
    }


    /**
     * Deletes the friendship status between two people, effectively removing it.
     *
     * @param username1 The username of the person removing a friend.
     * @param username2 The username of the friend being removed.
     * @return whether the operation was successful.
     */
    public boolean removeFriend(String username1, String username2) {

        String[] usernames = sortUsernames(username1, username2);

        String firstUsername = usernames[0];
        String secondUsername = usernames[1];

        return dbFriendship.removeFirend(firstUsername, secondUsername);
    }

    /**
     * Returns the friendship status between two users.
     *
     * @param username1 The username of the first user.
     * @param username2 The username of the second user.
     * @return whether the operation was successful.
     */
    public int getStatus(String username1, String username2) {

        String[] usernames = sortUsernames(username1, username2);

        String firstUsername = usernames[0];
        String secondUsername = usernames[1];

        return dbFriendship.getStatus(firstUsername, secondUsername);
    }

    /**
     * This method will return the requests
     *
     * @param name This is the user's username.
     * @return Returns a String[].
     */
    public String[] getSentRequests(String name) {
        ArrayList<String> names = dbFriendship.getSentRequests(name);
        return names.toArray(new String[names.size()]);
    }

    /**
     * This method will return the requests
     *
     * @param name This is the user's username.
     * @return Returns a String[].
     */
    public String[] getReceivedRequests(String name) {
        ArrayList<String> names = dbFriendship.getReceivedRequests(name);
        return names.toArray(new String[names.size()]);
    }

}
