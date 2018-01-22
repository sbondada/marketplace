package com.rest.api.marketplace.controllers;

import com.rest.api.marketplace.models.Seller;
import com.rest.api.marketplace.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/rest/resources")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME , method = RequestMethod.POST)
    public ResponseEntity<String> createSeller(@RequestBody Seller sellerObj) {
        return sellerService.createSeller(sellerObj);
    }

    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "{seller_id}", method = RequestMethod.GET)
    public Seller getSeller(@PathParam("seller_id") String id) {
        return sellerService.getSeller(id);
    }


    @RequestMapping(value = "/" + Seller.REST_RESOURCE_NAME + "{seller_id}", method = RequestMethod.POST)
    public ResponseEntity<String> editSeller(@PathParam("seller_id") String id, @RequestBody Seller sellerObj) {
        return sellerService.editSeller(id, sellerObj);
    }
}