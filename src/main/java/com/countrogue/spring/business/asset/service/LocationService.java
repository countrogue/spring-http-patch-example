package com.countrogue.spring.business.asset.service;

import com.countrogue.spring.business.asset.domain.Location;

import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
public interface LocationService {

    List<Location> findAll();
}
