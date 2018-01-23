package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.MarketplaceConfiguration;
import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;
import com.rest.api.marketplace.transports.InMemTransport;
import com.rest.api.marketplace.transports.MarketplaceTransport;
import com.rest.api.marketplace.transports.MongoTransport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Objects.isNull;


public abstract class BaseDao<T extends MarketplaceResource> implements Dao<T>{

    private MarketplaceTransport transport;
    @Autowired
    private InMemTransport inMemTransport;
    @Autowired
    private MongoTransport mongoTransport;

    public void init(){
        switch (MarketplaceConfiguration.TRANSPORT_SELECTOR) {
            case MongoTransport.NAME:
                transport = mongoTransport;
                break;
            case InMemTransport.NAME:
            default:
                transport = inMemTransport;
                break;
        }
    }

    public void store(LookupKey key, T object){
        if(isNull(transport)){
            init();
        }
        transport.store(key, object);
    }
    public void update(LookupKey key, T object){
        if(isNull(transport)){
            init();
        }
        transport.update(key, object);
    }
    public T load(LookupKey key){
        if(isNull(transport)){
            init();
        }
        return (T) transport.load(key);
    }
    public List<T> list(LookupKey key) {
        if(isNull(transport)){
            init();
        }
        return transport.list(key);
    }
    public void delete(LookupKey key){
        if(isNull(transport)){
            init();
        }
        transport.delete(key);
    }

}
