package com.client.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SinglePostController {

    String posttype;
    String postval;
    Date postdate;
    String postclass;
    @FXML
    private FontIcon postIcon;

    @FXML
    private Label postType;

    @FXML
    private Label postValue;

    @FXML
    private Label postTime;

    @FXML
    private JFXButton deletePostBtn;

    /**
     * Create single post.
     *
     * @param posttype  sets posttype using string
     * @param postvalue sets postvalue using string
     * @param postclass sets postclass using string
     * @param posttime  sets posttime using string
     */
    public SinglePostController(String posttype, String postvalue,
                                String postclass, Date posttime) {
        this.posttype = posttype;
        this.postval = postvalue;
        this.postdate = posttime;
        this.postclass = postclass;
    }

    @FXML
    void initialize() {
        postType.setText(posttype);
        postValue.setText(postval);
        postTime.setText(timeFormat(postdate));

        if (postclass.equals("food")) {
            postIcon.setIconLiteral("fa-cutlery");
            foodchecker();
        }

        if (postclass.equals("transport")) {
            transportchecker();
        }

        if (postclass.equals("consumption")) {
            consumptionchecker();
        }
    }

    private void foodchecker() {
        if (posttype.equals("breakfast")) {
            postType.setText("Breakfast");
        } else if (posttype.equals("lunch")) {
            postType.setText("Lunch");
        } else {
            postType.setText("Dinner");
        }
        if (postval.equals("Vegetarian")) {
            postIcon.setIconColor(Color.rgb(52, 152, 219));
        }

        if (postval.equals("Meat")) {
            postIcon.setIconColor(Color.rgb(230, 126, 34));
        }
    }

    private void consumptionchecker() {
        if (posttype.equals("Recycle")) {
            postIcon.setIconLiteral("fa-recycle");
            postType.setText("Recycled");
            postvalchecker();

        }
        if (posttype.equals("used")) {
            postIcon.setIconLiteral("fa-hand-peace-o");
            postType.setText("Bought second-hand");
            postvalchecker();
        }
        if (posttype.equals("Local Produce")) {
            postIcon.setIconLiteral("fa-map-marker");
            postType.setText("Bought local produce");
            postvalchecker();
        }
    }

    private void transportchecker() {
        if (posttype.equals("car")) {
            postIcon.setIconLiteral("fa-car");
            postIcon.setIconColor(Color.rgb(230, 126, 34));
            postType.setText("Car");
        } else if (posttype.equals("plane")) {
            postIcon.setIconLiteral("fa-plane");
            postIcon.setIconColor(Color.rgb(231, 76, 60));
            postType.setText("Plane");
        } else if (posttype.equals("publictransportation")) {
            postIcon.setIconLiteral("fa-train");
            postIcon.setIconColor(Color.rgb(52, 152, 219));
            postType.setText("Public Transportation");
        } else {
            postType.setText("Bike");
        }
    }

    private void postvalchecker() {
        if (postval.equals("No")) {
            postIcon.setIconColor(Color.rgb(230, 126, 34));
        }
    }

    @FXML
    void deletePost(ActionEvent event) {
        if (event.getSource() == deletePostBtn) {
            //yay
        }
    }

    /**
     * formats time to simple format.
     *
     * @param date sets date using Date
     * @return String
     */
    public String timeFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        String result = sdf.format(date);
        return result;
    }


}

