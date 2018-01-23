package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.LookupKey;
import org.springframework.stereotype.Component;

@Component
public class BidDao extends BaseDao<Bid> {
    public static final String DATASTORE = "bid";

    public void create(Bid bidObj){
        LookupKey key = new LookupKey(DATASTORE, bidObj.getId());
        store(key, bidObj);
    }

    public Bid get(String id){
        LookupKey key = new LookupKey(DATASTORE, id);
        return load(key);
    }

    public void edit(String id, Bid bidObj){
        LookupKey key = new LookupKey(DATASTORE, id);
        update(key, bidObj);
    }

}
