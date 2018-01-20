package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.MarketplaceResource;
import com.rest.api.marketplace.transports.MarketplaceTransport;


public abstract class BaseDao<T extends MarketplaceResource> implements Dao<T>{

    private MarketplaceTransport ClassicTransport;
    private MarketplaceTransport PersistenceTransport;




}
