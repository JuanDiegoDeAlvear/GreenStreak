package com.client.controllers;

import com.client.Main;
import com.client.ServerCommunication;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.SignUpUser;
import com.client.logic.TransportationSurvey;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class SignupController {
    @FXML
    private Pane userInfoPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private Label msgLabel;

    @FXML
    private Pane foodPane;

    @FXML
    private JFXComboBox<String> amountOrganicFood;

    @FXML
    private JFXComboBox<String> dietChoice;

    @FXML
    private JFXComboBox<String> amountLocalFood;

    @FXML
    private JFXComboBox<String> amountWastedFood;

    @FXML
    private JFXComboBox<String> amountFoodPackaged;

    @FXML
    private JFXComboBox<String> amountFoodCompost;

    @FXML
    private JFXButton nextBtnFood;

    @FXML
    private Label msgLabelFood;

    @FXML
    private Pane transportationPane;

    @FXML
    private JFXTextField aveHoursFly;

    @FXML
    private JFXComboBox<String> typeOfCar;

    @FXML
    private JFXTextField aveKmPerDay;

    @FXML
    private JFXComboBox<String> mainModeTransport;

    @FXML
    private JFXButton nextBtnTransport;

    @FXML
    private Label msgLabelTransportation;

    @FXML
    private Pane energyPane;

    @FXML
    private JFXComboBox<String> typeHouse;

    @FXML
    private JFXTextField amountRoomates;

    @FXML
    private JFXCheckBox greenEnergy;

    @FXML
    private JFXCheckBox efficientLighting;

    @FXML
    private JFXComboBox<String> amountEnergy;

    @FXML
    private JFXButton nextBtnEnergy;

    @FXML
    private Label msgLabelEnergy;

    @FXML
    private Pane lifestylePane;

    @FXML
    private JFXComboBox<String> howOftenBigPurchase;

    @FXML
    private JFXComboBox<String> weeklyWaste;

    @FXML
    private JFXComboBox<String> recycle;

    @FXML
    private JFXComboBox<String> applianceUse;

    @FXML
    private JFXTextField aveShowerTime;

    @FXML
    private JFXButton createAccountBtn;

    @FXML
    private Label msgLabelLife;

    private SignUpUser signupuser = new SignUpUser();

    private Queue<Pane> pages;
    private Pane currentPane;

    /**
     * pops us an alert for Authentication error.
     *
     * @param statment sets statment as String
     */
    public static void alert(String statment) {
        Alert alert = new Alert(Alert.AlertType.ERROR, statment, ButtonType.OK);
        alert.setTitle("Authentication Error");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    //if passwords match returns true
    @FXML
    boolean checkPasswordMatch() {
        if (password.getText().equals("")) {
            LoginController.alert("Add Password");
            return false;
        }
        if (!password.getText().equals(confirmPassword.getText())) {
            msgLabel.setText("Passwords don't match!");
            return false;
        }
        if (password.getLength() < 8) {
            msgLabel.setText("Password should be more than 8 characters");
            return false;
        }
        msgLabel.setText("");
        return true;
    }

    //Send to server to check
    @FXML
    boolean checkValidEmail() {
        try {
            InternetAddress emailAddr = new InternetAddress(email.getText());
            emailAddr.validate();
            msgLabel.setText("");
        } catch (AddressException ex) {
            System.out.println("Wrong Email ");
            msgLabel.setText("Please enter a valid email");
            return false;
        }
        if (ServerCommunication.emailExists(email.getText())) {
            msgLabel.setText("email exists");
            return false;
        }
        return true;
    }

    //send to server to check
    @FXML
    boolean checkValidUsername() {

        String userName = username.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");

        if (userName == null) {
            msgLabel.setText("Username cannot be empty");
            return false;
        }
        if (!pattern.matcher(userName).matches()) {
            msgLabel.setText("Please enter a valid username");
            return false;
        }
        if (userName.length() < 5) {
            msgLabel.setText("Username should be more than 5 characters");
            return false;

        }
        if (ServerCommunication.usernameExists(userName)) {
            msgLabel.setText(" Username is taken try another one  ");
            return false;
        }
        msgLabel.setText("");
        return true;
    }

    @FXML
    void nextPage(ActionEvent event) {

        if (currentPane.equals(userInfoPane) && checkUserPane()) {
            getNextPaneHelp();
            signupuser.setUsername(username.getText());
            signupuser.setEmail(email.getText());
            signupuser.setPassword(password.getText());

        } else if (currentPane.equals(foodPane) && checkFoodPane()) {
            signupuser.setFoodProfile(foodSurveyCreator());
            getNextPaneHelp();

        } else if (currentPane.equals(transportationPane) && checkTransportationPane()) {
            signupuser.setTransportationProfile(transportSurveyCreator());
            getNextPaneHelp();
        } else if (currentPane.equals(energyPane) && checkEnergyPane()) {
            signupuser.setEnergyProfile(energySurveyCreator());
            getNextPaneHelp();
        } else if (currentPane.equals(lifestylePane)) {
            getNextPaneHelp();

        } else {
            LoginController.alert("Something is missing");
        }


    }

    private void getNextPaneHelp() {
        currentPane.setVisible(false);
        currentPane = pages.poll();
        if (currentPane != null) {

            currentPane.setVisible(true);
        }
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        if (checkLifestylePane()) {
            signupuser.setLifeStyleProfile(lifeStyleSurveyCreator());
            System.out.println(signupuser.getFoodProfile().getDietChoice());
            System.out.println(signupuser.getFoodProfile().getAmountLocalFood());
            System.out.println(signupuser.getLifeStyleProfile().getAveShowerTime());

            if (ServerCommunication.sendsignupuser(signupuser)) {
                switchToLoginScreen(event);
            } else {
                LoginController.alert("Connection error");
            }
        } else {
            LoginController.alert("AGAIN missed something");
        }


    }

    @FXML
    void switchToLoginScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //grab the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Main.setCurrentScene(scene);
    }

    @FXML
    void initialize() {
        pages = new LinkedList<>();
        //created a queue to hold the various different pages of the survey
        pages.add(foodPane);
        pages.add(transportationPane);
        pages.add(energyPane);
        pages.add(lifestylePane);

        currentPane = userInfoPane; //set the current pane to the default

        msgLabel.setText("");
        msgLabelEnergy.setText("");
        msgLabelFood.setText("");
        msgLabelLife.setText("");
        msgLabelTransportation.setText("");

        amountOrganicFood.getItems().add("All");
        amountOrganicFood.getItems().add("Most");
        amountOrganicFood.getItems().add("Some");
        amountOrganicFood.getItems().add("None");

        dietChoice.getItems().add("Vegan");
        dietChoice.getItems().add("Vegetarian");
        dietChoice.getItems().add("Low meat and dairy");
        dietChoice.getItems().add("Average meat and dairy");
        dietChoice.getItems().add("Heavy meat and dairy");

        amountLocalFood.getItems().add("Almost all");
        amountLocalFood.getItems().add("Above average");
        amountLocalFood.getItems().add("Average");
        amountLocalFood.getItems().add("Very little");

        amountWastedFood.getItems().add("Above average");
        amountWastedFood.getItems().add("Average");
        amountWastedFood.getItems().add("Below average");
        amountWastedFood.getItems().add("Very little");

        amountFoodPackaged.getItems().add("Above average");
        amountFoodPackaged.getItems().add("Average");
        amountFoodPackaged.getItems().add("Below average");
        amountFoodPackaged.getItems().add("Very little");

        amountFoodCompost.getItems().add("None");
        amountFoodCompost.getItems().add("About a quarter");
        amountFoodCompost.getItems().add("Half");
        amountFoodCompost.getItems().add("All food waste");

        typeOfCar.getItems().add("Small car");
        typeOfCar.getItems().add("Medium car");
        typeOfCar.getItems().add("Large car");
        typeOfCar.getItems().add("Electric");
        typeOfCar.getItems().add("No car");

        mainModeTransport.getItems().add("Public transport");
        mainModeTransport.getItems().add("Car");
        mainModeTransport.getItems().add("Walking/Biking");
        mainModeTransport.getItems().add("Motorcycle/Moped");

        typeHouse.getItems().add("Apartment");
        typeHouse.getItems().add("Small house");
        typeHouse.getItems().add("Medium house");
        typeHouse.getItems().add("Large house");

        amountEnergy.getItems().add("Below average");
        amountEnergy.getItems().add("Average");
        amountEnergy.getItems().add("Above average");

        howOftenBigPurchase.getItems().add("More than 5");
        howOftenBigPurchase.getItems().add("Around 2 - 4");
        howOftenBigPurchase.getItems().add("Maybe once");
        howOftenBigPurchase.getItems().add("Very rarely");

        weeklyWaste.getItems().add("Less than 15kg");
        weeklyWaste.getItems().add("15kg - 30kg");
        weeklyWaste.getItems().add("30kg - 45kg");
        weeklyWaste.getItems().add("45kg - 60kg");
        weeklyWaste.getItems().add("More than 60kg");

        recycle.getItems().add("Glass");
        recycle.getItems().add("Paper");
        recycle.getItems().add("Plastic");
        recycle.getItems().add("Metal");

        applianceUse.getItems().add("10x a week");
        applianceUse.getItems().add("5 - 10x a week");
        applianceUse.getItems().add("1 - 5x a week");
        applianceUse.getItems().add("Not applicable");
    }

    /**
     * Stuff.
     *
     * @return stuff
     */
    private boolean checkUserPane() {
        return checkValidUsername() && checkPasswordMatch() && checkValidEmail();
    }

    private boolean checkFoodPane() {
        return amountOrganicFood.getValue() != null && dietChoice.getValue() != null
                && amountLocalFood.getValue() != null
                && amountWastedFood.getValue() != null
                && amountFoodPackaged.getValue() != null
                && amountFoodCompost.getValue() != null;
    }

    private boolean checkTransportationPane() {
        return !aveHoursFly.getText().equals("") && typeOfCar.getValue() != null
                && mainModeTransport.getValue() != null
                && !aveKmPerDay.getText().equals("");

    }

    private boolean checkLifestylePane() {
        return applianceUse.getValue() != null && howOftenBigPurchase.getValue() != null
                && recycle.getValue() != null
                && !aveShowerTime.getText().equals("");

    }

    private boolean checkEnergyPane() {

        return !amountRoomates.getText().equals("") && typeHouse.getValue() != null
                && amountEnergy.getValue() != null;

    }

    /**
     * Create FoodSurvey  from user input.
     *
     * @return FoodSurvey
     */
    public FoodSurvey foodSurveyCreator() {
        FoodSurvey fsurvey = new FoodSurvey();
        fsurvey.setAmountOrganicFood(amountOrganicFood.getValue());
        fsurvey.setDietChoice(dietChoice.getValue());
        fsurvey.setAmountLocalFood(amountLocalFood.getValue());
        fsurvey.setAmountWastedFood(amountWastedFood.getValue());
        fsurvey.setAmountFoodPackaged(amountFoodPackaged.getValue());
        fsurvey.setAmountFoodCompost(amountFoodCompost.getValue());
        return fsurvey;

    }

    /**
     * Create LifeSurvey  from user input.
     *
     * @return LifeSurvey
     */
    public LifeStyleSurvey lifeStyleSurveyCreator() {
        LifeStyleSurvey lifesurvey = new LifeStyleSurvey();
        lifesurvey.setApplianceUse(applianceUse.getValue());
        lifesurvey.setAveShowerTime(Integer.parseInt(aveShowerTime.getText()));
        lifesurvey.setHowOftenBigPurchase(howOftenBigPurchase.getValue());
        lifesurvey.setWeeklyWaste(weeklyWaste.getValue());
        lifesurvey.setRecycle(recycle.getValue());
        return lifesurvey;

    }

    /**
     * Create energy survey from user input.
     *
     * @return Energy Survey
     */
    public EnergySurvey energySurveyCreator() {
        EnergySurvey esurvey = new EnergySurvey();
        esurvey.setTypeHouse(typeHouse.getValue());
        esurvey.setAmountRoommates(Integer.parseInt(amountRoomates.getText()));
        esurvey.setAmountEnergy(amountEnergy.getValue());
        esurvey.setGreenEnergy(greenEnergy.isSelected());
        esurvey.setEfficientLighting(efficientLighting.isSelected());
        return esurvey;
    }

    /**
     * Create transportation survey from user input.
     *
     * @return Transportation Survey
     */
    public TransportationSurvey transportSurveyCreator() {
        TransportationSurvey tsurvey = new TransportationSurvey();
        tsurvey.setAveHoursFly(Integer.parseInt(aveHoursFly.getText()));
        tsurvey.setTypeOfCar(typeOfCar.getValue());
        tsurvey.setAveKmPerDay(Integer.parseInt(aveKmPerDay.getText()));
        tsurvey.setMainModeTransport(mainModeTransport.getValue());
        return tsurvey;
    }

}


