package com.rest.api.marketplace.services;

import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    private ProjectDao projectDao;

    public ProjectService(){
        projectDao = new ProjectDao();
    }

    public List<Project> getProjectList(){
        return projectDao.getList();
    }

    public ResponseEntity<Project> getProject(String id){
        Project project = projectDao.get(id);
        if(isNull(project)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(project, HttpStatus.OK);

    }

    public ResponseEntity<String> createProject(Project projectObj) {
        projectDao.create(projectObj);
        return ResponseEntity.ok("Project Sucessfully created");
    }
}
