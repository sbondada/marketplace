package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.daos.MarketplaceDaoRepository;
import com.rest.api.marketplace.daos.ProjectDao;
import com.rest.api.marketplace.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/rest/resources")
public class ProjectsController {

    @Autowired
    private MarketplaceDaoRepository daoRepository;

    private ProjectDao daoObject;

    public void init(){
        if (isNull(daoObject)) {
            daoObject = (ProjectDao) daoRepository.getDaoObject(Project.class);
        }
    }

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME, method = RequestMethod.GET)
    public List<Project> getProjectList(){
        init();
        return daoObject.getList();
    }

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME + "/{project_id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable("project_id") String id){
        init();
        return daoObject.get(id);
    }

}
