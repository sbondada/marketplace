package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;

import java.util.List;

public interface Dao<T extends MarketplaceResource> {

    void store(LookupKey key, T object);
    T load(LookupKey key) throws DatastoreDoesnotExistException, KeyDoesnotExistException;
    List<T> list(LookupKey key) throws DatastoreDoesnotExistException;
    void delete(LookupKey key);
}
