package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/rest/resources")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME, method = RequestMethod.GET)
    public List<Project> getProjectList(){
       return projectService.getProjectList();
    }

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME + "/{project_id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@PathParam("project_id") String id){
       return projectService.getProject(id);
    }

    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}/" + Project.REST_RESOURCE_NAME,
                    method = RequestMethod.POST)
    public ResponseEntity<String> createProject(@PathParam("seller_id") String selllerId,
                                                @RequestBody Project projectObj) {
      return projectService.createProject(selllerId, projectObj);
    }

    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}/" + Project.REST_RESOURCE_NAME +"/{project_id}",
                    method = RequestMethod.POST)
    public ResponseEntity<String> editProject(@PathParam("seller_id") String sellerId,
                                               @PathParam("project_id") String projectId,
                                               @RequestBody Project projectObj){
        return projectService.editProject(sellerId, projectId, projectObj);
    }



}
