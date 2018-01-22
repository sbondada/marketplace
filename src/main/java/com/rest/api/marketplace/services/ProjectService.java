package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.daos.SellerDao;
import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.models.Seller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    private ProjectDao projectDaoObj;
    private SellerDao sellerDaoObj;

    public ProjectService(){
        projectDaoObj = new ProjectDao();
        sellerDaoObj = new SellerDao();
    }

    public List<Project> getProjectList(){
        return projectDaoObj.getList();
    }

    public ResponseEntity<Project> getProject(String id){
        Project project = projectDaoObj.get(id);
        if(isNull(project)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(project, HttpStatus.OK);

    }

    public ResponseEntity<String> createProject(String sellerId, Project projectObj) {
        Seller sellerObj = sellerDaoObj.get(sellerId);
        if (sellerId!= projectObj.getSellerId()){
            return new ResponseEntity("Incorrect data. project doesnt seem to associate with the seller", HttpStatus.BAD_REQUEST);
        }
        if (!isNull(projectDaoObj.get(projectObj.getId()))){
            return new ResponseEntity("Incorrect data. project with same id exists", HttpStatus.BAD_REQUEST);
        }
        if (!isNull(sellerObj)){
            projectDaoObj.create(projectObj);
            if (projectObj.getProjectStatus()==Project.PROJECT_STATUS_ACTIVE){
                sellerObj.addActiveProjects(projectObj.getId());
            }
            else {
                sellerObj.addFinishedProjects(projectObj.getId());
            }
            sellerDaoObj.edit(sellerId, sellerObj);
            return new ResponseEntity("Bid Succesfully created", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editProject(String sellerId, String projectId, Project updatedProjectObj){
        Seller sellerObj = sellerDaoObj.get(sellerId);
        if (sellerId != updatedProjectObj.getSellerId()){
            return new ResponseEntity("Incorrect data. project doesnt seem to associate with the seller", HttpStatus.UNAUTHORIZED);
        }
        Project projectObj = projectDaoObj.get(projectId);
        if (isNull(projectObj)) {
            return new ResponseEntity("Project doesnot exist", HttpStatus.NOT_FOUND);
        }
        if (!isNull(projectObj) && sellerObj.getActiveProjects().contains(projectId)){
            projectObj.update(updatedProjectObj);
            projectDaoObj.edit(projectId, projectObj);
            return new ResponseEntity("Bid Updated Succesfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Bid creation failed, Buyer not found", HttpStatus.NOT_FOUND);
        }

    }
}
