package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Objects.isNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seller extends User{

    public static final String REST_RESOURCE_NAME = "sellers";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @JsonProperty("join_date")
    private Date joinDate;
    @JsonProperty("active_project_ids")
    private ArrayList<String> activeProjectIds;
    @JsonProperty("finished_project_ids")
    private ArrayList<String> finishedProjectIds;
    //To be future feature
    @JsonProperty("total_spent")
    private Long totalSpent;
    //To be future feature
    @JsonProperty("avg_paying_rate")
    private Float avgPayingRate;

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public ArrayList<String> getActiveProjectIds() {
        if (isNull(activeProjectIds)){
            activeProjectIds = new ArrayList<>();
        }
        return activeProjectIds;
    }

    public void setActiveProjectIds(ArrayList<String> activeProjectIds) {
        this.activeProjectIds = activeProjectIds;
    }

    public ArrayList<String> getFinishedProjectIds() {
        if (isNull(finishedProjectIds)){
            finishedProjectIds = new ArrayList<>();
        }
        return finishedProjectIds;
    }

    public void setFinishedProjectIds(ArrayList<String> finishedProjectIds) {
        this.finishedProjectIds = finishedProjectIds;
    }

    public Long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Long totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Float getAvgPayingRate() {
        return avgPayingRate;
    }

    public void setAvgPayingRate(Float avgHourlyRate) {
        this.avgPayingRate = avgHourlyRate;
    }

    public void addActiveProjectIds(String projectId){
        if (!getActiveProjectIds().contains(projectId)) {
            this.activeProjectIds.add(projectId);
        }
    }

    public void removeActiveProjectIdss(String projectId){
        this.activeProjectIds.remove(projectId);
    }

    public void addFinishedProjectIds(String projectId){
        if (!getFinishedProjectIds().contains(projectId)) {
            this.finishedProjectIds.add(projectId);
        }
    }

    public void removeFinishedProjectIds(String projectId) {
        this.finishedProjectIds.remove(projectId);
    }

}
