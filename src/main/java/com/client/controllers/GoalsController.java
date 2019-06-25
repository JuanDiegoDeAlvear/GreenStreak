package com.client.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GoalsController {

    private static Scene currentScene;
    private static Stage stage;
    @FXML
    private JFXButton buttonX;
    @FXML
    private JFXButton buttonY;
    @FXML
    private JFXButton buttonZ;
    @FXML
    private JFXButton buttonQ;
    @FXML
    private JFXButton buttonW;
    @FXML
    private Label randomLabel;
    @FXML
    private TextField textInput;
    @FXML
    private TextArea textArea;

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        GoalsController.currentScene = currentScene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GoalsController.stage = stage;
    }


    //Logout button
    @FXML
    void methodQ(ActionEvent event) {

    }

    @FXML
    void methodW(ActionEvent event) {

    }

    @FXML
    void methodX(ActionEvent event) {

    }

    @FXML
    void methodY(ActionEvent event) {


    }

    @FXML
    void methodZ(ActionEvent event) {

    }


}
