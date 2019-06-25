package com.client.logic;

import java.util.Date;
import java.util.Objects;

public class SuperPost {

    String username;
    Date date;
    String postClass;
    int id;

    /**
     * Constructor.
     *
     * @param username  set username using String
     * @param date      set date using Date
     * @param postClass set name with string
     */
    public SuperPost(String username, Date date, String postClass, int id) {
        this.username = username;
        this.date = date;
        this.postClass = postClass;
        this.id = id;
    }

    /**
     * Empty Constructor.
     */
    public SuperPost() {
        this.username = null;
        this.date = null;
        this.postClass = null;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPostClass() {
        return this.postClass;
    }

    public void setPostClass(String postClass) {
        this.postClass = postClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuperPost)) {
            return false;
        }
        SuperPost superPost = (SuperPost) obj;
        return Objects.equals(getUsername(), superPost.getUsername())
                && superPost.getId() == this.getId()
                && Objects.equals(getDate(), superPost.getDate())
                && Objects.equals(getPostClass(), superPost.getPostClass());
    }

}
