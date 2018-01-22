package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.models.Buyer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class BuyerService {

    private BuyerDao buyerDaoObj;

    public BuyerService(){
        buyerDaoObj = new BuyerDao();
    }

    public ResponseEntity<String> createBuyer(Buyer buyerObj) {
        buyerDaoObj.create(buyerObj);
        return ResponseEntity.ok("Buyer Sucessfully created");
    }

    public Buyer getBuyer(String id) {
        return buyerDaoObj.get(id);
    }

    public ResponseEntity<String> editBuyer(String id, Buyer buyerObj) {
        if (!isNull(buyerDaoObj.get(id))) {
            return new ResponseEntity("Buyer doesnot exist", HttpStatus.NOT_FOUND);
        }
        else{
            buyerDaoObj.edit(id, buyerObj);
            return ResponseEntity.ok("Buyer Sucessfully updated");
        }
    }

}
