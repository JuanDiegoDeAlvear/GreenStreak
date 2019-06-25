package com.client.logic;

public class LifeStyleSurvey extends Survey {

    String howOftenBigPurchase;
    String weeklyWaste;
    String recycle;
    String applianceUse;
    int aveShowerTime;

    /**
     * Empty constructor.
     */
    public LifeStyleSurvey() {
        this.howOftenBigPurchase = null;
        this.weeklyWaste = null;
        this.recycle = null;
        this.applianceUse = null;
        this.aveShowerTime = 0;
    }

    /**
     * Constructor.
     */
    public LifeStyleSurvey(String howOftenBigPurchase, String weeklyWaste, String recycle,
                           String applianceUse, int aveShowerTime) {
        this.howOftenBigPurchase = howOftenBigPurchase;
        this.weeklyWaste = weeklyWaste;
        this.recycle = recycle;
        this.applianceUse = applianceUse;
        this.aveShowerTime = aveShowerTime;
    }

    public String getHowOftenBigPurchase() {
        return howOftenBigPurchase;
    }

    public void setHowOftenBigPurchase(String howOftenBigPurchase) {
        this.howOftenBigPurchase = howOftenBigPurchase;
    }

    public String getWeeklyWaste() {
        return weeklyWaste;
    }

    public void setWeeklyWaste(String weeklyWaste) {
        this.weeklyWaste = weeklyWaste;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public String getApplianceUse() {
        return applianceUse;
    }

    public void setApplianceUse(String applianceUse) {
        this.applianceUse = applianceUse;
    }

    public int getAveShowerTime() {
        return aveShowerTime;
    }

    public void setAveShowerTime(int aveShowerTime) {
        this.aveShowerTime = aveShowerTime;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LifeStyleSurvey)) {
            return false;
        }

        LifeStyleSurvey that = (LifeStyleSurvey) obj;

        checkHalf(that);


        if (getRecycle() != null ? !getRecycle().equals(that.getRecycle()) : that
                .getRecycle() != null) {
            return false;
        }
        return getApplianceUse() != null ? !getApplianceUse()
                .equals(that.getApplianceUse()) : that.getApplianceUse() != null;
    }

    private boolean checkHalf(LifeStyleSurvey that) {
        if (getAveShowerTime() != that.getAveShowerTime()) {
            return false;
        }
        if (getHowOftenBigPurchase() != null ? !getHowOftenBigPurchase()
                .equals(that.getHowOftenBigPurchase()) : that.getHowOftenBigPurchase() != null) {
            return false;
        }

        return getWeeklyWaste() != null ? getWeeklyWaste()
                .equals(that.getWeeklyWaste()) : that.getWeeklyWaste() == null;
    }

    @Override
    public String toString() {
        return "LifeStyleSurvey{"
                + "howOftenBigPurchase='"
                + howOftenBigPurchase + '\'' + ", weeklyWaste='"
                + weeklyWaste + '\'' + ", recycle='" + recycle + '\''
                + ", applianceUse='" + applianceUse + '\'' + ", aveShowerTime="
                + aveShowerTime + '}';
    }
}
