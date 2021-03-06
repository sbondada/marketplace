package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

public interface MarketplaceTransport<T extends MarketplaceResource> {

    void store(LookupKey key, T object);
    void update(LookupKey key, T object);
    T load(LookupKey key) throws DatastoreDoesnotExistException,KeyDoesnotExistException;
    List<T> list(LookupKey key) throws DatastoreDoesnotExistException;
    void delete(LookupKey key);

}
