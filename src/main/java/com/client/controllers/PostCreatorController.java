package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.TransportationPost;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PostCreatorController {

    private JFXButton selectedNav;
    private JFXButton selectedTab;
    private AnchorPane selectedCategory;
    private AnchorPane selectedPane;
    private AnchorPane parent;
    @FXML
    private AnchorPane newPostDialog;
    @FXML
    private JFXButton foodNav;
    @FXML
    private JFXButton tranportationNav;
    @FXML
    private JFXButton consumptionNav;
    @FXML
    private AnchorPane foodPane;
    @FXML
    private JFXButton breakfastTab;
    @FXML
    private JFXButton lunchTab;
    @FXML
    private JFXButton dinnerTab;
    @FXML
    private AnchorPane breakfastPane;
    @FXML
    private JFXToggleButton breakfastVegan;
    @FXML
    private ToggleGroup breakfastFood;
    @FXML
    private JFXToggleButton breakfastVegetarian;
    @FXML
    private JFXToggleButton breakfastMeat;
    @FXML
    private JFXButton breakfastPostBtn;
    @FXML
    private AnchorPane lunchPane;
    @FXML
    private JFXToggleButton lunchVegan;
    @FXML
    private ToggleGroup lunchFood;
    @FXML
    private JFXToggleButton lunchVegetarian;
    @FXML
    private JFXToggleButton lunchMeat;
    @FXML
    private JFXButton lunchPostBtn;
    @FXML
    private AnchorPane dinnerPane;
    @FXML
    private JFXToggleButton dinnerVegan;
    @FXML
    private ToggleGroup dinnerFood;
    @FXML
    private JFXToggleButton dinnerVegetarian;
    @FXML
    private JFXToggleButton dinnerMeat;
    @FXML
    private JFXButton dinnerPostBtn;
    @FXML
    private AnchorPane transportationPane;
    @FXML
    private JFXButton bikeTab;
    @FXML
    private JFXButton carTab;
    @FXML
    private JFXButton transportTab;
    @FXML
    private JFXButton planeTab;
    @FXML
    private AnchorPane bikePane;
    @FXML
    private JFXTextField bikeKm;
    @FXML
    private JFXButton bikePostBtn;
    @FXML
    private AnchorPane carPane;
    @FXML
    private JFXTextField carKm;
    @FXML
    private JFXButton carPostBtn;
    @FXML
    private AnchorPane transportPane;
    @FXML
    private JFXTextField publicTransportKm;
    @FXML
    private JFXButton publicTransportPostBtn;
    @FXML
    private AnchorPane planePane;
    @FXML
    private JFXTextField planeKm;
    @FXML
    private JFXButton planePostBtn;
    @FXML
    private AnchorPane consumptionPane;
    @FXML
    private JFXButton recycleTab;
    @FXML
    private JFXButton produceTab;
    @FXML
    private JFXButton usedTab;
    @FXML
    private AnchorPane recyclePane;
    @FXML
    private JFXToggleButton recycleBool;
    @FXML
    private JFXButton recyclePostBtn;
    @FXML
    private AnchorPane producePane;
    @FXML
    private JFXToggleButton localProduceBool;
    @FXML
    private JFXButton producePostBtn;
    @FXML
    private AnchorPane usedPane;
    @FXML
    private JFXToggleButton usedBool;
    @FXML
    private JFXButton usedPostBtn;
    @FXML
    private JFXButton cancelBtn;

    public PostCreatorController(AnchorPane parent) {
        this.parent = parent;
    }

    @FXML
    void cancelPost(ActionEvent event) {
        parent.getChildren().clear();
        parent.setVisible(false);
    }

    @FXML
    void categoryNavigation(ActionEvent event) {

        selectedTab.setTextFill(Color.rgb(255, 255, 255));
        selectedCategory.setVisible(false);
        selectedPane.setVisible(false);
        if (event.getSource() == foodNav) {
            if (selectedNav != foodNav) {
                selectedNav = foodNav;
                selectedCategory = foodPane;
                selectedPane = breakfastPane;
                selectedTab = breakfastTab;
            }
        }
        if (event.getSource() == tranportationNav) {
            if (selectedNav != tranportationNav) {
                selectedNav = tranportationNav;
                selectedCategory = transportationPane;
                selectedPane = bikePane;
                selectedTab = bikeTab;
            }
        }
        if (event.getSource() == consumptionNav) {
            if (selectedNav != consumptionNav) {
                selectedNav = consumptionNav;
                selectedCategory = consumptionPane;
                selectedPane = recyclePane;
                selectedTab = recycleTab;
            }
        }
        selectedPane.setVisible(true);
        selectedCategory.setVisible(true);
        selectedTab.setTextFill(Color.rgb(39, 174, 96));

    }

    @FXML
    void changeTab(ActionEvent event) {
        selectedTab.setTextFill(Color.rgb(255, 255, 255));
        selectedPane.setVisible(false);
        whichFoodPane(event);
        whichTransportPane(event);
        whichConsumePane(event);
        selectedTab.setTextFill(Color.rgb(39, 174, 96));
        selectedPane.setVisible(true);

    }

    private void whichConsumePane(ActionEvent event) {

        if (event.getSource() == produceTab) {
            selectedTabOptions(produceTab, producePane);
        }

        if (event.getSource() == usedTab) {
            selectedTabOptions(usedTab, usedPane);
        }

        if (event.getSource() == recycleTab) {
            selectedTabOptions(recycleTab, recyclePane);
        }
    }


    private void whichTransportPane(ActionEvent event) {
        if (event.getSource() == carTab) {
            selectedTabOptions(carTab, carPane);
        }
        if (event.getSource() == transportTab) {
            selectedTabOptions(transportTab, transportPane);
        }
        if (event.getSource() == planeTab) {
            selectedTabOptions(planeTab, planePane);
        }
        if (event.getSource() == bikeTab) {
            selectedTabOptions(bikeTab, bikePane);
        }
    }

    private void whichFoodPane(ActionEvent event) {
        if (event.getSource() == breakfastTab) {
            selectedTabOptions(breakfastTab, breakfastPane);
        }
        if (event.getSource() == lunchTab) {
            selectedTabOptions(lunchTab, lunchPane);
        }
        if (event.getSource() == dinnerTab) {
            selectedTabOptions(dinnerTab, dinnerPane);
        }
    }

    private void selectedTabOptions(JFXButton breakfastTab, AnchorPane breakfastPane) {
        if (selectedTab != breakfastTab) {
            selectedTab = breakfastTab;
            selectedPane = breakfastPane;
        }
    }

    @FXML
    void newConsumptionPost(ActionEvent event) {
        if (event.getSource() == recyclePostBtn) {
            ConsumptionPost consumptionPost = new ConsumptionPost();
            consumptionPost.setConsume("Recycle");
            consumptionPost.setState(recycleBool.isSelected());
            consumptionPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadConsume(consumptionPost);
            LoginController.userprofile.getAchievement().checkfiveDaysr();
            RankingsController.scoreCalc();
            LoginController.userprofile.getAchievement().checkfirstPost();
            //clear input
            recycleBool.selectedProperty().setValue(false);
        } else if (event.getSource() == producePostBtn) {
            ConsumptionPost consumptionPost = new ConsumptionPost();
            consumptionPost.setConsume("Local Produce");
            consumptionPost.setState(localProduceBool.isSelected());
            consumptionPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadConsume(consumptionPost);
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear input
            localProduceBool.selectedProperty().setValue(false);
        } else if (event.getSource() == usedPostBtn) {
            ConsumptionPost consumptionPost = new ConsumptionPost();
            consumptionPost.setConsume("used");
            consumptionPost.setState(usedBool.isSelected());
            consumptionPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadConsume(consumptionPost);
            LoginController.userprofile.getAchievement().checkLocal();
            RankingsController.scoreCalc();
            LoginController.userprofile.getAchievement().checkfirstPost();
            //clear input
            usedBool.selectedProperty().setValue(false);
        }
    }

    @FXML
    void newFoodPost(ActionEvent event) {
        if (event.getSource() == breakfastPostBtn) {
            Toggle selection = breakfastFood.getSelectedToggle();
            FoodPost foodpost = new FoodPost();
            foodpost.setType((String) selection.getUserData());
            foodpost.setMeal("breakfast");
            foodpost.setUsername(LoginController.getusername());
            ServerCommunication.uploadfood(foodpost);
            LoginController.userprofile.getAchievement().checkfiveDaysv();
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear input
            selection.setSelected(false);
        } else if (event.getSource() == lunchPostBtn) {
            Toggle selection = lunchFood.getSelectedToggle();
            FoodPost foodpost = new FoodPost();
            foodpost.setType((String) selection.getUserData());
            foodpost.setMeal("lunch");
            foodpost.setUsername(LoginController.getusername());
            ServerCommunication.uploadfood(foodpost);
            LoginController.userprofile.getAchievement().checkfiveDaysv();
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear input
            selection.setSelected(false);
        } else if (event.getSource() == dinnerPostBtn) {
            Toggle selection = dinnerFood.getSelectedToggle();
            FoodPost foodpost = new FoodPost();
            foodpost.setType((String) selection.getUserData());
            foodpost.setMeal("dinner");
            foodpost.setUsername(LoginController.getusername());
            ServerCommunication.uploadfood(foodpost);
            LoginController.userprofile.getAchievement().checkfiveDaysv();
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear input
            selection.setSelected(false);
        }
    }

    @FXML
    void newTransportationPost(ActionEvent event) {
        if (event.getSource() == bikePostBtn) {
            TransportationPost transportationPost = new TransportationPost();
            transportationPost.setType("bike");
            transportationPost.setKilometer(Double.parseDouble(bikeKm.getText()));
            transportationPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadTrans(transportationPost);
            LoginController.userprofile.getAchievement().checkhundKm();
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();

            //clear form
            bikeKm.setText("");

        } else if (event.getSource() == carPostBtn) {
            TransportationPost transportationPost = new TransportationPost();
            transportationPost.setType("car");
            transportationPost.setKilometer(Double.parseDouble(carKm.getText()));
            transportationPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadTrans(transportationPost);
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear form
            carKm.setText("");

        } else if (event.getSource() == publicTransportPostBtn) {
            TransportationPost transportationPost = new TransportationPost();
            transportationPost.setType("publictransportation");
            transportationPost.setKilometer(Double.parseDouble(publicTransportKm.getText()));
            transportationPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadTrans(transportationPost);
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
            //clear form
            publicTransportKm.setText("");
        } else if (event.getSource() == planePostBtn) {
            TransportationPost transportationPost = new TransportationPost();
            transportationPost.setType("plane");
            transportationPost.setKilometer(Double.parseDouble(planeKm.getText()));
            transportationPost.setUsername(LoginController.getusername());
            ServerCommunication.uploadTrans(transportationPost);
            LoginController.userprofile.getAchievement().checkfirstPost();
            RankingsController.scoreCalc();
        }
    }

    @FXML
    void validate(KeyEvent event) {

    }

    @FXML
    void initialize() {
        this.selectedNav = foodNav;
        this.selectedTab = breakfastTab;
        this.selectedCategory = foodPane;
        this.selectedPane = breakfastPane;
        selectedCategory.setVisible(true);
        selectedPane.setVisible(true);

        selectedTab.setTextFill(Color.rgb(39, 174, 96));
        breakfastVegan.setUserData("Vegan");
        breakfastVegetarian.setUserData("Vegetarian");
        breakfastMeat.setUserData("Meat");
        lunchVegan.setUserData("Vegan");
        lunchVegetarian.setUserData("Vegetarian");
        lunchMeat.setUserData("Meat");
        dinnerVegan.setUserData("Vegan");
        dinnerVegetarian.setUserData("Vegetarian");
        dinnerMeat.setUserData("Meat");
    }

}
