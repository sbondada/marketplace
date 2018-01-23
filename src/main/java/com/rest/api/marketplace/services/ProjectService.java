package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.models.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDaoObj;
    @Autowired
    private SellerDao sellerDaoObj;

    public List<Project> getFilteredProjectList(String bidStatus){
        List<Project> projectList = projectDaoObj.getList();
        for( Project projectObj: projectList){
            if (projectObj.getBidEndDate().before(new Date())){
                projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
                projectDaoObj.create(projectObj);
            }
            if (!projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)){
                // only showing the lowest bid rate and the rater if the bid is closed.
                projectObj.setLowestBidRate(null);
                projectObj.setLowestBidder(null);
            }
           if (!isNull(bidStatus) && !projectObj.getBidStatus().equals(bidStatus)){
               projectList.remove(projectObj);
           }
        }
        return projectList;
    }

    public ResponseEntity<Project> getProject(String id){
        Project projectObj = projectDaoObj.get(id);
        if(isNull(projectObj)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)){
            return new ResponseEntity(projectObj, HttpStatus.OK);
        }
        if (projectObj.getBidEndDate().before(new Date())){
            projectObj.setBidStatus(Project.BID_STATUS_CLOSE);
            projectDaoObj.create(projectObj);
        }
        if (!projectObj.getBidStatus().equals(Project.BID_STATUS_CLOSE)){
            // only showing the lowest bid rate and the rater if the bid is closed.
            projectObj.setLowestBidRate(null);
            projectObj.setLowestBidder(null);
        }
        return new ResponseEntity(projectObj, HttpStatus.OK);

    }

    public ResponseEntity<String> createProject(String sellerId, Project projectObj) {
        Seller sellerObj = sellerDaoObj.get(sellerId);
        if (isNull(sellerObj)){
            return new ResponseEntity("Incorrect data, Seller not found", HttpStatus.BAD_REQUEST);
        }
        if ( !sellerId.equals(projectObj.getSellerId())){
            return new ResponseEntity("Incorrect data. project doesnt seem to associate with the seller", HttpStatus.BAD_REQUEST);
        }
        if (isNull(projectObj) && isNull(projectObj.getId()) && !isNull(projectDaoObj.get(projectObj.getId()))){
            return new ResponseEntity("Incorrect data. project with invalid id", HttpStatus.BAD_REQUEST);
        }
        if (isNull(projectObj.getBidEndDate()) ){
            return new ResponseEntity("Incorrect data. provide bid end date", HttpStatus.BAD_REQUEST);
        }
        projectObj.setCreationDate(new Date());
        projectDaoObj.create(projectObj);
        if(isNull(projectObj.getProjectStatus())){
            projectObj.setProjectStatus(Project.PROJECT_STATUS_ACTIVE);
        }
        sellerObj.addActiveProjects(projectObj.getId());
        sellerDaoObj.edit(sellerId, sellerObj);
        return new ResponseEntity("Project Succesfully created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> editProject(String sellerId, String projectId, Project updatedProjectObj){
        Seller sellerObj = sellerDaoObj.get(sellerId);
        if (!sellerId.equals(updatedProjectObj.getSellerId())){
            return new ResponseEntity("Incorrect data. project doesnt seem to associate with the seller", HttpStatus.UNAUTHORIZED);
        }
        Project projectObj = projectDaoObj.get(projectId);
        if (isNull(projectObj)) {
            return new ResponseEntity("Project doesnot exist", HttpStatus.NOT_FOUND);
        }
        if (!isNull(projectObj) && sellerObj.getActiveProjects().contains(projectId)){
            projectObj.update(updatedProjectObj);
            projectDaoObj.edit(projectId, projectObj);
            return new ResponseEntity("Project Updated Succesfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Project creation failed, Seller not found", HttpStatus.NOT_FOUND);
        }

    }
}
