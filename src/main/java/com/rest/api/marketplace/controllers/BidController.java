package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Bid;
import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/rest/resources")
public class BidController {

    @Autowired
    private BidService bidService;

    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}/" + Bid.REST_RESOURCE_NAME,
                    method = RequestMethod.GET)
    public ResponseEntity<String> createBid(@PathParam("buyer_id") String buyerId, @RequestBody Bid bidObj) {
        return bidService.createBid(buyerId, bidObj);
    }

    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "/{buyer_id}/" + Bid.REST_RESOURCE_NAME + "/{bid_id}",
            method = RequestMethod.GET)
    public ResponseEntity<Bid> getBid(@PathParam("buyer_id") String buyerId, @PathParam("bid_id") String bidId) {
        return bidService.getBid(buyerId, bidId);
    }

}
