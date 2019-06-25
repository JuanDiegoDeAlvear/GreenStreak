package com.client.controllers;

import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.SuperPost;
import com.client.logic.TransportationPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostGroupController {

    ArrayList<SuperPost> posts;
    Date date;
    String user;
    @FXML
    private Label postDate;
    @FXML
    private Label username;
    @FXML
    private VBox postsContainer;

    /**
     * Constructor.
     *
     * @param date     sets date using Date
     * @param username sets username using String
     */
    public PostGroupController(Date date, String username) {
        this.date = date;
        this.user = username;
        this.posts = new ArrayList<>();
    }

    public void add(SuperPost poster) {
        posts.add(poster);
    }

    @FXML
    void initialize() {
        postDate.setText(dateFormat(date));
        username.setText(user);
        for (int i = 0; i < posts.size(); i++) {
            SuperPost poster = posts.get(i);
            String type;
            String value;
            String postclass;
            if (poster.getPostClass().equals("transportation")) {
                TransportationPost post = (TransportationPost) poster;
                type = post.getType();
                double distance = post.getKilometer();
                value = Math.floor(distance * 100) / 100 + " km";
                postclass = "transport";
            } else if (poster.getPostClass().equals("food")) {
                FoodPost post = (FoodPost) poster;
                type = post.getMeal();
                value = post.getType();
                postclass = "food";
            } else if (poster.getPostClass().equals("ConsumptionPost")) {
                ConsumptionPost post = (ConsumptionPost) poster;
                type = post.getConsume();
                postclass = "consumption";
                if (post.getState()) {
                    value = "Yes";
                } else {
                    value = "No";
                }
            } else {
                postclass = "error";
                type = "Error";
                value = "Error";
            }
            Date date = poster.getDate();
            try {
                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/post.fxml"));
                SinglePostController spc = new SinglePostController(type, value, postclass, date);
                loader.setController(spc);
                loader.setRoot(postsContainer);
                loader.load();
            } catch (IOException e) {
                System.out.println("Error here");
                e.printStackTrace();
            }
        }
    }

    /**
     * chnages style of the da to simple.
     *
     * @param da sets date using Date
     * @return Sting as
     */
    public String dateFormat(Date da) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = sdf.format(da);
        return date;
    }
}

