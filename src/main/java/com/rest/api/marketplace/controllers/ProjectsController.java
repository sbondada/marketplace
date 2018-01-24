package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Project;
import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.services.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;


@RestController
@Api(value = "Project Controller", description = "Lets you CREATE, GET and EDIT the Project")
@RequestMapping("/rest/resources")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME, method = RequestMethod.GET)
    public List<Project> getFilteredProjectList(@RequestParam(value="bid_status", required=false) String bidStatus) {
            return projectService.getFilteredProjectList(bidStatus);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Project Not Found"),
                    @ApiResponse(code = 200, message = "Project Successfully Loaded")
            }
    )
    @RequestMapping(value = "/" + Project.REST_RESOURCE_NAME + "/{project_id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@PathVariable("project_id") String id){
       return projectService.getProject(id);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request, Incorrect Data"),
                    @ApiResponse(code = 201, message = "Project Successfully Created")
            }
    )
    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}/" + Project.REST_RESOURCE_NAME,
                    method = RequestMethod.PUT)
    public ResponseEntity<String> createProject(@PathVariable("seller_id") String selllerId,
                                                @RequestBody Project projectObj) {
      return projectService.createProject(selllerId, projectObj);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 401, message = "Project doesn't exist or project doesn't seem to associate with the Seller"),
                    @ApiResponse(code = 404, message = "Project Updation failed"),
                    @ApiResponse(code = 201, message = "Project Updated Successful")
            }
    )
    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}/" + Project.REST_RESOURCE_NAME +"/{project_id}",
                    method = RequestMethod.POST)
    public ResponseEntity<String> editProject(@PathVariable("seller_id") String sellerId,
                                               @PathVariable("project_id") String projectId,
                                               @RequestBody Project projectObj){
        return projectService.editProject(sellerId, projectId, projectObj);
    }

}
