package com.rest.api.marketplace.models;

import java.util.ArrayList;
import java.util.Date;

public class Seller extends User{

    private ArrayList<Review> reviews;
    private Date joinDate;
    private ArrayList<Project> activeProjects;
    private ArrayList<Project> finshedProjects;
    private long totalSpent;
    private PayRate avgPayingRate;

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public ArrayList<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public ArrayList<Project> getFinshedProjects() {
        return finshedProjects;
    }

    public void setFinshedProjects(ArrayList<Project> finshedProjects) {
        this.finshedProjects = finshedProjects;
    }

    public long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(long totalSpent) {
        this.totalSpent = totalSpent;
    }

    public PayRate getAvgPayingRate() {
        return avgPayingRate;
    }

    public void setAvgPayingRate(PayRate avgHourlyRate) {
        this.avgPayingRate = avgHourlyRate;
    }

}
