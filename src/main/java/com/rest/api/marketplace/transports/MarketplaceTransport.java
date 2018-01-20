package com.rest.api.marketplace.transports;

import com.rest.api.marketplace.models.MarketplaceResource;

import java.util.List;

public interface MarketplaceTransport<T extends MarketplaceResource> {

    void store(String id, T object);
    T load(String id);
    List<T> list();
    void delete(String id);

}
