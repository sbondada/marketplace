package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.transports.DatastoreDoesnotExistException;
import com.rest.api.marketplace.transports.KeyDoesnotExistException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerService.class);
    @Autowired
    private ProjectDao projectDaoObj;
    @Autowired
    private SellerDao sellerDaoObj;

    public ResponseEntity<List<Project>> getFilteredProjectList(String bidStatus) {
        try {
            List<Project> projectList = projectDaoObj.getList();
            for (Project projectObj : projectList) {
                if (projectObj.getBidEndDate().before(new Date())) {
                    LOGGER.debug("setting bid status is closed for project (" + projectObj.getId() + ")");
                    projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                    projectDaoObj.create(projectObj);
                }
                if (!projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)) {
                    LOGGER.debug("Hiding the bit rates and id untill the bid status is close");
                    // only showing the lowest bid rate and the rater if the bid is closed.
                    projectObj.setLowestBidRate(null);
                    projectObj.setLowestBidder(null);
                }
                if (!isNull(bidStatus) && !projectObj.getBidStatus().equals(bidStatus)) {
                    projectList.remove(projectObj);
                }
            }
            return new ResponseEntity(projectList, HttpStatus.OK);
        } catch (DatastoreDoesnotExistException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Project> getProject(String id){
        try {
            Project projectObj = projectDaoObj.get(id);
            if (projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)) {
                return new ResponseEntity(projectObj, HttpStatus.OK);
            }
            if (projectObj.getBidEndDate().before(new Date())) {
                LOGGER.debug("setting bid status is closed for project (" + projectObj.getId() + ")");
                projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                projectDaoObj.create(projectObj);
            }
            if (!projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)) {
                LOGGER.debug("Hiding the bit rates and id untill the bid status is close");
                // only showing the lowest bid rate and the rater if the bid is closed.
                projectObj.setLowestBidRate(null);
                projectObj.setLowestBidder(null);
            }
            return new ResponseEntity(projectObj, HttpStatus.OK);
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> createProject(String sellerId, Project projectObj) {
        try {
            String responseString;
            Seller sellerObj = sellerDaoObj.get(sellerId);
            if (isNull(projectObj.getBidEndDate())) {
                responseString = "Incorrect data. provide bid end date";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.BAD_REQUEST);
            }
            if (!sellerId.equals(projectObj.getSellerId())) {
                responseString = "Incorrect data. project(" + projectObj.getId() + ") doesnt seem to associate with the seller(" + sellerId + ")";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.UNAUTHORIZED);
            }
            if (isNull(projectObj.getId()) && !isNull(projectDaoObj.get(projectObj.getId()))) {
                responseString = "Incorrect data. project with invalid id (" + projectObj.getId() + ")";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.BAD_REQUEST);
            }

            projectObj.setCreationDate(new Date());
            projectDaoObj.create(projectObj);
            if (isNull(projectObj.getProjectStatus())) {
                projectObj.setProjectStatus(Project.PROJECT_STATUS_ACTIVE);
            }
            sellerObj.addActiveProjectIds(projectObj.getId());
            sellerDaoObj.edit(sellerId, sellerObj);
            return new ResponseEntity("Project Succesfully created", HttpStatus.CREATED);
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editProject(String sellerId, String projectId, Project updatedProjectObj){
        try {
            String responseString;
            Seller sellerObj = sellerDaoObj.get(sellerId);
            if (!sellerId.equals(updatedProjectObj.getSellerId())) {
                responseString = "Incorrect data. project(" + projectId + ") doesnt seem to associate with the seller(" + sellerId + ")";
                LOGGER.debug(responseString);
                return new ResponseEntity(responseString, HttpStatus.UNAUTHORIZED);
            }
            Project projectObj = projectDaoObj.get(projectId);

            if (sellerObj.getActiveProjectIds().contains(projectId)) {
                projectObj.update(updatedProjectObj);
                projectDaoObj.edit(projectId, projectObj);
                return new ResponseEntity("Project Updated Succesfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Project creation failed, Seller not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (DatastoreDoesnotExistException | KeyDoesnotExistException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
