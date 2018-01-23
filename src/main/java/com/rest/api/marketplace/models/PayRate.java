package com.rest.api.marketplace.models;

//To be future feature (giving more ways to bid)
public class PayRate implements MarketplaceModel{
    private enum PayType{
        HOURLY, MONTHLY, FIXED
    }

    private PayType type;
    private Integer value;

    public PayType getType() {
        return type;
    }

    public void setType(PayType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
