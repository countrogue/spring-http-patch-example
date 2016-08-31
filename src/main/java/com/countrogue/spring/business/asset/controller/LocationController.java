package com.countrogue.spring.business.asset.controller;

import com.countrogue.spring.business.asset.domain.Location;
import com.countrogue.spring.business.asset.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    @Autowired LocationService locationService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Location> list() {

        return locationService.findAll();
    }
}
