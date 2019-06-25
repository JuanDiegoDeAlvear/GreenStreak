package com.client.logic;

public class FoodSurvey extends Survey {
    // change to private
    String amountOrganicFood;
    String dietChoice;
    String amountLocalFood;
    String amountWastedFood;
    String amountFoodPackaged;
    String amountFoodCompost;

    /**
     * Empty constructor.
     */
    public FoodSurvey() {
        this.amountFoodCompost = null;
        this.dietChoice = null;
        this.amountLocalFood = null;
        this.amountWastedFood = null;
        this.amountFoodPackaged = null;
        this.amountFoodCompost = null;

    }

    /**
     * Constructor.
     */
    public FoodSurvey(String amountOrganicFood, String dietChoice, String amountLocalFood,
                      String amountWastedFood, String amountFoodPackaged,
                      String amountFoodCompost) {
        this.amountOrganicFood = amountOrganicFood;
        this.dietChoice = dietChoice;
        this.amountLocalFood = amountLocalFood;
        this.amountWastedFood = amountWastedFood;
        this.amountFoodPackaged = amountFoodPackaged;
        this.amountFoodCompost = amountFoodCompost;
    }

    public String getAmountOrganicFood() {
        return amountOrganicFood;
    }

    public void setAmountOrganicFood(String amountOrganicFood) {
        this.amountOrganicFood = amountOrganicFood;
    }

    public String getDietChoice() {
        return dietChoice;
    }

    public void setDietChoice(String dietChoice) {
        this.dietChoice = dietChoice;
    }

    public String getAmountLocalFood() {
        return amountLocalFood;
    }

    public void setAmountLocalFood(String amountLocalFood) {
        this.amountLocalFood = amountLocalFood;
    }

    public String getAmountWastedFood() {
        return amountWastedFood;
    }

    public void setAmountWastedFood(String amountWastedFood) {
        this.amountWastedFood = amountWastedFood;
    }

    public String getAmountFoodPackaged() {
        return amountFoodPackaged;
    }

    public void setAmountFoodPackaged(String amountFoodPackaged) {
        this.amountFoodPackaged = amountFoodPackaged;
    }

    public String getAmountFoodCompost() {
        return amountFoodCompost;
    }

    public void setAmountFoodCompost(String amountFoodCompost) {
        this.amountFoodCompost = amountFoodCompost;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FoodSurvey)) {
            return false;
        }

        FoodSurvey that = (FoodSurvey) obj;


        if (getAmountFoodCompost() != null ? !getAmountFoodCompost().equals(that
                .getAmountFoodCompost()) : that.getAmountFoodCompost() != null) {
            return false;
        }

        if (getAmountWastedFood() != null ? !getAmountWastedFood().equals(that
                .getAmountWastedFood()) : that.getAmountWastedFood() != null) {
            return false;
        }
        if (getAmountFoodPackaged() != null ? !getAmountFoodPackaged().equals(that
                .getAmountFoodPackaged()) : that.getAmountFoodPackaged() != null) {
            return false;
        }

        return checkHalf(that);
    }

    private boolean checkHalf(FoodSurvey that) {

        if (getAmountOrganicFood() != null ? !getAmountOrganicFood()
                .equals(that.getAmountOrganicFood()) : that.getAmountOrganicFood() != null) {
            return false;
        }
        if (getDietChoice() != null ? !getDietChoice()
                .equals(that.getDietChoice()) : that.getDietChoice() != null) {
            return false;
        }
        return getAmountLocalFood() != null ? getAmountLocalFood().equals(that
                .getAmountLocalFood()) : that.getAmountLocalFood() == null;

    }


    @Override
    public String toString() {
        return "FoodSurvey{" + "amountOrganicFood='" + amountOrganicFood + '\''
                + ", dietChoice='" + dietChoice + '\''
                + ", amountLocalFood='" + amountLocalFood + '\''
                + ", amountWastedFood='" + amountWastedFood + '\''
                + ", amountFoodPackaged='" + amountFoodPackaged + '\''
                + ", amountFoodCompost='" + amountFoodCompost + '\''
                + '}';
    }
}
