package com.countrogue.spring.business.asset.controller;

import com.countrogue.spring.business.asset.domain.Asset;
import com.countrogue.spring.business.asset.service.AssetService;
import com.countrogue.spring.config.patch.json.Patch;
import com.countrogue.spring.config.patch.json.PatchRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by socrates on 8/30/16.
 */
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    @Autowired AssetService assetService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Asset> list() {

        return assetService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Asset create(@RequestBody Asset asset) {

        return assetService.save(asset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Asset update(@PathVariable String id,
                        @RequestBody Asset asset) {

        asset.setId(id);
        return assetService.save(asset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Patch(service = AssetService.class, id = String.class)
    public Asset patch(@PathVariable String id,
                       @PatchRequestBody Asset asset) {

        asset.setId(id);
        return assetService.save(asset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {

        assetService.delete(id);
    }
}
