package com.rest.api.marketplace.transports;

public class DatastoreDoesnotExistException extends Exception{
    public DatastoreDoesnotExistException(String message){
        super(message);
    }
}
