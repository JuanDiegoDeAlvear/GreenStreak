package com.client.controllers;

import com.client.ServerCommunication;
import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.PostCatalog;
import com.client.logic.TransportationPost;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import eu.hansolo.tilesfx.skins.BarChartItem;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController {

    XYChart.Series<String, String> series;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane dashboarContentPane;

    @FXML
    private VBox pain1;

    @FXML
    private VBox pain2;

    @FXML
    private VBox pain3;

    @FXML
    private VBox pain4;

    @FXML
    private VBox pain5;

    @FXML
    private Label points;

    @FXML
    private VBox pointsTile;
    private int size = 200;
    private Tile food;
    private Tile transport;
    private Tile carbon;
    private Tile greenstreak;
    private Tile consumption;

    @FXML
    void initialize() {

        RankingsController.scoreCalc();

        setPoints(LoginController.userprofile.getScore().getScore());
        //need to be changed after the server fix it
        //setGreenStreak(LoginController.userprofile.getScore().getGreenstreak());

        BarChartItem bci1 = new BarChartItem("Vegan", vegancheck(), Color.rgb(46, 204, 113));
        BarChartItem bci2 = new BarChartItem("Vegetarian", vegeterianCheck(),
                Color.rgb(52, 152, 219));
        BarChartItem bci3 = new BarChartItem("Meat", meatCheck(), Color.rgb(230, 126, 34));

        food = TileBuilder.create()
                .prefSize(size + 80, size + 50)
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Meal History")
                .barChartItems(bci1, bci2, bci3)
                .build();
        food.setTextVisible(true);
        food.setBackgroundColor(Color.rgb(44, 62, 80));
        pain1.getChildren().add(food);


        XYChart.Series<String, Number> bikes = new XYChart.Series<>();
        bikes.setName("Bike");
        TilesFXSeries bikeData = new TilesFXSeries(bikes);
        bikeData.setStroke(Color.rgb(46, 204, 113));
        bikeData.setLegendSymbolFill(Color.rgb(46, 204, 113));

        XYChart.Series<String, Number> cars = new XYChart.Series<>();
        cars.setName("Car");
        TilesFXSeries carData = new TilesFXSeries(cars);
        carData.setStroke(Color.rgb(230, 126, 34));
        carData.setLegendSymbolFill(Color.rgb(230, 126, 34));

        XYChart.Series<String, Number> publicTrans = new XYChart.Series<>();
        publicTrans.setName("Public Transport");
        TilesFXSeries pubtransData = new TilesFXSeries(publicTrans);
        pubtransData.setStroke(Color.rgb(52, 152, 219));
        pubtransData.setLegendSymbolFill(Color.rgb(52, 152, 219));

        transport = TileBuilder.create()
                .prefSize(2 * size, size + 50)
                .skinType(Tile.SkinType.SMOOTHED_CHART)
                .chartType(Tile.ChartType.LINE)
                .title("Transportation")
                .backgroundColor(Color.rgb(44, 62, 80))
                .chartGridColor(Color.rgb(107, 140, 173))
                .tilesFxSeries(bikeData, carData, pubtransData)
                .chartGridColor(Color.rgb(107, 140, 173))
                .foregroundColor(Color.rgb(107, 140, 173))
                .build();

        pain2.getChildren().add(transport);

        transportGraph();

        carbon = TileBuilder.create()
                .prefSize(size, size + 60)
                .skinType(Tile.SkinType.GAUGE)
                .title("Carbon Footprint" + "\n" + "The amount of CO2 you release in a year")
                .unit("tons of CO2")
                .threshold(7)
                .value(LoginController.userprofile.getScore().getFootprint())
                .minValue(-5)
                .maxValue(30)
                .barColor(Color.rgb(46, 204, 113))
                .thresholdColor(Color.rgb(241, 196, 15))
                .backgroundColor(Color.rgb(44, 62, 80))
                .build();

        pain3.getChildren().add(carbon);

        FontIcon leaf = new FontIcon();
        leaf.setIconLiteral("icm-fire");
        leaf.setIconColor(Color.rgb(46, 204, 113));
        leaf.setIconSize(32);

        int score = LoginController.userprofile.getScore().getScore();
        int level = (score / 1000) + 1;

        greenstreak = TileBuilder.create()
                .prefSize(size, size)
                .skinType(Tile.SkinType.CIRCULAR_PROGRESS)
                .title("GreenStreak" )
                .unit("%")
                .graphic(leaf)
                .backgroundColor(Color.rgb(44, 62, 80))
                .barColor(Color.rgb(46, 204, 113))
                .value((score - (level - 1) * 1000) / ((double) 1000) * 100)
                .valueColor(Color.rgb(46, 204, 113))
                .barBackgroundColor(Color.rgb(236, 240, 241))
                .textSize(Tile.TextSize.BIGGER)
                .build();
        pain4.getChildren().add(greenstreak);

        BarChartItem bci4 = new BarChartItem("Recycled", recyclecheck(), Color.rgb(39, 174, 96));
        BarChartItem bci5 = new BarChartItem("Bought Local", localcheck(), Color.rgb(68, 189, 50));
        BarChartItem bci6 = new BarChartItem("Bought Second Hand",
                secondHandcheck(), Color.rgb(26, 188, 156));
        consumption = TileBuilder.create()
                .prefSize(size + 50, size + 65)
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Consumption History")
                .barChartItems(bci4, bci5, bci6)
                .backgroundColor(Color.rgb(44, 62, 80))
                .autoScale(false)
                .build();
        consumption.setTextVisible(true);
        pain5.getChildren().add(consumption);

    }

    private void transportGraph() {
        PostCatalog posts = ServerCommunication.recievepostarray(LoginController.getusername());
        posts = PostCatalog.sortByDate(posts);

        TransportationPost[] transposts = ServerCommunication
                .getTransportationPosts(LoginController.getusername());

        List<TransportationPost> tposts = Arrays.asList(transposts);
        tposts.sort(Comparator.comparing(TransportationPost::getDate));

        int domain = (tposts.size() <= 14) ? tposts.size() : 14;

        for (int i = 0; i < domain; i++) {
            Date date = tposts.get(i).getDate();
            String day = parseDay(date);
            String type = tposts.get(i).getType();
            double dist = tposts.get(i).getKilometer();

            setTransport(type, dist, day);
        }

        //RankingsController.scoreCalc();


    }

    private String parseDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return month + "." + day;
    }


    private int secondHandcheck() {
        int result = 0;
        ConsumptionPost[] check = ServerCommunication.getConsumptionPosts(
                LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            ConsumptionPost poster = check[i];
            if (poster.getConsume().equals("used")) {
                result++;
            }
        }
        return result ;
    }

    private int localcheck() {
        int result = 0;
        ConsumptionPost[] check = ServerCommunication.getConsumptionPosts(
                LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            ConsumptionPost poster = check[i];
            if (poster.getConsume().equals("Local Produce")) {
                result++;
            }
        }
        return result ;
    }

    private int recyclecheck() {
        int result = 0;
        ConsumptionPost[] check = ServerCommunication.getConsumptionPosts(
                LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            ConsumptionPost poster = check[i];
            if (poster.getConsume().equals("Recycle")) {
                result++;
            }
        }
        return result ;
    }

    private int vegancheck() {
        int result = 0;
        FoodPost[] check = ServerCommunication.getFoodPosts(LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            FoodPost poster = check[i];
            if (poster.getType().equals("Vegan")) {
                result++;
            }
        }
        return result ;
    }

    private int vegeterianCheck() {
        int result = 0;
        FoodPost[] check = ServerCommunication.getFoodPosts(LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            FoodPost poster = check[i];
            if (poster.getType().equals("Vegetarian")) {
                result++;
            }
        }
        return result ;
    }

    private int meatCheck() {
        int result = 0;
        FoodPost[] check = ServerCommunication.getFoodPosts(LoginController.getusername());

        for (int i = 0 ; i < check.length ; i++) {
            FoodPost poster = check[i];
            if (poster.getType().equals("Meat")) {
                result++;
            }
        }
        return result ;
    }

    public void setPoints(int val) {
        points.setText(val + "");
    }

    /**
     * Sets value for the greenstreak bar.
     * @param val value to add to greenstreak
     */
    public void setGreenStreak(double val) {
        val = val * 100;
        System.out.println(val);
        greenstreak.setValue(val);
    }

    /**
     * Method that takes amount of certain meals.
     *
     * @param numVegan      num of Vegan meals in total
     * @param numVegetarian num of Vegetarian meals in total
     * @param numMeat       num of meat meals in total
     */
    public void setFood(int numVegan, int numVegetarian, int numMeat) {
        BarChartItem bci1 = new BarChartItem("Vegan", numVegan, Color.rgb(46, 204, 113));
        BarChartItem bci2 = new BarChartItem("Vegetarian", numVegetarian, Color.rgb(52, 152, 219));
        BarChartItem bci3 = new BarChartItem("Meat", numMeat, Color.rgb(230, 126, 34));

        food.clearBarChartItems();
        food.addBarChartItem(bci1);
        food.addBarChartItem(bci2);
        food.addBarChartItem(bci3);
    }

    /**
     * SetTransport.
     *
     * @param type vehicle
     * @param dist distance
     * @param day  what date
     */
    public void setTransport(String type, double dist, String day) {
        //this method must be called for every data point
        if (type.equals("bike")) {
            transport.getSeries().get(0).getData().add(new XYChart.Data<>(day, dist));
        }

        if (type.equals("car")) {
            transport.getSeries().get(1).getData().add(new XYChart.Data<>(day, dist));
        }

        if (type.equals("publictransportation")) {
            transport.getSeries().get(2).getData().add(new XYChart.Data<>(day, dist));
        }
    }

    public void setCarbon(double carbonFootprint) {
        carbon.setValue(carbonFootprint);
    }
}
