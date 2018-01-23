package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.*;
import com.rits.cloning.Cloner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemTransport<T extends MarketplaceResource> implements MarketplaceTransport<T>{
    public static final String NAME = "classic";

    private HashMap<String, HashMap<String ,T>> repositoryMap;
    private Cloner cloner;

    public InMemTransport(){
        super();
        this.repositoryMap = new HashMap<>();
        this.cloner = new Cloner();
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

    public void update(LookupKey key, T object){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore)) {
            repositoryMap.get(datastore).put(id, object);
        }
    }

    public T load(LookupKey key){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore) && repositoryMap.get(datastore).containsKey(id)) {
            return cloner.deepClone(repositoryMap.get(datastore).get(id));
        }
        else{
            return null;
        }
    }

    public List<T> list(LookupKey key) {
        String datastore = key.getDatastore();
        if (repositoryMap.containsKey(datastore)) {
            return new ArrayList<>(cloner.deepClone(repositoryMap.get(datastore).values()));
        }
        else{
            return null;
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
