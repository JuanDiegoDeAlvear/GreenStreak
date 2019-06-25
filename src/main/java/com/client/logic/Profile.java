package com.client.logic;


import java.util.ArrayList;

public class Profile {

    private String name;
    private Score score;
    private Achievement achievement;
    private ArrayList<String> friendlist;

    private EnergySurvey energyProfile;
    private FoodSurvey foodProfile;
    private TransportationSurvey transportationProfile;
    private LifeStyleSurvey lifeStyleProfile;

    /**
     * Constructor.
     */
    public Profile(String name,
                   EnergySurvey energyProfile, FoodSurvey foodProfile,
                   TransportationSurvey transportationProfile,
                   LifeStyleSurvey lifeStyleProfile) {
        this.name = name;
        this.score = new Score(0, 0, 0, 0);
        this.energyProfile = energyProfile;
        this.achievement = null;
        this.foodProfile = foodProfile;
        this.transportationProfile = transportationProfile;
        this.lifeStyleProfile = lifeStyleProfile;
        this.friendlist = new ArrayList<>();

    }


    /**
     * Empty constructor.
     */
    public Profile() {
        this.name = null;
        this.score = new Score(0, 0, 0, 0);
        this.achievement = null;
        this.energyProfile = null;
        this.foodProfile = null;
        this.transportationProfile = null;
        this.lifeStyleProfile = null;
        this.friendlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public EnergySurvey getEnergyProfile() {
        return energyProfile;
    }

    public void setEnergyProfile(EnergySurvey energyProfile) {
        this.energyProfile = energyProfile;
    }

    public FoodSurvey getFoodProfile() {
        return foodProfile;
    }

    public void setFoodProfile(FoodSurvey foodProfile) {
        this.foodProfile = foodProfile;
    }

    public TransportationSurvey getTransportationProfile() {
        return transportationProfile;
    }

    public void setTransportationProfile(TransportationSurvey transportationProfile) {
        this.transportationProfile = transportationProfile;
    }

    public LifeStyleSurvey getLifeStyleProfile() {
        return lifeStyleProfile;
    }

    public void setLifeStyleProfile(LifeStyleSurvey lifeStyleProfile) {
        this.lifeStyleProfile = lifeStyleProfile;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    /**
     * Add Friend.
     *
     * @param name add username as string
     * @return String as username
     */
    public String addFriend(String name) {
        for (int x = 0; x < friendlist.size(); x++) {
            if (friendlist.get(x) == null) {
                friendlist.set(x, name);
                return name + " has been added to " + this.getName();
            }
        }
        friendlist.add(name);
        return name + " has been added to " + this.getName();
    }

    /**
     * remove friend from friendlist.
     *
     * @param name set profile using String
     * @return String
     */
    public String removeFriend(String name) {
        for (int x = 0; x < friendlist.size(); x++) {
            if (friendlist.get(x) == name) {
                friendlist.remove(name);
                return name + " has been removed";
            }
        }
        return name + " was not a friend to " + this.getName();
    }

    public ArrayList<String> getFriendList() {
        return friendlist;
    }

    public void setFriendList(ArrayList friendList) {
        this.friendlist = friendList;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }

        Profile profile = (Profile) obj;

        if (getLifeStyleProfile() != null ? !getLifeStyleProfile()
                .equals(profile.getLifeStyleProfile()) : profile.getLifeStyleProfile() != null) {
            return false;
        }

        if (getTransportationProfile() != null ? !getTransportationProfile()
                .equals(profile.getTransportationProfile()) : profile
                .getTransportationProfile() != null) {
            return false;
        }
        return checkHalf(profile) && checkAnotherPart(profile);
    }

    private boolean checkAnotherPart(Profile profile) {
        if (getFriendList() != null ? !getFriendList()
                .equals(profile.getFriendList()) : profile.getFriendList() != null) {
            return false;
        }
        if (getEnergyProfile() != null ? !getEnergyProfile()
                .equals(profile.getEnergyProfile()) : profile.getEnergyProfile() != null) {
            return false;
        }
        return getFoodProfile() != null ? getFoodProfile()
                .equals(profile.getFoodProfile()) : profile.getFoodProfile() == null;
    }


    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    private boolean checkHalf(Profile profile) {

        return getName() != null ? getName()
                .equals(profile.getName()) : profile.getName() == null;

    }

}
