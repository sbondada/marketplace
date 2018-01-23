package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Buyer extends User{

    public static final String REST_RESOURCE_NAME = "buyers";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @JsonProperty("joinDate")
    private Date joinDate;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Float getAvgAskingRate() {
        return avgAskingRate;
    }

    public void setAvgAskingRate(Float avgAskingRate) {
        this.avgAskingRate = avgAskingRate;
    }

    public ArrayList<String> getActiveProjects() {
        if (isNull(activeProjects)) {
            activeProjects = new ArrayList<>();
        }
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<String> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public ArrayList<String> getFinishedProjects() {
        if (isNull(finishedProjects)){
            finishedProjects = new ArrayList<>();
        }
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
        if (isNull(submittedBids)){
            submittedBids = new ArrayList<>();
        }
        return submittedBids;
    }

    public void setSubmittedBids(ArrayList<String> submittedBids) {
        this.submittedBids = submittedBids;
    }

    public void addBid(String bidId){
        if (!getSubmittedBids().contains(bidId)){
            submittedBids.add(bidId);
        }
    }

    public void removeBid(String bidId){
        this.submittedBids.remove(bidId);
    }

    public void addActiveProjects(String projectId){
        if (isNull(activeProjects)){
            activeProjects = new ArrayList<>();
        }
        if (!activeProjects.contains(projectId)) {
            this.activeProjects.add(projectId);
        }
    }

    public void removeActiveProjects(String projectId){
        this.activeProjects.remove(projectId);
    }

    public void addFinishedProjects(String projectId){
        if (!finishedProjects.contains(projectId)) {
            this.finishedProjects.add(projectId);
        }
    }

    public void removeFinishedProjects(String projectId){
        this.finishedProjects.remove(projectId);
    }

}
