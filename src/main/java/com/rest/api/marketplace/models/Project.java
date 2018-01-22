package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements MarketplaceResource{

    public static final String REST_RESOURCE_NAME = "projects";

    private String id;
    private String title;
    private String description;
    private ArrayList<Tag> tags;
    @JsonProperty("est_DOD")
    private Date estDeliveryTime;
    @JsonProperty("est_cost")
    private Float estimatedCost;
    @JsonProperty("bid_end_date")
    private Date bidEndDate;
    @JsonProperty("creation_date")
    private Date creationDate;
    private String publisherId;
    @JsonProperty("bid_status")
    private String bidStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public Date getEstDeliveryTime() {
        return estDeliveryTime;
    }

    public void setEstDeliveryTime(Date estDeliveryTime) {
        this.estDeliveryTime = estDeliveryTime;
    }

    public  Float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public Date getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId= publisherId;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

}
