package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Project;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/resources")
public class ProjectsController {


    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME, method = RequestMethod.GET)
    public List<Project> getProjectList(){

    }

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME + "/{project_id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("project_id") int id){

    }

}
