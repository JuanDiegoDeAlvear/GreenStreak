package com.client.controllers;

import com.client.Main;
import com.client.ServerCommunication;
import com.client.logic.Calculations;
import com.client.logic.Profile;
import com.client.logic.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    public static Profile userprofile = new Profile();
    private static User client = new User();
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton signupBtn;

    public static String getusername() {
        return client.getusername();
    }

    /**
     * Creates alert using statment.
     *
     * @param statment sets statment using String
     */
    public static void alert(String statment) {
        Alert alert = new Alert(Alert.AlertType.ERROR, statment, ButtonType.OK);
        alert.setTitle("Authentication Error");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        if (!loginAuthorization(username.getText(), password.getText())) {
            alert("Something is missing");
        } else if (ServerCommunication.checkLogin(client)) {
            userprofile.setName(LoginController.client.getusername());
            userprofile = ServerCommunication.getProfile(username.getText(), username.getText());

            System.out.println(userprofile.getFoodProfile().getAmountOrganicFood());
            double carbon = Calculations.totalCarbon(userprofile.getFoodProfile(),
                    userprofile.getTransportationProfile(), userprofile.getEnergyProfile());
            double eco = Calculations.totalEco(userprofile.getFoodProfile(),
                    userprofile.getTransportationProfile(), userprofile.getEnergyProfile(),
                    userprofile.getLifeStyleProfile());

            userprofile.getScore().setFootprint(carbon);
            userprofile.getScore().setFootprintEco(eco);

            Stage stage = Main.getStage();
            Parent content = FXMLLoader.load(getClass().getResource("/fxml/appFrame.fxml"));
            setStageContent(stage, content);

            if (SystemTray.isSupported()) {
                displayTray();
            } else {
                System.err.println("System tray not supported!");
            }
        } else {
            alert("Wrong username or password ");
        }
    }

    @FXML
    void loginWithEnterKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            login(new ActionEvent());
        }
    }

    @FXML
    void switchToSignupScreen(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node)
                event.getSource()).getScene().getWindow(); //grab the stage
        Parent content = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        setStageContent(stage, content);
    }

    @FXML
    void initialize() {}

    private boolean loginAuthorization(String un, String pass) {
        if (un.equals("") || pass.equals("")) {
            return false;
        } else {
            client.setUsername(un);
            client.setPassword(pass);
            return true;
        }
    }

    private void setStageContent(Stage stage, Parent content) {
        Scene scene = new Scene(content);

        Main.setCurrentScene(scene);

        String css = this.getClass().getResource("/css/global.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
    }


    /**
     * System Tray.
     *
     */
    private void displayTray() {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("leaf.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (userprofile.getAchievement().isLoggedin()) {
            trayIcon.displayMessage("GreenStreak",
                    "welcomeback , " + getusername(), TrayIcon.MessageType.INFO);
        } else {
            userprofile.getAchievement().loggedin();

        }
    }
}
