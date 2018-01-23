package com.rest.api.marketplace.daos;

import com.rest.api.marketplace.models.LookupKey;
import com.rest.api.marketplace.models.Seller;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SellerDao extends BaseDao<Seller> {

    public static final String DATASTORE = "buyer";

    public List<Seller> getList(){
        LookupKey key = new LookupKey(DATASTORE, null);
        return list(key);
    }

    public Seller get(String id){
        LookupKey key = new LookupKey(DATASTORE, id);
        return load(key);
    }

    public void create(Seller sellerObj){
        LookupKey key = new LookupKey(DATASTORE, sellerObj.getId());
        store(key, sellerObj);
    }

    public void edit(String sellerId, Seller sellerObj){
        LookupKey key = new LookupKey(DATASTORE, sellerId);
        update(key, sellerObj);
    }
}
