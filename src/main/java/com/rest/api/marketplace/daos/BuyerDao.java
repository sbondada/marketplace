package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyerDao extends BaseDao<Buyer> {
    public static final String DATASTORE = "buyer";

    public List<Buyer> getList() throws DatastoreDoesnotExistException{
        LookupKey key = new LookupKey(DATASTORE, null);
        return list(key);
    }

    public Buyer get(String id) throws DatastoreDoesnotExistException, KeyDoesnotExistException{
        LookupKey key = new LookupKey(DATASTORE, id);
        return load(key);
    }

    public void create(Buyer buyerObj){
        LookupKey key = new LookupKey(DATASTORE, buyerObj.getId());
        store(key, buyerObj);
    }

    public void edit(String buyerId, Buyer buyerObj){
        LookupKey key = new LookupKey(DATASTORE, buyerId);
        update(key, buyerObj);
    }
}
