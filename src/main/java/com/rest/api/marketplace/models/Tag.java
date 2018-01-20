package com.rest.api.marketplace.models;

public class Tag implements MarketplaceModel{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
