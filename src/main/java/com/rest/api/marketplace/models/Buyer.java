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
    @JsonProperty("join_date")
    private Date joinDate;
    @JsonProperty("active_project_ids")
    private ArrayList<String> activeProjectIds;
    @JsonProperty("finished_project_ids")
    private ArrayList<String> finishedProjectIds;
    //To be future feature
    @JsonProperty("total_earnings")
    private Long totalEarnings;
    //To be future feature
    @JsonProperty("avg_asking_rate")
    private Float avgAskingRate;
    @JsonProperty("submitted_bid_ids")
    private ArrayList<String> submittedBidIds;

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

    public ArrayList<String> getActiveProjectIds() {
        if (isNull(activeProjectIds)) {
            activeProjectIds = new ArrayList<>();
        }
        return activeProjectIds;
    }

    public void setActiveProjectIds(ArrayList<String> activeProjectIds) {
        this.activeProjectIds= activeProjectIds;
    }

    public ArrayList<String> getFinishedProjectIds() {
        if (isNull(finishedProjectIds)){
            finishedProjectIds= new ArrayList<>();
        }
        return finishedProjectIds;
    }

    public void setFinishedProjectIds(ArrayList<String> finishedProjectIds) {
        this.finishedProjectIds = finishedProjectIds;
    }

    public Long getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Long totalEarned) {
        this.totalEarnings = totalEarnings;
    }

    public ArrayList<String> getSubmittedBidIds() {
        if (isNull(submittedBidIds)){
            submittedBidIds = new ArrayList<>();
        }
        return submittedBidIds;
    }

    public void setSubmittedBidIds(ArrayList<String> submittedBidIds) {
        this.submittedBidIds= submittedBidIds;
    }

    public void addBidId(String bidId){
        if (!getSubmittedBidIds().contains(bidId)){
            submittedBidIds.add(bidId);
        }
    }

    public void removeBid(String bidId){
        this.submittedBidIds.remove(bidId);
    }

    public void addActiveProjectIds(String projectId){
        if (!getActiveProjectIds().contains(projectId)) {
            this.activeProjectIds.add(projectId);
        }
    }

    public void removeActiveProjects(String projectId){
        this.activeProjectIds.remove(projectId);
    }

    public void addFinishedProjects(String projectId){
        if (!getFinishedProjectIds().contains(projectId)) {
            this.finishedProjectIds.add(projectId);
        }
    }

    public void removeFinishedProjects(String projectId){
        this.finishedProjectIds.remove(projectId);
    }



}
