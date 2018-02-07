package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class SellerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerService.class);
    @Autowired
    private SellerDao sellerDaoObj;

    public ResponseEntity<String> createSeller(Seller sellerObj) {
        String responseObject;
        if (isNull(sellerObj) || isNull(sellerObj.getId())){
            responseObject = "Incorrect data. seller with invalid id";
            LOGGER.debug(responseObject);
            return new ResponseEntity(responseObject, HttpStatus.BAD_REQUEST);
        }
        sellerObj.setJoinDate(new Date());
        sellerDaoObj.create(sellerObj);
        return ResponseEntity.ok("Seller Sucessfully created");
    }

    public ResponseEntity<Seller> getSeller(String id) {
        try{
            Seller sellerObj = sellerDaoObj.get(id);
            return new ResponseEntity(sellerObj, HttpStatus.OK);
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editSeller(String id, Seller updatedSellerObj) {
        try {
            String responseMessage;
            Seller sellerObj = sellerDaoObj.get(id);
            if (isNull(updatedSellerObj)) {
                responseMessage = "invalid data, updated seller object is null";
                LOGGER.debug(responseMessage);
                return new ResponseEntity(responseMessage, HttpStatus.NOT_FOUND);
            } else {
                sellerObj.update(updatedSellerObj);
                sellerDaoObj.edit(id, updatedSellerObj);
                return ResponseEntity.ok("Seller Sucessfully updated");
            }
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
