package com.client.logic;

public class TransportationPost extends SuperPost {

    private double kilometer;
    private String type;

    /**
     * Constructor.
     *
     * @param km   sets double to km
     * @param type sets String type to type
     */


    public TransportationPost(double km, String type) {
        super(null, null, "transportation", 0);
        this.kilometer = km;
        this.type = type;
        this.username = null;
    }

    /**
     * Empty constructor.
     */
    public TransportationPost() {
        super(null, null, "transportation", 0);
        this.kilometer = 0;
        this.type = null;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double kilometer) {
        this.kilometer = kilometer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransportationPost)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        TransportationPost that = (TransportationPost) obj;

        if (Double.compare(that.getKilometer(), getKilometer()) != 0) {
            return false;
        }
        return getType() != null ? getType().equals(that.getType()) : that.getType() == null;
    }

    @Override
    public String toString() {
        return "TransportationPost{"
                + "kilometer=" + kilometer
                + ", type='" + type + '\''
                + ", username='" + username + '\''
                + ", date="
                + date + ", postClass='" + postClass + '\''
                + '}';
    }
}
