package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.PostCatalog;
import com.client.logic.SuperPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FeedController {

    private PostCatalog posts;

    @FXML
    private VBox postsContainer;

    @FXML
    void initialize() {

        posts = ServerCommunication.recievepostarray(LoginController.getusername());

        ArrayList<ArrayList<SuperPost>> datedposts = sortPostsByDate(posts);

        for (int i = 0; i < datedposts.size(); i++) {
            PostGroupController groupedPosts = new PostGroupController(datedposts.get(i)
                    .get(0).getDate(),
                    datedposts.get(i).get(0).getUsername());

            for (int k = 0; k < datedposts.get(i).size(); k++) {
                groupedPosts.add(datedposts.get(i).get(k));
            }
            try {
                FXMLLoader loader =
                        new FXMLLoader(getClass().getResource("/fxml/postsByDate.fxml"));
                loader.setController(groupedPosts);
                loader.setRoot(postsContainer);
                loader.load();
            } catch (IOException e) {
                System.out.println("Error here");
                e.printStackTrace();
            }

        }
        LoginController.userprofile.getAchievement().checkfiveDaysv();
        LoginController.userprofile.getAchievement().checkfiveDaysr();
        LoginController.userprofile.getAchievement().checkLocal();
        LoginController.userprofile.getAchievement().checkfirstPost();


    }

    /**
     * compare posts by date.
     *
     * @param date set date using Date
     * @param obj  set obj using Date
     * @return boolean
     */
    public boolean compareDate(Date date, Date obj) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_YEAR);

        Calendar calo = Calendar.getInstance();
        calo.setTimeInMillis(obj.getTime());
        int year2 = calo.get(Calendar.YEAR);
        int day2 = calo.get(Calendar.DAY_OF_YEAR);

        return year == year2 && day == day2;
    }

    /**
     * sort posts by date.
     *
     * @param postcatalog aet postcatalog using PostCatalog
     * @return arraylist
     */
    public ArrayList<ArrayList<SuperPost>> sortPostsByDate(PostCatalog postcatalog) {
        ArrayList<ArrayList<SuperPost>> datedposts = new ArrayList<>();

        PostCatalog posts = PostCatalog.sortByDate(postcatalog);

        Date prev = null;
        int index = 0;

        for (int i = 0; i < posts.size(); i++) {

            SuperPost poster = posts.get(i);

            if (i == 0) {
                prev = poster.getDate();
                ArrayList<SuperPost> bucket = new ArrayList<>();
                bucket.add(poster);
                datedposts.add(bucket);
            } else {
                if (compareDate(poster.getDate(), prev)) {
                    datedposts.get(index).add(poster);
                } else {
                    prev = poster.getDate();
                    ArrayList<SuperPost> bucket = new ArrayList<>();
                    bucket.add(poster);
                    datedposts.add(bucket);
                    index++;
                }
            }

        }

        return datedposts;
    }
}
