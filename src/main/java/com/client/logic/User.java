package com.client.logic;

public class User {

    private String username;
    private String password;

    /**
     * Empty constructor.
     */
    public User() {
        this.username = null;
        this.password = null;

    }

    /**
     * Constructor.
     *
     * @param id       set user ID using String
     * @param passcode set user password using String
     */
    public User(String id, String passcode) {
        this.username = id;
        this.password = passcode;

    }

    public String getusername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User that = (User) obj;
            return this.getusername().equals(that
                    .getusername()) && this.getPassword().equals(that.getPassword());
        } else {
            return false;
        }
    }
}
