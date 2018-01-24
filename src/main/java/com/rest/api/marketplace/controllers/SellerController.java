package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.services.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/resources")
@Api(value = "Seller Controller", description = "Lets you CREATE, GET and EDIT the Seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request, Incorrect Data"),
                    @ApiResponse(code = 201, message = "Seller Successfully Created")
            }
    )
    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME , method = RequestMethod.PUT)
    public ResponseEntity<String> createSeller(@RequestBody Seller sellerObj) {
        return sellerService.createSeller(sellerObj);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Seller Not Found"),
                    @ApiResponse(code = 201, message = "Seller Successfully Loaded")
            }
    )
    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}", method = RequestMethod.GET)
    public ResponseEntity<Seller> getSeller(@PathVariable("seller_id") String id) {
        return sellerService.getSeller(id);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Seller Updation Failed"),
                    @ApiResponse(code = 200, message = "Seller Successfully Updated")
            }
    )
    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "/{seller_id}", method = RequestMethod.POST)
    public ResponseEntity<String> editSeller(@PathVariable("seller_id") String id, @RequestBody Seller sellerObj) {
        return sellerService.editSeller(id, sellerObj);
    }
}
