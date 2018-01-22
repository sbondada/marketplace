package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.models.Buyer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    private BuyerDao BuyerDaoObj;

    public BuyerService(){
        BuyerDaoObj = new BuyerDao();
    }

    public ResponseEntity<String> createBuyer(Buyer buyerObj) {
        BuyerDaoObj.create(buyerObj);
        return ResponseEntity.ok("Buyer Sucessfully created");
    }

    public Buyer getBuyer(String id) {
        return BuyerDaoObj.get(id);
    }

}
