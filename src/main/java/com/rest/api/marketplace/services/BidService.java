package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BidDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.models.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class BidService {
    private BuyerDao buyerDaoObj;
    private BidDao bidDaoObj;
    private ProjectDao projectDaoObj;

    public BidService(){
       buyerDaoObj = new BuyerDao();
       bidDaoObj = new BidDao();
       projectDaoObj = new ProjectDao();
    }

   public ResponseEntity<String> createBid(String buyerId, Bid bidObj) {
       Buyer buyerObj = buyerDaoObj.get(buyerId);
       if (!buyerId.equals(bidObj.getBuyerId())){
           return new ResponseEntity("Incorrect data. bid doesnt seem to associate with the buyer", HttpStatus.BAD_REQUEST);
       }
       if (!isNull(bidDaoObj.get(bidObj.getId()))){
           return new ResponseEntity("Incorrect data. bid with same id exists", HttpStatus.BAD_REQUEST);
       }
        Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
        if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)){
           return new ResponseEntity("Bid closed on the project or project not active. so Cannot edit the bids", HttpStatus.UNAUTHORIZED);
        }
       if (!isNull(buyerObj)){
           bidDaoObj.create(bidObj);
           buyerObj.addBid(bidObj.getId());
           buyerDaoObj.edit(buyerId, buyerObj);
           return new ResponseEntity("Bid Succesfully created", HttpStatus.CREATED);
       }
       else{
           return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
       }
   }

    public ResponseEntity<Bid> getBid(String buyerId, String bidId) {
        Buyer buyerObj = buyerDaoObj.get(buyerId);
        if (!isNull(buyerObj) && buyerObj.getSubmittedBids().contains(bidId)){
            Bid bidObj = bidDaoObj.get(bidId);
            return new ResponseEntity(bidObj, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editBid(String buyerId, String bidId, Bid updatedBidObj) {
        Buyer buyerObj = buyerDaoObj.get(buyerId);
        if (!buyerId.equals(updatedBidObj.getBuyerId())){
           return new ResponseEntity("Incorrect data. bid doesnt seem to associate with the buyer", HttpStatus.UNAUTHORIZED);
        }
        Bid bidObj = bidDaoObj.get(bidId);
        if (isNull(bidObj)) {
            return new ResponseEntity("bid doesnot exist", HttpStatus.NOT_FOUND);
        }
        Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
        if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)){
           return new ResponseEntity("Bid closed on the project or project not active. so Cannot edit the bids", HttpStatus.UNAUTHORIZED);
        }
        if (!isNull(buyerObj) && buyerObj.getSubmittedBids().contains(bidId)){
            bidObj.update(updatedBidObj);
            bidDaoObj.edit(bidId, bidObj);
            return new ResponseEntity("Bid Updated Succesfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
        }

    }

}
