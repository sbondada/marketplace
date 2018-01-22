package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Bid implements MarketplaceResource{

    public static final String REST_RESOURCE_NAME = "bids";
    private String id;
    @JsonProperty("associated_project_id")
    private String associatedProjectId;
    //for now the bid rate is always per hour. extras to be the future feature.
    @JsonProperty("bid_rate")
    private Float bidRate;
    @JsonProperty("publisher_id")
    private String buyerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssociatedProjectId() {
        return associatedProjectId;
    }

    public void setAssociatedProjectId(String associatedProjectId) {
        this.associatedProjectId = associatedProjectId;
    }

    public Float getBidRate() {
        return bidRate;
    }

    public void setBidRate(Float bidRate) {
        this.bidRate = bidRate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void update(Bid updatedObj){
        this.bidRate = updatedObj.getBidRate();
    }

}
