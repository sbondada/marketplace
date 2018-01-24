package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BidDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class BidService {
    @Autowired
    private BuyerDao buyerDaoObj;
    @Autowired
    private BidDao bidDaoObj;
    @Autowired
    private ProjectDao projectDaoObj;

   public ResponseEntity<String> createBid(String buyerId, Bid bidObj) {
       Buyer buyerObj = buyerDaoObj.get(buyerId);
       if (!buyerId.equals(bidObj.getBuyerId())){
           return new ResponseEntity("Incorrect data. bid doesnt seem to associate with the buyer", HttpStatus.BAD_REQUEST);
       }
       if (!isNull(bidDaoObj.get(bidObj.getId()))){
           return new ResponseEntity("Incorrect data. bid with same id exists", HttpStatus.BAD_REQUEST);
       }
       Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
       if (isNull(projectObj)){
           return new ResponseEntity("Incorrect data. No valid associated project", HttpStatus.BAD_REQUEST);
       }
       if (projectObj.getBidEndDate().before(new Date())){
           projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
           projectDaoObj.create(projectObj);
       }
       if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)){
           return new ResponseEntity("Bid closed on the project or project not active. so Cannot edit the bids", HttpStatus.UNAUTHORIZED);
       }
       if (isNull(bidObj) || isNull(bidObj.getId()) || isNull(bidObj.getBidRate()) ) {
           return new ResponseEntity("Incorrect data. invalid bid object. missing id or bid rate", HttpStatus.BAD_REQUEST);
       }
       if (!isNull(buyerObj)){
           bidObj.setExpiryDate(projectObj.getBidEndDate());
           bidDaoObj.create(bidObj);
           if (projectObj.adjustBidRate(bidObj.getBidRate(), bidObj.getId())){
               projectDaoObj.create(projectObj);
           }
           buyerObj.addBidId(bidObj.getId());
           buyerDaoObj.edit(buyerId, buyerObj);
           return new ResponseEntity("Bid Successfully created", HttpStatus.CREATED);
       }
       else{
           return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
       }
   }

    public ResponseEntity<Bid> getBid(String buyerId, String bidId) {
        Buyer buyerObj = buyerDaoObj.get(buyerId);
        if (!isNull(buyerObj) && buyerObj.getSubmittedBidIds().contains(bidId)){
            Bid bidObj = bidDaoObj.get(bidId);
            Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
            if (projectObj.getBidEndDate().before(new Date())){
                projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                projectDaoObj.create(projectObj);
            }
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
        if (isNull(projectObj)){
            return new ResponseEntity("Incorrect data. No valid associated project", HttpStatus.BAD_REQUEST);
        }
        if (projectObj.getBidEndDate().before(new Date())){
            projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
            projectDaoObj.create(projectObj);
        }
        if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)){
           return new ResponseEntity("Bid closed on the project or project not active. so Cannot edit the bids", HttpStatus.UNAUTHORIZED);
        }
        if (isNull(bidObj) || isNull(bidObj.getId()) || isNull(bidObj.getBidRate()) ) {
            return new ResponseEntity("Incorrect data. invalid bid object. missing id or bid rate", HttpStatus.BAD_REQUEST);
        }
        if (!isNull(buyerObj) && buyerObj.getSubmittedBidIds().contains(bidId)){
            bidObj.update(updatedBidObj);
            bidDaoObj.edit(bidId, bidObj);
            if (projectObj.adjustBidRate(bidObj.getBidRate(), bidObj.getId())){
                projectDaoObj.create(projectObj);
            }
           return new ResponseEntity("Bid Updated Successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Bid Updation failed, Buyer not found", HttpStatus.NOT_FOUND);
        }

    }

}
