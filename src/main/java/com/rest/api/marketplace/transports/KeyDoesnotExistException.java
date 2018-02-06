package com.rest.api.marketplace.transports;

public class KeyDoesnotExistException extends Exception{
    public KeyDoesnotExistException(String message) {
        super(message);
    }
}
