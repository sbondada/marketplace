package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemTransport<T extends MarketplaceResource> implements MarketplaceTransport<T>{
    public static final String NAME = "classic";

    private HashMap<String, HashMap<String ,T>> repositoryMap;

    public InMemTransport(){
        super();
        this.repositoryMap = new HashMap<>();
    }

    public void store(LookupKey key, T object){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore)) {
            repositoryMap.get(datastore).put(id, object);
        }
        else{
            HashMap<String, T> dataMap = new HashMap<>();
            dataMap.put(id, object);
            repositoryMap.put(datastore, dataMap);
        }
    }

    public T load(LookupKey key){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore) && repositoryMap.get(datastore).containsKey(id)) {
            return repositoryMap.get(datastore).get(id);
        }
        else{
            return null;
        }
    }

    public List<T> list(LookupKey key) {
        String datastore = key.getDatastore();
        if (repositoryMap.containsKey(datastore)) {
            return new ArrayList<>(repositoryMap.get(datastore).values());
        }
        else{
            return new ArrayList<>();
        }
    }

    public void delete(LookupKey key){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore) && repositoryMap.get(datastore).containsKey(id)) {
            repositoryMap.get(datastore).remove(id);
        }
    }
}
