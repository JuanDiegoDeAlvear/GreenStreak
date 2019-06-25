package com.client.logic;

public class FoodPost extends SuperPost {


    private String meal;
    private String type;


    /**
     * Constructor.
     *
     * @param meal sets m from a String
     * @param type sets t from a String
     */
    //set new Date intializer
    public FoodPost(String meal, String type) {
        super(null, null, "food", 0);
        this.meal = meal;
        this.type = type;

    }

    /**
     * Empty Constructor.
     */
    public FoodPost() {
        super(null, null, "food", 0);
        this.meal = null;
        this.type = null;
    }


    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FoodPost)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        FoodPost foodPost = (FoodPost) obj;

        if (getMeal() != null ? !getMeal()
                .equals(foodPost.getMeal()) : foodPost.getMeal() != null) {
            return false;
        }
        return getType() != null ? getType()
                .equals(foodPost.getType()) : foodPost.getType() == null;
    }

}
