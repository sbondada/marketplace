package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.services.BuyerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/resources")
@Api(value = "Buyer Controller", description = "Lets you CREATE, GET and EDIT the Buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request, Incorrect Data"),
                    @ApiResponse(code = 201, message = "Buyer Successfully Created")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME , method = RequestMethod.POST)
    public ResponseEntity<String> createBuyer(@RequestBody Buyer buyerObj) {
        return buyerService.createBuyer(buyerObj);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Buyer Not Found"),
                    @ApiResponse(code = 201, message = "Buyer Successfully Loaded")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}", method = RequestMethod.GET)
    public ResponseEntity<Buyer> getBuyer(@PathVariable("buyer_id") String id) {
        return buyerService.getBuyer(id);
    }


    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Buyer Updation Failed"),
                    @ApiResponse(code = 200, message = "Buyer Successfully Updated")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editBuyer(@PathVariable("buyer_id") String id, @RequestBody Buyer buyerObj) {
        return buyerService.editBuyer(id, buyerObj);
    }

}
