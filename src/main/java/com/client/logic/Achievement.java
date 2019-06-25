package com.client.logic;

import com.client.ServerCommunication;
import com.client.controllers.LoginController;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


public class Achievement {

    private boolean firstPost;
    private boolean firstFriend;
    private boolean hundKm;
    private boolean fiveDaysr;
    private boolean toprank;
    private boolean lowEco;
    private boolean loggedin;
    private boolean carblsix;
    private boolean fivedaysv;
    private boolean local;


    /**
     * Empty constructor.
     */
    public Achievement() {
        this.firstPost = false;
        this.firstFriend = false;
        this.hundKm = false;
        this.fiveDaysr = false;
        this.toprank = false;
        this.lowEco = false;
        this.loggedin = false;
        this.carblsix = false;
        this.fivedaysv = false;
    }


    /**
     * First Friend Achievement and if true
     * it sends a SystemTray notification
     * and sends achievements to server.
     */
    public void checkfirstfriend() {
        if (!isFirstFriend()) {
            if (LoginController.userprofile.getFriendList().size() >= 1) {
                setFirstFriend(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());
                displayTray("Friendship bond");
            }
        }
    }

    /**
     * First Post Achievement and if true
     * it sends a SystemTray notification
     * and sends achievements to server.
     */
    public void checkfirstPost() {
        if (!isFirstPost()) {
            PostCatalog posts = ServerCommunication
                    .recievepostarray(LoginController.getusername());
            if (posts.size() >= 1) {
                setFirstPost(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());

                displayTray("You've made your first post");

            }
        }

    }

    /**
     * Low eco than 5.2 Achievement and if true
     * it sends a SystemTray notification
     * and sends achievements to server.
     */
    public void checklowEco() {
        if (!isLowEco()) {
            if (Calculations.totalEco(LoginController.userprofile.getFoodProfile(),
                    LoginController.userprofile.getTransportationProfile(), LoginController
                            .userprofile.getEnergyProfile(),
                    LoginController.userprofile.getLifeStyleProfile()) <= 5.2) {
                setLowEco(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());

                displayTray("Eco score better than average");
            }

        }

    }

    /**
     * checks if user achieved the Buying local more than 5 days
     * if true it sends it to server and stops.
     */
    public void checkLocal() {
        if (!isLocal()) {
            ConsumptionPost[] posts = ServerCommunication
                    .getConsumptionPosts(LoginController.getusername());
            int local = 0;
            for (int i = 0; i != posts.length; i++) {
                if (posts[i].getConsume().equals("Local Produce")) {
                    local++;
                }
                if (local >= 5) {
                    setLocal(true);
                    ServerCommunication.sendAchievements(LoginController
                            .getusername(),LoginController
                            .userprofile.getAchievement());
                    displayTray("Supporter of local production");
                }
            }
        }
    }

    /**
     * check if user achieved the 100KM achievement
     * by bike if true sends to server and stops.
     */
    public void checkhundKm() {
        if (!isHundKm()) {
            if (hundKmhelp()) {
                setHundKm(true);
                ServerCommunication.sendAchievements(LoginController
                        .getusername(),LoginController
                        .userprofile.getAchievement());

                displayTray("Biker");
                //send to server
            }
        }
    }

    /**
     * Helper function to check if the user
     * have the Distance covered by bike is 100 or more.
     *
     * @return boolean
     */
    private boolean hundKmhelp() {
        double distance = 0;

        TransportationPost[] checker = ServerCommunication
                .getTransportationPosts(LoginController.getusername());
        for (int i = 0; i < checker.length; i++) {
            SuperPost poster = checker[i];
            TransportationPost post = (TransportationPost) poster;
            if (post.getType().equals("bike")) {
                distance = distance + post.getKilometer();
            }
        }
        return distance >= 100;
    }

    /**
     * checks if the user achieved the Five Days of Recycle
     * if true then sends it to server and stops.
     */
    public void checkfiveDaysr() {
        ArrayList<String> check = new ArrayList<>();
        fivedaysrhelp(ServerCommunication.getConsumptionPosts(LoginController
                .getusername()), check);

        Set<String> set = new LinkedHashSet<>(check.size());
        for (String element : check) {
            set.addAll(Collections.singleton(element));
        }

        ArrayList<String> uniqueList = new ArrayList<>(set);

        if (uniqueList.size() == 5) {
            setFiveDaysr(true);
            ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                    .userprofile.getAchievement());
            displayTray("Mr Recycle");
        }
    }

    /**
     * Helper function to Five Days of Recycle
     * check is filtered to get recycle.
     *
     * @param posts sets posts with array of Consumptionposts
     * @param check sets the check with arraylist
     */
    private void fivedaysrhelp(ConsumptionPost[] posts, ArrayList<String> check) {
        for (int i = 0; i < posts.length; i++) {
            SuperPost poster = posts[i];
            ConsumptionPost post = (ConsumptionPost) poster;
            if (post.getConsume().equals("Recycle") && post.getState()) {
                check.add(dateFormat(post.getDate()));
            }
        }

    }

    /**
     * check if the user achieved the first on the league
     * if true sends to server and stops.
     */
    public void checktoprank() {
        if (!isToprank()) {
            ArrayList<RankingEntry> el = ServerCommunication.globalRankingScore();
            if (el.get(0).getUsername() == LoginController.getusername()) {
                setToprank(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());
                displayTray("Top League");
            }
        }
    }

    /**
     * This method will help when testing the server getAchievements method
     *
     * @param obj This is the object.
     * @return The method will return a boolean value.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Achievement that = (Achievement) obj;
        return isFirstPost() == that.isFirstPost()
                && isFirstFriend() == that.isFirstFriend()
                && equalhelper(that)
                && equalhelp(that);
    }

    private boolean equalhelper(Achievement that) {
        return isHundKm() == that.isHundKm()
                && isFiveDaysr() == that.isFiveDaysr()
                && isFivedaysv() == that.isFivedaysv()
                && isLocal() == that.isLocal();
    }

    private boolean equalhelp(Achievement that) {
        return isToprank() == that.isToprank()
                && isLowEco() == that.isLowEco()
                && isLoggedin() == that.isLoggedin()
                && isCarblsix() == that.isCarblsix() ;
    }


    /**
     * Helper function to check for the achievement
     * Five Days vegan.
     * check is being changed to filter the vegan posts only.
     *
     * @param posts sets posts as Array of foodpost
     * @param check sets check as an Arraylist
     */
    private void fivedaysvhelp(FoodPost[] posts, ArrayList<String> check) {
        for (int i = 0; i < posts.length; i++) {
            SuperPost poster = posts[i];
            FoodPost post = (FoodPost) poster;
            if (post.getType().equals("Vegan")) {
                check.add(dateFormat(post.getDate()));
            }

        }
    }

    /**
     * checks if the user achieved the First Login
     * if true it will send it to server and stop.
     */
    public void loggedin() {
        if (!isLoggedin()) {
            setLoggedin(true);
            ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                    .userprofile.getAchievement());
            displayTray("Login for the first time");
        }
    }

    /**
     * checks if the user achieved the Footprint less
     * than 16 if true it will send it to server and stop.
     */
    public void carblsixach() {
        if (!isCarblsix()) {
            if (LoginController.userprofile.getScore().getFootprint() < 16) {
                displayTray("Better Than Average");
                setCarblsix(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());
            }
        }
    }

    /**
     * Check if the user achieved the Five Days of vegan
     * if true it will send it to sever and stop.
     */
    public void checkfiveDaysv() {
        if (!isFivedaysv()) {
            ArrayList<String> check = new ArrayList<>();

            fivedaysvhelp(ServerCommunication.getFoodPosts(LoginController
                .getusername()), check);

            Set<String> set = new LinkedHashSet<>(check.size());
            for (String element : check) {
                set.addAll(Collections.singleton(element));
            }

            ArrayList<String> uniqueList = new ArrayList<>(set);
            if (uniqueList.size() == 5) {
                setFivedaysv(true);
                ServerCommunication.sendAchievements(LoginController.getusername(),LoginController
                        .userprofile.getAchievement());
                displayTray("Mr Recycle");
            }
        }
    }

    void displayTray(String statment) {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit()
                .createImage("resources/images/leaf.png");
        TrayIcon trayIcon = new TrayIcon(image, "GreenStreak");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        trayIcon.displayMessage("You made an Achievement",
                statment, TrayIcon.MessageType.INFO);
    }

    String dateFormat(Date da) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = sdf.format(da);
        return date;
    }

    @Override
    public String toString() {
        return "Achievement{" + "firstPost=" + firstPost
                + ", firstFriend=" + firstFriend + ", hundKm=" + hundKm
                + ", fiveDaysr=" + fiveDaysr + ", toprank=" + toprank
                + ", lowEco=" + lowEco + ", loggedin=" + loggedin
                + ", carblsix=" + carblsix + ", fivedaysv=" + fivedaysv
                + ", local=" + local + '}';
    }

    public boolean isFirstPost() {
        return firstPost;
    }

    public void setFirstPost(boolean firstPost) {
        this.firstPost = firstPost;
    }

    public boolean isFirstFriend() {
        return firstFriend;
    }

    public void setFirstFriend(boolean firstFriend) {
        this.firstFriend = firstFriend;
    }

    public boolean isHundKm() {
        return hundKm;
    }

    public void setHundKm(boolean hundKm) {
        this.hundKm = hundKm;
    }

    public boolean isFiveDaysr() {
        return fiveDaysr;
    }

    public void setFiveDaysr(boolean fiveDaysr) {
        this.fiveDaysr = fiveDaysr;
    }

    public boolean isToprank() {
        return toprank;
    }

    public void setToprank(boolean toprank) {
        this.toprank = toprank;
    }

    public boolean isLowEco() {
        return lowEco;
    }

    public void setLowEco(boolean lowEco) {
        this.lowEco = lowEco;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public boolean isCarblsix() {
        return carblsix;
    }

    public void setCarblsix(boolean bool) {
        carblsix = bool;
    }

    public boolean isFivedaysv() {
        return fivedaysv;
    }

    public void setFivedaysv(boolean fivedaysv) {
        this.fivedaysv = fivedaysv;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
}
