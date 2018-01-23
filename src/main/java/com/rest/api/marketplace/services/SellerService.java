package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDaoObj;

    public ResponseEntity<String> createSeller(Seller sellerObj) {
        if (isNull(sellerObj) && isNull(sellerObj.getId())){
            return new ResponseEntity("Incorrect data. seller with invalid id", HttpStatus.BAD_REQUEST);
        }
        sellerDaoObj.create(sellerObj);
        return ResponseEntity.ok("Seller Sucessfully created");
    }

    public ResponseEntity<Seller> getSeller(String id) {
        Seller sellerObj = sellerDaoObj.get(id);
        if (isNull(sellerObj)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(sellerObj, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> editSeller(String id, Seller updatedSellerObj) {
        Seller sellerObj = sellerDaoObj.get(id);
        if (isNull(sellerObj)) {
            return new ResponseEntity("Seller doesnot exist", HttpStatus.NOT_FOUND);
        }
        else{
            sellerObj.update(updatedSellerObj);
            sellerDaoObj.edit(id, updatedSellerObj);
            return ResponseEntity.ok("Seller Sucessfully updated");
        }
    }

}
