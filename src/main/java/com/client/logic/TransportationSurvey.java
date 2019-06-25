package com.client.logic;

public class TransportationSurvey extends Survey {
    private String typeOfCar;
    private String mainModeTransport;
    private int aveHoursFly;
    private int aveKmPerDay;

    /**
     * Constructor empty.
     */
    public TransportationSurvey() {
        this.aveHoursFly = 0;
        this.typeOfCar = null;
        this.aveKmPerDay = 0;
        this.mainModeTransport = null;

    }

    /**
     * Constructor.
     *
     * @param aveHoursFly       set Hours user has fly using integer
     * @param typeOfCar         set Type of car the user use using String
     * @param aveKmPerDay       set distance the user travel per day using integer
     * @param mainModeTransport set Main method of transportation of the user using String
     */
    public TransportationSurvey(int aveHoursFly, String typeOfCar,
                                int aveKmPerDay, String mainModeTransport) {
        this.aveHoursFly = aveHoursFly;
        this.typeOfCar = typeOfCar;
        this.aveKmPerDay = aveKmPerDay;
        this.mainModeTransport = mainModeTransport;
    }

    public String getTypeOfCar() {
        return typeOfCar;
    }

    public void setTypeOfCar(String typeOfCar) {
        this.typeOfCar = typeOfCar;
    }

    public String getMainModeTransport() {
        return mainModeTransport;
    }

    public void setMainModeTransport(String mainModeTransport) {
        this.mainModeTransport = mainModeTransport;
    }

    public int getAveHoursFly() {
        return aveHoursFly;
    }

    public void setAveHoursFly(int aveHoursFly) {
        this.aveHoursFly = aveHoursFly;
    }

    public int getAveKmPerDay() {
        return aveKmPerDay;
    }

    public void setAveKmPerDay(int aveKmPerDay) {
        this.aveKmPerDay = aveKmPerDay;
    }

    @Override
    public boolean equals(Object obj) {


        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransportationSurvey)) {
            return false;
        }

        TransportationSurvey that = (TransportationSurvey) obj;

        if (getAveHoursFly() != that.getAveHoursFly()) {
            return false;
        }
        if (getAveKmPerDay() != that.getAveKmPerDay()) {
            return false;
        }
        if (getTypeOfCar() != null ? !getTypeOfCar().equals(that
                .getTypeOfCar()) : that.getTypeOfCar() != null) {
            return false;
        }
        return getMainModeTransport() != null ? getMainModeTransport()
                .equals(that.getMainModeTransport()) : that.getMainModeTransport() == null;
    }


    @Override
    public String toString() {
        return "TransportationSurvey{"
                + "typeOfCar='" + typeOfCar + '\''
                + ", mainModeTransport='" + mainModeTransport + '\''
                + ", aveHoursFly=" + aveHoursFly
                + ", aveKmPerDay=" + aveKmPerDay
                + '}';
    }
}

