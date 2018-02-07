package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.BidDao;
import com.rest.api.marketplace.daos.BuyerDao;
import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.models.Project;
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
public class BidService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BidService.class);
    @Autowired
    private BuyerDao buyerDaoObj;
    @Autowired
    private BidDao bidDaoObj;
    @Autowired
    private ProjectDao projectDaoObj;

   public ResponseEntity<String> createBid(String buyerId, Bid bidObj) {
       try {
           String responseString;
           if (isNull(bidObj) || isNull(bidObj.getBidRate())) {
               responseString = "Incorrect data. invalid bid object. missing id or bid rate";
               LOGGER.debug(responseString);
               return new ResponseEntity(responseString, HttpStatus.BAD_REQUEST);
           }

           Buyer buyerObj = buyerDaoObj.get(buyerId);
           if (!buyerId.equals(bidObj.getBuyerId())) {
               responseString = "Incorrect data. bid(" + bidObj.getId() + ") doesnt seem to associate with the buyer (" + buyerId + ")";
               LOGGER.debug(responseString);
               return new ResponseEntity(responseString, HttpStatus.BAD_REQUEST);
           }

           Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
           if (projectObj.getBidEndDate().before(new Date())) {
               LOGGER.debug("Bid status is closed, So cant create bid Object with id " + bidObj.getId());
               projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
               projectDaoObj.create(projectObj);
           }
           if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)) {
               responseString = "Bid closed on the project or project not active. so Cannot edit the bids";
               LOGGER.debug(responseString);
               return new ResponseEntity(responseString, HttpStatus.UNAUTHORIZED);
           }

           bidObj.setExpiryDate(projectObj.getBidEndDate());
           bidDaoObj.create(bidObj);
           if (projectObj.adjustBidRate(bidObj.getBidRate(), bidObj.getId())) {
               LOGGER.debug("Updating Project " + projectObj.getId() + "with the latest bid (" + bidObj.getId() + ")");
               projectDaoObj.create(projectObj);
           }
           buyerObj.addBidId(bidObj.getId());
           buyerDaoObj.edit(buyerId, buyerObj);
           return new ResponseEntity("Bid Successfully created", HttpStatus.CREATED);
       }
       catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }

    public ResponseEntity<Bid> getBid(String buyerId, String bidId) {
        try {
            Buyer buyerObj = buyerDaoObj.get(buyerId);
            if (buyerObj.getSubmittedBidIds().contains(bidId)) {
                Bid bidObj = bidDaoObj.get(bidId);
                Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
                if (projectObj.getBidEndDate().before(new Date())) {
                    LOGGER.debug("Bid status is closed, So cant update bid Object with id " + bidObj.getId());
                    projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                    projectDaoObj.create(projectObj);
                }
                return new ResponseEntity(bidObj, HttpStatus.OK);
            } else {
                LOGGER.debug("Could not get the Bid with bidid '"+ bidId + "'");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editBid(String buyerId, String bidId, Bid updatedBidObj) {
        try {
            String responseString;
            Bid bidObj = bidDaoObj.get(bidId);
            if (isNull(updatedBidObj) || isNull(updatedBidObj.getId()) || isNull(updatedBidObj.getBidRate())) {
                responseString = "Incorrect data. invalid updated bid object. missing id or bid rate";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.BAD_REQUEST);
            }

            Buyer buyerObj = buyerDaoObj.get(buyerId);
            if (!buyerId.equals(updatedBidObj.getBuyerId())) {
                responseString = "Incorrect data. bid(" + bidObj.getId() + ")doesnt seem to associate with the buyer (" + buyerId + ")";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.UNAUTHORIZED);
            }

            Project projectObj = projectDaoObj.get(bidObj.getAssociatedProjectId());
            if (projectObj.getBidEndDate().before(new Date())) {
                LOGGER.debug("Bid status is closed, So cant update bid Object with id " + bidObj.getId());
                projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                projectDaoObj.edit(projectObj.getId(), projectObj);
            }
            if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE) || projectObj.getProjectStatus().equals(Project.PROJECT_STATUS_FINISHED)) {
                responseString = "Bid closed on the project (" + projectObj.getId() + ") or project not active. so Cannot edit the bids";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.UNAUTHORIZED);
            }

            if (buyerObj.getSubmittedBidIds().contains(bidId)) {
                bidObj.update(updatedBidObj);
                bidDaoObj.edit(bidId, bidObj);
                if (projectObj.adjustBidRate(bidObj.getBidRate(), bidObj.getId())) {
                    LOGGER.debug("Updating Project " + projectObj.getId() + "with the latest bid (" + bidObj.getId() + ")");
                    projectDaoObj.create(projectObj);
                }
                return new ResponseEntity("Bid Updated Successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Bid Updation failed, Buyer not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
