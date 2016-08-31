package com.countrogue.spring.business.asset.service;

import com.countrogue.spring.business.asset.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
