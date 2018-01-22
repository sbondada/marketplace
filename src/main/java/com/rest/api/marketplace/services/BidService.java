package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BidDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class BidService {
    private BuyerDao buyerDaoObj;
    private BidDao bidDaoObj;

    public BidService(){
       buyerDaoObj = new BuyerDao();
       bidDaoObj = new BidDao();
    }

   public ResponseEntity<String> createBid(String buyerId, Bid bidObj) {
       Buyer buyerObj = buyerDaoObj.get(buyerId);
       if (!isNull(buyerObj) && !buyerObj.getSubmittedBids().contains(bidObj.getId())){
           bidObj.setBuyerId(buyerId);
           bidDaoObj.create(bidObj);
           buyerObj.addBid(bidObj.getId());
           buyerDaoObj.update(buyerId, buyerObj);
           return new ResponseEntity("Bid Succesfully created", HttpStatus.CREATED);
       }
       else{
           return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
       }
   }

    public ResponseEntity<Bid> getBid(String buyerId, String bidId) {
        Buyer buyerObj = buyerDaoObj.get(buyerId);
        if (!isNull(buyerObj) && buyerObj.getSubmittedBids().contains(bidId)){
            Bid bid = bidDaoObj.get(bidId);
            return new ResponseEntity(bid, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
