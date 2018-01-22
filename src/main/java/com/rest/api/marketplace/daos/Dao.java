package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

public interface Dao<T extends MarketplaceResource> {

    void store(LookupKey key, T object);
    T load(LookupKey key);
    List<T> list(LookupKey key);
    void delete(LookupKey key);
}
