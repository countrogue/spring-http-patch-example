package com.countrogue.spring.business.asset.service;

import com.countrogue.spring.business.asset.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    @Autowired AssetRepository assetRepository;

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public Asset find(String id) {
        return assetRepository.findOne(id);
    }

    @Override
    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public void delete(String id) {
        assetRepository.delete(id);
    }
}
