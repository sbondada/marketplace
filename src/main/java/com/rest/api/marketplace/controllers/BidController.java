package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.services.BidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/resources")
@Api(value = "Bid Controller", description = "Lets you CREATE, GET and EDIT the Bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request, Incorrect Data"),
                    @ApiResponse(code = 401, message = "Bid closed on the Project or Project not active"),
                    @ApiResponse(code = 404, message = "Bid Creation Failed"),
                    @ApiResponse(code = 201, message = "Bid Successfully Created")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}/" + Bid.REST_RESOURCE_NAME,
                    method = RequestMethod.PUT)
    public ResponseEntity<String> createBid(@PathVariable("buyer_id") String buyerId, @RequestBody Bid bidObj) {
        return bidService.createBid(buyerId, bidObj);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "Bid Not Found"),
                    @ApiResponse(code = 200, message = "Bid Successfully Loaded")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}/" + Bid.REST_RESOURCE_NAME + "/{bid_id}",
            method = RequestMethod.GET)
    public ResponseEntity<Bid> getBid(@PathVariable("buyer_id") String buyerId, @PathVariable("bid_id") String bidId) {
        return bidService.getBid(buyerId, bidId);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Bad Request, Incorrect Data"),
                    @ApiResponse(code = 401, message = "Bid closed on the Project or Project not active or Bid doesn't seem to associate with the Buyer"),
                    @ApiResponse(code = 404, message = "Bid Updation Failed"),
                    @ApiResponse(code = 201, message = "Bid Updated Successful")
            }
    )
    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}/" + Bid.REST_RESOURCE_NAME + "/{bid_id}",
            method = RequestMethod.POST)
    public ResponseEntity<String> editBid(@PathVariable("buyer_id") String buyerId, @PathVariable("bid_id") String bidId,
                                         @RequestBody Bid bidObj) {
        return bidService.editBid(buyerId, bidId, bidObj);
    }
}
