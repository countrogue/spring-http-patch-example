package com.countrogue.spring.business.asset.service;

import com.countrogue.spring.business.asset.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by socrates on 8/30/16.
 */
public interface LocationRepository extends JpaRepository<Location, String> {
}
