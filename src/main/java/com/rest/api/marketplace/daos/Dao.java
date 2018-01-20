package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

public interface Dao<T extends MarketplaceResource> {

    void store(String id, T object);
    T load(String id);
    List<T> list();
    void delete(String id);
}
