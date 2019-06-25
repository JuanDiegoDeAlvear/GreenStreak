package com.client.logic;

public class SignUpUser {
    private String username;
    private String password;
    private String email;

    private EnergySurvey energyProfile;
    private FoodSurvey foodProfile;
    private TransportationSurvey transportationProfile;
    private LifeStyleSurvey lifeStyleProfile;

    /**
     * Empty constructor.
     */
    public SignUpUser() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.energyProfile = null;
        this.foodProfile = null;
        this.transportationProfile = null;
        this.lifeStyleProfile = null;
    }

    /**
     * Constructor.
     */
    public SignUpUser(String username, String password, String email,
                      EnergySurvey energyProfile, FoodSurvey foodProfile,
                      TransportationSurvey transportationProfile,
                      LifeStyleSurvey lifeStyleProfile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.energyProfile = energyProfile;
        this.foodProfile = foodProfile;
        this.transportationProfile = transportationProfile;
        this.lifeStyleProfile = lifeStyleProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignUpUser)) {
            return false;
        }

        SignUpUser that = (SignUpUser) obj;


        if (getLifeStyleProfile() != null ? !getLifeStyleProfile()
                .equals(that.getLifeStyleProfile()) : that.getLifeStyleProfile() != null) {
            return false;
        }

        if (getTransportationProfile() != null ? !getTransportationProfile().equals(that
                .getTransportationProfile()) : that
                .getTransportationProfile() != null) {
            return false;
        }
        return checkHalf(that);
    }

    private boolean checkHalf(SignUpUser that) {

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that
                .getUsername() != null) {
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that
                .getPassword() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) {
            return false;
        }
        if (getEnergyProfile() != null ? !getEnergyProfile().equals(that.getEnergyProfile()) : that
                .getEnergyProfile() != null) {
            return false;
        }
        return getFoodProfile() != null ? getFoodProfile()
                .equals(that.getFoodProfile()) : that.getFoodProfile() == null;

    }


}
