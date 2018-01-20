package com.rest.api.marketplace.models;

import java.util.Date;
import java.util.UUID;

public class Review implements MarketplaceResource{

    private String id;
    private String message;
    private Date publishedDate;
    private User publisher;

    public Review(String message, User publisher) {
       this.id = UUID.randomUUID().toString();
       this.message = message;
       this.publishedDate =  new Date();
       this.publisher =  publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

}
