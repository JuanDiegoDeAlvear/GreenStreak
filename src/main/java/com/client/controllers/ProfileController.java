package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.PostCatalog;
import com.client.logic.SuperPost;
import com.client.logic.TransportationPost;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

public class ProfileController {

    @FXML
    private JFXButton settingsBtn;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label username;

    @FXML
    private Label score;

    @FXML
    private Label carbonFootprint;

    @FXML
    private Label ecoFootprint;

    @FXML
    private JFXProgressBar greenstreak;

    @FXML
    private VBox recentActivityBox;

    @FXML
    private VBox achievementsBox;

    @FXML
    private JFXButton foodSurveyTab;

    @FXML
    private JFXButton energySurveyTab;

    @FXML
    private JFXButton transportSurveyTab;

    @FXML
    private JFXButton lifestyleSurveyTab;

    @FXML
    private AnchorPane foodSurveyPane;

    @FXML
    private JFXButton foodEdit;

    @FXML
    private AnchorPane energySurveyPane;

    @FXML
    private JFXButton energyEdit;

    @FXML
    private AnchorPane transportSurveyPane;

    @FXML
    private JFXButton transportEdit;

    @FXML
    private AnchorPane lifestyleSurveyPane;

    @FXML
    private JFXButton lifestyleEdit;

    @FXML
    private Label foodOrganic;

    @FXML
    private Label foodDiet;

    @FXML
    private Label foodLocal;

    @FXML
    private Label foodWaste;

    @FXML
    private Label foodPrepackaged;

    @FXML
    private Label foodCompost;

    @FXML
    private Label energyHouseType;

    @FXML
    private Label energyRoommates;

    @FXML
    private JFXCheckBox energyGreenBoolean;

    @FXML
    private JFXCheckBox energyEfficientBoolean;

    @FXML
    private Label energyEstimatedUse;

    @FXML
    private Label transportFlightHours;

    @FXML
    private Label transportCar;

    @FXML
    private Label transportDistance;

    @FXML
    private Label transportMainMode;

    @FXML
    private Label lifestyleBigPurchase;

    @FXML
    private Label lifestyleWeeklyWaste;

    @FXML
    private Label lifestyleRecycle;

    @FXML
    private Label lifestyleAppliance;

    @FXML
    private Label lifestyleShowerTime;

    @FXML
    private AnchorPane editSurvey;

    @FXML
    private VBox recentPostsContainer;

    @FXML
    private AnchorPane badgesDrawer;

    @FXML
    private JFXButton viewBadges;

    @FXML
    private Label carbonSaved;

    @FXML
    private Label greenstreakVal;

    private JFXButton selectedTab;
    private AnchorPane selectedPane;

    @FXML
    void changeSurveyTab(ActionEvent event) {
        FontIcon icon = (FontIcon) selectedTab.getGraphic();
        icon.setIconColor(Color.rgb(0, 0, 0, 1));
        selectedTab.setTextFill(Color.rgb(0, 0, 0));
        if (event.getSource() == foodSurveyTab) {
            if (!selectedTab.equals(foodSurveyTab)) {
                selectedPane.setVisible(false);
                selectedTab = foodSurveyTab;
                foodSurveyPane.setVisible(true);
                selectedPane = foodSurveyPane;
            }
        }
        if (event.getSource() == energySurveyTab) {
            if (!selectedTab.equals(energySurveyTab)) {
                selectedPane.setVisible(false);
                selectedTab = energySurveyTab;
                energySurveyPane.setVisible(true);
                selectedPane = energySurveyPane;
            }
        }
        if (event.getSource() == transportSurveyTab) {
            if (!selectedTab.equals(transportSurveyTab)) {
                selectedPane.setVisible(false);
                selectedTab = transportSurveyTab;
                transportSurveyPane.setVisible(true);
                selectedPane = transportSurveyPane;
            }
        }
        if (event.getSource() == lifestyleSurveyTab) {
            if (!selectedTab.equals(lifestyleSurveyTab)) {
                selectedPane.setVisible(false);
                selectedTab = lifestyleSurveyTab;
                lifestyleSurveyPane.setVisible(true);
                selectedPane = lifestyleSurveyPane;
            }
        }
        selectedTab.setTextFill(Color.rgb(39, 174, 96));
        FontIcon ic = (FontIcon) selectedTab.getGraphic();
        ic.setIconColor(Color.rgb(39, 174, 96, 1));
    }

    @FXML
    void openSurveyEditor(ActionEvent event) {
        String selected;
        if (event.getSource() == foodEdit) {
            selected = "food";
        } else if (event.getSource() == energyEdit) {
            selected = "energy";
        } else if (event.getSource() == transportEdit) {
            selected = "transport";
        } else if (event.getSource() == lifestyleEdit) {
            selected = "lifestyle";
        } else {
            selected = "";
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/surveyEditor.fxml"));
        SurveyEditorController sec = new SurveyEditorController(selected, editSurvey);
        loader.setController(sec);
        try {
            Parent root = loader.load();
            editSurvey.setVisible(true);
            editSurvey.getChildren().addAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openBadgeDrawer(ActionEvent event) {
        if (!badgesDrawer.isVisible()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/badgedraw.fxml"));
            BadgeDrawerController bdc = new BadgeDrawerController(badgesDrawer);
            loader.setController(bdc);
            try {
                Node root = loader.load();
                badgesDrawer.getChildren().setAll(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
            badgesDrawer.setVisible(true);
        } else {
            badgesDrawer.setVisible(false);
        }

    }

    @FXML
    void initialize() throws MalformedURLException, AWTException {
        RankingsController.scoreCalc();

        selectedTab = foodSurveyTab;
        selectedPane = foodSurveyPane;
        FontIcon ic = (FontIcon) selectedTab.getGraphic();
        ic.setIconColor(Color.rgb(39, 174, 96, 1));
        selectedTab.setTextFill(Color.rgb(39, 174, 96));

        username.setText(LoginController.getusername());
        carbonFootprint.setText((int)LoginController.userprofile.getScore().getFootprint() + "");
        ecoFootprint.setText((int)LoginController.userprofile.getScore().getFootprintEco() + "");
        greenstreak.setSecondaryProgress(50);
        score.setText(LoginController.userprofile
                .getScore().getScore() + "");
        carbonSaved.setText( (int)(LoginController
                .userprofile.getScore().getFootprint() - 16) * -1 + "");


        foodOrganic.setText(LoginController.userprofile.getFoodProfile()
                .getAmountOrganicFood());
        foodDiet.setText(LoginController.userprofile.getFoodProfile()
                .getDietChoice());
        foodLocal.setText(LoginController.userprofile.getFoodProfile()
                .getAmountLocalFood());
        foodWaste.setText(LoginController.userprofile.getFoodProfile()
                .getAmountWastedFood());
        foodPrepackaged.setText(LoginController.userprofile.getFoodProfile()
                .getAmountFoodPackaged());
        foodCompost.setText(LoginController.userprofile.getFoodProfile()
                .getAmountFoodCompost());


        transportFlightHours.setText(LoginController.userprofile
                .getTransportationProfile().getAveHoursFly() + " Hrs");
        transportCar.setText(LoginController.userprofile
                .getTransportationProfile().getTypeOfCar());
        transportMainMode.setText(LoginController.userprofile
                .getTransportationProfile().getMainModeTransport());
        transportDistance.setText(LoginController.userprofile
                .getTransportationProfile().getAveKmPerDay() + " KM");


        energyHouseType.setText(LoginController.userprofile.getEnergyProfile()
                .getTypeHouse());
        energyEstimatedUse.setText(LoginController.userprofile.getEnergyProfile()
                .getAmountEnergy());
        energyRoommates.setText(LoginController.userprofile.getEnergyProfile()
                .getAmountRoommates() + "");
        energyGreenBoolean.setDisable(LoginController.userprofile.getEnergyProfile()
                .getGreenEnergy());
        energyEfficientBoolean.setDisable(LoginController.userprofile.getEnergyProfile()
                .getEfficientLighting());


        lifestyleBigPurchase.setText(LoginController.userprofile.getLifeStyleProfile()
                .getHowOftenBigPurchase());
        lifestyleWeeklyWaste.setText(LoginController.userprofile.getLifeStyleProfile()
                .getWeeklyWaste());
        lifestyleRecycle.setText(LoginController.userprofile.getLifeStyleProfile()
                .getRecycle());
        lifestyleAppliance.setText(LoginController.userprofile.getLifeStyleProfile()
                .getApplianceUse());
        lifestyleShowerTime.setText(LoginController.userprofile.getLifeStyleProfile()
                .getAveShowerTime() + "");
        energyGreenBoolean.selectedProperty().setValue(LoginController.userprofile
                .getEnergyProfile().getGreenEnergy());
        energyEfficientBoolean.selectedProperty().setValue(LoginController.userprofile
                .getEnergyProfile().getEfficientLighting());

        if (LoginController.userprofile.getScore().getScore() > LoginController.userprofile
                .getScore().getGreenstreak()) {
            LoginController.userprofile.getScore()
                    .setGreenstreak(LoginController.userprofile.getScore().getScore() * 2);
        }
        if (LoginController.userprofile.getScore().getScore() == LoginController
                .userprofile.getScore().getGreenstreak()) {
            LoginController.userprofile.getScore().setScore(LoginController
                    .userprofile.getScore().getScore() + (LoginController
                    .userprofile.getScore().getGreenstreak() / 4));
        }

        RankingsController.scoreCalc();
        LoginController.userprofile.getAchievement().checklowEco();
        LoginController.userprofile.getAchievement().carblsixach();
        int score = LoginController.userprofile.getScore().getScore();
        int level = (score / 1000) + 1;
        System.out.println(level + "This is the level");
        System.out.println(score - (level - 1) * 1000);
        System.out.println((score - (level - 1) * 1000) / ((double) 1000));
        greenstreak.setProgress((score - (level - 1) * 1000) / ((double) 1000));

        //greenstreak.setProgress(Streak.progress((double) LoginController.userprofile.getScore()
        //.getGreenstreak(), (double) LoginController.userprofile.getScore().getScore()));
        greenstreakVal.setText("Level:" + level);
        loadRecentPosts();
    }

    /**
     * Load first 3 posts in recent activity of the profile recent
     * activity panel.
     */
    public void loadRecentPosts() {
        PostCatalog posts = PostCatalog.sortByDate(ServerCommunication
                .recievepostarray(LoginController.getusername()));

        int num = (3 <= posts.size()) ? 3 : posts.size();
        for (int i = 0; i < num; i++) {
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
                        new FXMLLoader(getClass().getResource("/fxml/recentpost.fxml"));
                SinglePostController spc = new SinglePostController(type, value, postclass, date);
                loader.setController(spc);
                loader.setRoot(recentPostsContainer);
                loader.load();
            } catch (IOException e) {
                System.out.println("Error here");
                e.printStackTrace();
            }
        }
    }

}
