package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.*;
import com.rits.cloning.Cloner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemTransport<T extends MarketplaceResource> implements MarketplaceTransport<T> {
    public static final String NAME = "classic";

    public static final Logger LOGGER = LoggerFactory.getLogger(InMemTransport.class);

    private HashMap<String, HashMap<String ,T>> repositoryMap;
    private Cloner cloner;

    public InMemTransport(){
        super();
        this.repositoryMap = new HashMap<>();
        this.cloner = new Cloner();
    }

    public void store(LookupKey key, T object) {
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore)) {
            LOGGER.debug("'" + datastore + "' datastore already exist");
            LOGGER.debug("Storing '" + datastore + "' Object with id " + id);
            repositoryMap.get(datastore).put(id, object);
        }
        else{
            LOGGER.debug("Creating a new '" + datastore + "' datastore");
            HashMap<String, T> dataMap = new HashMap<>();
            dataMap.put(id, object);
            repositoryMap.put(datastore, dataMap);
        }
        LOGGER.info("'" + datastore + "' datastore Object with id " + id + " Successfully stored");
    }

    public void update(LookupKey key, T object){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore)) {
            LOGGER.debug("'" + datastore + "' datastore Object with id " + id + " exist");
            LOGGER.debug("Storing '" + datastore + "' datastore Object with id " + id);
            repositoryMap.get(datastore).put(id, object);
            LOGGER.info("'" + datastore + "' datastore Object with id " + id + " updated successfully");
        }
        LOGGER.debug("'" + datastore + "' datastore Object with id " + id + " do not exist");
        LOGGER.info("'" + datastore + "' datastore Object with id " + id + " update failed");
    }

    public T load(LookupKey key) throws DatastoreDoesnotExistException, KeyDoesnotExistException {
        String message;
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore) && repositoryMap.get(datastore).containsKey(id)) {
            LOGGER.debug("'" + datastore + "' datastore Object with id " + id + " exist");
            LOGGER.debug("Getting '" + datastore + "' Object with id " + id);
            T object = repositoryMap.get(datastore).get(id);
            LOGGER.info("'" + datastore + "' datastore Object with id " + id + " Get Successful");
            return cloner.deepClone(object);
        }
        else if (!repositoryMap.containsKey(datastore)){
            message = "Could not find '" + datastore + "' datastore, Store the object before loading them";
            LOGGER.error(message);
            throw new DatastoreDoesnotExistException(message);
        }
        else if (!repositoryMap.get(datastore).containsKey(id)){
            message = "Could not find '" + id + "', key doesnot exist in '" + datastore +"'";
            LOGGER.error(message);
            throw new KeyDoesnotExistException(message);
        }
        else {
            return null;
        }
    }

    public List<T> list(LookupKey key) throws DatastoreDoesnotExistException{
        String message;
        String datastore = key.getDatastore();
        if (repositoryMap.containsKey(datastore)) {
            LOGGER.debug("'" + datastore + "' datastore Object exists");
            LOGGER.debug("Getting '" + datastore + "' datastore Objects");
            Collection<T> values = repositoryMap.get(datastore).values();
            LOGGER.info("'" + datastore + "' Objects Get Successful");
            return new ArrayList<>(cloner.deepClone(values));
        }
        else if (!repositoryMap.containsKey(datastore)){
            message = "Could not find '" + datastore + "' datastore, Store the object before loading them";
            LOGGER.error(message);
            throw new DatastoreDoesnotExistException(message);
        }
        else{
            return null;
        }
    }

    public void delete(LookupKey key){
        String datastore = key.getDatastore();
        String id = key.getId();
        if (repositoryMap.containsKey(datastore) && repositoryMap.get(datastore).containsKey(id)) {
            LOGGER.debug("'" + datastore + "' datastore Object with id " + id + "exist");
            LOGGER.debug("Storing '" + datastore + "' Object with id " + id);
            repositoryMap.get(datastore).remove(id);
            LOGGER.info("'" + datastore + "' datastore Object with id " + id + "removed successfully");
        }
        LOGGER.debug("'" + datastore + "' datastore Object with id " + id + "do not exist");
        LOGGER.info("'" + datastore + "' datastore Object with id " + id + "delete failed");
    }
}
