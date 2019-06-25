package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.Profile;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class UserFriendController {

    @FXML
    private JFXButton username;

    @FXML
    private Label score;

    @FXML
    private Label footprint;

    @FXML
    private JFXButton addFriendBtn;

    @FXML
    private JFXButton removeFriendBtn;

    @FXML
    private JFXButton cancelRequestBtn;

    @FXML
    private JFXButton acceptRequest;

    @FXML
    private HBox requestAction;

    @FXML
    private JFXButton declineRequestBtn;

    private String user;
    private int points;
    private String carbonprint;
    private boolean isFriend;
    private FriendsController friendsController;

    /**
     * Empty Constructor.
     * friendsController set friendscontroller using class
     */
    public UserFriendController(String username, FriendsController friendsController) {
        this.user = username;
        this.points = 0;
        this.carbonprint = null;
        this.friendsController = friendsController;
    }

    private String getname() {
        return this.user;
    }

    @FXML
    void addFriend() {
        if (ServerCommunication.sendFriendRequest(LoginController.getusername(), getname())) {
            alert("Friend request has been sent to " + this.getname());
            friendsController.initialize();
            friendsController.loadFriends(LoginController.userprofile
                    .getFriendList(), friendsController.getFriendsPane());
        } else {
            alert("Connection error");
            friendsController.initialize();
            friendsController.loadFriends(LoginController.userprofile
                    .getFriendList(), friendsController.getFriendsPane());

        }

    }

    @FXML
    void removeFriend() {
        if (alert()) {
            if (ServerCommunication.removeFriend(LoginController.getusername(),
                    getname())) {
                LoginController.userprofile.removeFriend(getname());
                alert("Changes are made");
                friendsController.initialize();
                friendsController.loadFriends(LoginController
                        .userprofile.getFriendList(), friendsController.getFriendsPane());
            } else {
                alert("Connection error");
                friendsController.initialize();
                friendsController.loadFriends(LoginController
                        .userprofile.getFriendList(), friendsController.getFriendsPane());

            }

        }

    }

    @FXML
    void acceptRequest(ActionEvent event) {
        if (ServerCommunication.acceptFriendRequest(LoginController
                .getusername(),getname())) {
            LoginController.userprofile.addFriend(getname());
            friendsController.loadFriends(ServerCommunication.getSentRequests(
                    LoginController.getusername()), friendsController.requestsPane);
        } else {
            alert("Connection Error");
        }
    }

    @FXML
    void declineRequest(ActionEvent event) {
        if (ServerCommunication.declineFriendRequest(LoginController
                .getusername(),getname())) {
            friendsController.loadFriends(ServerCommunication.getSentRequests(
                    LoginController.getusername()), friendsController.requestsPane);
        } else {
            alert("Connection Error");
        }
    }

    @FXML
    void viewUser() {
        if (ServerCommunication.getStatus(LoginController.getusername(), user) == 1) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/friendcompare.fxml"));
            FriendsCompareController fcp = new FriendsCompareController(LoginController
                    .userprofile,
                    ServerCommunication.getProfile(user ,LoginController
                            .getusername()), this.friendsController);
            loader.setController(fcp);
            try {
                Parent root = loader.load();
                AnchorPane paner = friendsController.getfriendcomp();
                paner.getChildren().addAll(root);
                paner.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert("Sorry this user is not your friend");
        }

    }

    @FXML
    void initialize() {
        Profile target = ServerCommunication.getProfile(user,LoginController.getusername());
        this.username.setText(user);
        switch (ServerCommunication.getStatus(LoginController.getusername(), user)) {
            case 1:
                addFriendBtn.setVisible(false);
                removeFriendBtn.setVisible(true);
                cancelRequestBtn.setVisible(false);
                this.score.setText(target.getScore().getScore() + "");
                this.score.setVisible(true);
                this.footprint.setText((int) target.getScore().getFootprint() + "");
                this.footprint.setVisible(true);
                break;
            case 0:
                addFriendBtn.setVisible(false);
                removeFriendBtn.setVisible(false);
                if (checker()) {
                    this.requestAction.setVisible(true);
                } else {
                    cancelRequestBtn.setVisible(true);
                }
                this.score.setVisible(false);
                this.footprint.setVisible(false);
                break;
            case 2:
                removeFriendBtn.setVisible(false);
                addFriendBtn.setVisible(false);
                cancelRequestBtn.setVisible(false);
                this.score.setVisible(false);
                this.footprint.setVisible(false);
                break;
            default:
                break;

        }

    }

    private boolean checker() {
        ArrayList<String> check = ServerCommunication
                .getSentRequests(LoginController.getusername());
        for (int i = 0 ;  i < check.size() ; i ++  ) {
            if (check.get(i).equals(getname())) {
                return false ;
            }
        }
        return true ;
    }

    @FXML
    void cancelRequest(ActionEvent event) {

        if (ServerCommunication.cancelRequest(LoginController.getusername(), this.user)) {
            alert("request canceled");
            if (friendsController.selectedPane.equals(friendsController.sentPane)) {
                friendsController.loadFriends(ServerCommunication
                                .getSentRequests(LoginController.getusername()),
                        friendsController.sentPane);
            } else {
                friendsController.selectedPane = friendsController.getFriendsPane();
                friendsController.loadFriends(ServerCommunication
                                .getFriends(LoginController.getusername()),
                        friendsController.getFriendsPane());
            }
        }
    }

    private boolean alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Are you sure you want to remove this user ?");
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    private void alert(String statment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(statment);
        alert.showAndWait();
    }
}

