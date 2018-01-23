package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BaseDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.models.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class BuyerService {
    @Autowired
    private BuyerDao buyerDaoObj;

    public ResponseEntity<String> createBuyer(Buyer buyerObj) {
        if (isNull(buyerObj) && isNull(buyerObj.getId())){
            return new ResponseEntity("Incorrect data. buyer with invalid id", HttpStatus.BAD_REQUEST);
        }
        buyerObj.setJoinDate(new Date());
        buyerDaoObj.create(buyerObj);
        return ResponseEntity.ok("Buyer Sucessfully created");
    }

    public ResponseEntity<Buyer> getBuyer(String id) {
        Buyer buyerObj = buyerDaoObj.get(id);
        if (isNull(buyerObj)){
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(buyerObj, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> editBuyer(String id, Buyer updatedBuyerObj) {
        Buyer buyerObj = buyerDaoObj.get(id);
        if (isNull(buyerObj)) {
            return new ResponseEntity("Buyer doesnot exist", HttpStatus.NOT_FOUND);
        }
        else{
            buyerObj.update(updatedBuyerObj);
            buyerDaoObj.edit(id, buyerObj);
            return ResponseEntity.ok("Buyer Sucessfully updated");
        }
    }

}
