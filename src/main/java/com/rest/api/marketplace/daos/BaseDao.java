package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.MarketplaceConfiguration;
import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.MarketplaceResource;
import com.rest.api.marketplace.transports.InMemTransport;
import com.rest.api.marketplace.transports.MarketplaceTransport;
import com.rest.api.marketplace.transports.MongoTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


public abstract class BaseDao<T extends MarketplaceResource> implements Dao<T>{

    @Autowired
    @Qualifier("inMemTransport")
    private MarketplaceTransport classicTransport;
    @Autowired
    @Qualifier("mongoTransport")
    private MarketplaceTransport persistentTransport;
    @Autowired
    @Qualifier("daoRepository")
    private DaoRepository daoRepository;

    public MarketplaceTransport transport;

    public DaoRepository getDaoRepository() {
        return daoRepository;
    }

    public void init(){
       switch (MarketplaceConfiguration.TRANSPORT_SELECTOR) {
           case MongoTransport.NAME:
               transport = persistentTransport;
               break;
           case InMemTransport.NAME:
           default:
               transport = classicTransport;
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
