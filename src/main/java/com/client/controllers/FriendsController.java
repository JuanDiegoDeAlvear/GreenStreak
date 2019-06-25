package com.client.controllers;

import com.client.ServerCommunication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class FriendsController {

    public JFXButton selectedNav;
    public VBox selectedPane;
    @FXML
    public VBox requestsPane;
    @FXML
    public VBox sentPane;
    @FXML
    VBox friendsPane;
    @FXML
    AnchorPane friendsCompare;
    @FXML
    private JFXTextField searchBar;
    @FXML
    private JFXButton friendsNav;
    @FXML
    private JFXButton requestsNav;
    @FXML
    private JFXButton sentNav;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private VBox resultBox;

    private ArrayList<String> searchresult;



    @FXML
    void search(ActionEvent event) {
        searchresult = ServerCommunication.search(searchBar.getText());
        this.selectedPane = resultBox;
        loadFriends(searchresult, friendsPane);
        if (searchresult.size() == 0) {
            alert("No users exists with such name ");
            this.selectedPane = friendsPane;
            loadFriends(LoginController.userprofile.getFriendList(), friendsPane);
        }
    }

    @FXML
    void searchWithEnterKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            searchresult = ServerCommunication.search(searchBar.getText());
            this.selectedPane = resultBox;
            loadFriends(searchresult, friendsPane);
            if (searchresult.size() == 0) {
                alert("No users exists with such name ");
                this.selectedPane = friendsPane;
                loadFriends(LoginController.userprofile.getFriendList(), friendsPane);
            }
        }
    }


    @FXML
    void changeTab(ActionEvent event) {

        selectedPane.setVisible(false);
        selectedNav.setStyle("-fx-background-color: #2c3e50;");

        if (event.getSource() == friendsNav) {
            selectedNav = friendsNav;
            selectedPane = friendsPane;
            loadFriends(LoginController.userprofile.getFriendList(), friendsPane);
        }

        if (event.getSource() == requestsNav) {
            selectedNav = requestsNav;
            selectedPane = requestsPane;
            loadFriends(ServerCommunication.getReceivedRequests(
                    LoginController.getusername()), requestsPane);
        }

        if (event.getSource() == sentNav) {
            selectedNav = sentNav;
            selectedPane = sentPane;
            loadFriends(ServerCommunication.getSentRequests(
                    LoginController.getusername()), sentPane);
        }

        selectedPane.setVisible(true);
        selectedNav.setStyle("-fx-background-color: #27ae60;");
    }

    @FXML
    void initialize() {
        selectedPane = friendsPane;
        selectedNav = friendsNav;
        selectedPane.setVisible(true);
        loadFriends(LoginController.userprofile.getFriendList(), friendsPane);
        LoginController.userprofile.getAchievement().checkfirstfriend();

    }

    void alert(String statment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(statment);
        alert.showAndWait();
    }

    /**
     * gets array and process it by creating UserFriendController.
     */
    public void loadFriends(ArrayList<String> friends, VBox friendsPane) {
        filter(friends);
        friendsPane.getChildren().clear();
        for (int i = 0; i < friends.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/fxml/user.fxml"));
            UserFriendController ufc = new UserFriendController(friends.get(i),
                    this);
            loader.setController(ufc);
            try {
                Parent root = loader.load();
                friendsPane.getChildren().addAll(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void filter(ArrayList<String> friends) {
        for (int i = 0 ; i < friends.size(); i ++) {
            if (friends.get(i).equals(LoginController.getusername())) {
                friends.remove(i);
            }
        }
    }

    public VBox getFriendsPane() {
        return friendsPane;
    }

    public VBox getreqpane() {
        return requestsPane;
    }

    public AnchorPane getfriendcomp() {
        return friendsCompare;
    }


}
