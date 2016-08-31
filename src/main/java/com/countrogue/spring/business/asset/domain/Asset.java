package com.countrogue.spring.business.asset.domain;

import com.countrogue.spring.business.common.domain.BasicEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by socrates on 8/30/16.
 */
@Data
@Entity
public class Asset extends BasicEntity {

    private String name;
    private ZonedDateTime dateAcquired;
    private long count;

    @ManyToMany
    @JoinTable(name = "asset_location",
               joinColumns = @JoinColumn(name = "asset_id"),
               inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations;
}
