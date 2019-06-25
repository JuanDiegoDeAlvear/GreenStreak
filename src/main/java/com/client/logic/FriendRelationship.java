package com.client.logic;

public class FriendRelationship {
    private String senderUsername;
    private String targetUsername;
    private int status;

    /**
     * Constructor.
     *
     * @param senderUsername sets senderusername as String
     * @param targetUsername sets targetusername as string
     * @param status   sets status as int
     */
    public FriendRelationship(String senderUsername, String targetUsername, int status) {
        this.senderUsername = senderUsername;
        this.targetUsername = targetUsername;
        this.status = status;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getTargetUsername() {
        return targetUsername;
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
