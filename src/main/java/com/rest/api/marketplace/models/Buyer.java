package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Buyer extends User{

    public static final String REST_RESOURCE_NAME = "buyers";

    @JsonProperty("active_projects")
    private ArrayList<String> activeProjects;
    @JsonProperty("finished_projects")
    private ArrayList<String> finishedProjects;
    @JsonProperty("total_earnings")
    private Long totalEarnings;
    @JsonProperty("finished_projects")
    private Float avgAskingRate;
    @JsonProperty("submitted_bids")
    private ArrayList<String> submittedBids;

    public Float getAvgAskingRate() {
        return avgAskingRate;
    }

    public void setAvgAskingRate(Float avgAskingRate) {
        this.avgAskingRate = avgAskingRate;
    }

    public ArrayList<String> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<String> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public ArrayList<String> getFinishedProjects() {
        return finishedProjects;
    }

    public void setFinishedProjects(ArrayList<String> finishedProjects) {
        this.finishedProjects = finishedProjects;
    }

    public long getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(long totalEarned) {
        this.totalEarnings = totalEarnings;
    }

    public ArrayList<String> getSubmittedBids() {
        return submittedBids;
    }

    public void setSubmittedBids(ArrayList<String> submittedBids) {
        this.submittedBids = submittedBids;
    }

    public void addBid(String bidId){
        this.submittedBids.add(bidId);
    }

}
