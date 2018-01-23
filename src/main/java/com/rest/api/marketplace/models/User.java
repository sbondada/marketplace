package com.rest.api.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class User implements MarketplaceResource{

    private String id;
    private String name;
    private String company;
    @JsonProperty("contact_details")
    private Contact contactDetails;
//    //To be a future feature
//    private ArrayList<Review> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        company = company;
    }

    public Contact getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Contact contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    //only updating the fields which are updatable
    public void update(User updatedUserObj){
        setContactDetails(updatedUserObj.getContactDetails());
    }

//    public ArrayList<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(ArrayList<Review> reviews) {
//        this.reviews = reviews;
//    }
}
