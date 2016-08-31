package com.countrogue.spring.business.asset.service;

import com.countrogue.spring.business.asset.domain.Asset;

import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
public interface AssetService {

    List<Asset> findAll();
    Asset find(String id);
    Asset save(Asset asset);
    void delete(String id);
}
