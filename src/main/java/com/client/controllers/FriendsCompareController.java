package com.client.controllers;

import com.client.logic.Profile;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FriendsCompareController {

    @FXML
    private Label userOne;

    @FXML
    private Label scoreOne;

    @FXML
    private Label carbonFootOne;

    @FXML
    private Label ecoFootOne;

    @FXML
    private Label carbonSavedOne;

    @FXML
    private Label userTwo;

    @FXML
    private Label scoreTwo;

    @FXML
    private Label carbonFootTwo;

    @FXML
    private Label ecoFootTwo;

    @FXML
    private Label carbonSavedTwo;

    @FXML
    private JFXButton closeBtn;

    private String usernameone;
    private String usernametwo;


    private String scoreone;
    private String scoretwo;

    private String carbonfootone;
    private String carbonfoottow;
    private String carbonsavedone;
    private String carbonsavetwo;

    private String ecoone;
    private String ecotwo;

    private FriendsController friendsController;


    /**
     * Empty Constructor.
     * profile1 sets Profile using profile
     * profile2 sets Profile using Profile
     */
    public FriendsCompareController(Profile profile1, Profile profile2,
                                    FriendsController friendController) {
        usernameone = profile1.getName();
        usernametwo = profile2.getName();
        scoreone = String.valueOf(profile1.getScore().getScore());
        scoretwo = String.valueOf(profile2.getScore().getScore());
        carbonfootone = String.valueOf( (int) profile1.getScore().getFootprint());
        carbonfoottow = String.valueOf( (int) profile2.getScore().getFootprint());
        carbonsavedone = String.valueOf( ((int) profile1.getScore().getFootprint() - 16) * - 1);
        carbonsavetwo = String.valueOf(( (int) profile2.getScore().getFootprint() - 16) * - 1);
        ecoone = String.valueOf( (int) profile1.getScore().getFootprintEco());
        ecotwo = String.valueOf( (int) profile2.getScore().getFootprintEco());

        this.friendsController = friendController;
    }

    @FXML
    void close(ActionEvent event) {
        friendsController.friendsCompare.setVisible(false);

    }

    @FXML
    void initialize() {
        this.userOne.setText(usernameone);
        this.scoreOne.setText(scoreone);
        this.carbonFootOne.setText(carbonfootone);
        this.ecoFootOne.setText(ecoone);
        this.userTwo.setText(usernametwo);
        this.scoreTwo.setText(scoretwo);
        this.carbonFootTwo.setText(carbonfoottow);
        this.ecoFootTwo.setText(ecotwo);
        this.carbonSavedOne.setText(carbonsavedone);
        this.carbonSavedTwo.setText(carbonsavetwo);
    }


}
