package com.client.controllers;

import com.client.Main;
import com.client.logic.Profile;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class AppFrameController {

    @FXML
    private JFXButton homeNavBtn;

    @FXML
    private JFXButton profileNavBtn;

    @FXML
    private JFXButton rankingsNavBtn;

    @FXML
    private JFXButton feedNavBtn;

    @FXML
    private JFXButton goalsNavBtn;

    @FXML
    private JFXButton postNavBtn;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane newPostContainer;

    @FXML
    private JFXButton friendsNavBtn;

    private JFXButton selected;
    @FXML
    private JFXButton logoutBtn;

    @FXML
    void changeContentPane(ActionEvent event) throws IOException {

        FontIcon icon = (FontIcon) selected.getGraphic();
        icon.setIconColor(Color.rgb(233, 233, 233));
        selected.setTextFill(Color.rgb(233, 233, 233));

        this.selected.getStyleClass().clear();
        this.selected.getStyleClass().add("navBtn");
        this.selected.getStyleClass().add("jfx-button");
        this.selected.getStyleClass().add("button");

        if (event.getSource() == rankingsNavBtn) {
            this.selected = rankingsNavBtn;
            contentPane.getChildren().setAll((Node) FXMLLoader
                    .load(getClass().getResource("/fxml/rankings.fxml")));
        }

        if (event.getSource() == feedNavBtn) {
            this.selected = feedNavBtn;
            contentPane.getChildren().setAll((Node)
                    FXMLLoader.load(getClass().getResource("/fxml/feed.fxml")));
        }

        if (event.getSource() == goalsNavBtn) {
            contentPane.getChildren().setAll((Node)
                    FXMLLoader.load(getClass().getResource("/fxml/goals.fxml")));
        }

        if (event.getSource() == profileNavBtn) {
            this.selected = profileNavBtn;
            try {
                contentPane.getChildren().setAll((Node)
                        FXMLLoader.load(getClass().getResource("/fxml/profilepage.fxml")));
            } catch (IOException e) {
                System.out.println("error from appframe");
                e.printStackTrace();
            }
        }

        if (event.getSource() == homeNavBtn) {
            this.selected = homeNavBtn;
            contentPane.getChildren().setAll((Node)
                    FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml")));
        }

        if (event.getSource() == friendsNavBtn) {
            this.selected = friendsNavBtn;
            try {
                contentPane.getChildren().setAll((Node)
                        FXMLLoader.load(getClass().getResource("/fxml/friends.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        selected.getStyleClass().add("activeNav");
        FontIcon ic = (FontIcon) selected.getGraphic();
        ic.setIconColor(Color.rgb(46, 204, 113));
        selected.setTextFill(Color.rgb(46, 204, 113));
    }

    @FXML
    void logout(ActionEvent event) {
        if (alert()) {
            LoginController.userprofile = new Profile();
            Stage stage = Main.getStage();
            Parent content = null;
            try {
                content = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setStageContent(stage, content);

        }
    }

    @FXML
    void createNewPost(ActionEvent event) {
        //This class doesn't create the new post, but loads the new post dialog box.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newpostdialog.fxml"));
        try {
            PostCreatorController npc = new PostCreatorController(newPostContainer);
            loader.setController(npc);
            Parent root = loader.load();
            newPostContainer.getChildren().setAll(root);
            newPostContainer.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() throws IOException {
        contentPane.getChildren().setAll((Node)
                FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml")));
        this.selected = homeNavBtn;
        FontIcon ic = (FontIcon) selected.getGraphic();
        ic.setIconColor(Color.rgb(46, 204, 113));
        selected.setTextFill(Color.rgb(46, 204, 113));
    }

    private void setStageContent(Stage stage, Parent content) {
        Scene scene = new Scene(content);

        Main.setCurrentScene(scene);

        String css = this.getClass().getResource("/css/global.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
    }

    private boolean alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Are you sure you want to logout ?  ");
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
}
