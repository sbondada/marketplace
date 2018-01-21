package com.rest.api.marketplace.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Project implements MarketplaceResource{

    public static final String REST_RESOURCE_NAME = "projects";

    private String id;
    private String title;
    private String description;
    private ArrayList<Tag> tags;
    private Date estDeliveryTime;
    private PayRate estimatedCost;
    private Date bidEndDate;
    private Date creationDate;
    private Seller publisher;

    public Project(String title){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.creationDate = new Date();
    }


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

    public PayRate getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(PayRate estimatedCost) {
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

    public Seller getPublisher() {
        return publisher;
    }

    public void setPublisher(Seller publisher) {
        this.publisher = publisher;
    }

}
