package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

// The class needs to be implemented for the persistence storage
public class MongoTransport<T extends MarketplaceResource> implements  MarketplaceTransport<T>{

    public void store(String id, T object){
        return;
    }
    public T load(String id){
        return null;
    }
    public List<T> list() {
        return null;
    }
    public void delete(String id){
        return;
    }

}
