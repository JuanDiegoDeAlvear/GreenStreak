package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.EnergySurvey;
import com.client.logic.FoodSurvey;
import com.client.logic.LifeStyleSurvey;
import com.client.logic.TransportationSurvey;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

public class SurveyEditorController {

    AnchorPane selectedPane;
    String pane;
    AnchorPane root;
    JFXButton selectedTab;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton foodTab;

    @FXML
    private JFXButton energyTab;

    @FXML
    private JFXButton transportTab;

    @FXML
    private JFXButton lifestyleTab;

    @FXML
    private AnchorPane surveyPane;

    @FXML
    private AnchorPane foodPane;

    @FXML
    private JFXComboBox<String> foodOrganic;

    @FXML
    private JFXComboBox<String> foodDiet;

    @FXML
    private JFXComboBox<String> foodLocal;

    @FXML
    private JFXComboBox<String> foodWaste;

    @FXML
    private JFXComboBox<String> foodPrepackaged;

    @FXML
    private JFXComboBox<String> foodCompost;

    @FXML
    private JFXButton saveFoodBtn;

    @FXML
    private AnchorPane energyPane;

    @FXML
    private JFXComboBox<String> energyHouse;

    @FXML
    private JFXTextField energyRoommate;

    @FXML
    private JFXCheckBox energyGreenBoolean;

    @FXML
    private JFXCheckBox energyEfficientBoolean;

    @FXML
    private JFXComboBox<String> energyUseEstimate;

    @FXML
    private JFXButton saveEnergyBtn;

    @FXML
    private AnchorPane transportPane;

    @FXML
    private JFXTextField transportFlightHours;

    @FXML
    private JFXComboBox<String> transportCar;

    @FXML
    private JFXTextField transportTravel;

    @FXML
    private JFXComboBox<String> transportMainMode;

    @FXML
    private JFXButton saveTransportBtn;

    @FXML
    private AnchorPane lifestylePane;

    @FXML
    private JFXComboBox<String> lifestyleBigPurchase;

    @FXML
    private JFXComboBox<String> lifestyleWeeklyWaste;

    @FXML
    private JFXComboBox<String> lifestyleRecycle;

    @FXML
    private JFXComboBox<String> lifestyleAppliances;

    @FXML
    private JFXTextField lifestyleShowerTime;

    @FXML
    private JFXButton saveLifestyleBtn;

    private FoodSurvey foodSurvey = new FoodSurvey();

    private TransportationSurvey transportSurvey = new TransportationSurvey();

    private EnergySurvey energySurvey = new EnergySurvey();

    private LifeStyleSurvey lifeStyleSurvey = new LifeStyleSurvey();


    public SurveyEditorController(String pane, AnchorPane root) {
        this.pane = pane;
        this.root = root;
    }

    @FXML
    void changeTab(ActionEvent event) {
        FontIcon icon = (FontIcon) selectedTab.getGraphic();
        icon.setIconColor(Color.rgb(255, 255, 255, 1));

        selectedTab.setTextFill(Color.rgb(255, 255, 255));

        if (event.getSource() == foodTab) {
            changepane(foodTab, foodPane);
        }

        if (event.getSource() == energyTab) {
            changepane(energyTab, energyPane);

        }

        if (event.getSource() == transportTab) {
            changepane(transportTab, transportPane);

        }

        if (event.getSource() == lifestyleTab) {
            changepane(lifestyleTab, lifestylePane);

        }

        selectedTab.setTextFill(Color.rgb(39, 174, 96));

        FontIcon ic = (FontIcon) selectedTab.getGraphic();
        ic.setIconColor(Color.rgb(39, 174, 96, 1));
    }

    private void changepane(JFXButton foodTab, AnchorPane foodPane) {
        if (!selectedTab.equals(foodTab)) {
            selectedPane.setVisible(false);
            selectedTab = foodTab;
            foodPane.setVisible(true);
            selectedPane = foodPane;
        }
    }


    @FXML
    void close() {
        this.root.setVisible(false);
    }

    @FXML
    void save(ActionEvent event) {
        if (event.getSource() == saveFoodBtn) {
            savefoodhelp();
        } else if (event.getSource() == saveTransportBtn) {
            savetransporthelp();
        } else if (event.getSource() == saveEnergyBtn) {
            saveenergyhelp();
        } else if (event.getSource() == saveLifestyleBtn) {
            savelifestylehelp();
        }

    }

    private void savefoodhelp() {
        foodSurveyCreator();
        if (!LoginController.userprofile.getFoodProfile().equals(foodSurvey)) {
            saveOptions(foodSurvey);
        }
        close();
    }

    private void savetransporthelp() {
        transportSurveyCreator();
        if (!LoginController.userprofile.getTransportationProfile().equals(transportSurvey)) {
            saveOptions(transportSurvey);
        }
        LoginController.userprofile.getAchievement().carblsixach();
        close();
    }

    private void savelifestylehelp() {
        lifeStyleSurveyCreator();
        if (!LoginController.userprofile.getLifeStyleProfile().equals(lifeStyleSurvey)) {
            saveOptions(lifeStyleSurvey);
        }
        LoginController.userprofile.getAchievement().carblsixach();
        close();
    }

    private void saveenergyhelp() {
        energySurveyCreator();
        if (!LoginController.userprofile.getEnergyProfile().equals(energySurvey)) {
            saveOptions(energySurvey);
        }
        LoginController.userprofile.getAchievement().carblsixach();
        close();
    }

    private void saveOptions(FoodSurvey foodSurvey) {
        if (alert()) {
            if (ServerCommunication.sendFoodSurvey(foodSurvey,
                    LoginController.getusername())) {
                LoginController.userprofile.setFoodProfile(foodSurvey);
                //close window
            } else {
                alert("Connection Error");
            }
        }
    }

    private void saveOptions(TransportationSurvey transportSurvey) {
        if (alert()) {
            if (ServerCommunication.sendTransportSurvey(transportSurvey,
                    LoginController.getusername())) {
                LoginController.userprofile.setTransportationProfile(transportSurvey);
                //close window
            } else {
                alert("Connection Error");
            }
        }
    }

    private void saveOptions(EnergySurvey energySurvey) {
        if (alert()) {
            if (ServerCommunication.sendEnergySurvey(energySurvey,
                    LoginController.getusername())) {
                LoginController.userprofile.setEnergyProfile(energySurvey);
                //close window
            } else {
                alert("Connection Error");
            }
        }
    }

    private void saveOptions(LifeStyleSurvey lifeStyle) {
        if (alert()) {
            if (ServerCommunication.sendLifeStyleSurvey(lifeStyle,
                    LoginController.getusername())) {
                LoginController.userprofile.setLifeStyleProfile(lifeStyle);
                //close window
            } else {
                alert("Connection Error");
            }
        }
    }

    @FXML
    void initialize() {
        if (this.pane.equals("food")) {
            this.selectedPane = foodPane;
            this.selectedTab = foodTab;

        } else if (this.pane.equals("energy")) {
            this.selectedPane = energyPane;
            this.selectedTab = energyTab;

        } else if (this.pane.equals("transport")) {
            this.selectedPane = transportPane;
            this.selectedTab = transportTab;

        } else if (this.pane.equals("lifestyle")) {
            this.selectedPane = lifestylePane;
            this.selectedTab = lifestyleTab;

        } else {
            this.selectedPane = foodPane;
            this.selectedTab = foodTab;
        }

        FontIcon ic = (FontIcon) selectedTab.getGraphic();
        ic.setIconColor(Color.rgb(39, 174, 96, 1));
        selectedTab.setTextFill(Color.rgb(39, 174, 96));
        selectedPane.setVisible(true);


        //Here we also need make what the user chose at signup the default selection

        foodOrganic.getItems().add("All");
        foodOrganic.getItems().add("Most");
        foodOrganic.getItems().add("Some");
        foodOrganic.getItems().add("None");
        foodOrganic.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountOrganicFood());


        foodDiet.getItems().add("Vegan");
        foodDiet.getItems().add("Vegetarian");
        foodDiet.getItems().add("Low meat and dairy");
        foodDiet.getItems().add("Average meat and dairy");
        foodDiet.getItems().add("Heavy meat and dairy");
        foodDiet.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getDietChoice());

        foodLocal.getItems().add("Almost all");
        foodLocal.getItems().add("Above average");
        foodLocal.getItems().add("Average");
        foodLocal.getItems().add("Very little");
        foodLocal.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountOrganicFood());

        foodWaste.getItems().add("Above average");
        foodWaste.getItems().add("Average");
        foodWaste.getItems().add("Below average");
        foodWaste.getItems().add("Very little");
        foodWaste.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountWastedFood());

        foodPrepackaged.getItems().add("Above average");
        foodPrepackaged.getItems().add("Average");
        foodPrepackaged.getItems().add("Below average");
        foodPrepackaged.getItems().add("Very little");
        foodPrepackaged.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountFoodPackaged());

        foodCompost.getItems().add("None");
        foodCompost.getItems().add("About a quarter");
        foodCompost.getItems().add("Half");
        foodCompost.getItems().add("All food waste");
        foodCompost.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountFoodCompost());

        foodLocal.getSelectionModel().select(LoginController.userprofile
                .getFoodProfile().getAmountLocalFood());


        transportCar.getItems().add("Small car");
        transportCar.getItems().add("Medium car");
        transportCar.getItems().add("Large car");
        transportCar.getItems().add("Electric");
        transportCar.getItems().add("No car");
        transportCar.getSelectionModel().select(LoginController.userprofile
                .getTransportationProfile().getTypeOfCar());

        transportMainMode.getItems().add("Public transport");
        transportMainMode.getItems().add("Car");
        transportMainMode.getItems().add("Walking/Biking");
        transportMainMode.getItems().add("Motorcycle/Moped");
        transportMainMode.getSelectionModel().select(LoginController.userprofile
                .getTransportationProfile().getMainModeTransport());

        energyHouse.getItems().add("Apartment");
        energyHouse.getItems().add("Small house");
        energyHouse.getItems().add("Medium house");
        energyHouse.getItems().add("Large house");
        energyHouse.getSelectionModel().select(LoginController.userprofile
                .getEnergyProfile().getTypeHouse());

        energyUseEstimate.getItems().add("Below average");
        energyUseEstimate.getItems().add("Average");
        energyUseEstimate.getItems().add("Above average");
        energyUseEstimate.getSelectionModel().select(LoginController.userprofile
                .getEnergyProfile().getAmountEnergy());

        energyGreenBoolean.selectedProperty().setValue(LoginController.userprofile
                .getEnergyProfile().getGreenEnergy());

        energyEfficientBoolean.selectedProperty().setValue(LoginController.userprofile
                .getEnergyProfile().getEfficientLighting());

        lifestyleBigPurchase.getItems().add("More than 5");
        lifestyleBigPurchase.getItems().add("Around 2 - 4");
        lifestyleBigPurchase.getItems().add("Maybe once");
        lifestyleBigPurchase.getItems().add("Very rarely");
        lifestyleBigPurchase.getSelectionModel().select(LoginController.userprofile
                .getLifeStyleProfile().getHowOftenBigPurchase());

        lifestyleWeeklyWaste.getItems().add("Less than 15kg");
        lifestyleWeeklyWaste.getItems().add("15kg - 30kg");
        lifestyleWeeklyWaste.getItems().add("30kg - 45kg");
        lifestyleWeeklyWaste.getItems().add("45kg - 60kg");
        lifestyleWeeklyWaste.getItems().add("More than 60kg");
        lifestyleWeeklyWaste.getSelectionModel().select(LoginController.userprofile
                .getLifeStyleProfile().getWeeklyWaste());

        lifestyleRecycle.getItems().add("Glass");
        lifestyleRecycle.getItems().add("Paper");
        lifestyleRecycle.getItems().add("Plastic");
        lifestyleRecycle.getItems().add("Metal");
        lifestyleRecycle.getSelectionModel().select(LoginController.userprofile
                .getLifeStyleProfile().getRecycle());

        lifestyleAppliances.getItems().add("10x a week");
        lifestyleAppliances.getItems().add("5 - 10x a week");
        lifestyleAppliances.getItems().add("1 - 5x a week");
        lifestyleAppliances.getItems().add("Not applicable");
        lifestyleAppliances.getSelectionModel().select(LoginController.userprofile
                .getLifeStyleProfile().getApplianceUse());
        lifestyleShowerTime.setText(LoginController.userprofile
                .getLifeStyleProfile().getAveShowerTime() + "");
        transportFlightHours.setText(LoginController.userprofile
                .getTransportationProfile().getAveHoursFly() + "");
        transportTravel.setText(LoginController.userprofile
                .getTransportationProfile().getAveKmPerDay() + "");
        energyRoommate.setText(LoginController.userprofile
                .getEnergyProfile().getAmountRoommates() + "");
    }

    private void foodSurveyCreator() {
        foodSurvey.setAmountOrganicFood(foodOrganic.getValue());
        foodSurvey.setDietChoice(foodDiet.getValue());
        foodSurvey.setAmountLocalFood(foodLocal.getValue());
        foodSurvey.setAmountWastedFood(foodWaste.getValue());
        foodSurvey.setAmountFoodPackaged(foodPrepackaged.getValue());
        foodSurvey.setAmountFoodCompost(foodCompost.getValue());
    }

    private void transportSurveyCreator() {

        transportSurvey.setAveHoursFly(Integer.parseInt(transportFlightHours.getText()));
        transportSurvey.setTypeOfCar(transportCar.getValue());
        transportSurvey.setAveKmPerDay(Integer.parseInt(transportTravel.getText()));
        transportSurvey.setMainModeTransport(transportMainMode.getValue());

    }

    private void energySurveyCreator() {

        energySurvey.setTypeHouse(energyHouse.getValue());
        energySurvey.setAmountRoommates(Integer.parseInt(energyRoommate.getText()));
        energySurvey.setAmountEnergy(energyUseEstimate.getValue());
        energySurvey.setGreenEnergy(energyGreenBoolean.isSelected());
        energySurvey.setEfficientLighting(energyEfficientBoolean.isSelected());

    }

    private void lifeStyleSurveyCreator() {
        lifeStyleSurvey.setApplianceUse(lifestyleAppliances.getValue());
        lifeStyleSurvey.setAveShowerTime(Integer.parseInt(lifestyleShowerTime.getText()));
        lifeStyleSurvey.setHowOftenBigPurchase(lifestyleBigPurchase.getValue());
        lifeStyleSurvey.setWeeklyWaste(lifestyleWeeklyWaste.getValue());
        lifeStyleSurvey.setRecycle(lifestyleRecycle.getValue());

    }

    boolean alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Apply changes ? ");
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    private void alert(String statment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(statment);
        alert.showAndWait();
    }


}

