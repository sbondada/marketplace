package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seller extends User{

    public static final String REST_RESOURCE_NAME = "sellers";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @JsonProperty("join_date")
    private Date joinDate;
    @JsonProperty("active_projects")
    private ArrayList<String> activeProjects;
    @JsonProperty("finished_projects")
    private ArrayList<String> finishedProjects;
    @JsonProperty("total_spent")
    private long totalSpent;
    @JsonProperty("avg_paying_rate")
    private Float avgPayingRate;

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    public void setFinishedProjects(ArrayList<String> finshedProjects) {
        this.finishedProjects = finishedProjects;
    }

    public long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(long totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Float getAvgPayingRate() {
        return avgPayingRate;
    }

    public void setAvgPayingRate(Float avgHourlyRate) {
        this.avgPayingRate = avgHourlyRate;
    }

    public void addActiveProjects(String projectId){
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

    public void removeFinishedProjects(String projectId) {
        this.finishedProjects.remove(projectId);
    }
}
