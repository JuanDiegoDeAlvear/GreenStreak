package com.client.logic;

public class ConsumptionPost extends SuperPost {
    private String consume;
    private boolean state;


    /**
     * Empty Constructor.
     */
    public ConsumptionPost() {
        super(null, null, "ConsumptionPost", 0);
        this.consume = null;
        this.state = false;
    }

    /**
     * Constructor.
     *
     * @param consume sets consume with a String
     * @param state   sets state with a boolean
     */
    public ConsumptionPost(String consume, boolean state) {
        super(null, null, "ConsumptionPost", 0);
        this.state = state;
        this.consume = consume;

    }

    public void setPostClass(String postClass) {
        this.postClass = postClass;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConsumptionPost)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        ConsumptionPost that = (ConsumptionPost) obj;

        if (getState() != that.getState()) {
            return false;
        }
        return getConsume() != null ? getConsume()
                .equals(that.getConsume()) : that.getConsume() == null;
    }


}
