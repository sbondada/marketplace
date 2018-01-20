package com.rest.api.marketplace.models;

import java.util.UUID;

public class Bid implements MarketplaceResource{

    private String id;
    private Project associatedProject;
    private PayRate bidRate;
    private Buyer publisher;

    public Bid(Project associatedProject, Buyer publisher){
        this.id = UUID.randomUUID().toString();
        this.associatedProject = associatedProject;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getAssociatedProject() {
        return associatedProject;
    }

    public void setAssociatedProject(Project associatedProject) {
        this.associatedProject = associatedProject;
    }

    public PayRate getBidRate() {
        return bidRate;
    }

    public void setBidRate(PayRate bidRate) {
        this.bidRate = bidRate;
    }

    public Buyer getPublisher() {
        return publisher;
    }

    public void setPublisher(Buyer publisher) {
        this.publisher = publisher;
    }

}
