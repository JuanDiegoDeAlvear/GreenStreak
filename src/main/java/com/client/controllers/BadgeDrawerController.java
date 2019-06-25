package com.client.controllers;

import com.client.logic.Achievement;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BadgeDrawerController {

    @FXML
    private VBox badgeContainer;

    @FXML
    private ImageView ach1;

    @FXML
    private Label achText1;

    @FXML
    private ImageView ach2;

    @FXML
    private Label achText2;

    @FXML
    private ImageView ach3;

    @FXML
    private Label achText3;

    @FXML
    private ImageView ach4;

    @FXML
    private Label achText4;

    @FXML
    private ImageView ach5;

    @FXML
    private Label achText5;

    @FXML
    private VBox badgeContainer1;

    @FXML
    private ImageView ach6;

    @FXML
    private Label achText6;

    @FXML
    private ImageView ach7;

    @FXML
    private Label achText7;

    @FXML
    private ImageView ach8;

    @FXML
    private Label achText8;

    @FXML
    private ImageView ach9;

    @FXML
    private Label achText9;

    @FXML
    private ImageView ach10;

    @FXML
    private Label achText10;

    @FXML
    private JFXButton close;

    private AnchorPane badgedraw;

    public BadgeDrawerController(AnchorPane badgedraw) {
        this.badgedraw = badgedraw;
    }

    @FXML
    void close(ActionEvent event) {
        badgedraw.setVisible(false);
    }

    @FXML
    void initialize() {
        Achievement ach = LoginController.userprofile.getAchievement();

        if (ach.isLoggedin()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/leafbadge.png"));
            ach1.setImage(ic);
            achText1.setText("First Login");
        }

        if (ach.isFirstPost()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/post.png"));
            ach2.setImage(ic);
            achText2.setText("First Post");
        }

        if (ach.isFirstFriend()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/friends.png"));
            ach3.setImage(ic);
            achText3.setText("First Friend");
        }

        if (ach.isFiveDaysr()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/recyclebadge.png"));
            ach4.setImage(ic);
            achText4.setText("Recycle 5 Days");
        }

        if (ach.isHundKm()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/bike.png"));
            ach5.setImage(ic);
            achText5.setText("Biked 100 km");
        }

        if (ach.isCarblsix()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/footprintbadge.png"));
            ach6.setImage(ic);
            achText6.setText("Low Carbon Footprint");
        }

        checker(ach);
    }

    private void checker(Achievement ach) {
        if (ach.isLocal()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/local.png"));
            ach7.setImage(ic);
            achText7.setText("Buy Local");
        }

        if (ach.isToprank()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/leader1.png"));
            ach8.setImage(ic);
            achText8.setText("Top Ranked");
        }

        if (ach.isLowEco()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/earthbadge.png"));
            ach9.setImage(ic);
            achText9.setText("Your Eco AF");
        }

        if (ach.isFivedaysv()) {
            Image ic = new Image(getClass().getResourceAsStream("/images/Bananabadge.png"));
            ach10.setImage(ic);
            achText10.setText("5 Days Vegan");
        }
    }

}

