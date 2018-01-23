package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Buyer;
import com.rest.api.marketplace.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/resources")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME , method = RequestMethod.POST)
    public ResponseEntity<String> createBuyer(@RequestBody Buyer buyerObj) {
        return buyerService.createBuyer(buyerObj);
    }

    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "{buyer_id}", method = RequestMethod.GET)
    public ResponseEntity<Buyer> getBuyer(@PathVariable("buyer_id") String id) {
        return buyerService.getBuyer(id);
    }


    @RequestMapping(value = "/" + Buyer.REST_RESOURCE_NAME + "{buyer_id}", method = RequestMethod.POST)
    public ResponseEntity<String> editBuyer(@PathVariable("buyer_id") String id, @RequestBody Buyer buyerObj) {
        return buyerService.editBuyer(id, buyerObj);
    }

}
