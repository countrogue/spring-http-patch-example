package com.countrogue.spring.business.asset.domain;

import com.countrogue.spring.business.common.domain.BasicEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by socrates on 8/30/16.
 */
@Entity
@Data
public class Location extends BasicEntity {

    private String name;
}
