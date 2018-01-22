package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Seller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class SellerService {
    private SellerDao sellerDaoObj;

    public SellerService(){
        sellerDaoObj = new SellerDao();
    }

    public ResponseEntity<String> createSeller(Seller sellerObj) {
        sellerDaoObj.create(sellerObj);
        return ResponseEntity.ok("Seller Sucessfully created");
    }

    public Seller getSeller(String id) {
        return sellerDaoObj.get(id);
    }

    public ResponseEntity<String> editSeller(String id, Seller sellerObj) {
        if (!isNull(sellerDaoObj.get(id))) {
            return new ResponseEntity("Seller doesnot exist", HttpStatus.NOT_FOUND);
        }
        else{
            sellerDaoObj.edit(id, sellerObj);
            return ResponseEntity.ok("Seller Sucessfully updated");
        }
    }

}
