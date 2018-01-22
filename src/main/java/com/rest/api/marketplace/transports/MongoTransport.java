package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

// The class needs to be implemented for the persistence storage
public class MongoTransport<T extends MarketplaceResource> implements  MarketplaceTransport<T>{

    public static final String NAME = "persistent";

    public void update(LookupKey key, T object){
        return;
    }
    public void store(LookupKey key, T object) {
        return;
    }
    public T load(LookupKey key){
        return null;
    }
    public List<T> list(LookupKey key) {
        return null;
    }
    public void delete(LookupKey key){
        return;
    }

}
