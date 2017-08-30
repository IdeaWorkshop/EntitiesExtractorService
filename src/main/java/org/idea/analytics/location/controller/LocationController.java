package org.idea.analytics.location.controller;

import org.idea.analytics.location.model.Address;
import org.idea.analytics.location.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class LocationController {
    public final static Logger logger = LoggerFactory.getLogger(LocationController.class);
    public final static String LOCATION = "/location/";

    @Autowired
    LocationService service;

    @RequestMapping(value = LOCATION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Address getAddress(WebRequest request) {
        String location = request.getParameter("q");
        if (location == null || location.trim().isEmpty()) {
            return null;
        }


        Address address = service.getAddress(location);
        return address;
    }
}
