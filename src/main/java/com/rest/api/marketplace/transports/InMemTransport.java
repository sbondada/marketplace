package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.*;

import java.util.HashMap;
import java.util.List;

public class InMemTransport<T extends MarketplaceResource> implements MarketplaceTransport<T>{

    private HashMap<String, HashMap<String ,T>> repositoryMap;

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
