package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BaseDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class BuyerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerService.class);
    @Autowired
    private BuyerDao buyerDaoObj;

    public ResponseEntity<String> createBuyer(Buyer buyerObj) {
        String responseMesssage;
        if (isNull(buyerObj) && isNull(buyerObj.getId())){
            responseMesssage = "Incorrect data. Undefined buyer object or buyer with invalid id";
            LOGGER.debug(responseMesssage);
            return new ResponseEntity(responseMesssage, HttpStatus.BAD_REQUEST);
        }
        buyerObj.setJoinDate(new Date());
        buyerDaoObj.create(buyerObj);
        return ResponseEntity.ok("Buyer Sucessfully created");
    }

    public ResponseEntity<Buyer> getBuyer(String id) {
        try {
            Buyer buyerObj = buyerDaoObj.get(id);
            return new ResponseEntity(buyerObj, HttpStatus.OK);
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editBuyer(String id, Buyer updatedBuyerObj) {
        try {
            String responseMessage;
            Buyer buyerObj = buyerDaoObj.get(id);
            if (isNull(updatedBuyerObj)) {
                responseMessage = "invalid data, updated buyer object is null";
                LOGGER.debug(responseMessage);
                return new ResponseEntity(responseMessage, HttpStatus.NOT_FOUND);
            } else {
                buyerObj.update(updatedBuyerObj);
                buyerDaoObj.edit(id, buyerObj);
                return ResponseEntity.ok("Buyer Sucessfully updated");
            }
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
