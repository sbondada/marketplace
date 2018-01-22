package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seller extends User{

    public static final String REST_RESOURCE_NAME = "sellers";

    private Date joinDate;
    @JsonProperty("active_projects")
    private ArrayList<Project> activeProjects;
    @JsonProperty("finished_projects")
    private ArrayList<Project> finshedProjects;
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

    public Float getAvgPayingRate() {
        return avgPayingRate;
    }

    public void setAvgPayingRate(Float avgHourlyRate) {
        this.avgPayingRate = avgHourlyRate;
    }

}
