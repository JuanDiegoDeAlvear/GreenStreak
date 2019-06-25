package com.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene currentScene;

    private static Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        Main.currentScene = currentScene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.getOnCloseRequest();
        Parent root = FXMLLoader.load(getClass().getResource(
                "/fxml/login.fxml")); //retrieve the login view. root is the first node.
        stage.setTitle("Green Streak");
        currentScene = new Scene(root); //create new scene using the root node as view.
        stage.setScene(currentScene); //set the stage with the new scene
        Font.loadFont(getClass().getResourceAsStream("/fonts/Raleway/Raleway-Regular.ttf"), 12);
        stage.getIcons().add(new Image(getClass().getResource(
                "/images/leaf.png").toExternalForm()));

        stage.show();
        stage.setResizable(false);
        setStage(stage);


    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }
}
