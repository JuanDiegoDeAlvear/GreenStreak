package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.FoodPost;
import com.client.logic.PostCatalog;
import com.client.logic.RankingEntry;
import com.client.logic.ScoreCalc;
import com.client.logic.TransportationPost;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class RankingsController {

    @FXML
    private VBox scoreRanking;

    @FXML
    private VBox carbonRanking;

    @FXML
    private VBox ecoRanking;

    @FXML
    private Label rank1;

    @FXML
    private Label rank11;

    @FXML
    private Label rank22;


    /**
     * Compares dates on posts.
     *
     * @param date date
     * @param obj  date
     * @return difference in dates
     */
    public static boolean compareDate(Date date, Date obj) {
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
     * Calculates score for a posts.
     */
    public static void scoreCalc() {

        LoginController.userprofile.getScore().setScore(0);
        PostCatalog posts = PostCatalog.sortByDate(ServerCommunication
                .recievepostarray(LoginController.getusername()));
        for (int i = 0; i < posts.size(); i++) {
            Date today = new Date();
            System.out.println(posts.size());
            System.out.println(i);
            if (!(compareDate(posts.get(i).getDate(), today))) {
                System.out.println(posts.get(i).getDate());
                posts.getPostcatalog().remove(posts.get(i));


            }
        }
        for (int i = 0; i != posts.size(); i++) {
            System.out.println(posts.size());
            System.out.println("Got here");
            if (posts.get(i).getPostClass().equals("food")) {
                FoodPost food = (FoodPost) posts.get(i);
                LoginController.userprofile.getScore().setScore(
                        LoginController.userprofile.getScore().getScore() + ScoreCalc
                                .foodScore(food.getType()));

            } else if (posts.get(i).getPostClass().equals("transportation")) {
                TransportationPost transport = (TransportationPost) posts.get(i);
                transporthelp(transport);

            } else if (posts.get(i).getPostClass().equals("ConsumptionPost")) {
                LoginController.userprofile.getScore().setScore(
                        LoginController.userprofile.getScore().getScore()
                                + 250);

            }
            ServerCommunication.sendScore(LoginController.userprofile.getName(),
                    LoginController.userprofile.getScore());
        }
    }

    private static void transporthelp(TransportationPost transport) {
        if (transport.getType().equals("car")) {
            LoginController.userprofile.getScore().setScore(
                    LoginController.userprofile.getScore().getScore()
                            -
                            ScoreCalc.carScore((int) transport.getKilometer()));
        }
        if (transport.getType().equals("bike")) {
            LoginController.userprofile.getScore().setScore(
                    LoginController.userprofile.getScore().getScore()
                            +
                            ScoreCalc.bikeScore((int) transport.getKilometer()));
        }
        if (transport.getType().equals("plane")) {
            LoginController.userprofile.getScore().setScore(
                    LoginController.userprofile.getScore().getScore()
                            -
                            ScoreCalc.planeScore((int) transport.getKilometer()));
        }
        if (transport.getType().equals("publictransportation")) {
            LoginController.userprofile.getScore().setScore(
                    LoginController.userprofile.getScore().getScore()
                            + ScoreCalc.publicScore((int) transport.getKilometer(),
                            LoginController.userprofile
                            .getTransportationProfile()));
        }
    }

    @FXML
    void initialize() {
        LoginController.userprofile.getAchievement().checktoprank();


        ArrayList<RankingEntry> score = ServerCommunication.globalRankingScore();

        System.out.println(score.size());
        String rank = "";
        for (int i = 0; i != score.size(); i++) {

            rank = rank + "\n" + score.get(i).getUsername() + " " + score.get(i).getScore();

        }
        rank1.setText(rank);
        String rankcarbon = "";

        RankingEntry[] carbon = ServerCommunication.globalRankingCarbonfootprint();
        Collections.reverse(Arrays.asList(carbon));

        for (int i = 0; i != carbon.length; i++) {

            rankcarbon = rankcarbon + "\n" + carbon[i].getUsername() + " " + carbon[i].getScore();

        }
        rank11.setText(rankcarbon);

        String rankfoot = "";
        RankingEntry[] foot = ServerCommunication.globalRankingEcofootprint();
        Collections.reverse(Arrays.asList(foot));

        for (int i = 0; i != foot.length; i++) {

            rankfoot = rankfoot + "\n" + foot[i].getUsername() + " " + foot[i].getScore();

        }
        rank22.setText(rankfoot);
    }

}

