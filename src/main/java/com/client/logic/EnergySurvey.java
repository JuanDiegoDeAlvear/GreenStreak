package com.client.logic;

public class EnergySurvey extends Survey {

    String typeHouse;
    String amountEnergy;
    int amountRoommates;
    boolean greenEnergy;
    boolean efficientLighting;

    /**
     * Empty constructor.
     */
    public EnergySurvey() {

        this.typeHouse = null;
        this.amountRoommates = 0;
        this.amountEnergy = null;
        this.greenEnergy = false;
        this.efficientLighting = false;
    }

    /**
     * Constructor.
     */
    public EnergySurvey(String typeHouse, int amountRoommates, String amountEnergy,
                        boolean greenEnergy, boolean efficientLighting) {

        this.typeHouse = typeHouse;
        this.amountRoommates = amountRoommates;
        this.amountEnergy = amountEnergy;
        this.greenEnergy = greenEnergy;
        this.efficientLighting = efficientLighting;
    }


    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
    }

    public String getAmountEnergy() {
        return amountEnergy;
    }

    public void setAmountEnergy(String amountEnergy) {
        this.amountEnergy = amountEnergy;
    }

    public int getAmountRoommates() {
        return amountRoommates;
    }

    public void setAmountRoommates(int amountRoommates) {
        this.amountRoommates = amountRoommates;
    }

    public boolean getGreenEnergy() {
        return greenEnergy;
    }

    public void setGreenEnergy(boolean greenEnergy) {
        this.greenEnergy = greenEnergy;
    }

    public boolean getEfficientLighting() {
        return efficientLighting;
    }

    public void setEfficientLighting(boolean efficientLighting) {
        this.efficientLighting = efficientLighting;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnergySurvey)) {
            return false;
        }

        EnergySurvey that = (EnergySurvey) obj;

        if (getAmountRoommates() != that.getAmountRoommates()) {
            return false;
        }
        if (getGreenEnergy() != that.getGreenEnergy()) {
            return false;
        }
        if (getEfficientLighting() != that.getEfficientLighting()) {
            return false;
        }
        if (getTypeHouse() != null ? !getTypeHouse()
                .equals(that.getTypeHouse()) : that.getTypeHouse() != null) {
            return false;
        }
        return getAmountEnergy() != null ? getAmountEnergy()
                .equals(that.getAmountEnergy()) : that.getAmountEnergy() == null;
    }

    @Override
    public String toString() {
        return "EnergySurvey{" + "typeHouse='" + typeHouse + '\''
                + ", amountEnergy='" + amountEnergy + '\''
                + ", amountRoommates=" + amountRoommates
                + ", greenEnergy=" + greenEnergy
                + ", efficientLighting=" + efficientLighting
                + '}';
    }
}
