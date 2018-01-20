package com.rest.api.marketplace.models;

public class PayRate implements MarketplaceModel{
    private enum PayType{
        HOURLY, MONTHLY, FIXED
    }

    private PayType type;
    private int value;

    public PayType getType() {
        return type;
    }

    public void setType(PayType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
