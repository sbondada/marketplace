package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Objects.isNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements MarketplaceResource{

    public static final String REST_RESOURCE_NAME = "projects";
    public static final String BID_STATUS_OPEN = "open";
    public static final String BID_STATUS_CLOSE = "close";
    public static final String PROJECT_STATUS_ACTIVE = "active";
    public static final String PROJECT_STATUS_FINISHED = "finished";

    private String id;
    private String title;
    private String description;
    private ArrayList<Tag> tags;
    @JsonProperty("est_DOD")
    private Date estDeliveryTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm")
    @JsonProperty("bid_end_date")
    private Date bidEndDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @JsonProperty("creation_date")
    private Date creationDate;
    @JsonProperty("seller_id")
    private String sellerId;
    @JsonProperty("bid_status")
    private String bidStatus;
    @JsonProperty("project_status")
    private String projectStatus;
    @JsonProperty("lowest_bid_rate")
    private Float lowestBidRate;
    @JsonProperty("lowest_bidder")
    private String lowestBidder;


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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public boolean adjustBidRate(Float bidRate, String bidId){
       if(isNull(getLowestBidRate()) || getLowestBidRate() > bidRate){
           setLowestBidRate(bidRate);
           setLowestBidder(bidId);
           return true;
       }
       return false;
    }

    public Float getLowestBidRate() {
        return lowestBidRate;
    }

    public void setLowestBidRate(Float lowestBidRate) {
        this.lowestBidRate = lowestBidRate;
    }

    public String getLowestBidder() {
        return lowestBidder;
    }

    public void setLowestBidder(String lowestBidder) {
        this.lowestBidder = lowestBidder;
    }

    //only updating the fields which are updatable
    public void update(Project updatedProjectObj){
        this.title = updatedProjectObj.title;
        this.description = updatedProjectObj.description;
        this.tags = updatedProjectObj.tags;
        this.estDeliveryTime = updatedProjectObj.estDeliveryTime;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return  super.clone();
    }
}
