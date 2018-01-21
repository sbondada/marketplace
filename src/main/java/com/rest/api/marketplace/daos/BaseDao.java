package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.MarketplaceConfiguration;
import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;
import com.rest.api.marketplace.transports.InMemTransport;
import com.rest.api.marketplace.transports.MarketplaceTransport;
import com.rest.api.marketplace.transports.MongoTransport;

import java.util.List;


public abstract class BaseDao<T extends MarketplaceResource> implements Dao<T>{

    private MarketplaceTransport transport;

    public void init(){
        switch (MarketplaceConfiguration.TRANSPORT_SELECTOR) {
            case MongoTransport.NAME:
                transport = new MongoTransport();
                break;
            case InMemTransport.NAME:
            default:
                transport = new InMemTransport();
                break;
        }
    }

    public void store(LookupKey key, T object){
        transport.store(key, object);
    }

    public T load(LookupKey key){
        return (T) transport.load(key);
    }
    public List<T> list(LookupKey key) {
        return transport.list(key);
    }
    public void delete(LookupKey key){
        transport.delete(key);
    }

}
