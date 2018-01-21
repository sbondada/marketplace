package com.rest.api.marketplace.models;

import java.util.ArrayList;
import java.util.UUID;

public class Buyer extends User{

    public static final String REST_RESOURCE_NAME = "buyers";

    private ArrayList<Review> reviews;
    private PayRate avgAskingRate;
    private ArrayList<Project> activeProjects;
    private ArrayList<Project> finishedProjects;
    private long totalEarned;

    public Buyer(String name, String company) {
        this.setId(UUID.randomUUID().toString());
        this.setName(name);
        this.setCompany(company);
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public PayRate getAvgAskingRate() {
        return avgAskingRate;
    }

    public void setAvgAskingRate(PayRate avgAskingRate) {
        this.avgAskingRate = avgAskingRate;
    }

    public ArrayList<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public ArrayList<Project> getFinishedProjects() {
        return finishedProjects;
    }

    public void setFinishedProjects(ArrayList<Project> finishedProjects) {
        this.finishedProjects = finishedProjects;
    }

    public long getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(long totalEarned) {
        this.totalEarned = totalEarned;
    }

}
